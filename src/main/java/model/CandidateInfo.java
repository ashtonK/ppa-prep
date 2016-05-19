package model;


import java.io.Serializable;
import javax.persistence.*;

@Entity
public class CandidateInfo implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id @GeneratedValue
	private long id;

	private  int problem;
	private  String firstName;
	private  String lastName;
	private  String loginID;
	private  String compStatus;
	private  String evalStatus;

	public CandidateInfo(){
		
	}
	public CandidateInfo( String firstName,String lastName,String loginID,int problem,String compStatus, String evalStatus){
		this.firstName=firstName;
		this.lastName=lastName;
		this.problem=problem;
		this.loginID=loginID;
		this.compStatus=compStatus;
		this.evalStatus=evalStatus;
	}

	public long getId(){
		return id;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}

	public int getProblem() {
		return problem;
	}

	public String getLoginID() {
		return loginID;
	}

	public String getCompStatus() {
		return compStatus;
	}

	public String getEvalStatus() {
		return evalStatus;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setProblem(int problem) {
		this.problem = problem;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public void setCompStatus(String compStatus) {
		this.compStatus = compStatus;
	}

	public void setEvalStatus(String evalStatus) {
		this.evalStatus = evalStatus;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", loginID=" + loginID
				+ ", compStatus=" + compStatus + ", evalStatus=" + evalStatus + "]";
	}

}