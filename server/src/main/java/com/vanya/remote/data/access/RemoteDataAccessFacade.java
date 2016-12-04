package com.vanya.remote.data.access;

import com.vanya.data.access.api.DataAccessFacade;
import com.vanya.model.Jalousie;
import com.vanya.model.State;
import com.vanya.model.UnconfirmedUser;
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
        restTemplate.put(uri + "/user/" + id, user, User.class);
    }

    @Override
    public void addJalousieForUser(long id, Jalousie jalousie) {
        restTemplate.postForLocation(uri + "/user/" + id + "/addJalousie", jalousie, Jalousie.class);
    }

    @Override
    public void removeJalousie(long userId, long id) {
        restTemplate.delete(uri + "/user/" + userId + "/jalousie/" + id);
    }

    @Override
    public List<Jalousie> getAllJalousies(long userId) {
        return (List<Jalousie>) restTemplate.getForEntity(uri + "/user/" + userId + "/jalousies", List.class).getBody();
    }

    @Override
    public Jalousie getJalousie(long userId, long jalousieId) {
        return restTemplate.getForEntity(uri + "/user/" + userId + "/jalousie" + jalousieId, Jalousie.class).getBody();
    }

    @Override
    public void changeJalousies(long userId, long jalId, Jalousie jalousie) {
        restTemplate.put(uri + "/user/" + userId + "/jalousie/" + jalId, jalousie, Jalousie.class);
    }

    @Override
    public UnconfirmedUser getUnconfirmedUser(long id) {
        return restTemplate.getForEntity(uri + "/unconfirmedUser/" + id, UnconfirmedUser.class).getBody();
    }

    @Override
    public void saveUnconfirmedUser(UnconfirmedUser unconfirmedUser) {
        restTemplate.postForLocation(uri + "unconfirmedUser/addNew", unconfirmedUser, UnconfirmedUser.class);
    }

    @Override
    public void deleteUnconfirmedUser(long id) {
        restTemplate.delete(uri + "/unconfirmedUser/" + id);
    }

    public void addStateForJalousie(long userId, long jalousieId, State state) {
        Jalousie jalousie = getJalousie(userId, jalousieId);
        jalousie.addNewState(state);
        changeJalousies(userId,jalousieId,jalousie);
        restTemplate.postForLocation(jalousie.getHost()+"/addState",state,State.class);
    }

    public void removeState(long userId, long jalousieId, long stateId) {
        Jalousie jalousie = getJalousie(userId, jalousieId);
        jalousie.removeState((int) stateId);
        changeJalousies(userId,jalousieId,jalousie);
        restTemplate.delete(jalousie.getHost() + "/state/" + stateId);
    }

    public List<State> getAllStates(long userId, long jalousieId) {
        Jalousie jalousie = getJalousie(userId, jalousieId);
        return jalousie.getStates();

    }

    public State currentState(long userId, long jalousieId) {
        Jalousie jalousie = getJalousie(userId, jalousieId);
        return jalousie.getState();
    }
}
