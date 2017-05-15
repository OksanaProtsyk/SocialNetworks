package com.ukma.protsyk.na.controller.vk;

import com.ukma.protsyk.na.gephi.CommunityDetection;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by okpr0814 on 5/15/2017.
 */
@Controller
public class VkontakteCommunityController {
    @RequestMapping(value="/vkontakte/community", method= RequestMethod.GET)

    public String detectCommunity(Model model) {
        CommunityDetection comDet = new CommunityDetection();
        comDet.script();
        return "nodes";
    }
    }
