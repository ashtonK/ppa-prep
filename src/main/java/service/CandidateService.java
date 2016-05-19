package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.CandidateInfo;

public class CandidateService {

	public CandidateService(){

	}
	public List<HashMap<String, String>> getCandidateList(){
		List<HashMap<String,String>> resultlist = new ArrayList<HashMap<String,String>>();
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Query q1=em.createQuery("SELECT p FROM CandidateInfo p");
		List<CandidateInfo> results = q1.getResultList();
		em.close();
		emf.close();
		for(CandidateInfo candidate : results){
			resultlist.add(this.candidate2EntryList(candidate));
		}
		System.out.println("Returning Candidate List");
		return resultlist;
	}
	private HashMap<String,String> candidate2EntryList(CandidateInfo candidate){
		HashMap<String,String> candidateHash = new HashMap<String,String>();
		candidateHash.put("firstName", candidate.getFirstName());
		candidateHash.put("lastName", candidate.getLastName());
		candidateHash.put("loginID", candidate.getLoginID());
		candidateHash.put("problem", ""+candidate.getProblem());
		candidateHash.put("compStatus", candidate.getCompStatus());
		candidateHash.put("evalStatus", candidate.getEvalStatus());
		return candidateHash;
	}
	public CandidateInfo get1Candidate(String loginID) throws NoResultException{
		System.out.println("User Requested : "+loginID);
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q1=em.createQuery("SELECT p FROM CandidateInfo p WHERE loginID=\""+loginID+"\"");
		CandidateInfo result =(CandidateInfo) q1.getSingleResult();
		em.close();
		emf.close();
		return result;
	}
	public void addCandidate(CandidateInfo candidate){

		System.out.println("Adding Candidate");
		CandidateInfo newCandidate= new CandidateInfo(candidate.getFirstName(),candidate.getLastName(),candidate.getLoginID(),candidate.getProblem(),
				candidate.getCompStatus(),candidate.getEvalStatus());
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(newCandidate);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	public void deleteCandidate(String candidateID){
		System.out.println("Deleting Candidate: "+candidateID);
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
		EntityManager em = emf.createEntityManager();
		System.out.println("."+candidateID+".");

		em.getTransaction().begin();
		Query q1=em.createQuery("SELECT p FROM CandidateInfo p WHERE loginID="+candidateID);
		CandidateInfo deletingCandidate = (CandidateInfo) q1.getSingleResult();
		em.remove(deletingCandidate);
		System.out.println("deleted");
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	public void updateCandidate(CandidateInfo candidate){
		if(!ExistsInDB(candidate.getLoginID())){
			System.out.println("Error: Candidate Does not exist in Database");
		}
		else{
			System.out.println("updating Candidate: "+candidate.getLoginID());
			EntityManagerFactory emf =
					Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			String candidateID=candidate.getLoginID();
			Query q1=em.createQuery("SELECT p FROM CandidateInfo p WHERE loginID=\""+candidateID+"\"");

			CandidateInfo updateCandidate = (CandidateInfo) q1.getSingleResult();

			updateCandidate.setFirstName(candidate.getFirstName());
			updateCandidate.setLastName(candidate.getLastName());
			updateCandidate.setProblem(candidate.getProblem());
			updateCandidate.setCompStatus(candidate.getCompStatus());
			updateCandidate.setEvalStatus(candidate.getEvalStatus());

			System.out.println("Candidate: "+candidate.getLoginID()+ " is Updated");
			em.getTransaction().commit();
			em.close();
			emf.close();
		}}

	private boolean ExistsInDB(String id){
		return true;
	}

}
