package vicinity.tmonitor.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThingType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String typeName;
	private Integer number;
	private Date timestamp;
	
	public ThingType() {
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the numberP
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param numberP the numberP to set
	 */
	public void setNumber(Integer numberP) {
		this.number = numberP;
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
