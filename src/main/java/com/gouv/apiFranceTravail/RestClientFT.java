package com.gouv.apiFranceTravail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import org.json.*;

public class RestClientFT {
    private static final String URL_NEW_TOKEN = "https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=partenaire";
    private static String token;

    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;


    static RestTemplate restTemplate = new RestTemplate();

    public RestClientFT() {
        System.out.println(clientId);
        //getToken();
    }

    private void getToken(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     
        body.add("grant_type", "client_credentials");
        body.add("client_id",clientId);
        body.add("client_secret", clientSecret);
        body.add("scope","api_labonneboitev1 api_offresdemploiv2 o2dsoffre api_baseadressenationalev1 api_pagesentreprisesv1 pagesentreprises");

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body, headers);

        ResponseEntity <String> result =  restTemplate.exchange(URL_NEW_TOKEN, HttpMethod.POST,httpEntity,String.class);

        String json = result.getBody();
        JSONObject jsonObject = new JSONObject(json);
        token = jsonObject.get("access_token").toString();
    }

    public JSONObject executeGET(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        
        HttpEntity<?> httpEntity = new HttpEntity<Object>(headers);
        ResponseEntity <String> result =  restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);
        String json = result.getBody();
        JSONObject jsonObject = new JSONObject(json);

        return jsonObject;
    }
}
