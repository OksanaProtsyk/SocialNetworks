/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ukma.protsyk.na.controller.vk;

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
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VKontakteFriendsController {

	private final VKontakte vkontakte;

	@Inject
	public VKontakteFriendsController(VKontakte vkontakte) {
		this.vkontakte = vkontakte;
	}

	@RequestMapping(value="/vkontakte/friends", method=RequestMethod.GET)
	public String showFeed(Model model) throws ClientException, ApiException {
		VkApiClient vk = new VkApiClient(HttpTransportClient.getInstance());
		GetResponse userIds = vk.friends().get(vkontakte.getUserActor()).execute();
		List<String> ids = userIds.getItems().stream().map(Object::toString).collect(Collectors.toList());
		List<UserXtrCounters> users = vk.users().get(vkontakte.getUserActor())
				.userIds(ids)
				.fields(UserField.PHOTO_50)
				.lang(Lang.EN)
				.execute();
		model.addAttribute("friends", users);

		return "vkontakte/friends";
	}
	
}
