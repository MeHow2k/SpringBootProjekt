package common;

import controllers.models.Wypozyczenie;
import lombok.Data;

@Data
public class WWypozyczenie {
    private int id;
    private String date;
    private String returndate;
    private String ksiazkaTitle;
    private String ksiazkaAuthor;
    private String czytelnikFirstname;
    private String czytelnikLastname;

    public WWypozyczenie(int id, String date, String returndate) {
        this.id = id;
        this.date = date;
        this.returndate = returndate;
    }

    public WWypozyczenie(Wypozyczenie wypozyczenie) {
        this.id = wypozyczenie.getId();
        this.date = wypozyczenie.getDate();
        this.returndate = wypozyczenie.getReturndate();
        if (wypozyczenie.getKsiazka() != null) {
            this.ksiazkaTitle = wypozyczenie.getKsiazka().getTitle();
            this.ksiazkaAuthor = wypozyczenie.getKsiazka().getAuthor();
        }else {
            this.ksiazkaTitle = "nie przypisano";
            this.ksiazkaAuthor = "nie przypisano";
        }
            if (wypozyczenie.getCzytelnik() != null) {
            this.czytelnikFirstname = wypozyczenie.getCzytelnik().getFirstname();
            this.czytelnikLastname = wypozyczenie.getCzytelnik().getLastname();
        }else {
            this.czytelnikFirstname = "nie przypisano";
            this.czytelnikLastname = "nie przypisano";
        }
    }

    public WWypozyczenie() {
        this.id = -1;
        this.date = "";
        this.returndate = "";
    }

    public WWypozyczenie(String errorText) {
        this.id = -1;
        this.date = "";
        this.returndate = "";
    }
}
