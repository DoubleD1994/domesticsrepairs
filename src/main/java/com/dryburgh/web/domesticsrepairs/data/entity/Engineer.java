package com.dryburgh.web.domesticsrepairs.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ENGINEER")
public class Engineer {

	@Id
	@Column(name="ENGINEER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long engineerId;
	
	@Column(name="NAME", nullable=false)
	private String engineerName;
	
	@Column(name="EMAIL", nullable=false, unique=true)
	private String engineerEmail;
	
	@Column(name="PHONE_NUMBER", nullable=false)
	private String engineerPhoneNumber;
	
	@Column(name="PASSWORD", nullable=false)
	private String engineerPassword;

	public long getEngineerId() {
		return this.engineerId;
	}
	
	public void setEngineerId(long engineerId) {
		this.engineerId = engineerId;
	}
	
	public String getEngineerName() {
		return engineerName;
	}

	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}

	public String getEngineerEmail() {
		return engineerEmail;
	}

	public void setEngineerEmail(String engineerEmail) {
		this.engineerEmail = engineerEmail;
	}

	public String getEngineerPhoneNumber() {
		return engineerPhoneNumber;
	}

	public void setEngineerPhoneNumber(String engineerPhoneNumber) {
		this.engineerPhoneNumber = engineerPhoneNumber;
	}

	public String getEngineerPassword() {
		return engineerPassword;
	}

	public void setEngineerPassword(String engineerPassword) {
		this.engineerPassword = engineerPassword;
	}

	@Override
	public String toString() {
		return "Engineer [engineerId=" + engineerId + ", engineerName=" + engineerName + ", engineerEmail="
				+ engineerEmail + ", engineerPhoneNumber=" + engineerPhoneNumber + ", engineerPassword="
				+ engineerPassword + "]";
	}
}
