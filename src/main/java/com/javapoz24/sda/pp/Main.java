package com.javapoz24.sda.pp;

public class Main {
    public static void main(String[] args) {

AdviceClient adviceClient = new AdviceClient();
Slip slip = adviceClient.fetchAdvice();
        System.out.println(slip.getAdvice());

    }
}
