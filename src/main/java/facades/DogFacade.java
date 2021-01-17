/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import dto.BreedDTO;
import entities.Searches;
import fetch.BreedFetcher;
import fetch.BreedImageFetcher;
import fetch.FactsFetcher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.HttpUtils;

/**
 *
 * @author Bruger
 */
public class DogFacade {
    private static EntityManagerFactory emf;
    private static DogFacade instance;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    
    
    public JSONObject getDog(String breed) throws MalformedURLException, IOException, ParseException {
       // EntityManager em = emf.createEntityManager();
        
        JSONObject obj = BreedFetcher.getBreedByName(breed);
        JSONObject facts = FactsFetcher.getFact();
        JSONObject img = BreedImageFetcher.getBreedImage(breed);
        //Searches search = new Searches(breed);
        
      /* em.getTransaction().begin();
       em.persist(search);
       em.getTransaction().commit();*/
        
        
        
        
        
        Object fact = facts.get("facts");
        
        
        obj.put("fact", fact);
        obj.put("img", img.get("image"));

        return obj;

    }
  
     
}
