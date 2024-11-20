package common;

import controllers.models.Czytelnik;
import lombok.Data;

/**
 * Klasa reprezentująca czytelnika w systemie bibliotecznym.
 * Zawiera podstawowe informacje o czytelniku, takie jak ID, imię i nazwisko.
 *
 * Klasa udostępnia różne konstruktory do tworzenia instancji na podstawie
 * obiektów tej samej klasy, klasy {@link controllers.models.Czytelnik},
 * a także do reprezentowania stanu błędu.
 *
 *  @author Michał Pasieka
 *  @version 1.0, 20.11.2024
 */
@Data
public class CCzytelnik {

    /** ID czytelnika. */
    private int id;

    /** Imię czytelnika. */
    private String firstname;

    /** Nazwisko czytelnika. */
    private String lastname;

    /**
     * Konstruktor inicjalizujący wszystkie pola.
     *
     * @param id         identyfikator czytelnika
     * @param firstname  imię czytelnika
     * @param lastname   nazwisko czytelnika
     */
    public CCzytelnik(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Konstruktor kopiujący, który tworzy nową instancję na podstawie
     * innego obiektu tej samej klasy.
     *
     * @param czytelnik obiekt klasy {@code CCzytelnik} do skopiowania
     */
    public CCzytelnik(CCzytelnik czytelnik) {
        this.id = czytelnik.getId();
        this.firstname = czytelnik.getFirstname();
        this.lastname = czytelnik.getLastname();
    }

    /**
     * Konstruktor konwertujący, który tworzy nową instancję na podstawie
     * obiektu klasy {@link controllers.models.Czytelnik}.
     *
     * @param czytelnik obiekt klasy {@code Czytelnik}, który ma zostać skonwertowany
     */
    public CCzytelnik(Czytelnik czytelnik) {
        this.id = czytelnik.getId();
        this.firstname = czytelnik.getFirstname();
        this.lastname = czytelnik.getLastname();
    }

    /**
     * Konstruktor domyślny, który inicjalizuje pola domyślnymi wartościami.
     * <ul>
     *   <li>ID - wartość domyślna: -1</li>
     *   <li>Imię - wartość domyślna: pusta wartość</li>
     *   <li>Nazwisko - wartość domyślna: pusta wartość</li>
     * </ul>
     */
    public CCzytelnik() {
        this.id = -1;
        this.firstname = "";
        this.lastname = "";
    }

    /**
     * Konstruktor inicjalizujący obiekt ze wskazanym tekstem błędu.
     * <ul>
     *   <li>ID - wartość domyślna: -1</li>
     *   <li>Imię - wartość: tekst błędu</li>
     *   <li>Nazwisko - wartość domyślna: pusta wartość</li>
     * </ul>
     *
     * @param errorText tekst błędu przypisany jako imię
     */
    public CCzytelnik(String errorText) {
        this.id = -1;
        this.firstname = errorText;
        this.lastname = "";
    }
}
