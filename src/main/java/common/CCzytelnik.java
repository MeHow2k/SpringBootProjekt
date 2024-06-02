package common;

import controllers.models.Czytelnik;
import controllers.models.Wypozyczenie;
import lombok.Data;

@Data
public class CCzytelnik {
    private int id;

    private String firstname;
    private String lastname;


    public CCzytelnik(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    public CCzytelnik(CCzytelnik czytelnik) {
        this.id = czytelnik.getId();
        this.firstname = czytelnik.getFirstname();
        this.lastname = czytelnik.getLastname();
    }

    public CCzytelnik(Czytelnik czytelnik) {
        this.id = czytelnik.getId();
        this.firstname = czytelnik.getFirstname();
        this.lastname = czytelnik.getLastname();

    }

    // Konstruktor domyślny
    public CCzytelnik() {
        this.id = -1;
        this.firstname = "";
        this.lastname = "";
    }

    // Konstruktor z tekstem błędu
    public CCzytelnik(String errorText) {
        this.id = -1;
        this.firstname = errorText;
        this.lastname = "";
    }
}
