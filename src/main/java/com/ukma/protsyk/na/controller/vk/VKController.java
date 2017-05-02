package com.ukma.protsyk.na.controller.vk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by okpr0814 on 5/2/2017.
 */

@Controller
@RequestMapping("/vkontakte")
public class VKController {

    @Autowired
    VKontakte vkontakte;

    @GetMapping
    public String renderIndex(ModelMap modelMap) {
        try {
           // final List<VKontakteProfile> profiles = vkontakte.friendsOperations().get().getItems();
          //  modelMap.put("profiles", profiles);
            modelMap.put("vkemail",vkontakte.usersOperations().getUser().getLastName());
            return "next";
        } catch (MissingAuthorizationException authEx) {
            return "redirect:/connect/vkontakte";
        }
    }





}
