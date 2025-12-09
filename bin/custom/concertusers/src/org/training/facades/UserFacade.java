package org.training.facades;

import org.training.data.UserData;

public interface UserFacade {
    UserData getCurrentUser(String uid);
}
