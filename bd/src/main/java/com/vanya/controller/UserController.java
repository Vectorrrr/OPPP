package com.vanya.controller;

import com.vanya.data.access.api.DataAccessFacade;
import com.vanya.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@RestController
public class UserController {
    @Autowired
    private DataAccessFacade dataAccessFacade;

    @RequestMapping(value = "/test", method = GET)
    public User getUser(@RequestParam(value = "name", defaultValue = "Vanya") String name) {
        return new User();
    }

    @RequestMapping(value = "user/{id}", method = GET, produces = "application/json")
    public User getUser(@PathVariable("id") long id) {
        return dataAccessFacade.getUser(id);
    }

    @RequestMapping(value = "/addUser",
            consumes = "application/json",
            method = POST)
    public User addUser(@RequestBody User user) {
        //todo add validation
        dataAccessFacade.saveUser(user);
        return user;
    }

    @RequestMapping(value = "/user/{id}", consumes = "application/json", method = PUT)
    public void changeUser(@PathVariable("id") long id,@RequestBody User user) {
        //todo add  validate
        dataAccessFacade.changeUser(user,id);
    }


    @RequestMapping(value = "/users", method = GET)
    public List<User> getUsers() {
        return dataAccessFacade.getAllUsers();
    }

    @RequestMapping(value = "/user/{id}", method = DELETE)
    public void deleteUser(@PathVariable("id") long id) {
        dataAccessFacade.deleteUser(id);
    }
}
