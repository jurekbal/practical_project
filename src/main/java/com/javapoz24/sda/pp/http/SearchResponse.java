package com.javapoz24.sda.pp.http;

import com.javapoz24.sda.pp.model.SlipDto;

import java.util.List;

public class SearchResponse {
    private int total_results;
    private String query;
    private List<SlipDto> slips;

    public SearchResponse(int total_results, String query, List<SlipDto> slips) {
        this.total_results = total_results;
        this.query = query;
        this.slips = slips;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<SlipDto> getSlips() {
        return slips;
    }

    public void setSlips(List<SlipDto> slips) {
        this.slips = slips;
    }

    @Override
    public String toString() {

        StringBuilder resultString = new StringBuilder("SearchResponse{" +
                "total_results=" + total_results +
                ", query='" + query + '\n');

        for (SlipDto slip : slips) {
            resultString.append(slip.toString());
            resultString.append('\n');
        }
        return resultString.toString();
    }
}
