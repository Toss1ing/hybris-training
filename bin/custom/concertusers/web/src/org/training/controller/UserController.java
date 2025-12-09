package org.training.controller;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.facades.UserFacade;

@Controller
public class UserController {

    private final static String USER_ATTRIBUTE = "user";

    private final static String USER_PAGE = "userPage";

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/user")
    public String userPage(@RequestParam(required = false) String uid, Model model) {
        model.addAttribute(USER_ATTRIBUTE, userFacade.getCurrentUser(uid));
        return USER_PAGE;
    }
}
