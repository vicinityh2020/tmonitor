package vicinity.tmonitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.Queries;
import vicinity.tmonitor.TmonitorApplication;
import vicinity.tmonitor.model.ThingsOwned;
import vicinity.tmonitor.repository.ThingsOwnedRepository;

@Service
public class ThingsOwnedService {

	@Autowired
	private ThingsOwnedRepository repository;
	
	public void udateData() {
		Date now = new Date();
	
		List<List<String>> rows = sendQueryList(Queries.queryOrganisationThingsTypeCount);
		
		for(List<String> row:rows) {
			ThingsOwned object = new ThingsOwned();
			object.setTimestamp(now);
			String organisationName = row.get(0);
			String type = row.get(1);
			String count = row.get(2);
			object.setName(organisationName);
			object.setTypeName(type);
			object.setNumber(Integer.valueOf(count));
			repository.save(object);
		}
	}
	
	private List<List<String>> sendQueryList(String query) {
		List<List<String>> results = new ArrayList<>();
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
						List<String> data = new ArrayList<>();
						String name = document.getJSONObject("ownerName").getString("value");
						String type = document.getJSONObject("type").getString("value");
						String count = document.getJSONObject("count").getString("value");
						data.add(name);
						data.add(type);
						data.add(count);
						results.add(data);
					}
					
				}
			}
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return results;
	}
}
