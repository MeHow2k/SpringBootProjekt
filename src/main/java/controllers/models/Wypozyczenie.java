package controllers.models;

import lombok.Data;

import javax.persistence.*;

@Data //Adnotacja @Data wystarczy aby Lombok wygenerował getery, setery, toString, equals i inne
@Entity
@Table(name = "Wypozyczenia")
public class Wypozyczenie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "date")
    String date;

    @Column(name = "returndate")
    String returndate;

    @ManyToOne
    private Ksiazka ksiazka;

    @ManyToOne
    private Czytelnik czytelnik;

    public Wypozyczenie(String date, String returndate) {
        //this.id = id;
        this.date = date;
        this.returndate = returndate;
    }


    public Wypozyczenie() {
        //this.id = -1;
        this.date = "";
        this.returndate = "";
    }


    public Wypozyczenie(String date) {
        //this.id = id;

        this.date = date;
        this.returndate = "nie oddano";
    }

    public Ksiazka getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazka ksiazka) {
        this.ksiazka = ksiazka;
    }

    public Czytelnik getCzytelnik() {
        return czytelnik;
    }

    public void setCzytelnik(Czytelnik czytelnik) {
        this.czytelnik = czytelnik;
    }

}
