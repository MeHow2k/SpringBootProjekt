package controllers.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Reprezentuje encję użytkownika w systemie zarządzania użytkownikami.
 * Użytkownik posiada unikalny identyfikator, nazwę użytkownika, hasło oraz zestaw ról.
 *
 * Klasa jest mapowana na tabelę w bazie danych za pomocą JPA.
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Entity
public class DBUser {

    /**
     * Unikalny identyfikator użytkownika (klucz główny).
     * Generowany automatycznie przy użyciu strategii {@link GenerationType#AUTO}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Nazwa użytkownika (login). */
    private String username;

    /** Hasło użytkownika (zaszyfrowane). */
    private String password;

    /**
     * Zbiór ról przypisanych do użytkownika.
     * Relacja wiele-do-wielu, zdefiniowana za pomocą tabeli pośredniej `user_roles`.
     * Dane ładowane są natychmiastowo dzięki strategii {@link FetchType#EAGER}.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<DBRole> roles = new HashSet<>();

    /**
     * Konstruktor domyślny.
     */
    public DBUser() {
    }

    /**
     * Pobiera zbiór ról przypisanych do użytkownika.
     *
     * @return zbiór ról użytkownika
     */
    public Set<DBRole> getRoles() {
        return roles;
    }

    /**
     * Pobiera identyfikator użytkownika.
     *
     * @return ID użytkownika
     */
    public Long getId() {
        return id;
    }

    /**
     * Ustawia identyfikator użytkownika.
     *
     * @param id nowy identyfikator użytkownika
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Pobiera nazwę użytkownika (login).
     *
     * @return nazwa użytkownika
     */
    public String getUsername() {
        return username;
    }

    /**
     * Ustawia nazwę użytkownika (login).
     *
     * @param username nowa nazwa użytkownika
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Pobiera hasło użytkownika (zaszyfrowane).
     *
     * @return hasło użytkownika
     */
    public String getPassword() {
        return password;
    }

    /**
     * Ustawia hasło użytkownika (zaszyfrowane).
     *
     * @param password nowe hasło użytkownika
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
