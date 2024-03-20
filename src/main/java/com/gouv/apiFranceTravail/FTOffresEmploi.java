package com.gouv.apiFranceTravail;

import org.json.JSONObject;

public class FTOffresEmploi {
    public static void main(String[] args) {
        RestClientFT restClientFT = new RestClientFT();
        JSONObject jsonObject = restClientFT.executeGET("https://api.pole-emploi.io/partenaire/offresdemploi/v2/offres/170YNGB");
        System.out.println(jsonObject);
    }
}
