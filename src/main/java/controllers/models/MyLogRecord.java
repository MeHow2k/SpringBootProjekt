package controllers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Reprezentuje rekord logu w systemie.
 *
 * Klasa mapowana na encję JPA i przechowywana w bazie danych. Zawiera informacje
 * o wiadomości logu, znaczniku czasu, poziomie logu oraz unikalnym identyfikatorze.
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Entity
public class MyLogRecord {

    /**
     * Unikalny identyfikator rekordu logu (klucz główny).
     * Generowany automatycznie przy użyciu strategii {@link GenerationType#IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Wiadomość logu. */
    private String message;

    /** Znacznik czasu logu (w formacie tekstowym). */
    private String timestamp;

    /** Poziom logu (np. INFO, ERROR). */
    private String level;

    /**
     * Konstruktor domyślny (potrzebny dla JPA).
     */
    public MyLogRecord() {
    }

    /**
     * Konstruktor inicjalizujący rekord logu z wiadomością i znacznikiem czasu.
     * Poziom logu jest domyślnie ustawiony na "INFO".
     *
     * @param message   wiadomość logu
     * @param timestamp znacznik czasu logu
     */
    public MyLogRecord(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.level = "INFO";
    }

    /**
     * Pobiera wiadomość logu.
     *
     * @return wiadomość logu
     */
    public String getMessage() {
        return message;
    }

    /**
     * Ustawia wiadomość logu.
     *
     * @param message wiadomość logu
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Pobiera znacznik czasu logu.
     *
     * @return znacznik czasu logu
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Ustawia znacznik czasu logu.
     *
     * @param timestamp znacznik czasu logu
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Pobiera poziom logu.
     *
     * @return poziom logu
     */
    public String getLevel() {
        return level;
    }

    /**
     * Ustawia poziom logu.
     *
     * @param level poziom logu (np. INFO, ERROR)
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Pobiera unikalny identyfikator rekordu logu.
     *
     * @return identyfikator logu
     */
    public Long getId() {
        return id;
    }
}
