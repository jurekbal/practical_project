package com.javapoz24.sda.pp;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu(new AdviceService(), new AdviceClient());
        menu.displayMenu();
    }

}
