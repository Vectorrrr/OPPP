package com.vanya.remote.data.access;

import com.vanya.data.access.api.DataAccessFacade;
import com.vanya.model.Jalousie;
import com.vanya.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@Component
public class RemoteDataAccessFacade implements DataAccessFacade {
    //todo make this config with property
    private final String uri = "http://localhost:8081/";

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void deleteUser(long id) {
        restTemplate.delete(uri + "/user/" + id);
    }

    @Override
    public void saveUser(User user) {
        restTemplate.postForLocation(uri + "addUser", user, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) restTemplate.getForEntity(uri + "users", List.class).getBody();
    }

    @Override
    public User getUser(long id) {
        return restTemplate.getForEntity(uri + "/user/" + id, User.class).getBody();
    }

    @Override
    public void changeUser(User user, long id) {
        restTemplate.put(uri+"/user/"+id,user,User.class);
    }

    @Override
    public void addJalousieForUser(long id, Jalousie jalousie) {
        restTemplate.postForLocation(uri + "/user/"+id+"/addJalousie", jalousie, Jalousie.class);
    }

    @Override
    public void removeJalousie(long userId, long id) {
        restTemplate.delete(uri + "/user/" + userId+"/jalousie/"+id);
    }

    @Override
    public List<Jalousie> getAllJalousies(long userId) {
        return (List<Jalousie>) restTemplate.getForEntity(uri +"/user/"+userId+"/jalousies" , List.class).getBody();
    }

    @Override
    public void changeJalousies(long userId, long jalId, Jalousie jalousie) {
        restTemplate.put(uri+"/user/"+userId+"/jalousie/"+jalId,jalousie,Jalousie.class);
    }
}
