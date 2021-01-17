/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Searches;
import static java.util.Collections.list;
import java.util.List;

import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 *
 * @author Bruger
 */
public class SearchFacade {

    private static EntityManagerFactory emf;
    private static SearchFacade instance;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService threadPool = HttpUtils.getThreadPool();

    private SearchFacade() {

    }

    public static SearchFacade getSearchFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SearchFacade();

        }
        return instance;

    }

    public Long getSearches(String breed) {
      EntityManager em = emf.createEntityManager();
      Long list = null; 
      try{
          em.getTransaction().begin();
          Query query = em.createQuery("SELECT COUNT(s) FROM Searches s WHERE s.breedName =:breed", Searches.class)
          .setParameter("breed", breed);
          list = (Long) query.getSingleResult();
          em.getTransaction().commit();
          
      }finally{
          em.close();
      }
      
         
        
        return list;
    }

}
