package controllers.Logger;

import controllers.models.MyLogRecord;
import controllers.repositories.MyLogRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Handler;

/**
 * Niestandardowy handler logów, który zapisuje logi do bazy danych.
 * Klasa rozszerza `Handler` z pakietu `java.util.logging` i implementuje metodę `publish`.
 * Logi są zapisywane w bazie danych za pomocą repozytorium `MyLogRecordRepository`.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Component
public class DatabaseLoggingHandler extends Handler {

    /**
     * Repozytorium do zapisywania logów w bazie danych.
     */
    private final MyLogRecordRepository logRecordRepository;

    /**
     * Konstruktor, który wstrzykuje zależność repozytorium logów.
     *
     * @param logRecordRepository repozytorium, które zarządza logami w bazie danych
     */
    @Autowired
    public DatabaseLoggingHandler(MyLogRecordRepository logRecordRepository) {
        this.logRecordRepository = logRecordRepository;
    }

    /**
     * Publikuje logi, zapisując je do bazy danych, jeśli są one logowalne.
     * Formatuje wiadomość logu, poziom logu oraz czas logowania przed zapisaniem.
     *
     * @param record rekord logu, który ma zostać zapisany
     */
    @Override
    public void publish(java.util.logging.LogRecord record) {

        // Sprawdzenie, czy log jest logowalny (czy ma odpowiedni poziom logowania)
        if (!isLoggable(record)) {
            return;
        }

        // Pobranie poziomu logowania
        String locLevel = record.getLevel().getName();

        // Tworzymy obiekt logu do zapisania w bazie danych
        MyLogRecord logRecord = new MyLogRecord();
        logRecord.setMessage(record.getMessage());

        // Utworzenie formatu dla daty i godziny
        LocalDateTime localData = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localData.format(formatter); // Formatowanie daty i godziny
        logRecord.setTimestamp(formattedDateTime);

        // Ustawienie poziomu logu
        logRecord.setLevel(locLevel);

        // Zapisanie logu w bazie danych
        logRecordRepository.save(logRecord);
    }

    /**
     * Flush operacji logowania (nie jest wymagane w tym przypadku).
     */
    @Override
    public void flush() {
        // Brak wymaganej logiki flush
    }

    /**
     * Zamyka handler logowania (nie jest wymagane w tym przypadku).
     */
    @Override
    public void close() throws SecurityException {
        // Brak wymaganej logiki close
    }
}
