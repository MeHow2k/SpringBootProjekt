package controllers.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Konfiguracja logowania dla aplikacji.
 * Umożliwia zdefiniowanie loggera oraz dodanie niestandardowych i standardowych handlerów do logowania.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Configuration
public class LoggingConfig {

    /**
     * Niestandardowy handler do obsługi logowania do bazy danych.
     * Handler musi implementować logikę zapisu logów do bazy danych.
     */
    @Autowired
    private DatabaseLoggingHandler databaseLoggingHandler;

    /**
     * Konfiguruje logger o nazwie "MyAppLogger".
     *
     * Dodaje niestandardowy handler do zapisu logów w bazie danych oraz standardowy
     * handler do logowania w konsoli. Ustawia poziom logowania na INFO.
     *
     * @return skonfigurowany logger
     */
    @Bean
    public Logger configureLogger() {
        // Tworzymy instancję loggera z unikalną nazwą
        Logger logger = Logger.getLogger("MyAppLogger");

        // Ustawiamy poziom logowania
        logger.setLevel(Level.INFO);

        // Dodajemy niestandardowy handler do logowania w bazie danych
        logger.addHandler(databaseLoggingHandler);

        // Opcjonalnie: dodanie handlera do logowania w konsoli
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);

        return logger;
    }
}
