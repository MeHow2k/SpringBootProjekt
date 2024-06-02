package common;

import controllers.models.Ksiazka;
import lombok.Data;

@Data
public class KKsiazka {
    private int id;

    private String title;
    private String author;
    private String ISBN;

    public KKsiazka(int id, String title, String author, String ISBN) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;

    }

    public KKsiazka(KKsiazka ksiazka) {
        this.id = ksiazka.getId();
        this.title = ksiazka.getTitle();
        this.author = ksiazka.getAuthor();
        this.ISBN = ksiazka.getISBN();
    }

    public KKsiazka(Ksiazka ksiazka) {
        this.id = ksiazka.getId();
        this.title = ksiazka.getTitle();
        this.author = ksiazka.getAuthor();
        this.ISBN = ksiazka.getISBN();

    }

    // Konstruktor domyślny
    public KKsiazka() {
        this.id = -1;
        this.title = "";
        this.author = "";
        this.ISBN = "";

    }

    // Konstruktor z tekstem błędu
    public KKsiazka(String errorText) {
        this.id = -1;
        this.title = errorText;
        this.author = "";
        this.ISBN = "";

    }
}
