package com.vanya.controller;

import com.vanya.model.Jalousie;
import com.vanya.remote.data.access.RemoteDataAccessFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@RestController
public class JalousieController {
    @Autowired
    private RemoteDataAccessFacade dataAccessFacade;

    @RequestMapping(value = "/user/{userId}/addJalousie", method = POST, consumes = "application/json")
    public void addJalousie(@PathVariable("userId") long userId, @RequestBody Jalousie jalousie){
        dataAccessFacade.addJalousieForUser(userId, jalousie);
    }

    @RequestMapping(value = "/user/{userId}/jalousie/{id}", method = DELETE)
    public void removeJalousie(@PathVariable("userId") long userId, @PathVariable("id") long jalId){
        dataAccessFacade.removeJalousie(userId,jalId);
    }

    @RequestMapping(value = "/user/{userId}/jalousies",method = GET,produces = "application/json")
    public List<Jalousie> getJalousies(@PathVariable("userId") long userId){
        return dataAccessFacade.getAllJalousies(userId);
    }
    @RequestMapping(value = "/user/{userId}/jalousie/{id}", method = PUT, consumes = "application/json")
    public void  changeJalousies(@PathVariable("userId") long userId,
                                 @PathVariable("id") long id,
                                 @RequestBody Jalousie jalousie){
        dataAccessFacade.changeJalousies(userId,id,jalousie);
    }

}
