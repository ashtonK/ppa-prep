package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
public class Problem implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id @GeneratedValue
	private long id;


	private  long problemID;
	private  String problemDomain;
	private  String problemContents;
	private  String problemLevel;
	public Problem(){

	}
	public Problem(long problemID, String problemDomain, String problemContents, String problemLevel) {
		this.problemID = problemID;
		this.problemDomain = problemDomain;
		this.problemContents = problemContents;
		this.problemLevel = problemLevel;
	}

	public long getProblemID() {
		return problemID;
	}

	public String getProblemDomain() {
		return problemDomain;
	}

	public String getProblemContents() {
		return problemContents;
	}

	public String getProblemLevel() {
		return problemLevel;
	}



}
