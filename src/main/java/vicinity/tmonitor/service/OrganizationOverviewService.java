package vicinity.tmonitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.Queries;
import vicinity.tmonitor.TmonitorApplication;
import vicinity.tmonitor.model.OrganizationOverview;
import vicinity.tmonitor.model.OrganizationThing;
import vicinity.tmonitor.repository.OrganizationOverviewRestRepository;
import vicinity.tmonitor.repository.OrganizationThingRestRepository;

@Service
public class OrganizationOverviewService {
	@Autowired
	private DashboardService dashboardService;
	
	private static final String GRAFANA_DASHBOARD_URL = "/api/dashboards/db";
	private static final String GRAFANA_DASHBOARD_CREATION_PAYLOAD = "{\n" + 
			"  \"dashboard\": {\n" + 
			"    \"id\": null,\n" + 
			"    \"uid\": null,\n" + 
			"    \"title\": \"Production Overview\",\n" + 
			"    \"tags\": [ \"templated\" ],\n" + 
			"    \"timezone\": \"browser\",\n" + 
			"    \"schemaVersion\": 16,\n" + 
			"    \"version\": 0\n" + 
			"  },\n" + 
			"  \"folderId\": 0,\n" + 
			"  \"overwrite\": false\n" + 
			"}";
	
	
	@Autowired
	private OrganizationOverviewRestRepository repoOrganizationOverview;
	@Autowired
	private OrganizationThingRestRepository repoOrganizationThing;
	
	public void updateData() {
		Date now = new Date();
		
		sendQueryOrganisation(Queries.queryOrganizationThings, now);
		Set<String> organisations = sendQueryOrganisationList(Queries.queryOrganizationList);
		System.out.println("ORGANISATIONS NUMBER: "+organisations.size());
		for(String organisation:organisations) {
			String organisationName = sendQueryOrganisationName(Queries.queryOrganizationName, organisation);
			if(organisationName!=null) {
				dashboardService.createDashboard(organisationName);
				OrganizationOverview organisationOverview = new OrganizationOverview();
				organisationOverview.setTimestamp(now);
				organisationOverview.setName(organisationName);
				
				organisationOverview.setThings(sendQueryCount(Queries.queryNumberOfThings.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
			
				organisationOverview.setThingsAdapters(sendQueryCount(Queries.queryNumberOfThingsAdapters.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsServices(sendQueryCount(Queries.queryNumberOfThingsService.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsInteroperable(sendQueryCount(Queries.queryNumberOfThingsWithMappings.replace("}", "?thing <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				
				organisationOverview.setThingsWithBuilding(sendQueryCount(Queries.queryNumberOfThingsWithBuildings.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithBuildingComplete(sendQueryCount(Queries.queryNumberOfThingsWithBuildingsComplete.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithCity(sendQueryCount(Queries.queryNumberOfThingsWithCities.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithCityComplete(sendQueryCount(Queries.queryNumberOfThingsWithCitiesCorrect.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithCountry(sendQueryCount(Queries.queryNumberOfThingsWithCountry.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithCountryComplete(sendQueryCount(Queries.queryNumberOfThingsWithCountryCorrect.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				organisationOverview.setThingsWithRoom(sendQueryCount(Queries.queryNumberOfThingsWithBuildingSpaces.replace("}", "?subject <http://iot.linkeddata.es/def/core#hasOwner> ?organisation . \n ?organisation <http://xmlns.com/foaf/0.1/name> \""+organisationName+"\" . \n}")));
				
				organisationOverview.setNumberOfContractsInvolved(sendQueryCount(Queries.queryOrganisationContractsPetitioner.replace("#Petitioner#",organisationName)));
				organisationOverview.setNumberOfContractsOwner(sendQueryCount(Queries.queryOrganisationContractsOwner.replace("#Petitioner#",organisationName)));
				
				repoOrganizationOverview.save(organisationOverview);
				
			}else {
				System.out.println("ERRORR!!!!!");
			}
		}
		
	}

	private Integer sendQueryCount(String query) {
		Integer count = 0;
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/sparql-query");
		headers.put("Accept", "application/json");
	
		try {
			JSONObject queryResultsJson = Unirest.post(TmonitorApplication.ENDPOINT).headers(headers).body(query).asJson().getBody().getObject();
			if(queryResultsJson.has("results")) {
				JSONObject resultsJson = queryResultsJson.getJSONObject("results");
				if(resultsJson.has("bindings")) {
					JSONArray bindingsJson = resultsJson.getJSONArray("bindings");
					count = bindingsJson.getJSONObject(0).getJSONObject("count").getInt("value");
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return count;
	}

	private void sendQueryOrganisation(String query, Date now) {
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/sparql-query");
		headers.put("Accept", "application/json");
		try {
			JSONObject queryResultsJson = Unirest.post(TmonitorApplication.ENDPOINT).headers(headers).body(query).asJson().getBody().getObject();
			if(queryResultsJson.has("results")) {
				JSONObject resultsJson = queryResultsJson.getJSONObject("results");
				if(resultsJson.has("bindings")) {
					JSONArray bindingsJson = resultsJson.getJSONArray("bindings");
					for(int index = 0; index<bindingsJson.length(); index++) {
						JSONObject document =  bindingsJson.getJSONObject(index);
						String organisationName = document.getJSONObject("organisationName").getString("value");
						String organisationID = "";
						if(document.has("organisationID"))
							organisationID = document.getJSONObject("organisationID").getString("value");
						String type = null;
						if(document.has("type"))
							type = Queries.cleanPrefixes(document.getJSONObject("type").getString("value"));
						OrganizationThing orgThing = new OrganizationThing();
						orgThing.setTimestamp(now);
						orgThing.setName(organisationName);
						orgThing.setOrganisationId(organisationID);
						
						orgThing.setType(type);
						
						repoOrganizationThing.save(orgThing);
					}
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
	
	}
	
	private Set<String> sendQueryOrganisationList(String query) {
		Set<String> organisations = new HashSet<>();
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/sparql-query");
		headers.put("Accept", "application/json");
		try {
			JSONObject queryResultsJson = Unirest.post(TmonitorApplication.ENDPOINT).headers(headers).body(query).asJson().getBody().getObject();
			if(queryResultsJson.has("results")) {
				JSONObject resultsJson = queryResultsJson.getJSONObject("results");
				if(resultsJson.has("bindings")) {
					JSONArray bindingsJson = resultsJson.getJSONArray("bindings");
					for(int index = 0; index<bindingsJson.length(); index++) {
						JSONObject document =  bindingsJson.getJSONObject(index);
						organisations.add(document.getJSONObject("organiastions").getString("value"));
					}
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return organisations;
	}
	
	private String sendQueryOrganisationName(String query, String organisationID) {
		String name = null;
		query = query.replace("}", "FILTER ( ?organiastions=<"+organisationID+"> ).\n}");
		
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/sparql-query");
		headers.put("Accept", "application/json");
		try {
			JSONObject queryResultsJson = Unirest.post(TmonitorApplication.ENDPOINT).headers(headers).body(query).asJson().getBody().getObject();
			
			if(queryResultsJson.has("results")) {
				JSONObject resultsJson = queryResultsJson.getJSONObject("results");
				if(resultsJson.has("bindings")) {
					JSONArray bindingsJson = resultsJson.getJSONArray("bindings");
					for(int index = 0; index<bindingsJson.length(); index++) {
						JSONObject document =  bindingsJson.getJSONObject(index);
						name = document.getJSONObject("name").getString("value");
					}
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
}
