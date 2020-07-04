package com.javapoz24.sda.pp;

import java.util.Scanner;

public class Main {

    private static final String URL = "https://api.adviceslip.com/";

    public static void main(String[] args) {

        displayMenu();
    }

    public static void displayMenu() {

        boolean contineuiing = true;

        while (contineuiing) {
            System.out.println("Advice book - Welcome");
            System.out.println("Select option:");
            System.out.println("1 - wylosuj cytat");
            System.out.println("2 - wyszukaj cytat");
            System.out.println("3 - moje cytaty"); // TODO w podmenu będzie dodawanie, wyświetlanie, usuwanie
            System.out.println("0 - wyjście");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 0: {
                    contineuiing = false;
                    break;
                }
                case 1: {
                    getRandomAdvice();
                    break;
                }
                case 2: {
                    System.out.println("Funkcja jeszcze nie jest zaimplementowana. Sorry");
                    break;
                }
                case 3: {
                    System.out.println("Funkcja jeszcze nie jest zaimplementowana. Sorry");
                    break;
                }
                default: {
                    System.out.println("Funkcja nieobsługiwana.");
                    break;
                }
            }
        }
    }

    public static void getRandomAdvice(){
        HttpClient httpClient = new HttpClient();
        Slip slip = httpClient.fetch(URL + "advice", SlipResponse.class).getSlip();
        System.out.println(slip.getAdvice());
    }
}
