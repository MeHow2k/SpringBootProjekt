package common;

import controllers.models.Ksiazka;
import lombok.Data;

/**
 * Klasa reprezentująca książkę w systemie bibliotecznym.
 * Zawiera podstawowe informacje o książce, takie jak ID, tytuł, autor oraz ISBN.
 *
 * Klasa udostępnia różne konstruktory do tworzenia instancji na podstawie
 * obiektów tej samej klasy, klasy {@link controllers.models.Ksiazka},
 * a także do reprezentowania stanu błędu.
 *
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Data
public class KKsiazka {

    /** ID książki. */
    private int id;

    /** Tytuł książki. */
    private String title;

    /** Autor książki. */
    private String author;

    /** Numer ISBN książki. */
    private String ISBN;

    /**
     * Konstruktor inicjalizujący wszystkie pola.
     *
     * @param id     identyfikator książki
     * @param title  tytuł książki
     * @param author autor książki
     * @param ISBN   numer ISBN książki
     */
    public KKsiazka(int id, String title, String author, String ISBN) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    /**
     * Konstruktor kopiujący, który tworzy nową instancję na podstawie
     * innego obiektu tej samej klasy.
     *
     * @param ksiazka obiekt klasy {@code KKsiazka} do skopiowania
     */
    public KKsiazka(KKsiazka ksiazka) {
        this.id = ksiazka.getId();
        this.title = ksiazka.getTitle();
        this.author = ksiazka.getAuthor();
        this.ISBN = ksiazka.getISBN();
    }

    /**
     * Konstruktor konwertujący, który tworzy nową instancję na podstawie
     * obiektu klasy {@link controllers.models.Ksiazka}.
     *
     * @param ksiazka obiekt klasy {@code Ksiazka}, który ma zostać skonwertowany
     */
    public KKsiazka(Ksiazka ksiazka) {
        this.id = ksiazka.getId();
        this.title = ksiazka.getTitle();
        this.author = ksiazka.getAuthor();
        this.ISBN = ksiazka.getISBN();
    }

    /**
     * Konstruktor domyślny, który inicjalizuje pola domyślnymi wartościami.
     * <ul>
     *   <li>ID - wartość domyślna: -1</li>
     *   <li>Tytuł - wartość domyślna: pusta wartość</li>
     *   <li>Autor - wartość domyślna: pusta wartość</li>
     *   <li>ISBN - wartość domyślna: pusta wartość</li>
     * </ul>
     */
    public KKsiazka() {
        this.id = -1;
        this.title = "";
        this.author = "";
        this.ISBN = "";
    }

    /**
     * Konstruktor inicjalizujący obiekt ze wskazanym tekstem błędu.
     * <ul>
     *   <li>ID - wartość domyślna: -1</li>
     *   <li>Tytuł - wartość: tekst błędu</li>
     *   <li>Autor - wartość domyślna: pusta wartość</li>
     *   <li>ISBN - wartość domyślna: pusta wartość</li>
     * </ul>
     *
     * @param errorText tekst błędu przypisany jako tytuł książki
     */
    public KKsiazka(String errorText) {
        this.id = -1;
        this.title = errorText;
        this.author = "";
        this.ISBN = "";
    }
}
