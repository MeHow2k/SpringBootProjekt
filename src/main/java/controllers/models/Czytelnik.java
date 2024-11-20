package controllers.models;

import lombok.Data;

import jakarta.persistence.*;

/**
 * Reprezentuje encję Czytelnik w systemie bibliotecznym.
 * Zawiera informacje o czytelniku takie jak ID, imię i nazwisko.
 * Klasa jest mapowana na tabelę "Czytelnik" w bazie danych.
 *
 * Dzięki adnotacji {@link lombok.Data}, Lombok generuje automatycznie metody
 * takie jak gettery, settery, {@code toString()}, {@code equals()} oraz {@code hashCode()}.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Data
@Entity
@Table(name = "Czytelnik")
public class Czytelnik {

    /** Unikalny identyfikator czytelnika (klucz główny). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    /** Imię czytelnika. */
    @Column(name = "firstname")
    private String firstname;

    /** Nazwisko czytelnika. */
    @Column(name = "lastname")
    private String lastname;

    /**
     * Tworzy reprezentację obiektu w formie tekstowej.
     *
     * @return String zawierający ID, imię i nazwisko czytelnika.
     */
    @Override
    public String toString() {
        return String.format("(id=%d, firstname='%s' surname='%s')", id, firstname, lastname);
    }

    /**
     * Konstruktor inicjalizujący czytelnika z podanymi danymi.
     *
     * @param firstname imię czytelnika
     * @param lastname  nazwisko czytelnika
     */
    public Czytelnik(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Konstruktor domyślny, który tworzy pustego czytelnika.
     *
     * <ul>
     *   <li>ID - wartość domyślna (ustawiana przez bazę danych)</li>
     *   <li>Imię - pusta wartość</li>
     *   <li>Nazwisko - pusta wartość</li>
     * </ul>
     */
    public Czytelnik() {
        this.firstname = "";
        this.lastname = "";
    }

}
