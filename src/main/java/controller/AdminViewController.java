package controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.ws.rs.GET;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.CandidateInfo;
import service.CandidateService;
import service.ProblemService;

@RestController
public class AdminViewController {
	ProblemService problemService = new ProblemService();
	CandidateService candidateService =new CandidateService();

	/*
	 * Candidate Functionalities
	 */
	// Get request to return All Candidates
	@CrossOrigin
	@RequestMapping("/getcandidates")
	public List<HashMap<String, String>> returnCandidates() {
		return candidateService.getCandidateList();
	}
	// Get Request to return a single Candidate
	@CrossOrigin
	@RequestMapping(value="/getsinglecandidate", method =RequestMethod.GET)
	public ResponseEntity<CandidateInfo> greeting(@RequestParam(value="loginID") String loginID) {
		CandidateInfo resultCandidate= new CandidateInfo();
		try{
		resultCandidate=candidateService.get1Candidate(loginID);
		}
		catch(NoResultException nrEx){
			System.out.println("Entity Not Found");
			return new ResponseEntity<CandidateInfo>(resultCandidate,HttpStatus.OK);
		}
		return new ResponseEntity<CandidateInfo>(resultCandidate,HttpStatus.OK);
	}

	// Post Request to create a single Candidate
	@CrossOrigin
	@RequestMapping(value= "/addcandidate", method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<CandidateInfo> createCandidate(@RequestBody CandidateInfo candidate) {
		System.out.println("Adding Candidate: " + candidate.getFirstName());
		candidateService.addCandidate(candidate);
		return new ResponseEntity<CandidateInfo>(candidate, HttpStatus.CREATED);

	}
	//Post Request to Delete a single Candidate
	@CrossOrigin
	@RequestMapping(value= "/deletecandidate", method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Void> deleteCandidate(@RequestBody String candidateID) {
		System.out.println("Deleting Candidate with ID: " + candidateID );
		candidateService.deleteCandidate(candidateID);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	// Post Request to Update a single Candidate
	@CrossOrigin
	@RequestMapping(value= "/updatecandidate", method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<Void> updateCandidate(@RequestBody CandidateInfo candidate) {
		System.out.println("Updating Candidate with ID: " + candidate.getLoginID());
		candidateService.updateCandidate(candidate);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	@CrossOrigin
	@RequestMapping("/getproblems")
	public List<HashMap<String,String>> returnProblems() {

		System.out.println("Returning Problem List");

		return problemService.getProblemList();
	}

}