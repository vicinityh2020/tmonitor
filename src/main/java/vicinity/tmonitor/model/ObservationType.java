package vicinity.tmonitor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ObservationType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String observationName;
	private Integer numberOfObservations;
	private Date timestamp;
	
	public ObservationType() {
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
	 * @return the observationName
	 */
	public String getObservationName() {
		return observationName;
	}

	/**
	 * @param observationName the observationName to set
	 */
	public void setObservationName(String observationName) {
		this.observationName = observationName;
	}

	/**
	 * @return the numberOfObservations
	 */
	public Integer getNumberOfObservations() {
		return numberOfObservations;
	}

	/**
	 * @param numberOfObservations the numberOfObservations to set
	 */
	public void setNumberOfObservations(Integer numberOfObservations) {
		this.numberOfObservations = numberOfObservations;
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
	

	
	
}
