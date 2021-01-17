/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Bruger
 */
@Entity

public class Searches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    Date dateTime;
    String breedName;

    public Searches() {
    }

    public Searches(String breedName) {
      
        dateTime = new Date();
        this.breedName = breedName;
    }

    public Date getDate() {
        return dateTime;
    }

    public void setDate(String date) {
        this.dateTime = new Date();
    }

    public String getName() {
        return breedName;
    }

    public void setName(String breedName) {
        this.breedName = breedName;
    }
    
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
}
