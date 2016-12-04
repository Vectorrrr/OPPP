package com.vanya.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
@Document(collection = "jalousie")
public class Jalousie {
    private static int stateInd=1;
    @Id
    private long id;
    private long userId;
    private int weight;
    private int high;
    private String host;
    private List<State> states = new ArrayList<>();
    private int totalEnergy;

    public Jalousie(int weight, int high, String allowableHost) {
        this.weight = weight;
        this.high = high;
        this.host = allowableHost;
    }

    public Jalousie(long id, long userId, int weight, int high) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.high = high;
    }


    public Jalousie(){
        this.id=new Date().getTime();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    public void setTotalEnergy(int totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    @Override
    public String toString() {
        return "Jalousie{" +
                "id=" + id +
                ", userId=" + userId +
                ", weight=" + weight +
                ", high=" + high +
                ", host='" + host + '\'' +
                ", states=" + states +
                ", totalEnergy=" + totalEnergy +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //todo переписать добавить возможность добовлять по минутам
    public State getState() {
        if(states==null){
            return null;
        }
        Date date=new Date();
        for(State state:states){
            if(state.getStartDate().getTime()<=date.getTime() &&
                    state.getFinishDate().getTime()>=date.getTime()
                    ){
                return state;
            }
        }
        return null;
    }
    //todo add logger
    public void addNewState(State newState) {
        newState.setIndex(stateInd++);
        states.add(newState);
        System.out.println(newState);
    }

    public void removeState(int stateId) {
        for(int i=0;i<states.size();++i){
            if(states.get(i).getIndex()==stateId){
                states.remove(i);
                return;
            }
        }
    }
}
