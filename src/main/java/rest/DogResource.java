/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import facades.DogFacade;
import fetch.BreedFetcher;
import fetch.BreedImageFetcher;

import fetch.FactsFetcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("dog")
public class DogResource {

    private static final ExecutorService threadPool = HttpUtils.getThreadPool();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    //private static  dogBreed = dogBreed..getZomatoFetcher(GSON, threadPool);
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    //private static final ZomatoFacade FACADE = ZomatoFacade.getZomatoFacade(EMF);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BreedResource
     */
    public DogResource() {
    }

    /**
     * Retrieves representation of an instance of rest.BreedResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/breeds")
    public String getAllbreeds() throws IOException, MalformedURLException, ParseException {

        return GSON.toJson(BreedFetcher.getAllBreeds());

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/img{breed}")
    public String image(@PathParam("breed") String breed) throws IOException, MalformedURLException, ParseException {

        return GSON.toJson(BreedImageFetcher.getBreedImage(breed));

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/fact")
    public String fact() throws IOException, MalformedURLException, ParseException {

        return GSON.toJson(FactsFetcher.getFact());

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/breeds{breed}")
    public String getBreed(@PathParam("breed") String breed) throws IOException, MalformedURLException, ParseException {

        return GSON.toJson(BreedFetcher.getBreedByName(breed));

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{breed}")
    public String getDog(@PathParam("breed") String breed) throws IOException, MalformedURLException, ParseException {
        DogFacade df = new DogFacade();
        return GSON.toJson(df.getDog(breed));
    }
}
