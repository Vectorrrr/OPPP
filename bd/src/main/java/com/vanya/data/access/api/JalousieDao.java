package com.vanya.data.access.api;

import com.vanya.model.Jalousie;

import java.util.List;

/**
 * @author Ivan Gladush
 * @since 07.11.16.
 */
public interface JalousieDao {
    void addJalousieForUser(long userId, Jalousie jalousie);


    Jalousie getJalousie(long userId, long jalousieId);

    void removeJalousie(long userId, long jalId);

    List<Jalousie> getAllJalousies(long userId);

    void changeJalousies(long userId, long id, Jalousie jalousie);

}
