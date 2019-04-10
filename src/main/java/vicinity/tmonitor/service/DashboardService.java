package vicinity.tmonitor.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import vicinity.tmonitor.TmonitorApplication;


@Service
public class DashboardService {

	

	public void createDashboard(String organizationName) {
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TmonitorApplication.API_KEY);
		headers.put("Content-Type", "application/json");
		try {
			String instance = TEMPLATE.replaceAll("#OrganizationName#", organizationName);
			
			String response = Unirest.post(TmonitorApplication.GRAFANA_ENDPOINT+"/api/dashboards/db").headers(headers).body(instance).asString().getBody();
			//System.out.println(response);
		} catch (UnirestException e) {
		
			e.printStackTrace();
		}
	
	}
	
	public void createVICINITYGeneralDashboard() {
		Map<String,String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+TmonitorApplication.API_KEY);
		headers.put("Content-Type", "application/json");
		try {
			String instance = ALT_TEMPLATE;
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
			// -- 
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"decimals\": 0,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 11,\n" + 
			"    \"w\": 10,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 0\n" + 
			"  },\n" + 
			"  \"id\": 4,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things,\\n  things_adapters,\\n  things_services,\\n  things_interoperable\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_adapters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_services\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_interoperable\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"organization_overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Overview of the things of this Organisation\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			// --
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"decimals\": 0,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 11,\n" + 
			"    \"w\": 14,\n" + 
			"    \"x\": 10,\n" + 
			"    \"y\": 0\n" + 
			"  },\n" + 
			"  \"id\": 2,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"params\": [\n" + 
			"            \"name\"\n" + 
			"          ],\n" + 
			"          \"type\": \"column\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  type AS \\\"Type\\\",\\n  COUNT(type) AS \\\"Number of things\\\"\\nFROM organization_thing\\nWHERE\\n name=\\\"#OrganizationName#\\\" AND $__timeFilter(timestamp)\\nGROUP BY timestamp, type\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"name\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"name\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"organization_thing\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Type of things owned by this organisation\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": 0,\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			// --
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"decimals\": 0,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 11,\n" + 
			"    \"w\": 8,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 11\n" + 
			"  },\n" + 
			"  \"id\": 10,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [],\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number_of_contracts_involved AS \\\"Contracts exposing data\\\",\\n  number_of_contracts_owner AS \\\"Contracts requesting data\\\"\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"id\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"observation_type\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"timestamp\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Contracts held by this organisation\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": 0,\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"decimals\": 0,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 11,\n" + 
			"    \"w\": 16,\n" + 
			"    \"x\": 8,\n" + 
			"    \"y\": 11\n" + 
			"  },\n" + 
			"  \"id\": 6,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things as \\\"Things of this Organisation\\\",\\n  things_with_building as \\\"Building\\\",\\n  things_with_building_complete as \\\"Building (complete)\\\",\\n  things_with_room as \\\"Building spaces\\\",\\n  things_with_city as \\\"City\\\",\\n  things_with_city_complete as \\\"City (complete)\\\",\\n  things_with_country as \\\"Country\\\",\\n  things_with_country_complete as \\\"Country (complete)\\\"\\nFROM organization_overview\\nWHERE name=\\\"#OrganizationName#\\\" AND $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_building\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_building_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_room\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_city\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_city_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_country\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_country_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"organization_overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Things in this organisation with contextual information\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": 0,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"}"+
			// --- last pannel
			"	]"+
			"  },\n" + 
			"  \"folderId\": 0,\n" + 
			"  \"overwrite\": true\n" + 
			"}";
	
	private final static String MAIN_DASHBOARD_TEMPLATE = "{\n" + 
			"  \"annotations\": {\n" + 
			"    \"list\": [\n" + 
			"      {\n" + 
			"        \"builtIn\": 1,\n" + 
			"        \"datasource\": \"-- Grafana --\",\n" + 
			"        \"enable\": true,\n" + 
			"        \"hide\": true,\n" + 
			"        \"iconColor\": \"rgba(0, 211, 255, 1)\",\n" + 
			"        \"name\": \"Annotations & Alerts\",\n" + 
			"        \"type\": \"dashboard\"\n" + 
			"      }\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"editable\": true,\n" + 
			"  \"gnetId\": null,\n" + 
			"  \"graphTooltip\": 0,\n" + 
			"  \"id\": null,\n" + 
			"  \"links\": [],\n" + 
			"  \"panels\": [\n" + 
			"    {\n" + 
			"      \"aliasColors\": {},\n" + 
			"      \"bars\": false,\n" + 
			"      \"dashLength\": 10,\n" + 
			"      \"dashes\": false,\n" + 
			"      \"fill\": 1,\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 5,\n" + 
			"        \"w\": 23,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 0\n" + 
			"      },\n" + 
			"      \"id\": 8,\n" + 
			"      \"legend\": {\n" + 
			"        \"avg\": false,\n" + 
			"        \"current\": false,\n" + 
			"        \"max\": false,\n" + 
			"        \"min\": false,\n" + 
			"        \"show\": true,\n" + 
			"        \"total\": false,\n" + 
			"        \"values\": false\n" + 
			"      },\n" + 
			"      \"lines\": true,\n" + 
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
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things AS \\\"Things\\\",\\n  things_with_adapters AS \\\"Adapters\\\",\\n  things_with_services AS \\\"Services\\\",\\n  things_with_mappings AS \\\"Interoperable Things\\\",\\n  things_with_organizations AS \\\"Organizations\\\",\\n  things_with_countries AS \\\"Countries\\\",\\n  things_with_cities AS \\\"Cities\\\",\\n  things_with_buildings AS \\\"Buildings\\\"\\nFROM overview\\nORDER BY timestamp\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_mappings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Interoperable Things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_organizations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Organizations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_countries\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Countries\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_cities\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Cities\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_buildings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Buildings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"overview\",\n" + 
			"          \"timeColumn\": \"timestamp\",\n" + 
			"          \"timeColumnType\": \"timestamp\",\n" + 
			"          \"where\": []\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"thresholds\": [],\n" + 
			"      \"timeFrom\": null,\n" + 
			"      \"timeRegions\": [],\n" + 
			"      \"timeShift\": null,\n" + 
			"      \"title\": \"Number of things in VICINITY evolution\",\n" + 
			"      \"tooltip\": {\n" + 
			"        \"shared\": true,\n" + 
			"        \"sort\": 0,\n" + 
			"        \"value_type\": \"individual\"\n" + 
			"      },\n" + 
			"      \"type\": \"graph\",\n" + 
			"      \"xaxis\": {\n" + 
			"        \"buckets\": null,\n" + 
			"        \"mode\": \"time\",\n" + 
			"        \"name\": null,\n" + 
			"        \"show\": true,\n" + 
			"        \"values\": []\n" + 
			"      },\n" + 
			"      \"yaxes\": [\n" + 
			"        {\n" + 
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
			"          \"show\": false\n" + 
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
			"      \"fill\": 1,\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 17,\n" + 
			"        \"w\": 12,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 5\n" + 
			"      },\n" + 
			"      \"id\": 2,\n" + 
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
			"          \"group\": [\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"$__interval\",\n" + 
			"                \"none\"\n" + 
			"              ],\n" + 
			"              \"type\": \"time\"\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"hide\": false,\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": true,\n" + 
			"          \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  things AS \\\"Total number of Things\\\",\\n  things_with_adapters AS \\\"Adapters\\\",\\n  things_with_services AS \\\"Services\\\",\\n  (things - (things_with_adapters+things_with_services)) AS \\\"Generic\\\",\\n  things_with_mappings AS \\\"Interoperable Things\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Total number of Things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_mappings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Interoperable Things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"overview\",\n" + 
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
			"      \"title\": \"Things in VICINITY\",\n" + 
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
			"          \"format\": \"short\",\n" + 
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
			"      \"Aggregate\": \"last\",\n" + 
			"      \"BarPadding\": 10,\n" + 
			"      \"BaseLineColor\": \"#5794F2\",\n" + 
			"      \"BaseLineValue\": null,\n" + 
			"      \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"DateTimeColName\": \"date\",\n" + 
			"      \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"      \"FlashHighLimitBar\": false,\n" + 
			"      \"FlashLowLimitBar\": false,\n" + 
			"      \"GroupColName\": \"\",\n" + 
			"      \"GroupCols\": 0,\n" + 
			"      \"GroupGap\": 5,\n" + 
			"      \"GroupLabelColor\": \"#ffffff\",\n" + 
			"      \"GroupLabelFontSize\": \"200%\",\n" + 
			"      \"GroupNameFilter\": \"\",\n" + 
			"      \"GroupSortString\": \"\",\n" + 
			"      \"HighAxisColor\": \"#ffffff\",\n" + 
			"      \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"      \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"      \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"      \"HighLimitValue\": null,\n" + 
			"      \"HighSideMargin\": 22,\n" + 
			"      \"Horizontal\": true,\n" + 
			"      \"LabelColName\": \"Name\",\n" + 
			"      \"LabelColor\": \"#ffffff\",\n" + 
			"      \"LabelFontSize\": \"100%\",\n" + 
			"      \"LabelMargin\": null,\n" + 
			"      \"LabelNameFilter\": \"\",\n" + 
			"      \"LableAngle\": 0,\n" + 
			"      \"LowAxisColor\": \"#ffffff\",\n" + 
			"      \"LowBarColor\": \"teal\",\n" + 
			"      \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"      \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"      \"LowLimitBarFlashTimeout\": 200,\n" + 
			"      \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"      \"LowLimitValue\": null,\n" + 
			"      \"LowSideMargin\": 22,\n" + 
			"      \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"      \"MaxLineValue\": null,\n" + 
			"      \"MinLineValue\": 0,\n" + 
			"      \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"      \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"      \"RecolorHighLimitBar\": true,\n" + 
			"      \"RecolorLowLimitBar\": false,\n" + 
			"      \"ScaleFactor\": 1,\n" + 
			"      \"ShowBaseLine\": true,\n" + 
			"      \"ShowDate\": false,\n" + 
			"      \"ShowGroupLabels\": true,\n" + 
			"      \"ShowHighLimitLine\": false,\n" + 
			"      \"ShowLabels\": true,\n" + 
			"      \"ShowLeftAxis\": true,\n" + 
			"      \"ShowLowLimitLine\": false,\n" + 
			"      \"ShowMaxLine\": false,\n" + 
			"      \"ShowMinLine\": false,\n" + 
			"      \"ShowRightAxis\": true,\n" + 
			"      \"ShowTooltips\": false,\n" + 
			"      \"ShowValues\": true,\n" + 
			"      \"SortColName\": \"Percentage\",\n" + 
			"      \"SortDirection\": \"ascending\",\n" + 
			"      \"TZOffsetHours\": 0,\n" + 
			"      \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"ValueColName\": \"Number of things\",\n" + 
			"      \"ValueColor\": \"#ffffff\",\n" + 
			"      \"ValueDecimals\": 0,\n" + 
			"      \"ValueFontSize\": \"100%\",\n" + 
			"      \"ValuePosition\": \"bar end\",\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 17,\n" + 
			"        \"w\": 11,\n" + 
			"        \"x\": 12,\n" + 
			"        \"y\": 5\n" + 
			"      },\n" + 
			"      \"id\": 24,\n" + 
			"      \"links\": [],\n" + 
			"      \"targets\": [\n" + 
			"        {\n" + 
			"          \"format\": \"table\",\n" + 
			"          \"group\": [],\n" + 
			"          \"hide\": false,\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": true,\n" + 
			"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  name AS \\\"Name\\\",\\n  COUNT(type) AS \\\"Number of things\\\"\\nFROM organization_thing\\nGROUP BY timestamp, name\\nORDER BY timestamp\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"number_of_observations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"observation_name\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"observation_type\",\n" + 
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
			"      \"title\": \"Things owned by organisations in VICINITY\",\n" + 
			"      \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"aliasColors\": {},\n" + 
			"      \"bars\": true,\n" + 
			"      \"cacheTimeout\": null,\n" + 
			"      \"dashLength\": 10,\n" + 
			"      \"dashes\": false,\n" + 
			"      \"fill\": 1,\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 9,\n" + 
			"        \"w\": 12,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 22\n" + 
			"      },\n" + 
			"      \"id\": 14,\n" + 
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
			"          \"group\": [\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"$__interval\",\n" + 
			"                \"none\"\n" + 
			"              ],\n" + 
			"              \"type\": \"time\"\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"hide\": false,\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things_with_interaction_patters) AS \\\"Total interaction patterns\\\",\\n  things_with_interaction_patters_readable AS \\\"Readabale\\\",\\n  things_with_interaction_patters_writable AS \\\"Writable\\\",\\n  things_with_interaction_patters_accesible AS \\\"Accesible\\\",\\n  things_with_interaction_patters_observing AS \\\"With observations\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Total interaction patterns\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_readable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Readabale\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_writable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Writable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_accesible\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Accesible\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_observing\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"With observations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"overview\",\n" + 
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
			"      \"title\": \"Accessibility of interaction patterns in VICINITY\",\n" + 
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
			"      \"aliasColors\": {},\n" + 
			"      \"bars\": true,\n" + 
			"      \"cacheTimeout\": null,\n" + 
			"      \"dashLength\": 10,\n" + 
			"      \"dashes\": false,\n" + 
			"      \"fill\": 1,\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 9,\n" + 
			"        \"w\": 12,\n" + 
			"        \"x\": 12,\n" + 
			"        \"y\": 22\n" + 
			"      },\n" + 
			"      \"id\": 16,\n" + 
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
			"          \"group\": [\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"$__interval\",\n" + 
			"                \"none\"\n" + 
			"              ],\n" + 
			"              \"type\": \"time\"\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"hide\": false,\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things_with_interaction_patters) AS \\\"Total interaction patterns\\\",\\n  avg(things_with_interaction_patters_property) AS \\\"Properties\\\",\\n  avg(things_with_interaction_patters_property_and_measure) AS \\\"Properties with units\\\",\\n  avg(things_with_interaction_patters_action) AS \\\"Action\\\",\\n  avg(things_with_interaction_patters_event) AS \\\"Event\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Total interaction patterns\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_property\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Properties\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_property_and_measure\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Properties with units\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_action\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Action\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_interaction_patters_event\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Event\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"overview\",\n" + 
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
			"      \"title\": \"Types of interaction patterns in VICINITY\",\n" + 
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
			"      \"aliasColors\": {},\n" + 
			"      \"bars\": true,\n" + 
			"      \"cacheTimeout\": null,\n" + 
			"      \"dashLength\": 10,\n" + 
			"      \"dashes\": false,\n" + 
			"      \"fill\": 1,\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 8,\n" + 
			"        \"w\": 24,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 31\n" + 
			"      },\n" + 
			"      \"id\": 18,\n" + 
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
			"          \"group\": [\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"$__interval\",\n" + 
			"                \"none\"\n" + 
			"              ],\n" + 
			"              \"type\": \"time\"\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"hide\": false,\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things) AS \\\"Things in VICINITY\\\",\\n  things_with_buildings AS \\\"Buildings\\\",\\n  things_with_buildings_complete AS \\\"Buildings (complete)\\\",\\n  things_with_building_spaces AS \\\"Building spaces\\\",\\n  things_with_cities AS \\\"Cities\\\",\\n  things_with_cities_complete AS \\\"Cities (complete)\\\",\\n  things_with_countries AS \\\"Countries\\\",\\n  things_with_countries_complete AS \\\"Countries (complete)\\\",\\n  things_with_organizations AS \\\"Organizations\\\",\\n  things_with_organizations_complete AS \\\"Organizations (complete)\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"avg\"\n" + 
			"                ],\n" + 
			"                \"type\": \"aggregate\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Things in VICINITY\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_buildings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Buildings\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_buildings_complete\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Buildings (complete)\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_building_spaces\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Building spaces\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_cities\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Cities\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_cities_complete\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Cities (complete)\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_countries\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Countries\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_countries_complete\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Countries (complete)\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_organizations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Organizations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_organizations_complete\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Organizations (complete)\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"overview\",\n" + 
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
			"      \"title\": \"Things with contextual information\",\n" + 
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
			"      \"Aggregate\": \"last\",\n" + 
			"      \"BarPadding\": 10,\n" + 
			"      \"BaseLineColor\": \"#5794F2\",\n" + 
			"      \"BaseLineValue\": null,\n" + 
			"      \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"DateTimeColName\": \"date\",\n" + 
			"      \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"      \"FlashHighLimitBar\": false,\n" + 
			"      \"FlashLowLimitBar\": false,\n" + 
			"      \"GroupColName\": \"\",\n" + 
			"      \"GroupCols\": 0,\n" + 
			"      \"GroupGap\": 5,\n" + 
			"      \"GroupLabelColor\": \"#ffffff\",\n" + 
			"      \"GroupLabelFontSize\": \"200%\",\n" + 
			"      \"GroupNameFilter\": \"\",\n" + 
			"      \"GroupSortString\": \"\",\n" + 
			"      \"HighAxisColor\": \"#ffffff\",\n" + 
			"      \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"      \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"      \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"      \"HighLimitValue\": null,\n" + 
			"      \"HighSideMargin\": 22,\n" + 
			"      \"Horizontal\": true,\n" + 
			"      \"LabelColName\": \"type_name\",\n" + 
			"      \"LabelColor\": \"#ffffff\",\n" + 
			"      \"LabelFontSize\": \"100%\",\n" + 
			"      \"LabelMargin\": null,\n" + 
			"      \"LabelNameFilter\": \"\",\n" + 
			"      \"LableAngle\": 0,\n" + 
			"      \"LowAxisColor\": \"#ffffff\",\n" + 
			"      \"LowBarColor\": \"teal\",\n" + 
			"      \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"      \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"      \"LowLimitBarFlashTimeout\": 200,\n" + 
			"      \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"      \"LowLimitValue\": null,\n" + 
			"      \"LowSideMargin\": 22,\n" + 
			"      \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"      \"MaxLineValue\": null,\n" + 
			"      \"MinLineValue\": 0,\n" + 
			"      \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"      \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"      \"RecolorHighLimitBar\": true,\n" + 
			"      \"RecolorLowLimitBar\": false,\n" + 
			"      \"ScaleFactor\": 1,\n" + 
			"      \"ShowBaseLine\": true,\n" + 
			"      \"ShowDate\": false,\n" + 
			"      \"ShowGroupLabels\": true,\n" + 
			"      \"ShowHighLimitLine\": false,\n" + 
			"      \"ShowLabels\": true,\n" + 
			"      \"ShowLeftAxis\": true,\n" + 
			"      \"ShowLowLimitLine\": false,\n" + 
			"      \"ShowMaxLine\": false,\n" + 
			"      \"ShowMinLine\": false,\n" + 
			"      \"ShowRightAxis\": true,\n" + 
			"      \"ShowTooltips\": false,\n" + 
			"      \"ShowValues\": true,\n" + 
			"      \"SortColName\": \"number\",\n" + 
			"      \"SortDirection\": \"ascending\",\n" + 
			"      \"TZOffsetHours\": 0,\n" + 
			"      \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"ValueColName\": \"metric\",\n" + 
			"      \"ValueColor\": \"#ffffff\",\n" + 
			"      \"ValueDecimals\": 0,\n" + 
			"      \"ValueFontSize\": \"100%\",\n" + 
			"      \"ValuePosition\": \"bar end\",\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 22,\n" + 
			"        \"w\": 13,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 39\n" + 
			"      },\n" + 
			"      \"id\": 6,\n" + 
			"      \"links\": [],\n" + 
			"      \"targets\": [\n" + 
			"        {\n" + 
			"          \"format\": \"table\",\n" + 
			"          \"group\": [],\n" + 
			"          \"metricColumn\": \"number\",\n" + 
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number AS metric,\\n  number,\\n  type_name\\nFROM thing_type\\nWHERE\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"number\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"type_name\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"thing_type\",\n" + 
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
			"      \"title\": \"Type of things in VICINITY\",\n" + 
			"      \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"Aggregate\": \"last\",\n" + 
			"      \"BarPadding\": 10,\n" + 
			"      \"BaseLineColor\": \"#5794F2\",\n" + 
			"      \"BaseLineValue\": null,\n" + 
			"      \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"DateTimeColName\": \"date\",\n" + 
			"      \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"      \"FlashHighLimitBar\": false,\n" + 
			"      \"FlashLowLimitBar\": false,\n" + 
			"      \"GroupColName\": \"\",\n" + 
			"      \"GroupCols\": 0,\n" + 
			"      \"GroupGap\": 5,\n" + 
			"      \"GroupLabelColor\": \"#ffffff\",\n" + 
			"      \"GroupLabelFontSize\": \"200%\",\n" + 
			"      \"GroupNameFilter\": \"\",\n" + 
			"      \"GroupSortString\": \"\",\n" + 
			"      \"HighAxisColor\": \"#ffffff\",\n" + 
			"      \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"      \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"      \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"      \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"      \"HighLimitValue\": null,\n" + 
			"      \"HighSideMargin\": 22,\n" + 
			"      \"Horizontal\": true,\n" + 
			"      \"LabelColName\": \"observation_name\",\n" + 
			"      \"LabelColor\": \"#ffffff\",\n" + 
			"      \"LabelFontSize\": \"100%\",\n" + 
			"      \"LabelMargin\": null,\n" + 
			"      \"LabelNameFilter\": \"\",\n" + 
			"      \"LableAngle\": 0,\n" + 
			"      \"LowAxisColor\": \"#ffffff\",\n" + 
			"      \"LowBarColor\": \"teal\",\n" + 
			"      \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"      \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"      \"LowLimitBarFlashTimeout\": 200,\n" + 
			"      \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"      \"LowLimitValue\": null,\n" + 
			"      \"LowSideMargin\": 22,\n" + 
			"      \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"      \"MaxLineValue\": null,\n" + 
			"      \"MinLineValue\": 0,\n" + 
			"      \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"      \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"      \"RecolorHighLimitBar\": true,\n" + 
			"      \"RecolorLowLimitBar\": false,\n" + 
			"      \"ScaleFactor\": 1,\n" + 
			"      \"ShowBaseLine\": true,\n" + 
			"      \"ShowDate\": false,\n" + 
			"      \"ShowGroupLabels\": true,\n" + 
			"      \"ShowHighLimitLine\": false,\n" + 
			"      \"ShowLabels\": true,\n" + 
			"      \"ShowLeftAxis\": true,\n" + 
			"      \"ShowLowLimitLine\": false,\n" + 
			"      \"ShowMaxLine\": false,\n" + 
			"      \"ShowMinLine\": false,\n" + 
			"      \"ShowRightAxis\": true,\n" + 
			"      \"ShowTooltips\": false,\n" + 
			"      \"ShowValues\": true,\n" + 
			"      \"SortColName\": \"number_of_observations\",\n" + 
			"      \"SortDirection\": \"ascending\",\n" + 
			"      \"TZOffsetHours\": 0,\n" + 
			"      \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"ValueColName\": \"number_of_observations\",\n" + 
			"      \"ValueColor\": \"#ffffff\",\n" + 
			"      \"ValueDecimals\": 0,\n" + 
			"      \"ValueFontSize\": \"100%\",\n" + 
			"      \"ValuePosition\": \"bar end\",\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 22,\n" + 
			"        \"w\": 11,\n" + 
			"        \"x\": 13,\n" + 
			"        \"y\": 39\n" + 
			"      },\n" + 
			"      \"id\": 12,\n" + 
			"      \"links\": [],\n" + 
			"      \"targets\": [\n" + 
			"        {\n" + 
			"          \"format\": \"table\",\n" + 
			"          \"group\": [],\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number_of_observations,\\n  observation_name\\nFROM observation_type\\nWHERE\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"number_of_observations\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"observation_name\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"observation_type\",\n" + 
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
			"      \"title\": \"Properties observed in VICINITY\",\n" + 
			"      \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"columns\": [],\n" + 
			"      \"fontSize\": \"100%\",\n" + 
			"      \"gridPos\": {\n" + 
			"        \"h\": 7,\n" + 
			"        \"w\": 24,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 61\n" + 
			"      },\n" + 
			"      \"id\": 20,\n" + 
			"      \"links\": [],\n" + 
			"      \"pageSize\": 100,\n" + 
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
			"          \"rawQuery\": false,\n" + 
			"          \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  name AS \\\"Organisation\\\",\\n  things AS \\\"Things\\\",\\n  things_adapters AS \\\"Adapters\\\",\\n  things_services AS \\\"Services\\\",\\n  things_interoperable AS \\\"Interoperable\\\"\\nFROM organization\\nWHERE\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"          \"refId\": \"A\",\n" + 
			"          \"select\": [\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"name\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Organisation\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Things\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Adapters\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Services\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_interoperable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Interoperable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ]\n" + 
			"          ],\n" + 
			"          \"table\": \"organization\",\n" + 
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
			"      \"title\": \"Organisations in VICINITY\",\n" + 
			"      \"transform\": \"table\",\n" + 
			"      \"type\": \"table\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"refresh\": \"1h\",\n" + 
			"  \"schemaVersion\": 18,\n" + 
			"  \"style\": \"dark\",\n" + 
			"  \"tags\": [],\n" + 
			"  \"templating\": {\n" + 
			"    \"list\": []\n" + 
			"  },\n" + 
			"  \"time\": {\n" + 
			"    \"from\": \"now/d\",\n" + 
			"    \"to\": \"now/d\"\n" + 
			"  },\n" + 
			"  \"timepicker\": {\n" + 
			"    \"refresh_intervals\": [\n" + 
			"      \"5s\",\n" + 
			"      \"10s\",\n" + 
			"      \"30s\",\n" + 
			"      \"1m\",\n" + 
			"      \"5m\",\n" + 
			"      \"15m\",\n" + 
			"      \"30m\",\n" + 
			"      \"1h\",\n" + 
			"      \"2h\",\n" + 
			"      \"1d\"\n" + 
			"    ],\n" + 
			"    \"time_options\": [\n" + 
			"      \"5m\",\n" + 
			"      \"15m\",\n" + 
			"      \"1h\",\n" + 
			"      \"6h\",\n" + 
			"      \"12h\",\n" + 
			"      \"24h\",\n" + 
			"      \"2d\",\n" + 
			"      \"7d\",\n" + 
			"      \"30d\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"timezone\": \"\",\n" + 
			"  \"title\": \"ThingsInVicinity\",\n" + 
			"  \"uid\": \"sDvgyEemk\",\n" + 
			"  \"version\": 54\n" + 
			"}";
	
	private final static String ALT_TEMPLATE = "{\n" + 
			"  \"dashboard\": {\n" + 
			"    \"id\": null,\n" + 
			"    \"uid\": null,\n" + 
			"    \"title\": \"ThingsInVICINITY\",\n" + 
			"    \"tags\": [ \"templated\" ],\n" + 
			"    \"timezone\": \"browser\",\n" + 
			"    \"schemaVersion\": 16,\n" + 
			"    \"version\": 0,\n" + 
			"	\"panels\": ["+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": false,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 5,\n" + 
			"    \"w\": 23,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 0\n" + 
			"  },\n" + 
			"  \"id\": 8,\n" + 
			"  \"legend\": {\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": false,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": false\n" + 
			"  },\n" + 
			"  \"lines\": true,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [],\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  things AS \\\"Things\\\",\\n  things_with_adapters AS \\\"Adapters\\\",\\n  things_with_services AS \\\"Services\\\",\\n  things_with_mappings AS \\\"Interoperable Things\\\",\\n  things_with_organizations AS \\\"Organizations\\\",\\n  things_with_countries AS \\\"Countries\\\",\\n  things_with_cities AS \\\"Cities\\\",\\n  things_with_buildings AS \\\"Buildings\\\"\\nFROM overview\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_adapters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Adapters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_services\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Services\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_mappings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Interoperable Things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_organizations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Organizations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_countries\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Countries\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_cities\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Cities\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_buildings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Buildings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"timestamp\",\n" + 
			"      \"where\": []\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Number of things in VICINITY evolution\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": true,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"time\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": []\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 17,\n" + 
			"    \"w\": 12,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 5\n" + 
			"  },\n" + 
			"  \"id\": 2,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"params\": [\n" + 
			"            \"$__interval\",\n" + 
			"            \"none\"\n" + 
			"          ],\n" + 
			"          \"type\": \"time\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  things AS \\\"Total number of Things\\\",\\n  things_with_adapters AS \\\"Adapters\\\",\\n  things_with_services AS \\\"Services\\\",\\n  (things - (things_with_adapters+things_with_services)) AS \\\"Generic\\\",\\n  things_with_mappings AS \\\"Interoperable Things\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Total number of Things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_adapters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Adapters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_services\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Services\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_mappings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Interoperable Things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Things in VICINITY\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"format\": \"short\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"Aggregate\": \"last\",\n" + 
			"  \"BarPadding\": 10,\n" + 
			"  \"BaseLineColor\": \"#5794F2\",\n" + 
			"  \"BaseLineValue\": null,\n" + 
			"  \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"DateTimeColName\": \"date\",\n" + 
			"  \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"  \"FlashHighLimitBar\": false,\n" + 
			"  \"FlashLowLimitBar\": false,\n" + 
			"  \"GroupColName\": \"\",\n" + 
			"  \"GroupCols\": 0,\n" + 
			"  \"GroupGap\": 5,\n" + 
			"  \"GroupLabelColor\": \"#ffffff\",\n" + 
			"  \"GroupLabelFontSize\": \"200%\",\n" + 
			"  \"GroupNameFilter\": \"\",\n" + 
			"  \"GroupSortString\": \"\",\n" + 
			"  \"HighAxisColor\": \"#ffffff\",\n" + 
			"  \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"  \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"  \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"  \"HighLimitValue\": null,\n" + 
			"  \"HighSideMargin\": 22,\n" + 
			"  \"Horizontal\": true,\n" + 
			"  \"LabelColName\": \"Name\",\n" + 
			"  \"LabelColor\": \"#ffffff\",\n" + 
			"  \"LabelFontSize\": \"100%\",\n" + 
			"  \"LabelMargin\": null,\n" + 
			"  \"LabelNameFilter\": \"\",\n" + 
			"  \"LableAngle\": 0,\n" + 
			"  \"LowAxisColor\": \"#ffffff\",\n" + 
			"  \"LowBarColor\": \"teal\",\n" + 
			"  \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"  \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"  \"LowLimitBarFlashTimeout\": 200,\n" + 
			"  \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"  \"LowLimitValue\": null,\n" + 
			"  \"LowSideMargin\": 22,\n" + 
			"  \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"  \"MaxLineValue\": null,\n" + 
			"  \"MinLineValue\": 0,\n" + 
			"  \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"  \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"  \"RecolorHighLimitBar\": true,\n" + 
			"  \"RecolorLowLimitBar\": false,\n" + 
			"  \"ScaleFactor\": 1,\n" + 
			"  \"ShowBaseLine\": true,\n" + 
			"  \"ShowDate\": false,\n" + 
			"  \"ShowGroupLabels\": true,\n" + 
			"  \"ShowHighLimitLine\": false,\n" + 
			"  \"ShowLabels\": true,\n" + 
			"  \"ShowLeftAxis\": true,\n" + 
			"  \"ShowLowLimitLine\": false,\n" + 
			"  \"ShowMaxLine\": false,\n" + 
			"  \"ShowMinLine\": false,\n" + 
			"  \"ShowRightAxis\": true,\n" + 
			"  \"ShowTooltips\": false,\n" + 
			"  \"ShowValues\": true,\n" + 
			"  \"SortColName\": \"Percentage\",\n" + 
			"  \"SortDirection\": \"ascending\",\n" + 
			"  \"TZOffsetHours\": 0,\n" + 
			"  \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"ValueColName\": \"Number of things\",\n" + 
			"  \"ValueColor\": \"#ffffff\",\n" + 
			"  \"ValueDecimals\": 0,\n" + 
			"  \"ValueFontSize\": \"100%\",\n" + 
			"  \"ValuePosition\": \"bar end\",\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 17,\n" + 
			"    \"w\": 11,\n" + 
			"    \"x\": 12,\n" + 
			"    \"y\": 5\n" + 
			"  },\n" + 
			"  \"id\": 24,\n" + 
			"  \"links\": [],\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"table\",\n" + 
			"      \"group\": [],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": true,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  name AS \\\"Name\\\",\\n  COUNT(type) AS \\\"Number of things\\\"\\nFROM organization_thing\\nGROUP BY timestamp, name\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"number_of_observations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"observation_name\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"observation_type\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Things owned by organisations in VICINITY\",\n" + 
			"  \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 9,\n" + 
			"    \"w\": 12,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 22\n" + 
			"  },\n" + 
			"  \"id\": 14,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"params\": [\n" + 
			"            \"$__interval\",\n" + 
			"            \"none\"\n" + 
			"          ],\n" + 
			"          \"type\": \"time\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things_with_interaction_patters) AS \\\"Total interaction patterns\\\",\\n  things_with_interaction_patters_readable AS \\\"Readabale\\\",\\n  things_with_interaction_patters_writable AS \\\"Writable\\\",\\n  things_with_interaction_patters_accesible AS \\\"Accesible\\\",\\n  things_with_interaction_patters_observing AS \\\"With observations\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Total interaction patterns\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_readable\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Readabale\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_writable\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Writable\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_accesible\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Accesible\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_observing\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"With observations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Accessibility of interaction patterns in VICINITY\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 9,\n" + 
			"    \"w\": 12,\n" + 
			"    \"x\": 12,\n" + 
			"    \"y\": 22\n" + 
			"  },\n" + 
			"  \"id\": 16,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"params\": [\n" + 
			"            \"$__interval\",\n" + 
			"            \"none\"\n" + 
			"          ],\n" + 
			"          \"type\": \"time\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things_with_interaction_patters) AS \\\"Total interaction patterns\\\",\\n  avg(things_with_interaction_patters_property) AS \\\"Properties\\\",\\n  avg(things_with_interaction_patters_property_and_measure) AS \\\"Properties with units\\\",\\n  avg(things_with_interaction_patters_action) AS \\\"Action\\\",\\n  avg(things_with_interaction_patters_event) AS \\\"Event\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Total interaction patterns\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_property\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Properties\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_property_and_measure\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Properties with units\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_action\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Action\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_interaction_patters_event\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Event\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Types of interaction patterns in VICINITY\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"aliasColors\": {},\n" + 
			"  \"bars\": true,\n" + 
			"  \"cacheTimeout\": null,\n" + 
			"  \"dashLength\": 10,\n" + 
			"  \"dashes\": false,\n" + 
			"  \"fill\": 1,\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 8,\n" + 
			"    \"w\": 24,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 31\n" + 
			"  },\n" + 
			"  \"id\": 18,\n" + 
			"  \"legend\": {\n" + 
			"    \"alignAsTable\": true,\n" + 
			"    \"avg\": false,\n" + 
			"    \"current\": true,\n" + 
			"    \"max\": false,\n" + 
			"    \"min\": false,\n" + 
			"    \"show\": true,\n" + 
			"    \"total\": false,\n" + 
			"    \"values\": true\n" + 
			"  },\n" + 
			"  \"lines\": false,\n" + 
			"  \"linewidth\": 1,\n" + 
			"  \"links\": [],\n" + 
			"  \"nullPointMode\": \"null as zero\",\n" + 
			"  \"paceLength\": 10,\n" + 
			"  \"percentage\": false,\n" + 
			"  \"pointradius\": 2,\n" + 
			"  \"points\": false,\n" + 
			"  \"renderer\": \"flot\",\n" + 
			"  \"seriesOverrides\": [],\n" + 
			"  \"stack\": false,\n" + 
			"  \"steppedLine\": false,\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"time_series\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"params\": [\n" + 
			"            \"$__interval\",\n" + 
			"            \"none\"\n" + 
			"          ],\n" + 
			"          \"type\": \"time\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"hide\": false,\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things) AS \\\"Things in VICINITY\\\",\\n  things_with_buildings AS \\\"Buildings\\\",\\n  things_with_buildings_complete AS \\\"Buildings (complete)\\\",\\n  things_with_building_spaces AS \\\"Building spaces\\\",\\n  things_with_cities AS \\\"Cities\\\",\\n  things_with_cities_complete AS \\\"Cities (complete)\\\",\\n  things_with_countries AS \\\"Countries\\\",\\n  things_with_countries_complete AS \\\"Countries (complete)\\\",\\n  things_with_organizations AS \\\"Organizations\\\",\\n  things_with_organizations_complete AS \\\"Organizations (complete)\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"avg\"\n" + 
			"            ],\n" + 
			"            \"type\": \"aggregate\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Things in VICINITY\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_buildings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Buildings\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_buildings_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Buildings (complete)\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_building_spaces\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Building spaces\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_cities\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Cities\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_cities_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Cities (complete)\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_countries\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Countries\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_countries_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Countries (complete)\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_organizations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Organizations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"things_with_organizations_complete\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"Organizations (complete)\"\n" + 
			"            ],\n" + 
			"            \"type\": \"alias\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"thresholds\": [],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeRegions\": [],\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Things with contextual information\",\n" + 
			"  \"tooltip\": {\n" + 
			"    \"shared\": false,\n" + 
			"    \"sort\": 0,\n" + 
			"    \"value_type\": \"individual\"\n" + 
			"  },\n" + 
			"  \"type\": \"graph\",\n" + 
			"  \"xaxis\": {\n" + 
			"    \"buckets\": null,\n" + 
			"    \"mode\": \"series\",\n" + 
			"    \"name\": null,\n" + 
			"    \"show\": true,\n" + 
			"    \"values\": [\n" + 
			"      \"current\"\n" + 
			"    ]\n" + 
			"  },\n" + 
			"  \"yaxes\": [\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": null,\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": null,\n" + 
			"      \"show\": true\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"decimals\": null,\n" + 
			"      \"format\": \"none\",\n" + 
			"      \"label\": \"\",\n" + 
			"      \"logBase\": 1,\n" + 
			"      \"max\": null,\n" + 
			"      \"min\": \"0\",\n" + 
			"      \"show\": false\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"yaxis\": {\n" + 
			"    \"align\": false,\n" + 
			"    \"alignLevel\": null\n" + 
			"  }\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"Aggregate\": \"last\",\n" + 
			"  \"BarPadding\": 10,\n" + 
			"  \"BaseLineColor\": \"#5794F2\",\n" + 
			"  \"BaseLineValue\": null,\n" + 
			"  \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"DateTimeColName\": \"date\",\n" + 
			"  \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"  \"FlashHighLimitBar\": false,\n" + 
			"  \"FlashLowLimitBar\": false,\n" + 
			"  \"GroupColName\": \"\",\n" + 
			"  \"GroupCols\": 0,\n" + 
			"  \"GroupGap\": 5,\n" + 
			"  \"GroupLabelColor\": \"#ffffff\",\n" + 
			"  \"GroupLabelFontSize\": \"200%\",\n" + 
			"  \"GroupNameFilter\": \"\",\n" + 
			"  \"GroupSortString\": \"\",\n" + 
			"  \"HighAxisColor\": \"#ffffff\",\n" + 
			"  \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"  \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"  \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"  \"HighLimitValue\": null,\n" + 
			"  \"HighSideMargin\": 22,\n" + 
			"  \"Horizontal\": true,\n" + 
			"  \"LabelColName\": \"type_name\",\n" + 
			"  \"LabelColor\": \"#ffffff\",\n" + 
			"  \"LabelFontSize\": \"100%\",\n" + 
			"  \"LabelMargin\": null,\n" + 
			"  \"LabelNameFilter\": \"\",\n" + 
			"  \"LableAngle\": 0,\n" + 
			"  \"LowAxisColor\": \"#ffffff\",\n" + 
			"  \"LowBarColor\": \"teal\",\n" + 
			"  \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"  \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"  \"LowLimitBarFlashTimeout\": 200,\n" + 
			"  \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"  \"LowLimitValue\": null,\n" + 
			"  \"LowSideMargin\": 22,\n" + 
			"  \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"  \"MaxLineValue\": null,\n" + 
			"  \"MinLineValue\": 0,\n" + 
			"  \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"  \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"  \"RecolorHighLimitBar\": true,\n" + 
			"  \"RecolorLowLimitBar\": false,\n" + 
			"  \"ScaleFactor\": 1,\n" + 
			"  \"ShowBaseLine\": true,\n" + 
			"  \"ShowDate\": false,\n" + 
			"  \"ShowGroupLabels\": true,\n" + 
			"  \"ShowHighLimitLine\": false,\n" + 
			"  \"ShowLabels\": true,\n" + 
			"  \"ShowLeftAxis\": true,\n" + 
			"  \"ShowLowLimitLine\": false,\n" + 
			"  \"ShowMaxLine\": false,\n" + 
			"  \"ShowMinLine\": false,\n" + 
			"  \"ShowRightAxis\": true,\n" + 
			"  \"ShowTooltips\": false,\n" + 
			"  \"ShowValues\": true,\n" + 
			"  \"SortColName\": \"number\",\n" + 
			"  \"SortDirection\": \"ascending\",\n" + 
			"  \"TZOffsetHours\": 0,\n" + 
			"  \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"ValueColName\": \"metric\",\n" + 
			"  \"ValueColor\": \"#ffffff\",\n" + 
			"  \"ValueDecimals\": 0,\n" + 
			"  \"ValueFontSize\": \"100%\",\n" + 
			"  \"ValuePosition\": \"bar end\",\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 22,\n" + 
			"    \"w\": 13,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 39\n" + 
			"  },\n" + 
			"  \"id\": 6,\n" + 
			"  \"links\": [],\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"table\",\n" + 
			"      \"group\": [],\n" + 
			"      \"metricColumn\": \"number\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number AS metric,\\n  number,\\n  type_name\\nFROM thing_type\\nWHERE\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"number\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"type_name\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"thing_type\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Type of things in VICINITY\",\n" + 
			"  \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"},"+
			//--
			"{\n" + 
			"  \"Aggregate\": \"last\",\n" + 
			"  \"BarPadding\": 10,\n" + 
			"  \"BaseLineColor\": \"#5794F2\",\n" + 
			"  \"BaseLineValue\": null,\n" + 
			"  \"DateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"DateTimeColName\": \"date\",\n" + 
			"  \"EvenRowColor\": \"rgba(61, 61, 64, 0.78)\",\n" + 
			"  \"FlashHighLimitBar\": false,\n" + 
			"  \"FlashLowLimitBar\": false,\n" + 
			"  \"GroupColName\": \"\",\n" + 
			"  \"GroupCols\": 0,\n" + 
			"  \"GroupGap\": 5,\n" + 
			"  \"GroupLabelColor\": \"#ffffff\",\n" + 
			"  \"GroupLabelFontSize\": \"200%\",\n" + 
			"  \"GroupNameFilter\": \"\",\n" + 
			"  \"GroupSortString\": \"\",\n" + 
			"  \"HighAxisColor\": \"#ffffff\",\n" + 
			"  \"HighBarColor\": \"rgb(120, 128, 0)\",\n" + 
			"  \"HighLimitBarColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashColor\": \"#5794F2\",\n" + 
			"  \"HighLimitBarFlashTimeout\": 1000,\n" + 
			"  \"HighLimitLineColor\": \"#ff0000\",\n" + 
			"  \"HighLimitValue\": null,\n" + 
			"  \"HighSideMargin\": 22,\n" + 
			"  \"Horizontal\": true,\n" + 
			"  \"LabelColName\": \"observation_name\",\n" + 
			"  \"LabelColor\": \"#ffffff\",\n" + 
			"  \"LabelFontSize\": \"100%\",\n" + 
			"  \"LabelMargin\": null,\n" + 
			"  \"LabelNameFilter\": \"\",\n" + 
			"  \"LableAngle\": 0,\n" + 
			"  \"LowAxisColor\": \"#ffffff\",\n" + 
			"  \"LowBarColor\": \"teal\",\n" + 
			"  \"LowLimitBarColor\": \"#ff0000\",\n" + 
			"  \"LowLimitBarFlashColor\": \"#ffa500\",\n" + 
			"  \"LowLimitBarFlashTimeout\": 200,\n" + 
			"  \"LowLimitLineColor\": \"#ff0000\",\n" + 
			"  \"LowLimitValue\": null,\n" + 
			"  \"LowSideMargin\": 22,\n" + 
			"  \"MaxLineColor\": \"rgb(74, 232, 12)\",\n" + 
			"  \"MaxLineValue\": null,\n" + 
			"  \"MinLineValue\": 0,\n" + 
			"  \"OddRowColor\": \"rgba(33, 33, 34, 0.92)\",\n" + 
			"  \"OutOfRangeLabelColor\": \"#ffffff\",\n" + 
			"  \"RecolorHighLimitBar\": true,\n" + 
			"  \"RecolorLowLimitBar\": false,\n" + 
			"  \"ScaleFactor\": 1,\n" + 
			"  \"ShowBaseLine\": true,\n" + 
			"  \"ShowDate\": false,\n" + 
			"  \"ShowGroupLabels\": true,\n" + 
			"  \"ShowHighLimitLine\": false,\n" + 
			"  \"ShowLabels\": true,\n" + 
			"  \"ShowLeftAxis\": true,\n" + 
			"  \"ShowLowLimitLine\": false,\n" + 
			"  \"ShowMaxLine\": false,\n" + 
			"  \"ShowMinLine\": false,\n" + 
			"  \"ShowRightAxis\": true,\n" + 
			"  \"ShowTooltips\": false,\n" + 
			"  \"ShowValues\": true,\n" + 
			"  \"SortColName\": \"number_of_observations\",\n" + 
			"  \"SortDirection\": \"ascending\",\n" + 
			"  \"TZOffsetHours\": 0,\n" + 
			"  \"TooltipDateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"  \"ValueColName\": \"number_of_observations\",\n" + 
			"  \"ValueColor\": \"#ffffff\",\n" + 
			"  \"ValueDecimals\": 0,\n" + 
			"  \"ValueFontSize\": \"100%\",\n" + 
			"  \"ValuePosition\": \"bar end\",\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 22,\n" + 
			"    \"w\": 11,\n" + 
			"    \"x\": 13,\n" + 
			"    \"y\": 39\n" + 
			"  },\n" + 
			"  \"id\": 12,\n" + 
			"  \"links\": [],\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"table\",\n" + 
			"      \"group\": [],\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  number_of_observations,\\n  observation_name\\nFROM observation_type\\nWHERE\\n  $__timeFilter(timestamp)\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"number_of_observations\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"params\": [\n" + 
			"              \"observation_name\"\n" + 
			"            ],\n" + 
			"            \"type\": \"column\"\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"observation_type\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": [],\n" + 
			"          \"type\": \"macro\"\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Properties observed in VICINITY\",\n" + 
			"  \"type\": \"michaeldmoore-multistat-panel\"\n" + 
			"},"+
			//
			"{\n" + 
			"  \"columns\": [],\n" + 
			"  \"fontSize\": \"100%\",\n" + 
			"  \"gridPos\": {\n" + 
			"    \"h\": 9,\n" + 
			"    \"w\": 24,\n" + 
			"    \"x\": 0,\n" + 
			"    \"y\": 61\n" + 
			"  },\n" + 
			"  \"id\": 20,\n" + 
			"  \"links\": [],\n" + 
			"  \"pageSize\": 100,\n" + 
			"  \"scroll\": true,\n" + 
			"  \"showHeader\": true,\n" + 
			"  \"sort\": {\n" + 
			"    \"col\": 0,\n" + 
			"    \"desc\": true\n" + 
			"  },\n" + 
			"  \"styles\": [\n" + 
			"    {\n" + 
			"      \"alias\": \"Time\",\n" + 
			"      \"dateFormat\": \"YYYY-MM-DD HH:mm:ss\",\n" + 
			"      \"pattern\": \"Time\",\n" + 
			"      \"type\": \"date\"\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"alias\": \"\",\n" + 
			"      \"colorMode\": null,\n" + 
			"      \"colors\": [\n" + 
			"        \"rgba(245, 54, 54, 0.9)\",\n" + 
			"        \"rgba(237, 129, 40, 0.89)\",\n" + 
			"        \"rgba(50, 172, 45, 0.97)\"\n" + 
			"      ],\n" + 
			"      \"decimals\": 0,\n" + 
			"      \"pattern\": \"/.*/\",\n" + 
			"      \"thresholds\": [],\n" + 
			"      \"type\": \"number\",\n" + 
			"      \"unit\": \"short\"\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"targets\": [\n" + 
			"    {\n" + 
			"      \"format\": \"table\",\n" + 
			"      \"group\": [\n" + 
			"        {\n" + 
			"          \"type\": \"column\",\n" + 
			"          \"params\": [\n" + 
			"            \"timestamp\"\n" + 
			"          ]\n" + 
			"        },\n" + 
			"        {\n" + 
			"          \"type\": \"column\",\n" + 
			"          \"params\": [\n" + 
			"            \"name\"\n" + 
			"          ]\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"metricColumn\": \"none\",\n" + 
			"      \"rawQuery\": false,\n" + 
			"      \"rawSql\": \"SELECT\\n  timestamp AS \\\"time\\\",\\n  name AS \\\"Organisation\\\",\\n  number_of_contracts_involved AS \\\"Contracts in which participates\\\",\\n  number_of_contracts_owner AS \\\"Owned contracts\\\",\\n  things_adapters AS \\\"Adapters\\\",\\n  things_services AS \\\"Services\\\"\\nFROM organization_overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY timestamp, name\\nORDER BY timestamp\",\n" + 
			"      \"refId\": \"A\",\n" + 
			"      \"select\": [\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"type\": \"column\",\n" + 
			"            \"params\": [\n" + 
			"              \"name\"\n" + 
			"            ]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"type\": \"alias\",\n" + 
			"            \"params\": [\n" + 
			"              \"Organisation\"\n" + 
			"            ]\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"type\": \"column\",\n" + 
			"            \"params\": [\n" + 
			"              \"number_of_contracts_involved\"\n" + 
			"            ]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"type\": \"alias\",\n" + 
			"            \"params\": [\n" + 
			"              \"Contracts in which participates\"\n" + 
			"            ]\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"type\": \"column\",\n" + 
			"            \"params\": [\n" + 
			"              \"number_of_contracts_owner\"\n" + 
			"            ]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"type\": \"alias\",\n" + 
			"            \"params\": [\n" + 
			"              \"Owned contracts\"\n" + 
			"            ]\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"type\": \"column\",\n" + 
			"            \"params\": [\n" + 
			"              \"things_adapters\"\n" + 
			"            ]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"type\": \"alias\",\n" + 
			"            \"params\": [\n" + 
			"              \"Adapters\"\n" + 
			"            ]\n" + 
			"          }\n" + 
			"        ],\n" + 
			"        [\n" + 
			"          {\n" + 
			"            \"type\": \"column\",\n" + 
			"            \"params\": [\n" + 
			"              \"things_services\"\n" + 
			"            ]\n" + 
			"          },\n" + 
			"          {\n" + 
			"            \"type\": \"alias\",\n" + 
			"            \"params\": [\n" + 
			"              \"Services\"\n" + 
			"            ]\n" + 
			"          }\n" + 
			"        ]\n" + 
			"      ],\n" + 
			"      \"table\": \"organization_overview\",\n" + 
			"      \"timeColumn\": \"timestamp\",\n" + 
			"      \"timeColumnType\": \"datetime\",\n" + 
			"      \"where\": [\n" + 
			"        {\n" + 
			"          \"type\": \"macro\",\n" + 
			"          \"name\": \"$__timeFilter\",\n" + 
			"          \"params\": []\n" + 
			"        }\n" + 
			"      ]\n" + 
			"    }\n" + 
			"  ],\n" + 
			"  \"timeFrom\": null,\n" + 
			"  \"timeShift\": null,\n" + 
			"  \"title\": \"Organisations in VICINITY\",\n" + 
			"  \"transform\": \"table\",\n" + 
			"  \"type\": \"table\"\n" + 
			"}"+
			//--- last pannel
			"	]"+
			"  },\n" + 
			"  \"folderId\": 0,\n" + 
			"  \"overwrite\": true\n" + 
			"}";
	
}
