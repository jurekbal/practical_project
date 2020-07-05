package com.javapoz24.sda.pp;

import com.javapoz24.sda.pp.model.Slip;
import com.javapoz24.sda.pp.model.SlipDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final AdviceService adviceService;

    public Menu(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

    public void displayMenu() {

        boolean contineuiing = true;

        while (contineuiing) {
            System.out.println();
            System.out.println("Advice book - Welcome");
            System.out.println("Select option:");
            System.out.println("1 - wylosuj cytat");
            System.out.println("2 - wyszukaj cytat");
            System.out.println("3 - moje cytaty"); // TODO w podmenu będzie dodawanie, wyświetlanie, usuwanie
            System.out.println("0 - wyjście");

            int option = -1;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            }

            switch (option) {
                case 0: {
                    contineuiing = false;
                    break;
                }
                case 1: {
                    SlipDto randomSlip = adviceService.getRandomAdvice();
                    adviceService.saveAdvice(randomSlip);
                    System.out.println();
                    System.out.println("****** Cytat dla Ciebie ******");
                    System.out.println(randomSlip.getAdvice());
                    System.out.println("******************************");
                    break;
                }
                case 2: {
                    List<Slip> list = adviceService.getAllAdvices();
                    System.out.println(Arrays.toString(list.toArray()));
                    break;
                }
                case 3: {
                    System.out.println("Funkcja jeszcze nie jest zaimplementowana. Sorry");
                    break;
                }
                case -1: {
                    System.out.println("Wpisz liczbę!");
                    break;
                }
                default: {
                    System.out.println("Funkcja nieobsługiwana.");
                    break;
                }
            }
        }
    }

}
