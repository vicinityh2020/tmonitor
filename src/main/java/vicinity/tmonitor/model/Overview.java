package vicinity.tmonitor.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Overview {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Integer Things;  
	
	
	private Integer thingsWithAdapters; 
	private Integer thingsWithServices; 
	
	private Date timestamp;
	
	// Context
	private Integer thingsWithOrganizations;  
	private Integer thingsWithOrganizationsComplete; 
	private Integer thingsWithCities; 
	private Integer thingsWithCitiesComplete; 
	private Integer thingsWithCountries; 
	private Integer thingsWithCountriesComplete; 
	private Integer thingsWithBuildings;  
	private Integer thingsWithBuildingsComplete;  
	private Integer thingsWithBuildingSpaces; 

	
	
	// Int. Patterns
	private Integer thingsWithInteractionPatters;
	private Integer thingsWithInteractionPattersObserving;
	
	private Integer thingsWithInteractionPattersReadable;
	private Integer thingsWithInteractionPattersWritable;
	private Integer thingsWithInteractionPattersAccesible;
	
	private Integer thingsWithInteractionPattersProperty;
	private Integer thingsWithInteractionPattersPropertyAndMeasure;
	private Integer thingsWithInteractionPattersEvent;
	private Integer thingsWithInteractionPattersAction;
	
	// Accesible things with Semantic Interoperability
	private Integer thingsWithMappings;
	
	public Overview() {
		timestamp = new Date();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the things
	 */
	public Integer getThings() {
		return Things;
	}

	/**
	 * @param things the things to set
	 */
	public void setThings(Integer things) {
		Things = things;
	}

	/**
	 * @return the thingsWithAdapters
	 */
	public Integer getThingsWithAdapters() {
		return thingsWithAdapters;
	}

	/**
	 * @param thingsWithAdapters the thingsWithAdapters to set
	 */
	public void setThingsWithAdapters(Integer thingsWithAdapters) {
		this.thingsWithAdapters = thingsWithAdapters;
	}

	/**
	 * @return the thingsWithServices
	 */
	public Integer getThingsWithServices() {
		return thingsWithServices;
	}

	/**
	 * @param thingsWithServices the thingsWithServices to set
	 */
	public void setThingsWithServices(Integer thingsWithServices) {
		this.thingsWithServices = thingsWithServices;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the thingsWithOrganizations
	 */
	public Integer getThingsWithOrganizations() {
		return thingsWithOrganizations;
	}

	/**
	 * @param thingsWithOrganizations the thingsWithOrganizations to set
	 */
	public void setThingsWithOrganizations(Integer thingsWithOrganizations) {
		this.thingsWithOrganizations = thingsWithOrganizations;
	}

	/**
	 * @return the thingsWithOrganizationsComplete
	 */
	public Integer getThingsWithOrganizationsComplete() {
		return thingsWithOrganizationsComplete;
	}

	/**
	 * @param thingsWithOrganizationsComplete the thingsWithOrganizationsComplete to set
	 */
	public void setThingsWithOrganizationsComplete(Integer thingsWithOrganizationsComplete) {
		this.thingsWithOrganizationsComplete = thingsWithOrganizationsComplete;
	}

	/**
	 * @return the thingsWithCities
	 */
	public Integer getThingsWithCities() {
		return thingsWithCities;
	}

	/**
	 * @param thingsWithCities the thingsWithCities to set
	 */
	public void setThingsWithCities(Integer thingsWithCities) {
		this.thingsWithCities = thingsWithCities;
	}

	/**
	 * @return the thingsWithCitiesComplete
	 */
	public Integer getThingsWithCitiesComplete() {
		return thingsWithCitiesComplete;
	}

	/**
	 * @param thingsWithCitiesComplete the thingsWithCitiesComplete to set
	 */
	public void setThingsWithCitiesComplete(Integer thingsWithCitiesComplete) {
		this.thingsWithCitiesComplete = thingsWithCitiesComplete;
	}

	/**
	 * @return the thingsWithCountries
	 */
	public Integer getThingsWithCountries() {
		return thingsWithCountries;
	}

	/**
	 * @param thingsWithCountries the thingsWithCountries to set
	 */
	public void setThingsWithCountries(Integer thingsWithCountries) {
		this.thingsWithCountries = thingsWithCountries;
	}

	/**
	 * @return the thingsWithCountriesComplete
	 */
	public Integer getThingsWithCountriesComplete() {
		return thingsWithCountriesComplete;
	}

	/**
	 * @param thingsWithCountriesComplete the thingsWithCountriesComplete to set
	 */
	public void setThingsWithCountriesComplete(Integer thingsWithCountriesComplete) {
		this.thingsWithCountriesComplete = thingsWithCountriesComplete;
	}

	/**
	 * @return the thingsWithBuildings
	 */
	public Integer getThingsWithBuildings() {
		return thingsWithBuildings;
	}

	/**
	 * @param thingsWithBuildings the thingsWithBuildings to set
	 */
	public void setThingsWithBuildings(Integer thingsWithBuildings) {
		this.thingsWithBuildings = thingsWithBuildings;
	}

	/**
	 * @return the thingsWithBuildingsComplete
	 */
	public Integer getThingsWithBuildingsComplete() {
		return thingsWithBuildingsComplete;
	}

	/**
	 * @param thingsWithBuildingsComplete the thingsWithBuildingsComplete to set
	 */
	public void setThingsWithBuildingsComplete(Integer thingsWithBuildingsComplete) {
		this.thingsWithBuildingsComplete = thingsWithBuildingsComplete;
	}

	/**
	 * @return the thingsWithBuildingSpaces
	 */
	public Integer getThingsWithBuildingSpaces() {
		return thingsWithBuildingSpaces;
	}

	/**
	 * @param thingsWithBuildingSpaces the thingsWithBuildingSpaces to set
	 */
	public void setThingsWithBuildingSpaces(Integer thingsWithBuildingSpaces) {
		this.thingsWithBuildingSpaces = thingsWithBuildingSpaces;
	}

	/**
	 * @return the thingsWithInteractionPatters
	 */
	public Integer getThingsWithInteractionPatters() {
		return thingsWithInteractionPatters;
	}

	/**
	 * @param thingsWithInteractionPatters the thingsWithInteractionPatters to set
	 */
	public void setThingsWithInteractionPatters(Integer thingsWithInteractionPatters) {
		this.thingsWithInteractionPatters = thingsWithInteractionPatters;
	}

	/**
	 * @return the thingsWithInteractionPattersObserving
	 */
	public Integer getThingsWithInteractionPattersObserving() {
		return thingsWithInteractionPattersObserving;
	}

	/**
	 * @param thingsWithInteractionPattersObserving the thingsWithInteractionPattersObserving to set
	 */
	public void setThingsWithInteractionPattersObserving(Integer thingsWithInteractionPattersObserving) {
		this.thingsWithInteractionPattersObserving = thingsWithInteractionPattersObserving;
	}

	/**
	 * @return the thingsWithInteractionPattersReadable
	 */
	public Integer getThingsWithInteractionPattersReadable() {
		return thingsWithInteractionPattersReadable;
	}

	/**
	 * @param thingsWithInteractionPattersReadable the thingsWithInteractionPattersReadable to set
	 */
	public void setThingsWithInteractionPattersReadable(Integer thingsWithInteractionPattersReadable) {
		this.thingsWithInteractionPattersReadable = thingsWithInteractionPattersReadable;
	}

	/**
	 * @return the thingsWithInteractionPattersWritable
	 */
	public Integer getThingsWithInteractionPattersWritable() {
		return thingsWithInteractionPattersWritable;
	}

	/**
	 * @param thingsWithInteractionPattersWritable the thingsWithInteractionPattersWritable to set
	 */
	public void setThingsWithInteractionPattersWritable(Integer thingsWithInteractionPattersWritable) {
		this.thingsWithInteractionPattersWritable = thingsWithInteractionPattersWritable;
	}

	/**
	 * @return the thingsWithInteractionPattersAccesible
	 */
	public Integer getThingsWithInteractionPattersAccesible() {
		return thingsWithInteractionPattersAccesible;
	}

	/**
	 * @param thingsWithInteractionPattersAccesible the thingsWithInteractionPattersAccesible to set
	 */
	public void setThingsWithInteractionPattersAccesible(Integer thingsWithInteractionPattersAccesible) {
		this.thingsWithInteractionPattersAccesible = thingsWithInteractionPattersAccesible;
	}

	/**
	 * @return the thingsWithInteractionPattersProperty
	 */
	public Integer getThingsWithInteractionPattersProperty() {
		return thingsWithInteractionPattersProperty;
	}

	/**
	 * @param thingsWithInteractionPattersProperty the thingsWithInteractionPattersProperty to set
	 */
	public void setThingsWithInteractionPattersProperty(Integer thingsWithInteractionPattersProperty) {
		this.thingsWithInteractionPattersProperty = thingsWithInteractionPattersProperty;
	}

	/**
	 * @return the thingsWithInteractionPattersPropertyAndMeasure
	 */
	public Integer getThingsWithInteractionPattersPropertyAndMeasure() {
		return thingsWithInteractionPattersPropertyAndMeasure;
	}

	/**
	 * @param thingsWithInteractionPattersPropertyAndMeasure the thingsWithInteractionPattersPropertyAndMeasure to set
	 */
	public void setThingsWithInteractionPattersPropertyAndMeasure(Integer thingsWithInteractionPattersPropertyAndMeasure) {
		this.thingsWithInteractionPattersPropertyAndMeasure = thingsWithInteractionPattersPropertyAndMeasure;
	}

	/**
	 * @return the thingsWithInteractionPattersEvent
	 */
	public Integer getThingsWithInteractionPattersEvent() {
		return thingsWithInteractionPattersEvent;
	}

	/**
	 * @param thingsWithInteractionPattersEvent the thingsWithInteractionPattersEvent to set
	 */
	public void setThingsWithInteractionPattersEvent(Integer thingsWithInteractionPattersEvent) {
		this.thingsWithInteractionPattersEvent = thingsWithInteractionPattersEvent;
	}

	/**
	 * @return the thingsWithInteractionPattersAction
	 */
	public Integer getThingsWithInteractionPattersAction() {
		return thingsWithInteractionPattersAction;
	}

	/**
	 * @param thingsWithInteractionPattersAction the thingsWithInteractionPattersAction to set
	 */
	public void setThingsWithInteractionPattersAction(Integer thingsWithInteractionPattersAction) {
		this.thingsWithInteractionPattersAction = thingsWithInteractionPattersAction;
	}

	/**
	 * @return the thingsWithMappings
	 */
	public Integer getThingsWithMappings() {
		return thingsWithMappings;
	}

	/**
	 * @param thingsWithMappings the thingsWithMappings to set
	 */
	public void setThingsWithMappings(Integer thingsWithMappings) {
		this.thingsWithMappings = thingsWithMappings;
	}
	
	
	

	
}
