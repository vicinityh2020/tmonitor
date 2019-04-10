package vicinity.tmonitor;

import java.util.ArrayList;
import java.util.List;

public class Queries {
	
	
	public static List<String> prefixes;
	
	static {
		prefixes = new ArrayList<>();
		prefixes.add("http://iot.linkeddata.es/def/core#");
		prefixes.add("http://iot.linkeddata.es/def/adapters#");
		prefixes.add("http://iot.linkeddata.es/def/wot#");
	}
	
	public static String cleanPrefixes(String value) {
		String newValue = value.toString();
		for(int index=0; index < prefixes.size(); index++) {
			newValue = newValue.replace(prefixes.get(index), "");
		}
		return newValue;
	}

	// *** 
	// ** 	OVERVIEW QUERIES
	// ***
	
	public static final String queryNumberOfThings = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"select (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"} ";
	public static final String queryNumberOfThingsAdapters = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"select (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:represents ?object.\n" + 
			"    ?object a ?type .\n" + 
			"    FILTER(regex(str(?type), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) ) .\n" + 
			"} ";
	
	public static final String queryNumberOfThingsService = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"select (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:represents ?object.\n" + 
			"    ?object a core:Service ."+
			"}";
	
	
	public static final String queryNumberOfThingsWithOrganizatios = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"select (count(distinct ?organization) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"   ?subject core:hasOwner ?organization .\n" + 
			"} ";
	
	public static final String queryNumberOfThingsWithOrganizatiosComplete = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"select (count(distinct ?organization) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:hasOwner ?organization .\n" + 
			"    ?organization foaf:name ?name .\n" + 
			"    ?organization foaf:openid ?id .\n" + 
			"} ";
	
	public static final String queryNumberOfThingsWithBuildings = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"select (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?building .\n" + 
			"    ?building a s4bldg:Building .\n" + 
			"} ";
	
	public static final String queryNumberOfThingsWithBuildingsComplete = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select DISTINCT (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?building .\n" + 
			"    ?building a s4bldg:Building .\n" + 
			"    ?building rdfs:label ?name .\n" + 
			"    ?building owl:sameAs ?id .\n" + 
			"} ";
	public static final String queryNumberOfThingsWithBuildingSpaces = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select DISTINCT (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?buildingSpace .\n" + 
			"    ?buildingSpace a s4bldg:BuildingSpace .\n" + 
			"    ?buildingSpace rdfs:label ?name .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithCities = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select (count(distinct ?subject) as ?count)  where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"   ?subject core:isLocatedAt ?city .\n" + 
			"    ?city a <https://w3id.org/def/saref4city#City> .  \n" + 
			"}";
	
	public static final String queryNumberOfThingsWithCitiesCorrect = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select (count(distinct ?subject) as ?count)  where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?city .\n" + 
			"    ?city a <https://w3id.org/def/saref4city#City> . \n" + 
			"    ?city rdfs:label ?name .\n" + 
			"    ?city owl:sameAs ?id.\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithCountry = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select (count(distinct ?subject) as ?count)  where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?country .\n" + 
			"    ?country a <https://w3id.org/def/saref4city#Country> .  \n" + 
			"}";
	
	public static final String queryNumberOfThingsWithCountryCorrect = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"select (count(distinct ?subject) as ?count)  where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:isLocatedAt ?country .\n" + 
			"    ?country a <https://w3id.org/def/saref4city#Country> . \n" + 
			"    ?country rdfs:label ?name .\n" + 
			"    ?country owl:sameAs ?id.\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPatters = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"\n" + 
			"select distinct (count(distinct ?pattern) as ?count)  where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersObserving = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count)  where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"       ?pattern sosa:observes ?observes  .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersReadable = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern <http://iot.linkeddata.es/def/wot#isReadableThrough> ?endpoint .\n" + 
			"    ?endpoint wot:href ?link .\n" + 
			"    ?endpoint wot:outputRawJSONString ?schema .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersWritable = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern <http://iot.linkeddata.es/def/wot#isWritableThrough> ?endpoint .\n" + 
			"    ?endpoint wot:href ?link .\n" + 
			"    ?endpoint wot:outputRawJSONString ?schema .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersAccesible= "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern <http://iot.linkeddata.es/def/wot#isAccessibleThrough> ?endpoint .\n" + 
			"    ?endpoint wot:href ?link .\n" + 
			"    ?endpoint wot:outputRawJSONString ?schema .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersProperty= "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern a wot:Property .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersPropertyAndMeasure= "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern a wot:Property .\n" + 
			"    ?pattern wot:isMeasuredIn ?measure .\n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersEvent= "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern a wot:Event .\n" + 
			"    \n" + 
			"}";
	
	public static final String queryNumberOfThingsWithIntPattersAction= "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?pattern) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern a wot:Action .\n" + 
			"    \n" + 
			"}";
	
	public static final String queryNumberOfThingsWithMappings = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct (count(distinct ?thing) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"    ?pattern core:hasValue ?value .\n" + 
			"    ?value core:isDescribedBy ?description .\n" + 
			"    ?description <http://iot.linkeddata.es/def/wot-mappings#hasAccessMapping> ?accessMapping .\n" + 
			"    ?accessMapping <http://iot.linkeddata.es/def/wot-mappings#hasMapping> ?mappings .\n" + 
			"    ?accessMapping <http://iot.linkeddata.es/def/wot-mappings#mapsResourcesFrom> ?endpoint .\n" + 
			"    ?endpoint <http://iot.linkeddata.es/def/wot#href> ?link .\n" + 
			"    ?mappings <http://iot.linkeddata.es/def/wot-mappings#predicate> ?predicate .\n" + 
			"    ?mappings <http://iot.linkeddata.es/def/wot-mappings#key> ?key .\n" + 
			"}";
	
	public static final String queryNumberOfThingsPerTypeAdapter = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"select ?type (count(distinct ?subject) as ?count) where { \n" + 
			"	?subject a wot:Thing .\n" + 
			"    ?subject core:represents ?object.\n" + 
			"    ?object a ?type .\n" + 
			"    FILTER(regex(str(?type), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) ) .\n" + 
			"} GROUP BY ?type ";

	public static final String queryNumberOfThingsPerObservation = "prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"prefix foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix sosa: <http://www.w3.org/ns/sosa/>\n" + 
			"prefix geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" + 
			"prefix ssn: <http://www.w3.org/ns/ssn/>\n" + 
			"prefix adp: <http://iot.linkeddata.es/def/adapters#> \n" + 
			"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" + 
			"prefix map: <http://iot.linkeddata.es/def/wot-mappings#> \n" + 
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"select distinct ?type (count(distinct ?thing) as ?count) where {\n" + 
			"    ?thing a wot:Thing .   \n" + 
			"    ?thing wot:providesInteractionPattern ?pattern .\n" + 
			"	?pattern sosa:observes ?type .\n" + 
			"} GROUP BY ?type";
	

	// *** 
	// ** 	ORGANIZATIONS QUERIES
	// ***
	
	
	
	
	public static final String queryOrganizationThings = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"\n" + 
			"select distinct ?organisationName ?organisationID ?type where { \n" + 
			"	?organisation a core:Agent .\n" + 
			"    ?organisation foaf:name ?organisationName .\n" + 
			"    OPTIONAL {?organisation foaf:openid ?organisationID .}\n" + 
			"    OPTIONAL{ ?organisation core:owns ?thing .\n" + 
			"    ?thing core:represents ?object .\n" + 
			"    ?object a ?type .\n" + 
			"   	FILTER(regex(str(?type), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?type), \"^http://iot.linkeddata.es/def/core#Service$\" ) ) .}\n" + 
			"}\n" + 
			"" ;
	
	public static final String queryOrganizationList = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"PREFIX wot: <http://xmlns.com/wot/0.1/>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"\n" + 
			"select distinct ?organiastions where { \n" + 
			"   	?organiastions a core:Agent.\n" + 
			"} \n" + 
			"";
	
	public static final String queryOrganizationName = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"PREFIX wot: <http://xmlns.com/wot/0.1/>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"\n" + 
			"select distinct ?name where { \n" + 
			"   	?organiastions a core:Agent.\n" + 
			"   	?organiastions foaf:name ?name.\n" + 
			"} \n" + 
			"";
	
	
	
	
	public static final String queryOrganisationContractsPetitioner = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX : <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"\n" + 
			"select distinct (count(distinct ?contractId) as ?count) where { \n" + 
			"	?contract a core:Contract .\n" + 
			"    ?contract core:contractId ?contractId .\n" + 
			"    ?contract core:requestedService ?requestedService .\n" + 
			"    ?requestedService core:represents ?requestedServiceObject .\n" + 
			"    ?requestedServiceObject a ?requestedServiceObjectType .\n" + 
			"    ?contract core:petitionerItemInvolved ?itemInvolved .\n" + 
			"    ?itemInvolved core:represents ?itemInvolvedObject .\n" + 
			"    ?itemInvolvedObject a ?itemInvolvedObjectType .\n" + 
			"    ?contract core:serviceOwner ?organisationOwner .\n" + 
			"    ?contract core:servicePetitioner ?organisationPetitioner .\n" + 
			"    OPTIONAL{ # Owner\n" + 
			"    ?organisationOwner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationOwner foaf:name ?organisationOwnerName .\n" + 
			"    }\n" + 
			"     OPTIONAL{# Petitioner\n" + 
			"    ?organisationPetitioner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationPetitioner foaf:name ?organisationPetitionerName .\n" + 
			"    }\n" + 
			"    FILTER(regex(str(?organisationPetitionerName), \"#Petitioner#\" )) .\n" + 
			"    \n" + 
			"      FILTER( (regex(str(?requestedServiceObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?requestedServiceObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) && (regex(str(?itemInvolvedObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?itemInvolvedObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) ).\n" + 
			"  \n" + 
			"}\n" + 
			"";
	
	public static final String queryOrganisationContractsOwner = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX : <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"\n" + 
			"select distinct (count(distinct ?contractId) as ?count) where { \n" + 
			"	?contract a core:Contract .\n" + 
			"    ?contract core:contractId ?contractId .\n" + 
			"    ?contract core:requestedService ?requestedService .\n" + 
			"    ?requestedService core:represents ?requestedServiceObject .\n" + 
			"    ?requestedServiceObject a ?requestedServiceObjectType .\n" + 
			"    ?contract core:petitionerItemInvolved ?itemInvolved .\n" + 
			"    ?itemInvolved core:represents ?itemInvolvedObject .\n" + 
			"    ?itemInvolvedObject a ?itemInvolvedObjectType .\n" + 
			"    ?contract core:serviceOwner ?organisationOwner .\n" + 
			"    ?contract core:servicePetitioner ?organisationPetitioner .\n" + 
			"    OPTIONAL{ # Owner\n" + 
			"    ?organisationOwner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationOwner foaf:name ?organisationOwnerName .\n" + 
			"    }\n" + 
			"     OPTIONAL{# Petitioner\n" + 
			"    ?organisationPetitioner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationPetitioner foaf:name ?organisationPetitionerName .\n" + 
			"    }\n" + 
			"    FILTER(regex(str(?organisationOwnerName), \"#Petitioner#\" )) .\n" + 
			"    \n" + 
			"      FILTER( (regex(str(?requestedServiceObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?requestedServiceObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) && (regex(str(?itemInvolvedObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?itemInvolvedObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) ).\n" + 
			"  \n" + 
			"}\n" + 
			"";


	private static final String queryContracts = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"PREFIX core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX : <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"\n" + 
			"select distinct ?contractId ?requestedService ?itemInvolved ?organisationOwnerName ?organisationPetitionerName ?itemInvolvedObjectType ?requestedServiceObjectType where { \n" + 
			"	?contract a core:Contract .\n" + 
			"    ?contract core:contractId ?contractId .\n" + 
			"    ?contract core:requestedService ?requestedService .\n" + 
			"    ?requestedService core:represents ?requestedServiceObject .\n" + 
			"    ?requestedServiceObject a ?requestedServiceObjectType .\n" + 
			"    ?contract core:petitionerItemInvolved ?itemInvolved .\n" + 
			"    ?itemInvolved core:represents ?itemInvolvedObject .\n" + 
			"    ?itemInvolvedObject a ?itemInvolvedObjectType .\n" + 
			"    ?contract core:serviceOwner ?organisationOwner .\n" + 
			"    ?contract core:servicePetitioner ?organisationPetitioner .\n" + 
			"    OPTIONAL{ # Owner\n" + 
			"    ?organisationOwner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationOwner foaf:name ?organisationOwnerName .\n" + 
			"    }\n" + 
			"     OPTIONAL{# Petitioner\n" + 
			"    ?organisationPetitioner a <http://iot.linkeddata.es/def/core#Agent>.\n" + 
			"    ?organisationPetitioner foaf:name ?organisationPetitionerName .\n" + 
			"    }\n" + 
			"  \n" + 
			"    \n" + 
			"      FILTER( (regex(str(?requestedServiceObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?requestedServiceObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) && (regex(str(?itemInvolvedObjectType), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) || regex(str(?itemInvolvedObjectType),\"^http://iot.linkeddata.es/def/core#Service$\" ) ) ).\n" + 
			"  \n" + 
			"}\n" + 
			"";
}


