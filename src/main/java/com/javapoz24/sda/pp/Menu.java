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
            System.out.println("Advice book - Witamy");
            System.out.println("Wybierz opcję:");
            System.out.println("1 - pokaż podmenu losowania cytatów");
            System.out.println("2 - pokaż wszystkie ulubione cytaty zapisane w bazie");
            System.out.println("3 - pokaż podmenu ulubionych cytatów zapisanych w bazie");
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
                    displaySubmenu_1();
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

    private void displaySubmenu_1(){
        boolean contineuiing = true;
        SlipDto fetchedSlip = null;

        while (contineuiing) {
            System.out.println();
            System.out.println("Podenu nowego cytatu");
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wylosuj cytat");
            System.out.println("2 - zapisz ostatnio wylosowany cytat do bazy");
            System.out.println("0 - wyjście do głównego Menu");

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
                    fetchedSlip = adviceService.getRandomAdvice();
                    System.out.println();
                    System.out.println("****** Cytat dla Ciebie ******");
                    System.out.println(fetchedSlip.getAdvice());
                    System.out.println("******************************");
                    break;
                }
                case 2: {
                    if (fetchedSlip != null) {
                        if (adviceService.saveAdvice(fetchedSlip)) {
                            System.out.println("Zapisano cytat do bazy.");
                            fetchedSlip = null;
                        } else {
                            System.out.println("Zapis do bazy zakończony NIEPOWODZENIEM - nie zapisano");
                        }
                    } else {
                        System.out.println("Najpierw wylosuj cytat!");
                    }
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
