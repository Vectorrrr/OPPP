package com.vanya.controller;

import com.vanya.auth.EmailSender;
import com.vanya.model.UnconfirmedUser;
import com.vanya.model.User;
import com.vanya.remote.data.access.RemoteDataAccessFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Date;

import static com.vanya.model.UnconfirmedUser.createUnconfirmedUser;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
@RestController
public class AuthController {
    @Autowired
    private RemoteDataAccessFacade remoteDataAccessFacade;
    @Autowired
    private EmailSender emailSender;

    @RequestMapping(value = "confirmationRegistration/{id}",method = GET)
    public String confirmUser(@PathVariable("id") int id){

        UnconfirmedUser uncUser = remoteDataAccessFacade.getUnconfirmedUser(id);
        if(uncUser==null){
            return "Not fonund";
        }

        remoteDataAccessFacade.deleteUnconfirmedUser(id);
        if(uncUser.getLastDate().compareTo(new Date())<0){
            return "Late";
        }
        remoteDataAccessFacade.saveUser(User.getUser(uncUser));
        return "<html><script>setTimeout(function(){window.location.href='http://localhost:8080';},1000); </script><h1>Регистрация  подтверждена</h1></html>";
    }

    @RequestMapping(value="/addUser",method = POST, consumes = "application/json")
    public void addUser(@RequestBody User user) throws MessagingException {
        UnconfirmedUser unconfirmedUser = createUnconfirmedUser(user);
        remoteDataAccessFacade.saveUnconfirmedUser(unconfirmedUser);
        emailSender.sendEmail(unconfirmedUser);
    }
}
