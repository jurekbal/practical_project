package com.javapoz24.sda.pp.model;

// Slip Data Transfer Object
// Obiekt pośredni - dopasowany do formatu danych JSON z Web API Slip Advice
public class SlipDto {
    private long id;
    private String advice;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
