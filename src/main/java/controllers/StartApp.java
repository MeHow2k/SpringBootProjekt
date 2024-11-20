package controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Klasa uruchamiająca aplikację Spring Boot.
 *
 * Główna klasa aplikacji, która inicjuje proces uruchamiania aplikacji Spring Boot przy pomocy metody {@link SpringApplication#run}.
 * Aplikacja jest opatrzona adnotacją {@link SpringBootApplication}, co oznacza, że jest to aplikacja Spring Boot z włączoną automatyczną konfiguracją,
 * skanowaniem komponentów i innymi funkcjami Spring Boot.
 *
 *
 * Metoda {@link #main(String[])} jest punktem wejścia do aplikacji. Uruchamia ona aplikację Spring Boot poprzez wywołanie
 * metody {@link SpringApplication#run}, która konfiguruje i uruchamia kontekst aplikacji.
 *
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@SpringBootApplication
public class StartApp {

    /**
     * Punkt wejścia do aplikacji Spring Boot. Uruchamia aplikację poprzez metodę {@link SpringApplication#run}.
     *
     * @param args argumenty wiersza poleceń, które mogą zostać przekazane do aplikacji
     */
    public static void main(String[] args) {
        SpringApplication.run(controllers.StartApp.class, args);
    }
}
