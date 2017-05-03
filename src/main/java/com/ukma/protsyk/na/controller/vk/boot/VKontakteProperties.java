package com.ukma.protsyk.na.controller.vk.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author sala
 */
@ConfigurationProperties(prefix = "spring.social.vkontakte")
public class VKontakteProperties {
    /**
     * clientId for vkontakte application
     */
    private String clientId;
    /**
     * clientSecret for vkontkate application
     */
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
