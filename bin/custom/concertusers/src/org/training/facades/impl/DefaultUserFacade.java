package org.training.facades.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.training.data.UserData;
import org.training.facades.UserFacade;

public class DefaultUserFacade implements UserFacade {

    private final UserService userService;

    public DefaultUserFacade(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserData getCurrentUser(final String uid) {
        UserModel user;

        if (uid == null || uid.isEmpty()) {
            user = userService.getCurrentUser();
        } else {
            user = userService.getUserForUID(uid);
        }

        return mapToUserData(user);
    }

    private UserData mapToUserData(final UserModel user) {
        UserData data = new UserData();
        data.setUid(user.getUid());
        data.setName(user.getName());
        data.setDescription(user.getDescription());
        return data;
    }
}