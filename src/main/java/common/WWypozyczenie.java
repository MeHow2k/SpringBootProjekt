package common;

import controllers.models.Wypozyczenie;
import lombok.Data;

/**
 * Klasa reprezentująca wypożyczenie w systemie bibliotecznym.
 * Zawiera informacje o dacie wypożyczenia, dacie zwrotu, książce oraz czytelniku.
 *
 * Klasa umożliwia tworzenie instancji na podstawie obiektu klasy
 * {@link controllers.models.Wypozyczenie}, jak również reprezentację stanu błędu.
 *
 *  @author Michał Pasieka
 *  @version 1.0, 20.11.2024
 */
@Data
public class WWypozyczenie {

    /** ID wypożyczenia. */
    private int id;

    /** Data wypożyczenia w formacie tekstowym. */
    private String date;

    /** Data zwrotu w formacie tekstowym. */
    private String returndate;

    /** Tytuł książki związanej z wypożyczeniem. */
    private String ksiazkaTitle;

    /** Autor książki związanej z wypożyczeniem. */
    private String ksiazkaAuthor;

    /** Imię czytelnika związane z wypożyczeniem. */
    private String czytelnikFirstname;

    /** Nazwisko czytelnika związane z wypożyczeniem. */
    private String czytelnikLastname;

    /**
     * Konstruktor inicjalizujący podstawowe informacje o wypożyczeniu.
     *
     * @param id          identyfikator wypożyczenia
     * @param date        data wypożyczenia
     * @param returndate  data zwrotu
     */
    public WWypozyczenie(int id, String date, String returndate) {
        this.id = id;
        this.date = date;
        this.returndate = returndate;
    }

    /**
     * Konstruktor konwertujący, który tworzy nową instancję na podstawie
     * obiektu klasy {@link controllers.models.Wypozyczenie}.
     *
     * @param wypozyczenie obiekt klasy {@code Wypozyczenie}, który ma zostać skonwertowany
     */
    public WWypozyczenie(Wypozyczenie wypozyczenie) {
        this.id = wypozyczenie.getId();
        this.date = wypozyczenie.getDate();
        this.returndate = wypozyczenie.getReturndate();

        if (wypozyczenie.getKsiazka() != null) {
            this.ksiazkaTitle = wypozyczenie.getKsiazka().getTitle();
            this.ksiazkaAuthor = wypozyczenie.getKsiazka().getAuthor();
        } else {
            this.ksiazkaTitle = "nie przypisano";
            this.ksiazkaAuthor = "nie przypisano";
        }

        if (wypozyczenie.getCzytelnik() != null) {
            this.czytelnikFirstname = wypozyczenie.getCzytelnik().getFirstname();
            this.czytelnikLastname = wypozyczenie.getCzytelnik().getLastname();
        } else {
            this.czytelnikFirstname = "nie przypisano";
            this.czytelnikLastname = "nie przypisano";
        }
    }

    /**
     * Konstruktor domyślny, który inicjalizuje pola domyślnymi wartościami.
     * <ul>
     *   <li>ID - wartość domyślna: -1</li>
     *   <li>Data wypożyczenia - wartość domyślna: pusta wartość</li>
     *   <li>Data zwrotu - wartość domyślna: pusta wartość</li>
     * </ul>
     */
    public WWypozyczenie() {
        this.id = -1;
        this.date = "";
        this.returndate = "";
    }

    /**
     * Konstruktor inicjalizujący obiekt w stanie błędu.
     *
     * @param errorText tekst błędu (niewykorzystywany w tej implementacji)
     */
    public WWypozyczenie(String errorText) {
        this.id = -1;
        this.date = "";
        this.returndate = "";
    }
}
