package controllers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Reprezentuje encję roli w systemie zarządzania użytkownikami.
 * Każda rola posiada unikalny identyfikator oraz nazwę.
 *
 * Klasa jest mapowana na tabelę w bazie danych za pomocą JPA.
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Entity
public class DBRole {

    /**
     * Unikalny identyfikator roli (klucz główny).
     * Generowany automatycznie przy użyciu strategii {@link GenerationType#AUTO}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Nazwa roli (np. "ADMIN", "USER"). */
    private String name;

    /**
     * Pobiera identyfikator roli.
     *
     * @return ID roli
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator roli.
     *
     * @param id nowy identyfikator roli
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Pobiera nazwę roli.
     *
     * @return nazwa roli
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia nazwę roli.
     *
     * @param name nowa nazwa roli
     */
    public void setName(String name) {
        this.name = name;
    }
}
