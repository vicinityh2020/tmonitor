package vicinity.tmonitor.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.Queries;
import vicinity.tmonitor.TmonitorApplication;


@Service
public class DashboardService {

	

	public void createDashboard(String organizationName) {
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TmonitorApplication.API_KEY);
		headers.put("Content-Type", "application/json");
		try {
			String instance = TEMPLATE.replaceAll("#OrganizationName#", organizationName);
			//System.out.println(instance);
			
			String response = Unirest.post(TmonitorApplication.GRAFANA_ENDPOINT+"/api/dashboards/db").headers(headers).body(instance).asString().getBody();
			//System.out.println(response);
			//System.exit(-1);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	
	}
	
	public void createVICINITYGeneralDashboard() {
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TmonitorApplication.API_KEY);
		headers.put("Content-Type", "application/json");
		try {
			String instance = Queries.ALT_TEMPLATE;
			String response = Unirest.post(TmonitorApplication.GRAFANA_ENDPOINT+"/api/dashboards/db").headers(headers).body(instance).asString().getBody();
			//System.out.println(response);
		} catch (UnirestException e) {
		
			e.printStackTrace();
		}
	
	}

	
	
	private static String TEMPLATE = "{\n" + 
			"  \"dashboard\": {\n" + 
			"    \"id\": null,\n" + 
			"    \"uid\": null,\n" + 
			"    \"title\": \"#OrganizationName#\",\n" + 
			"    \"tags\": [ \"templated\" ],\n" + 
			"    \"timezone\": \"browser\",\n" + 
			"    \"schemaVersion\": 16,\n" + 
			"    \"version\": 0,\n" + 
			"	\"panels\": ["+
			//panels 
			// -- panels
						"    {\n" + 
						"      \"aliasColors\": {},\n" + 
						"      \"bars\": true,\n" + 
						"      \"cacheTimeout\": null,\n" + 
						"      \"dashLength\": 10,\n" + 
						"      \"dashes\": false,\n" + 
						"      \"decimals\": 0,\n" + 
						"      \"fill\": 1,\n" + 
						"      \"gridPos\": {\n" + 
						"        \"h\": 11,\n" + 
						"        \"w\": 14,\n" + 
						"        \"x\": 0,\n" + 
						"        \"y\": 0\n" + 
						"      },\n" + 
						"      \"id\": 4,\n" + 
						"      \"legend\": {\n" + 
						"        \"alignAsTable\": true,\n" + 
						"        \"avg\": false,\n" + 
						"        \"current\": true,\n" + 
						"        \"max\": false,\n" + 
						"        \"min\": false,\n" + 
						"        \"show\": true,\n" + 
						"        \"total\": false,\n" + 
						"        \"values\": true\n" + 
						"      },\n" + 
						"      \"lines\": false,\n" + 
						"      \"linewidth\": 1,\n" + 
						"      \"links\": [],\n" + 
						"      \"nullPointMode\": \"null as zero\",\n" + 
						"      \"paceLength\": 10,\n" + 
						"      \"percentage\": false,\n" + 
						"      \"pointradius\": 2,\n" + 
						"      \"points\": false,\n" + 
						"      \"renderer\": \"flot\",\n" + 
						"      \"seriesOverrides\": [],\n" + 
						"      \"stack\": false,\n" + 
						"      \"steppedLine\": false,\n" + 
						"      \"targets\": [\n" + 
						"        {\n" + 
						"          \"format\": \"time_series\",\n" + 
						"          \"group\": [],\n" + 
						"          \"hide\": false,\n" + 
						"          \"metricColumn\": \"none\",\n" + 
						"          \"rawQuery\": true,\n" + 
						"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things,\\n  things_adapters,\\n  things_services,\\n  things_interoperable\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
						"          \"refId\": \"A\",\n" + 
						"          \"select\": [\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_adapters\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_services\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_interoperable\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ]\n" + 
						"          ],\n" + 
						"          \"table\": \"organization_overview\",\n" + 
						"          \"timeColumn\": \"timestamp\",\n" + 
						"          \"timeColumnType\": \"datetime\",\n" + 
						"          \"where\": [\n" + 
						"            {\n" + 
						"              \"name\": \"$__timeFilter\",\n" + 
						"              \"params\": [],\n" + 
						"              \"type\": \"macro\"\n" + 
						"            }\n" + 
						"          ]\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"thresholds\": [],\n" + 
						"      \"timeFrom\": null,\n" + 
						"      \"timeRegions\": [],\n" + 
						"      \"timeShift\": null,\n" + 
						"      \"title\": \"Overview of the things of this Organisation\",\n" + 
						"      \"tooltip\": {\n" + 
						"        \"shared\": false,\n" + 
						"        \"sort\": 0,\n" + 
						"        \"value_type\": \"individual\"\n" + 
						"      },\n" + 
						"      \"type\": \"graph\",\n" + 
						"      \"xaxis\": {\n" + 
						"        \"buckets\": null,\n" + 
						"        \"mode\": \"series\",\n" + 
						"        \"name\": null,\n" + 
						"        \"show\": true,\n" + 
						"        \"values\": [\n" + 
						"          \"current\"\n" + 
						"        ]\n" + 
						"      },\n" + 
						"      \"yaxes\": [\n" + 
						"        {\n" + 
						"          \"decimals\": null,\n" + 
						"          \"format\": \"none\",\n" + 
						"          \"label\": null,\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": null,\n" + 
						"          \"show\": true\n" + 
						"        },\n" + 
						"        {\n" + 
						"          \"decimals\": null,\n" + 
						"          \"format\": \"none\",\n" + 
						"          \"label\": \"\",\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": \"0\",\n" + 
						"          \"show\": false\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"yaxis\": {\n" + 
						"        \"align\": false,\n" + 
						"        \"alignLevel\": null\n" + 
						"      }\n" + 
						"    },\n" + 
						"    {\n" + 
						"      \"columns\": [],\n" + 
						"      \"fontSize\": \"100%\",\n" + 
						"      \"gridPos\": {\n" + 
						"        \"h\": 11,\n" + 
						"        \"w\": 10,\n" + 
						"        \"x\": 14,\n" + 
						"        \"y\": 0\n" + 
						"      },\n" + 
						"      \"id\": 16,\n" + 
						"      \"pageSize\": null,\n" + 
						"      \"scroll\": true,\n" + 
						"      \"showHeader\": true,\n" + 
						"      \"sort\": {\n" + 
						"        \"col\": 0,\n" + 
						"        \"desc\": true\n" + 
						"      },\n" + 
						"      \"styles\": [\n" + 
						"        {\n" + 
						"          \"alias\": \"Time\",\n" + 
						"          \"dateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
						"          \"pattern\": \"Time\",\n" + 
						"          \"type\": \"date\"\n" + 
						"        },\n" + 
						"        {\n" + 
						"          \"alias\": \"\",\n" + 
						"          \"colorMode\": null,\n" + 
						"          \"colors\": [\n" + 
						"            \"rgba(245, 54, 54, 0.9)\",\n" + 
						"            \"rgba(237, 129, 40, 0.89)\",\n" + 
						"            \"rgba(50, 172, 45, 0.97)\"\n" + 
						"          ],\n" + 
						"          \"decimals\": 2,\n" + 
						"          \"pattern\": \"/.*/\",\n" + 
						"          \"thresholds\": [],\n" + 
						"          \"type\": \"number\",\n" + 
						"          \"unit\": \"short\"\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"targets\": [\n" + 
						"        {\n" + 
						"          \"format\": \"table\",\n" + 
						"          \"group\": [],\n" + 
						"          \"metricColumn\": \"none\",\n" + 
						"          \"rawQuery\": true,\n" + 
						"          \"rawSql\": \"SELECT\\n  type_name,\\n  number\\nFROM things_owned\\nWHERE\\n  $__timeFilter(timestamp) && name = \\\"#OrganizationName#\\\"\\nORDER BY timestamp\",\n" + 
						"          \"refId\": \"A\",\n" + 
						"          \"select\": [\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"type_name\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"number\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ]\n" + 
						"          ],\n" + 
						"          \"table\": \"things_owned\",\n" + 
						"          \"timeColumn\": \"timestamp\",\n" + 
						"          \"timeColumnType\": \"datetime\",\n" + 
						"          \"where\": [\n" + 
						"            {\n" + 
						"              \"name\": \"$__timeFilter\",\n" + 
						"              \"params\": [],\n" + 
						"              \"type\": \"macro\"\n" + 
						"            }\n" + 
						"          ]\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"timeFrom\": null,\n" + 
						"      \"timeShift\": null,\n" + 
						"      \"title\": \"Panel Title\",\n" + 
						"      \"transform\": \"table\",\n" + 
						"      \"type\": \"table\"\n" + 
						"    },\n" + 
						"    {\n" + 
						"      \"aliasColors\": {},\n" + 
						"      \"bars\": true,\n" + 
						"      \"dashLength\": 10,\n" + 
						"      \"dashes\": false,\n" + 
						"      \"decimals\": 0,\n" + 
						"      \"fill\": 1,\n" + 
						"      \"gridPos\": {\n" + 
						"        \"h\": 11,\n" + 
						"        \"w\": 8,\n" + 
						"        \"x\": 0,\n" + 
						"        \"y\": 11\n" + 
						"      },\n" + 
						"      \"id\": 10,\n" + 
						"      \"legend\": {\n" + 
						"        \"alignAsTable\": true,\n" + 
						"        \"avg\": false,\n" + 
						"        \"current\": true,\n" + 
						"        \"max\": false,\n" + 
						"        \"min\": false,\n" + 
						"        \"show\": true,\n" + 
						"        \"total\": false,\n" + 
						"        \"values\": true\n" + 
						"      },\n" + 
						"      \"lines\": false,\n" + 
						"      \"linewidth\": 1,\n" + 
						"      \"links\": [],\n" + 
						"      \"nullPointMode\": \"null as zero\",\n" + 
						"      \"paceLength\": 10,\n" + 
						"      \"percentage\": false,\n" + 
						"      \"pointradius\": 2,\n" + 
						"      \"points\": false,\n" + 
						"      \"renderer\": \"flot\",\n" + 
						"      \"seriesOverrides\": [],\n" + 
						"      \"stack\": false,\n" + 
						"      \"steppedLine\": false,\n" + 
						"      \"targets\": [\n" + 
						"        {\n" + 
						"          \"format\": \"time_series\",\n" + 
						"          \"group\": [],\n" + 
						"          \"metricColumn\": \"none\",\n" + 
						"          \"rawQuery\": true,\n" + 
						"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number_of_contracts_involved AS \\\"Contracts exposing data\\\",\\n  number_of_contracts_owner AS \\\"Contracts requesting data\\\"\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
						"          \"refId\": \"A\",\n" + 
						"          \"select\": [\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"id\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ]\n" + 
						"          ],\n" + 
						"          \"table\": \"observation_type\",\n" + 
						"          \"timeColumn\": \"timestamp\",\n" + 
						"          \"timeColumnType\": \"timestamp\",\n" + 
						"          \"where\": [\n" + 
						"            {\n" + 
						"              \"name\": \"$__timeFilter\",\n" + 
						"              \"params\": [],\n" + 
						"              \"type\": \"macro\"\n" + 
						"            }\n" + 
						"          ]\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"thresholds\": [],\n" + 
						"      \"timeFrom\": null,\n" + 
						"      \"timeRegions\": [],\n" + 
						"      \"timeShift\": null,\n" + 
						"      \"title\": \"Contracts held by this organisation\",\n" + 
						"      \"tooltip\": {\n" + 
						"        \"shared\": false,\n" + 
						"        \"sort\": 0,\n" + 
						"        \"value_type\": \"individual\"\n" + 
						"      },\n" + 
						"      \"type\": \"graph\",\n" + 
						"      \"xaxis\": {\n" + 
						"        \"buckets\": null,\n" + 
						"        \"mode\": \"series\",\n" + 
						"        \"name\": null,\n" + 
						"        \"show\": true,\n" + 
						"        \"values\": [\n" + 
						"          \"current\"\n" + 
						"        ]\n" + 
						"      },\n" + 
						"      \"yaxes\": [\n" + 
						"        {\n" + 
						"          \"decimals\": 0,\n" + 
						"          \"format\": \"short\",\n" + 
						"          \"label\": null,\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": null,\n" + 
						"          \"show\": true\n" + 
						"        },\n" + 
						"        {\n" + 
						"          \"format\": \"short\",\n" + 
						"          \"label\": null,\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": null,\n" + 
						"          \"show\": true\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"yaxis\": {\n" + 
						"        \"align\": false,\n" + 
						"        \"alignLevel\": null\n" + 
						"      }\n" + 
						"    },\n" + 
						"    {\n" + 
						"      \"aliasColors\": {},\n" + 
						"      \"bars\": true,\n" + 
						"      \"cacheTimeout\": null,\n" + 
						"      \"dashLength\": 10,\n" + 
						"      \"dashes\": false,\n" + 
						"      \"decimals\": 0,\n" + 
						"      \"fill\": 1,\n" + 
						"      \"gridPos\": {\n" + 
						"        \"h\": 11,\n" + 
						"        \"w\": 16,\n" + 
						"        \"x\": 8,\n" + 
						"        \"y\": 11\n" + 
						"      },\n" + 
						"      \"id\": 6,\n" + 
						"      \"legend\": {\n" + 
						"        \"alignAsTable\": true,\n" + 
						"        \"avg\": false,\n" + 
						"        \"current\": true,\n" + 
						"        \"max\": false,\n" + 
						"        \"min\": false,\n" + 
						"        \"show\": true,\n" + 
						"        \"total\": false,\n" + 
						"        \"values\": true\n" + 
						"      },\n" + 
						"      \"lines\": false,\n" + 
						"      \"linewidth\": 1,\n" + 
						"      \"links\": [],\n" + 
						"      \"nullPointMode\": \"null as zero\",\n" + 
						"      \"paceLength\": 10,\n" + 
						"      \"percentage\": false,\n" + 
						"      \"pointradius\": 2,\n" + 
						"      \"points\": false,\n" + 
						"      \"renderer\": \"flot\",\n" + 
						"      \"seriesOverrides\": [],\n" + 
						"      \"stack\": false,\n" + 
						"      \"steppedLine\": false,\n" + 
						"      \"targets\": [\n" + 
						"        {\n" + 
						"          \"format\": \"time_series\",\n" + 
						"          \"group\": [],\n" + 
						"          \"hide\": false,\n" + 
						"          \"metricColumn\": \"none\",\n" + 
						"          \"rawQuery\": true,\n" + 
						"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things as \\\"Things of this Organisation\\\",\\n  things_with_building as \\\"Building\\\",\\n  things_with_building_complete as \\\"Building (complete)\\\",\\n  things_with_room as \\\"Building spaces\\\",\\n  things_with_city as \\\"City\\\",\\n  things_with_city_complete as \\\"City (complete)\\\",\\n  things_with_country as \\\"Country\\\",\\n  things_with_country_complete as \\\"Country (complete)\\\"\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
						"          \"refId\": \"A\",\n" + 
						"          \"select\": [\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_building\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_building_complete\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_room\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_city\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_city_complete\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_country\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ],\n" + 
						"            [\n" + 
						"              {\n" + 
						"                \"params\": [\n" + 
						"                  \"things_with_country_complete\"\n" + 
						"                ],\n" + 
						"                \"type\": \"column\"\n" + 
						"              }\n" + 
						"            ]\n" + 
						"          ],\n" + 
						"          \"table\": \"organization_overview\",\n" + 
						"          \"timeColumn\": \"timestamp\",\n" + 
						"          \"timeColumnType\": \"datetime\",\n" + 
						"          \"where\": [\n" + 
						"            {\n" + 
						"              \"name\": \"$__timeFilter\",\n" + 
						"              \"params\": [],\n" + 
						"              \"type\": \"macro\"\n" + 
						"            }\n" + 
						"          ]\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"thresholds\": [],\n" + 
						"      \"timeFrom\": null,\n" + 
						"      \"timeRegions\": [],\n" + 
						"      \"timeShift\": null,\n" + 
						"      \"title\": \"Things in this organisation with contextual information\",\n" + 
						"      \"tooltip\": {\n" + 
						"        \"shared\": false,\n" + 
						"        \"sort\": 0,\n" + 
						"        \"value_type\": \"individual\"\n" + 
						"      },\n" + 
						"      \"type\": \"graph\",\n" + 
						"      \"xaxis\": {\n" + 
						"        \"buckets\": null,\n" + 
						"        \"mode\": \"series\",\n" + 
						"        \"name\": null,\n" + 
						"        \"show\": true,\n" + 
						"        \"values\": [\n" + 
						"          \"current\"\n" + 
						"        ]\n" + 
						"      },\n" + 
						"      \"yaxes\": [\n" + 
						"        {\n" + 
						"          \"decimals\": 0,\n" + 
						"          \"format\": \"none\",\n" + 
						"          \"label\": null,\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": null,\n" + 
						"          \"show\": true\n" + 
						"        },\n" + 
						"        {\n" + 
						"          \"decimals\": null,\n" + 
						"          \"format\": \"none\",\n" + 
						"          \"label\": \"\",\n" + 
						"          \"logBase\": 1,\n" + 
						"          \"max\": null,\n" + 
						"          \"min\": \"0\",\n" + 
						"          \"show\": false\n" + 
						"        }\n" + 
						"      ],\n" + 
						"      \"yaxis\": {\n" + 
						"        \"align\": false,\n" + 
						"        \"alignLevel\": null\n" + 
						"      }\n" + 
						"    }\n" + 
						// --
						
			//--- last pannel
			"	]"+
			"  },\n" + 
			"  \"folderId\": 0,\n" + 
			"  \"overwrite\": true\n" + 
			"}";
	

			
}
