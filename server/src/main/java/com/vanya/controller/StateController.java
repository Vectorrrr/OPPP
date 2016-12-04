package com.vanya.controller;

import com.vanya.model.Jalousie;
import com.vanya.model.State;
import com.vanya.remote.data.access.RemoteDataAccessFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;
/**
 * Created by Hladush Ivan
 * on 07.11.16.
 */
@RestController
public class StateController {
    @Autowired
    private RemoteDataAccessFacade dataAccessFacade;

    @RequestMapping(value = "user/{userId}/jalousie/{id}/addState" ,consumes = "application/json",method=POST)
    public void addState(@PathVariable("userId") long userId,@PathVariable("id") long jalId, @RequestBody State state){
        dataAccessFacade.addStateForJalousie(userId,jalId,state);
    }
    @RequestMapping(value ="/user/{userId}/jalousie/{id}/state/{stateId}",method = DELETE)
    public void deleteState(@PathVariable("userId") long userId,
                            @PathVariable("id") long id,
                            @PathVariable("stateId") long stateId){
        dataAccessFacade.removeState(userId,id,stateId);
    }
    @RequestMapping(value ="/user/{userId}/jalousie/{id}/states",method = GET)
    public List<State> getAllStates(@PathVariable("userId") long userId,
                                    @PathVariable("id") long id){
        return dataAccessFacade.getAllStates(userId,id);
    }
    @RequestMapping(value ="/user/{userId}/jalousie/{id}/currantState",method = GET)
    public State getCurrentState(@PathVariable("userId") long userId,
                                 @PathVariable("id") long id){
        return dataAccessFacade.currentState(userId,id);
    }
}
