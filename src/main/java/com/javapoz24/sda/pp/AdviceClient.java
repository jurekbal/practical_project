package com.javapoz24.sda.pp;

import com.google.gson.Gson;
import com.javapoz24.sda.pp.http.*;
import com.javapoz24.sda.pp.model.SlipDto;

public class AdviceClient {
    private static final String URL = PropertiesManager.getProperty("URL");
    private final HttpClient httpClient = new HttpClient();

    public SlipDto getRandomAdvice(){
        String response = httpClient.fetch(URL + "advice");
        SlipResponse slipResponse = ResponseParser.parseFromString(response, SlipResponse.class);
        return slipResponse.getSlip();
    }

    public SearchResponse searchByKeyword(String keyword) throws SlipNotFoundException {
        String response = httpClient.fetch(URL + "advice/search/" + keyword);
        SearchResponse searchResponse = ResponseParser.parseFromString(response, SearchResponse.class);
        if (searchResponse.getQuery() == null) {
            throw new SlipNotFoundException("Slip not found for keyword: " + keyword);
        }
        return searchResponse;
    }

    public SlipDto searchById(long id){
        String s = httpClient.fetch(URL + "advice/" + id);
        s += '}';
        SlipResponse sr = ResponseParser.parseFromString(s, SlipResponse.class);
        return sr.getSlip();

    }

}
