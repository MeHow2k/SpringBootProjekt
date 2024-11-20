package controllers.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Reprezentuje encję książki w systemie bibliotecznym.
 *
 * Klasa jest mapowana na tabelę w bazie danych za pomocą JPA.
 * Każda książka posiada unikalny identyfikator, tytuł, autora oraz numer ISBN.
 * Dodatkowo przechowuje informacje o wypożyczeniach związanych z daną książką.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Entity
public class Ksiazka {

    /**
     * Unikalny identyfikator książki (klucz główny).
     * Generowany automatycznie przy użyciu strategii {@link GenerationType#IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** Tytuł książki. */
    private String title;

    /** Autor książki. */
    private String author;

    /** Numer ISBN książki. */
    private String ISBN;

    /**
     * Kolekcja wypożyczeń związanych z daną książką.
     * Relacja jeden-do-wielu, mapowana przez pole `ksiazka` w klasie {@link Wypozyczenie}.
     */
    @OneToMany(mappedBy = "ksiazka")
    private Set<Wypozyczenie> wypozyczenie;

    /**
     * Konstruktor domyślny (potrzebny dla JPA).
     * Inicjalizuje pusty zbiór wypożyczeń.
     */
    protected Ksiazka() {
        // wypozyczenie = new HashSet<Wypozyczenie>();
    }

    /**
     * Konstruktor tworzący obiekt książki z podanym tytułem, autorem i numerem ISBN.
     *
     * @param name   tytuł książki
     * @param author autor książki
     * @param ISBN   numer ISBN książki
     */
    public Ksiazka(String name, String author, String ISBN) {
        this.title = name;
        this.author = author;
        this.ISBN = ISBN;
        wypozyczenie = new HashSet<Wypozyczenie>();
    }

    /**
     * Zwraca reprezentację tekstową książki, zawierającą jej identyfikator, tytuł, autora i ISBN.
     *
     * @return String z informacjami o książce
     */
    @Override
    public String toString() {
        return String.format("(id=%d, title='%s', author='%s', ISBN='%s')", id, title, author, ISBN);
    }

    /**
     * Pobiera identyfikator książki.
     *
     * @return identyfikator książki
     */
    public int getId() {
        return id;
    }

    /**
     * Ustawia identyfikator książki.
     *
     * @param id nowy identyfikator książki
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Pobiera tytuł książki.
     *
     * @return tytuł książki
     */
    public String getTitle() {
        return title;
    }

    /**
     * Ustawia tytuł książki.
     *
     * @param title nowy tytuł książki
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Pobiera autora książki.
     *
     * @return autor książki
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Ustawia autora książki.
     *
     * @param author nowy autor książki
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Pobiera numer ISBN książki.
     *
     * @return numer ISBN książki
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Ustawia numer ISBN książki.
     *
     * @param ISBN nowy numer ISBN książki
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Pobiera zbiór wypożyczeń związanych z książką.
     *
     * @return zbiór wypożyczeń książki
     */
    public Set<Wypozyczenie> getWypozyczenie() {
        return wypozyczenie;
    }

    /**
     * Ustawia zbiór wypożyczeń związanych z książką.
     *
     * @param wypozyczenie nowy zbiór wypożyczeń
     */
    public void setWypozyczenie(Set<Wypozyczenie> wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }

    /**
     * Czyści zbiór wypożyczeń przypisanych do książki.
     * Inicjalizuje go jako nowy pusty zbiór.
     */
    public void clearWypozyczenie() {
        wypozyczenie = new HashSet<Wypozyczenie>();
    }
}
