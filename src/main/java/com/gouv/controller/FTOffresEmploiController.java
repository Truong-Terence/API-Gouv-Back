package com.gouv.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import com.gouv.apiFranceTravail.RestClientFT;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FTOffresEmploiController {
    @GetMapping("getJobOffer")
    public String getMethodName() {
        RestClientFT restClientFT = new RestClientFT();
        JSONObject jsonObject = restClientFT.executeGET("https://api.pole-emploi.io/partenaire/offresdemploi/v2/offres/170YNGB");
        return jsonObject.toString();
    }
}
