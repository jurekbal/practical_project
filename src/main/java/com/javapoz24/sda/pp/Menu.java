package com.javapoz24.sda.pp;

import com.javapoz24.sda.pp.http.SearchResponse;
import com.javapoz24.sda.pp.http.SlipNotFoundException;
import com.javapoz24.sda.pp.http.SlipResponse;
import com.javapoz24.sda.pp.model.Slip;
import com.javapoz24.sda.pp.model.SlipDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final AdviceService adviceService;
    private final AdviceClient adviceClient;

    public Menu(AdviceService adviceService, AdviceClient adviceClient) {
        this.adviceService = adviceService;
        this.adviceClient = adviceClient;
    }

    public void displayMenu() {

        boolean continuing = true;

        while (continuing) {
            System.out.println();
            System.out.println("Advice book - Witamy");
            System.out.println("Wybierz opcję:");
            System.out.println("1 - opcje podmenu losowania cytatów");
            System.out.println("2 - opcje wyszukiwania");
            System.out.println("3 - opcje ulubionych cytatów zapisanych w bazie");
            System.out.println("0 - wyjście");

            int option = -1;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            }

            switch (option) {
                case 0: {
                    continuing = false;
                    break;
                }
                case 1: {
                    displaySubmenu_1();
                    break;
                }
                case 2: {
                    displaySubmenu_2();
                    break;
                }
                case 3: {
                    displaySubmenu_3();
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

    private void displaySubmenu_1() {
        boolean continuing = true;
        SlipDto fetchedSlip = null;

        while (continuing) {
            System.out.println();
            System.out.println("Podmenu nowego cytatu");
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
                    continuing = false;
                    break;
                }
                case 1: {
                    fetchedSlip = adviceClient.getRandomAdvice();
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

    private void displaySubmenu_2() {
        boolean continuing = true;
        //SlipDto fetchedSlip = null;

        while (continuing) {
            System.out.println();
            System.out.println("Podmenu wyszukiwania");
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wyszukaj cytat po słowie kluczowym");
            System.out.println("2 - wyszukaj po Id");
            System.out.println("0 - wyjście do głównego Menu");

            int option = -1;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                option = Integer.parseInt((scanner.nextLine()));
            }

            switch (option) {
                case 0: {
                    continuing = false;
                    break;
                }
                case 1: {
                    System.out.println("Podaj szukane słowo kluczowe:");
                    String keyword = scanner.nextLine();
                    try {
                        SearchResponse sr = adviceClient.searchByKeyword(keyword);
                        System.out.println(sr) ;
                    } catch (SlipNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    System.out.println("Podaj szukane Id:");
                    long id = scanner.nextLong();
                    SlipDto slipDto = adviceClient.searchById(id);
                    System.out.println(slipDto);
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

    private void displaySubmenu_3() {

        boolean continuing = true;

        while (continuing) {
            System.out.println();
            System.out.println("Podmenu moich cytatów");
            System.out.println("Wybierz opcję:");
            System.out.println("1 - wyświetl moje cytaty");
            System.out.println("2 - usuń cytat (wg SlipId)");
            System.out.println("0 - wyjście");

            int option = -1;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            }

            switch (option) {
                case 0: {
                    continuing = false;
                    break;
                }
                case 1: {
                    List<Slip> list = adviceService.getAllAdvices();
                    System.out.println(Arrays.toString(list.toArray()));
                    break;
                }
                case 2: {
                    System.out.println("Usuwanie cytatu.\nPodaj nr SlipId cytatu do usunięcia");
                    long slipIdToDelete;
                    if (scanner.hasNextInt()) {
                        slipIdToDelete = scanner.nextLong();
                    } else {
                        System.out.println("BLĄD -> Nie wpisano liczby!");
                        break;
                    }
                    if (adviceService.deleteSlip(slipIdToDelete)) {
                        System.out.println("SUKCES -> Cytat został usunięty.");
                    } else {
                        System.out.println("PORAŻKA -> nie udało się usunąć cytatu");
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
