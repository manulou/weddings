package com.manulsoftware.weddings.web.secure.services;

import com.manulsoftware.weddings.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/secure/user")
    public User user() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setName(authenticatedUser.getName());
        user.setUsername(authenticatedUser.getUsername());
        user.setRole(authenticatedUser.getRole());
        user.setId(authenticatedUser.getId());
        user.setPassword(null);
        return user;
    }
}
