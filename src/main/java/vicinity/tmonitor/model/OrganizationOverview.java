package vicinity.tmonitor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrganizationOverview {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Date timestamp;
	private String organisationId;
	
	private Integer things;
	private Integer thingsAdapters;
	private Integer thingsServices;
	private Integer thingsInteroperable;
	
	// Context
	private Integer thingsWithBuilding;
	private Integer thingsWithBuildingComplete;
	private Integer thingsWithRoom;
	private Integer thingsWithCity;
	private Integer thingsWithCityComplete;
	private Integer thingsWithCountry;
	private Integer thingsWithCountryComplete;
	
	private Integer numberOfContractsOwner;
	private Integer numberOfContractsInvolved;
	
	
	public OrganizationOverview() {
		timestamp = new Date();
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the things
	 */
	public Integer getThings() {
		return things;
	}
	/**
	 * @param things the things to set
	 */
	public void setThings(Integer things) {
		this.things = things;
	}
	/**
	 * @return the thingsAdapters
	 */
	public Integer getThingsAdapters() {
		return thingsAdapters;
	}
	/**
	 * @param thingsAdapters the thingsAdapters to set
	 */
	public void setThingsAdapters(Integer thingsAdapters) {
		this.thingsAdapters = thingsAdapters;
	}
	/**
	 * @return the thingsServices
	 */
	public Integer getThingsServices() {
		return thingsServices;
	}
	/**
	 * @param thingsServices the thingsServices to set
	 */
	public void setThingsServices(Integer thingsServices) {
		this.thingsServices = thingsServices;
	}
	/**
	 * @return the thingsInteroperable
	 */
	public Integer getThingsInteroperable() {
		return thingsInteroperable;
	}
	/**
	 * @param thingsInteroperable the thingsInteroperable to set
	 */
	public void setThingsInteroperable(Integer thingsInteroperable) {
		this.thingsInteroperable = thingsInteroperable;
	}

	/**
	 * @return the organisationId
	 */
	public String getOrganisationId() {
		return organisationId;
	}

	/**
	 * @param organisationId the organisationId to set
	 */
	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	/**
	 * @return the thingsWithBuilding
	 */
	public Integer getThingsWithBuilding() {
		return thingsWithBuilding;
	}

	/**
	 * @param thingsWithBuilding the thingsWithBuilding to set
	 */
	public void setThingsWithBuilding(Integer thingsWithBuilding) {
		this.thingsWithBuilding = thingsWithBuilding;
	}

	/**
	 * @return the thingsWithBuildingComplete
	 */
	public Integer getThingsWithBuildingComplete() {
		return thingsWithBuildingComplete;
	}

	/**
	 * @param thingsWithBuildingComplete the thingsWithBuildingComplete to set
	 */
	public void setThingsWithBuildingComplete(Integer thingsWithBuildingComplete) {
		this.thingsWithBuildingComplete = thingsWithBuildingComplete;
	}

	/**
	 * @return the thingsWithRoom
	 */
	public Integer getThingsWithRoom() {
		return thingsWithRoom;
	}

	/**
	 * @param thingsWithRoom the thingsWithRoom to set
	 */
	public void setThingsWithRoom(Integer thingsWithRoom) {
		this.thingsWithRoom = thingsWithRoom;
	}

	/**
	 * @return the thingsWithCity
	 */
	public Integer getThingsWithCity() {
		return thingsWithCity;
	}

	/**
	 * @param thingsWithCity the thingsWithCity to set
	 */
	public void setThingsWithCity(Integer thingsWithCity) {
		this.thingsWithCity = thingsWithCity;
	}

	/**
	 * @return the thingsWithCityComplete
	 */
	public Integer getThingsWithCityComplete() {
		return thingsWithCityComplete;
	}

	/**
	 * @param thingsWithCityComplete the thingsWithCityComplete to set
	 */
	public void setThingsWithCityComplete(Integer thingsWithCityComplete) {
		this.thingsWithCityComplete = thingsWithCityComplete;
	}

	/**
	 * @return the thingsWithCountry
	 */
	public Integer getThingsWithCountry() {
		return thingsWithCountry;
	}

	/**
	 * @param thingsWithCountry the thingsWithCountry to set
	 */
	public void setThingsWithCountry(Integer thingsWithCountry) {
		this.thingsWithCountry = thingsWithCountry;
	}

	/**
	 * @return the thingsWithCountryComplete
	 */
	public Integer getThingsWithCountryComplete() {
		return thingsWithCountryComplete;
	}

	/**
	 * @param thingsWithCountryComplete the thingsWithCountryComplete to set
	 */
	public void setThingsWithCountryComplete(Integer thingsWithCountryComplete) {
		this.thingsWithCountryComplete = thingsWithCountryComplete;
	}

	/**
	 * @return the numberOfContractsOwner
	 */
	public Integer getNumberOfContractsOwner() {
		return numberOfContractsOwner;
	}

	/**
	 * @param numberOfContractsOwner the numberOfContractsOwner to set
	 */
	public void setNumberOfContractsOwner(Integer numberOfContractsOwner) {
		this.numberOfContractsOwner = numberOfContractsOwner;
	}

	/**
	 * @return the numberOfContractsInvolved
	 */
	public Integer getNumberOfContractsInvolved() {
		return numberOfContractsInvolved;
	}

	/**
	 * @param numberOfContractsInvolved the numberOfContractsInvolved to set
	 */
	public void setNumberOfContractsInvolved(Integer numberOfContractsInvolved) {
		this.numberOfContractsInvolved = numberOfContractsInvolved;
	}


	

	
}
