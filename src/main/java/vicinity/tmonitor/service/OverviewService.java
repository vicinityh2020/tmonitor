package vicinity.tmonitor.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.Queries;
import vicinity.tmonitor.TmonitorApplication;
import vicinity.tmonitor.model.Overview;
import vicinity.tmonitor.repository.OverviewRestRepository;

@Service
public class OverviewService {

	@Autowired
	private OverviewRestRepository repoOverview;

	public void updateData() {
		Overview overview = new Overview();

		
		overview.setThings(sendQueryCount(Queries.queryNumberOfThings));
		int numberOfServices  =sendQueryCount(Queries.queryNumberOfThingsService);
		overview.setThingsWithServices(numberOfServices);
		overview.setThingsWithAdapters(sendQueryCount(Queries.queryNumberOfThingsAdapters));
		overview.setThingsWithBuildings(sendQueryCount(Queries.queryNumberOfThingsWithBuildingsComplete));
		overview.setThingsWithBuildingsComplete(sendQueryCount(Queries.queryNumberOfThingsWithBuildingsComplete));
		overview.setThingsWithBuildingSpaces(sendQueryCount(Queries.queryNumberOfThingsWithBuildingSpaces));
		overview.setThingsWithCities(sendQueryCount(Queries.queryNumberOfThingsWithCities));	
		overview.setThingsWithCitiesComplete(sendQueryCount(Queries.queryNumberOfThingsWithCitiesCorrect));
		overview.setThingsWithCountries(sendQueryCount(Queries.queryNumberOfThingsWithCountry));
		overview.setThingsWithCountriesComplete(sendQueryCount(Queries.queryNumberOfThingsWithCountryCorrect));
		overview.setThingsWithInteractionPatters(sendQueryCount(Queries.queryNumberOfThingsWithIntPatters));
		overview.setThingsWithInteractionPattersAccesible(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersAccesible));
		overview.setThingsWithInteractionPattersAction(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersAction));
		overview.setThingsWithInteractionPattersEvent(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersEvent));
		overview.setThingsWithInteractionPattersObserving(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersObserving));
		overview.setThingsWithInteractionPattersProperty(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersProperty));
		overview.setThingsWithInteractionPattersPropertyAndMeasure(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersPropertyAndMeasure));
		overview.setThingsWithInteractionPattersReadable(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersReadable));
		overview.setThingsWithInteractionPattersWritable(sendQueryCount(Queries.queryNumberOfThingsWithIntPattersWritable));
		overview.setThingsWithMappings(sendQueryCount(Queries.queryNumberOfThingsWithMappings));
		overview.setThingsWithOrganizations(sendQueryCount(Queries.queryNumberOfThingsWithOrganizatios));
		overview.setThingsWithOrganizationsComplete(sendQueryCount(Queries.queryNumberOfThingsWithOrganizatiosComplete));
		
		
		
		repoOverview.save(overview);
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
	
	
}
