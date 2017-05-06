package com.ukma.protsyk.na.controller.facebook;
import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacebookProfileController {

    @Inject
    private ConnectionRepository connectionRepository;
    @RequestMapping(value="/facebookp", method= RequestMethod.GET)
    public String home(Model model) {
        Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
        if (connection == null) {
            return "redirect:/connect/facebook";
        }
        User user = connection.getApi().userOperations().getUserProfile();
        model.addAttribute("profile", user );
        model.addAttribute("name",user.getName() );
        return "facebook/profile";
    }


}
