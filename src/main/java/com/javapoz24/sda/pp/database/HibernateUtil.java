package com.javapoz24.sda.pp.database;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY;

    static {
        // pobieramy konfigurację i ustawiamy SESSION_FACTORY;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("/hibernate.cfg.xml"); // nie muszę wpisywać tej nazwy pliku, domyślnie ładuje ten plik

            SESSION_FACTORY = configuration.buildSessionFactory();
        } catch (HibernateException hibernateException) {
            System.err.println("Błąd inicjalizacji sesji.");
            hibernateException.printStackTrace(); // wypisz na ekran
            throw hibernateException; // rzuć wyjątek wyżej
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
