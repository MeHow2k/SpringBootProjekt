package controllers.models;

import lombok.Data;

import jakarta.persistence.*;

/**
 * Reprezentuje wypożyczenie książki przez czytelnika w systemie bibliotecznym.
 *
 * Klasa mapowana na encję JPA i przechowywana w bazie danych. Zawiera informacje
 * o dacie wypożyczenia, planowanej dacie zwrotu, wypożyczonej książce oraz czytelniku.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Data
@Entity
@Table(name = "Wypozyczenia")
public class Wypozyczenie {

    /**
     * Unikalny identyfikator wypożyczenia (klucz główny).
     * Generowany automatycznie przy użyciu strategii {@link GenerationType#IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    /** Data wypożyczenia książki. */
    @Column(name = "date")
    private String date;

    /** Planowana data zwrotu książki. */
    @Column(name = "returndate")
    private String returndate;

    /**
     * Książka, która została wypożyczona.
     * Relacja wiele-do-jednego z encją {@link Ksiazka}.
     */
    @ManyToOne
    private Ksiazka ksiazka;

    /**
     * Czytelnik, który wypożyczył książkę.
     * Relacja wiele-do-jednego z encją {@link Czytelnik}.
     */
    @ManyToOne
    private Czytelnik czytelnik;

    /**
     * Zwraca szczegółową reprezentację tekstową obiektu, zawierającą dane wypożyczenia,
     * czytelnika oraz książki.
     *
     * @return reprezentacja tekstowa obiektu
     */
    @Override
    public String toString() {
        return String.format(
                "(id=%d, date='%s' returndate='%s') Czytelnik: %s Książka: %s",
                id, date, returndate, czytelnik.toString(), ksiazka.toString());
    }

    /**
     * Konstruktor inicjalizujący wypożyczenie z datą wypożyczenia i planowaną datą zwrotu.
     *
     * @param date       data wypożyczenia
     * @param returndate planowana data zwrotu
     */
    public Wypozyczenie(String date, String returndate) {
        this.date = date;
        this.returndate = returndate;
    }

    /**
     * Konstruktor domyślny (potrzebny dla JPA).
     * Inicjalizuje pola domyślnymi wartościami.
     */
    public Wypozyczenie() {
        this.date = "";
        this.returndate = "";
    }

    /**
     * Konstruktor inicjalizujący wypożyczenie z datą wypożyczenia.
     * Data zwrotu ustawiana jest na "nie oddano".
     *
     * @param date data wypożyczenia
     */
    public Wypozyczenie(String date) {
        this.date = date;
        this.returndate = "nie oddano";
    }

    /**
     * Pobiera książkę związaną z wypożyczeniem.
     *
     * @return książka związana z wypożyczeniem
     */
    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    /**
     * Ustawia książkę dla wypożyczenia.
     *
     * @param ksiazka książka do przypisania
     */
    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    /**
     * Pobiera czytelnika związanego z wypożyczeniem.
     *
     * @return czytelnik związany z wypożyczeniem
     */
    public Czytelnik getCzytelnik() {
        return czytelnik;
    }

    /**
     * Ustawia czytelnika dla wypożyczenia.
     *
     * @param czytelnik czytelnik do przypisania
     */
    public void setCzytelnik(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
    }
}
