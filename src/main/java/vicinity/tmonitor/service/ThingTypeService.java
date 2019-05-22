package vicinity.tmonitor.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.Queries;
import vicinity.tmonitor.TmonitorApplication;
import vicinity.tmonitor.model.ThingType;
import vicinity.tmonitor.repository.ThingTypeRepository;

@Service
public class ThingTypeService {

	
	@Autowired
	private ThingTypeRepository repository;

	
	
	public void updateData() {
		
		
		Map<String,Integer> countingOfThings = sendQueryMap(Queries.queryNumberOfThingsPerTypeAdapter);
		int numberOfServices  = sendQueryCount(Queries.queryNumberOfThingsService);
		
		ThingType typeService = new ThingType();
		typeService.setTypeName("Service");
		typeService.setNumber(numberOfServices);
		repository.save(typeService);
		
		for(Entry<String, Integer> types:countingOfThings.entrySet()) {
			ThingType type = new ThingType();
			type.setTypeName(Queries.cleanPrefixes(types.getKey()));
			type.setNumber(types.getValue());
			repository.save(type);
		}
		
	}
	
	
	
	private Map<String,Integer> sendQueryMap(String query) {
		Map<String,Integer> results = new HashMap<>();
		Map<String,String> headers = new HashMap<>();
		headers.put("Content-Type", "application/sparql-query");
		headers.put("Accept", "application/json");
		try {
			JSONObject queryResultsJson = Unirest.post(TmonitorApplication.ENDPOINT).headers(headers).body(query).asJson().getBody().getObject();
			if(queryResultsJson.has("results")) {
				JSONObject resultsJson = queryResultsJson.getJSONObject("results");
				if(resultsJson.has("bindings")) {
					JSONArray bindingsJson = resultsJson.getJSONArray("bindings");
					for(int index=0; index < bindingsJson.length(); index++) {
						JSONObject document = bindingsJson.getJSONObject(index);
						String observed = document.getJSONObject("type").getString("value");
						Integer count = document.getJSONObject("count").getInt("value");
						results.put(observed, count);
					}
					
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return results;
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
