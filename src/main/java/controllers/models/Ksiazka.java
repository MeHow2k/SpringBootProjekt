package controllers.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


    @Entity
    public class Ksiazka {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private int id;
        private String title;
        private String author;
        private String ISBN;

        @OneToMany(mappedBy = "ksiazka")
        private Set<Wypozyczenie> wypozyczenie;

        protected Ksiazka() {
            //wypozyczenie = new HashSet<Wypozyczenie>();
        }


        public Ksiazka(String name,String author,String ISBN) {
            this.title = name;
            this.author= author;
            this.ISBN=ISBN;
            wypozyczenie = new HashSet<Wypozyczenie>();
        }


        @Override
        public String toString() {
            return String.format(
                    "Ksiazka[id=%d, title='%s' author='%s' ISBN='%s']",id, title,author,ISBN);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }

        public Set<Wypozyczenie> getWypozyczenie() {
            return wypozyczenie;
        }

        public void setWypozyczenie(Set<Wypozyczenie> wypozyczenie) {
            this.wypozyczenie = wypozyczenie;
        }

        public void clearWypozyczenie() {
            wypozyczenie = new HashSet<Wypozyczenie>();
        }


    }


