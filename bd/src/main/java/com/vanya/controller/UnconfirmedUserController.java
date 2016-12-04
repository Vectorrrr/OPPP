package com.vanya.controller;

import com.vanya.data.access.api.DataAccessFacade;
import com.vanya.data.access.impl.DataAccessFacadeMongoBDImpl;
import com.vanya.model.UnconfirmedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
@RestController
public class UnconfirmedUserController {
    @Autowired
    private DataAccessFacadeMongoBDImpl dataAccessFacade;

    @RequestMapping(value = "/unconfirmedUser/{id}", method = GET)
    public UnconfirmedUser getUnconfirmedUser(@PathVariable("id") long id) {
        return dataAccessFacade.getUnconfirmedUser(id);
    }

    @RequestMapping(value = "/unconfirmedUser/{id}",method = DELETE)
    public void removeUnconfirmedUser(@PathVariable("id") long id){
        dataAccessFacade.deleteUnconfirmedUser(id);
    }
    @RequestMapping(value = "/unconfirmedUser/addNew",method = POST)
    public void addNewUnconfirmedUser(@RequestBody UnconfirmedUser unconfirmedUser){
        dataAccessFacade.saveUnconfirmedUser(unconfirmedUser);
    }


}