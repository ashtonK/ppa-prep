package service;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Problem;


public class ProblemService {

	public ProblemService(){

	}

	public List<HashMap<String, String>> getProblemList(){
		List<HashMap<String,String>> resultlist = new ArrayList<HashMap<String,String>>();
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("$objectdb/db/candidates.odb");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Query q1=em.createQuery("SELECT p FROM Problem p");
		List<Problem> results = q1.getResultList();
		em.close();
		emf.close();
		for(Problem problem : results){
			resultlist.add(this.problem2EntryList(problem));
		}
			System.out.println("Returning Candidate List");
				return resultlist;
	}
	private HashMap<String,String> problem2EntryList(Problem problem){
		HashMap<String,String> problemHash = new HashMap<String,String>();
		problemHash.put("problemID", problem.getProblemID()+"");
		problemHash.put("problemDomain", problem.getProblemDomain());
		problemHash.put("problemContents", problem.getProblemContents());
		problemHash.put("problemLevel", problem.getProblemLevel());
				return problemHash;
	}
	



}
