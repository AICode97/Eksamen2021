/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.BreedDTO;
import fetch.BreedFetcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;

/**
 *
 * @author Bruger
 */
public class BreedFacade {
    private static EntityManagerFactory emf;
    private static BreedFacade instance;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    
    
    private BreedFacade() {
    }

    public static BreedFacade getDogFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BreedFacade();

        }
        return instance;

    }
  
     
}
