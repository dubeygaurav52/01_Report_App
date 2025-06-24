package in.gaurav.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="CITIZEN_PLANS_INFO")
public class CitizenPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CITIZEN_ID")
	private Integer citizenId;
	
	@Column(name="CITIZEN_NAME")
	private String citizenName;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name="BENEFIT_AMOUNT")
	private Double benefitAmount;
	
	@Column(name="DENIAL_REASON")
	private String denialReason;
	
	@Column(name="TERMINATION_REASON")
	private String terminationReason;
	
	@Column(name="TERMINATED_DATE")
	private LocalDate terminatedDate;
	
	@Column(name="GENDER")
	private String gender;
	
}
