package controllers.models;

import lombok.Data;

import javax.persistence.*;

@Data //Adnotacja @Data wystarczy aby Lombok wygenerował getery, setery, toString, equals i inne
@Entity
@Table(name = "Czytelnik")
public class Czytelnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;


    public Czytelnik(String firstname, String lastname) {
        //this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

    }

    public Czytelnik() {
        //this.id = -1;
        this.firstname = "";
        this.lastname = "";

    }


    public Czytelnik(String firstname, String lastname, String title, String author, String date) {
        //this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }


}
