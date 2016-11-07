package com.vanya.controller;

import com.vanya.model.User;
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
public class UserController {
    @Autowired
    private RemoteDataAccessFacade dataAccessFacade;

    @RequestMapping(value = "/users",method = GET)
    public List<User> getUsers(){
        return dataAccessFacade.getAllUsers();
    }

    @RequestMapping(value="/addUser",method = POST, consumes = "application/json")
    public void addUser(@RequestBody User user){
        //todo add validate
        dataAccessFacade.saveUser(user);
    }

    @RequestMapping(value="/user/{id}",method = DELETE,consumes = "application/json")
    public void removeUser(@PathVariable("id") long id){
        dataAccessFacade.deleteUser(id);
    }

    @RequestMapping(value = "user/{id}",method = GET,produces = "application/json")
    public User getUser(@PathVariable("id") long id){
        return dataAccessFacade.getUser(id);
    }
    @RequestMapping(value = "/user/{id}", consumes = "application/json", method = PUT)
    public void changeUser(@PathVariable("id") long id,@RequestBody User user) {
        //todo add  validate
        dataAccessFacade.changeUser(user,id);
    }


}
