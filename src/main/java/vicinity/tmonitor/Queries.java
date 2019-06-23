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
	
	
	//
	
	public static final String queryOrganisationThingsTypeCount = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" + 
			"prefix wot: <http://iot.linkeddata.es/def/wot#>\n" + 
			"prefix core: <http://iot.linkeddata.es/def/core#>\n" + 
			"PREFIX s4bldg: <https://w3id.org/def/saref4bldg#>\n" + 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
			"PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" + 
			"PREFIX map: <http://iot.linkeddata.es/def/wot-mappings#>\n" + 
			"select distinct ?ownerName ?type (count(distinct ?thing) as ?count)  where { \n" + 
			"    ?thing a wot:Thing .\n" + 
			"    ?thing core:represents ?object .\n" + 
			"    ?object a ?typeName .\n" + 
			"    ?thing core:hasOwner ?owner .\n" + 
			"    ?owner foaf:name ?ownerName .\n" + 
			"  \n" + 
			"    FILTER(regex(str(?typeName), \"^http://iot.linkeddata.es/def/adapters#.*$\" ) ) .\n"
			+ " BIND(REPLACE(str(?typeName), \"http://iot.linkeddata.es/def/adapters#\", \"adp:\") AS ?type) ." + 
			"} GROUP BY ?ownerName ?type ORDER BY DESC(?ownerName)";


	// HERE
	public static String ALT_TEMPLATE = "{\n" + 
			"  \"dashboard\": {\n" + 
			"    \"id\": null,\n" + 
			"    \"uid\": null,\n" + 
			"    \"title\": \"ThingsInVICINITY\",\n" + 
			"    \"tags\": [ \"templated\" ],\n" + 
			"    \"timezone\": \"browser\",\n" + 
			"    \"schemaVersion\": 16,\n" + 
			"    \"version\": 0,\n" + 
			"	\"panels\": ["+
			//--- panels
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
			"        \"y\": 5\n" + 
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
			"        \"w\": 12,\n" + 
			"        \"x\": 12,\n" + 
			"        \"y\": 14\n" + 
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
			"          \"rawSql\": \"SELECT\\n  $__timeGroupAlias(timestamp,$__interval),\\n  avg(things_with_interaction_patters) AS \\\"Total interaction patterns\\\",\\n  things_with_interaction_patters_readable AS \\\"Readabale\\\",\\n  things_with_interaction_patters_writable AS \\\"Writable\\\",\\n  things_with_interaction_patters_observing AS \\\"With observations\\\"\\nFROM overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY 1\\nORDER BY $__timeGroup(timestamp,$__interval)\",\n" + 
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
			"        \"h\": 8,\n" + 
			"        \"w\": 24,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 22\n" + 
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
			"        \"y\": 30\n" + 
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
			"        \"y\": 30\n" + 
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
			"        \"h\": 9,\n" + 
			"        \"w\": 24,\n" + 
			"        \"x\": 0,\n" + 
			"        \"y\": 52\n" + 
			"      },\n" + 
			"      \"id\": 20,\n" + 
			"      \"links\": [],\n" + 
			"      \"pageSize\": 100,\n" + 
			"      \"scroll\": true,\n" + 
			"      \"showHeader\": true,\n" + 
			"      \"sort\": {\n" + 
			"        \"col\": 8,\n" + 
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
			"          \"decimals\": 0,\n" + 
			"          \"pattern\": \"/.*/\",\n" + 
			"          \"thresholds\": [],\n" + 
			"          \"type\": \"number\",\n" + 
			"          \"unit\": \"short\"\n" + 
			"        }\n" + 
			"      ],\n" + 
			"      \"targets\": [\n" + 
			"        {\n" + 
			"          \"format\": \"table\",\n" + 
			"          \"group\": [\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"timestamp\"\n" + 
			"              ],\n" + 
			"              \"type\": \"column\"\n" + 
			"            },\n" + 
			"            {\n" + 
			"              \"params\": [\n" + 
			"                \"name\"\n" + 
			"              ],\n" + 
			"              \"type\": \"column\"\n" + 
			"            }\n" + 
			"          ],\n" + 
			"          \"metricColumn\": \"none\",\n" + 
			"          \"rawQuery\": true,\n" + 
			"          \"rawSql\": \"SELECT DISTINCT\\n  name AS \\\"Organisation\\\",\\n  things_adapters AS \\\"Adapters\\\",\\n  things_services AS \\\"Services\\\",\\n  things AS \\\"Things\\\",\\n  things_with_building AS \\\"Buildings\\\",\\n  things_with_room AS \\\"Rooms\\\",\\n  things_with_city AS \\\"Cities\\\",\\n  things_with_country AS \\\"Country\\\",\\n  things_interoperable AS \\\"Things Interoperable\\\"\\nFROM organization_overview\\nWHERE\\n  $__timeFilter(timestamp)\\nGROUP BY timestamp, name\\nORDER BY timestamp\",\n" + 
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
			"                  \"things_with_building\"\n" + 
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
			"                  \"things_with_room\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Rooms\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            [\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"things_with_city\"\n" + 
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
			"                  \"things_with_country\"\n" + 
			"                ],\n" + 
			"                \"type\": \"column\"\n" + 
			"              },\n" + 
			"              {\n" + 
			"                \"params\": [\n" + 
			"                  \"Country\"\n" + 
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
			"                  \"Things Interoperable\"\n" + 
			"                ],\n" + 
			"                \"type\": \"alias\"\n" + 
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
			"      \"timeFrom\": null,\n" + 
			"      \"timeShift\": null,\n" + 
			"      \"title\": \"Organisations in VICINITY\",\n" + 
			"      \"transform\": \"table\",\n" + 
			"      \"type\": \"table\"\n" + 
			"    }\n" + 
			//--- last pannel
			"	]"+
			"  },\n" + 
			"  \"folderId\": 0,\n" + 
			"  \"overwrite\": true\n" + 
			"}";

}


