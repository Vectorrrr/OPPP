package com.vanya.data.access.api;

import com.vanya.model.UnconfirmedUser;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
public interface UnconfirmedUserDao {
    UnconfirmedUser getUncofirmedUser(long id);

    void saveUncomfirmedUser(UnconfirmedUser user);

    void deleteUnconfirmedUser(long id);
}
