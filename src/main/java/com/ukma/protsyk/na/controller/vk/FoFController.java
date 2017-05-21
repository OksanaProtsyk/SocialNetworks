package com.ukma.protsyk.na.controller.vk;

import com.ukma.protsyk.na.gephi.DynamicGexfGraph;
import com.ukma.protsyk.na.gephi.ExportImport;
import com.ukma.protsyk.na.gephi.VisualizeUsers;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import org.springframework.stereotype.Controller;

/**
 * Created by okpr0814 on 5/5/2017.
 */

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.friends.responses.GetResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FoFController {

    private final VKontakte vkontakte;

    @Inject
    public FoFController(VKontakte vkontakte) {
        this.vkontakte = vkontakte;
    }

    @RequestMapping(value = "/fof", method = RequestMethod.GET)
    public String showFeed(Model model) throws ClientException, ApiException {
        VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
        GetResponse userIds = vk.friends().get(vkontakte.getUserActor()).execute();
        List<String> ids = userIds.getItems().stream().map(Object::toString).collect(Collectors.toList());
        List<UserXtrCounters> users = vk.users().get(vkontakte.getUserActor())
                .userIds(ids)
                .fields(UserField.PHOTO_50, UserField.SEX, UserField.BDATE, UserField.CITY, UserField.COUNTRY, UserField.LISTS, UserField.DOMAIN, UserField.HOME_TOWN,
                        UserField.CONTACTS, UserField.SITE,
                        UserField.EDUCATION, UserField.UNIVERSITIES,
                        UserField.FOLLOWERS_COUNT, UserField.COUNTERS,
                        UserField.NICKNAME, UserField.RELATIVES,
                        UserField.RELATION, UserField.PERSONAL,
                        UserField.CONNECTIONS,
                        UserField.SCHOOLS, UserField.OCCUPATION, UserField.ACTIVITIES, UserField.INTERESTS, UserField.MUSIC,
                        UserField.MOVIES, UserField.TV, UserField.BOOKS, UserField.GAMES, UserField.ABOUT, UserField.QUOTES, UserField.CAREER, UserField.MILITARY)
                .lang(Lang.EN)
                .execute();
        model.addAttribute("friends", users);

        for (int j = 60; j <70; j++) {


            GetResponse userIdFs = vk.friends().get().userId(users.get(j).getId()).execute();
            List<String> idFs = userIdFs.getItems().stream().map(Object::toString).collect(Collectors.toList());
            List<UserXtrCounters> userFs = vk.users().get(vkontakte.getUserActor())
                    .userIds(idFs)
                    .fields(UserField.PHOTO_50, UserField.SEX, UserField.BDATE, UserField.CITY, UserField.COUNTRY, UserField.LISTS, UserField.DOMAIN, UserField.HOME_TOWN,
                            UserField.CONTACTS, UserField.SITE,
                            UserField.EDUCATION, UserField.UNIVERSITIES,
                            UserField.FOLLOWERS_COUNT, UserField.COUNTERS,
                            UserField.NICKNAME, UserField.RELATIVES,
                            UserField.RELATION, UserField.PERSONAL,
                            UserField.CONNECTIONS,
                            UserField.SCHOOLS, UserField.OCCUPATION, UserField.ACTIVITIES, UserField.INTERESTS, UserField.MUSIC,
                            UserField.MOVIES, UserField.TV, UserField.BOOKS, UserField.GAMES, UserField.ABOUT, UserField.QUOTES, UserField.CAREER, UserField.MILITARY)
                    .lang(Lang.EN)
                    .execute();

            Map<String, List<String>> map = new HashMap<>();
            for (int i = 0; i <userFs.size(); i++) {
                try {
                    GetResponse userIds2 = vk.friends().get().userId(userFs.get(i).getId()).execute();
                    List<String> ids2 = userIds2.getItems().stream().map(Object::toString).collect(Collectors.toList());

                    map.put(userFs.get(i).getId().toString(), ids2);
                    System.out.println("User:" + userFs.get(i).getId().toString() + "Friends: " + ids2.toString());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } catch (ApiAccessException ex) {
                    System.out.print("Excep " + ex.getMessage());
                }
            }


            DynamicGexfGraph.generateGraph(users.get(j).getId(),vkontakte.getUserActor(), userFs, map);
            ExportImport f = new ExportImport();
            f.script();

        }
        //  VisualizeUsers u = new VisualizeUsers();
        // u.script();
        return "nodes";
    }

}
