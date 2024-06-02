package controllers.repositories;
//Dodanie danych na samym poczatku

import controllers.models.Czytelnik;
import controllers.models.Ksiazka;
import controllers.models.Wypozyczenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader2 implements ApplicationRunner {



    private WypozyczenieRepository wypRepository;
    private KsiazkaRepository ksiazkaRepository;
    private CzytelnikRepository czytelnikRepository;

    @Autowired
    public DataLoader2(WypozyczenieRepository wypRepository, KsiazkaRepository ksiazkaRepository,CzytelnikRepository czytelnikRepository)
    {
        this.wypRepository = wypRepository;
        this.ksiazkaRepository = ksiazkaRepository;
        this.czytelnikRepository = czytelnikRepository;
    }



    public void run(ApplicationArguments args)
    {
        try
        {

//            Wypozyczenie wyp1 = new Wypozyczenie("01-01-2024");
//            wypRepository.saveAndFlush(wyp1);
//
//            Wypozyczenie wyp2 = new Wypozyczenie("02-02-2024");
//            wypRepository.saveAndFlush(wyp2);
//
//            Wypozyczenie wyp3 = new Wypozyczenie("03-03-2024");
//            wypRepository.saveAndFlush(wyp3);
//
//            Wypozyczenie wyp4 = new Wypozyczenie("03-03-2023","22-02-2024");
//            wypRepository.saveAndFlush(wyp4);
//
//            Ksiazka k1 = new Ksiazka("Tytul1","Autor1","1111111");
//            ksiazkaRepository.saveAndFlush(k1);
//
//            Ksiazka k2 = new Ksiazka("Tytul2","Autor2","22222222");
//            ksiazkaRepository.saveAndFlush(k2);
//
//            Ksiazka k3 = new Ksiazka("Tytul2","Autor2","33333333");
//            ksiazkaRepository.saveAndFlush(k3);
//
//            Czytelnik c1 = new Czytelnik("Adam","Nowak");
//            czytelnikRepository.saveAndFlush(c1);
//            Czytelnik c2 = new Czytelnik("Anna","Kowal");
//            czytelnikRepository.saveAndFlush(c2);
//            Czytelnik c3 = new Czytelnik("Ewa","Wójcik");
//            czytelnikRepository.saveAndFlush(c3);
//
//            wyp1.setKsiazka(k3);
//            wyp2.setKsiazka(k2);
//            wyp3.setKsiazka(k1);
//            wyp4.setKsiazka(k2);
//
//            wyp1.setCzytelnik(c1);
//            wyp2.setCzytelnik(c2);
//            wyp3.setCzytelnik(c3);
//            wyp4.setCzytelnik(c1);
//
//            wypRepository.saveAndFlush(wyp1);
//            wypRepository.saveAndFlush(wyp2);
//            wypRepository.saveAndFlush(wyp3);
//            wypRepository.saveAndFlush(wyp4);
//
////            System.out.println(wyp1);
////            System.out.println(wyp2);
////            System.out.println(wyp3);
////            System.out.println(wyp4);
////
////            System.out.println(k1);
////            System.out.println(k2);
////
////            System.out.println(c1);
////            System.out.println(c2);
////            System.out.println(c3);



// Książki
            List<Ksiazka> ksiazki = Arrays.asList(
                    new Ksiazka("Władca Pierścieni", "J.R.R. Tolkien", "978-83-7659-394-4"),
                    new Ksiazka("Hobbit", "J.R.R. Tolkien", "978-83-7469-613-5"),
                    new Ksiazka("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "978-83-7184-755-4"),
                    new Ksiazka("Gra o Tron", "George R.R. Martin", "978-83-7480-197-4"),
                    new Ksiazka("Rok 1984", "George Orwell", "978-83-287-1534-0"),
                    new Ksiazka("Duma i uprzedzenie", "Jane Austen", "978-83-7885-876-8"),
                    new Ksiazka("Zbrodnia i kara", "Fiodor Dostojewski", "978-83-233-3366-1"),
                    new Ksiazka("Mistrz i Małgorzata", "Michaił Bułhakow", "978-83-7392-450-1"),
                    new Ksiazka("Lalka", "Bolesław Prus", "978-83-7392-460-0"),
                    new Ksiazka("Pan Tadeusz", "Adam Mickiewicz", "978-83-7392-470-9"),
                    new Ksiazka("Potop", "Henryk Sienkiewicz", "978-83-7392-480-8"),
                    new Ksiazka("Quo Vadis", "Henryk Sienkiewicz", "978-83-7392-490-7"),
                    new Ksiazka("Krzyżacy", "Henryk Sienkiewicz", "978-83-7392-500-3"),
                    new Ksiazka("W pustyni i w puszczy", "Henryk Sienkiewicz", "978-83-7392-510-2"),
                    new Ksiazka("Chłopi", "Władysław Reymont", "978-83-7392-520-1"),
                    new Ksiazka("Ferdydurke", "Witold Gombrowicz", "978-83-7392-530-0"),
                    new Ksiazka("Solaris", "Stanisław Lem", "978-83-7392-540-9"),
                    new Ksiazka("Wiedźmin", "Andrzej Sapkowski", "978-83-7392-550-8"),
                    new Ksiazka("Mały Książę", "Antoine de Saint-Exupéry", "978-83-7392-560-7"),
                    new Ksiazka("Bracia Karamazow", "Fiodor Dostojewski", "978-83-7392-570-6")
            );

            ksiazki.forEach(ksiazka -> ksiazkaRepository.saveAndFlush(ksiazka));

// Czytelnicy
            List<Czytelnik> czytelnicy = Arrays.asList(
                    new Czytelnik("Adam", "Nowak"),
                    new Czytelnik("Anna", "Kowal"),
                    new Czytelnik("Ewa", "Wójcik"),
                    new Czytelnik("Jan", "Kowalski"),
                    new Czytelnik("Piotr", "Wiśniewski"),
                    new Czytelnik("Paweł", "Wróbel"),
                    new Czytelnik("Marek", "Kozłowski"),
                    new Czytelnik("Tomasz", "Jankowski"),
                    new Czytelnik("Krzysztof", "Mazur"),
                    new Czytelnik("Andrzej", "Lewandowski"),
                    new Czytelnik("Michał", "Zieliński"),
                    new Czytelnik("Rafał", "Szymański"),
                    new Czytelnik("Dariusz", "Wojciechowski"),
                    new Czytelnik("Łukasz", "Kwiatkowski"),
                    new Czytelnik("Grzegorz", "Krawczyk"),
                    new Czytelnik("Marcin", "Kaczmarek"),
                    new Czytelnik("Kamil", "Piotrowski"),
                    new Czytelnik("Jarosław", "Grabowski"),
                    new Czytelnik("Patryk", "Pawlak"),
                    new Czytelnik("Dominik", "Dąbrowski")
            );

            czytelnicy.forEach(czytelnik -> czytelnikRepository.saveAndFlush(czytelnik));

// Wypożyczenia
            List<Wypozyczenie> wypozyczenia = Arrays.asList(
                    new Wypozyczenie("01-01-2024"),
                    new Wypozyczenie("02-02-2024"),
                    new Wypozyczenie("03-03-2024","22-03-2024"),
                    new Wypozyczenie("04-04-2024"),
                    new Wypozyczenie("05-05-2024"),
                    new Wypozyczenie("06-06-2024","22-03-2024"),
                    new Wypozyczenie("07-07-2024"),
                    new Wypozyczenie("08-08-2024"),
                    new Wypozyczenie("09-09-2023", "22-02-2024"),
                    new Wypozyczenie("01-01-2024"),
                    new Wypozyczenie("02-02-2024"),
                    new Wypozyczenie("03-03-2024"),
                    new Wypozyczenie("04-04-2024"),
                    new Wypozyczenie("05-05-2024"),
                    new Wypozyczenie("06-06-2024"),
                    new Wypozyczenie("07-07-2024"),
                    new Wypozyczenie("08-08-2024"),
                    new Wypozyczenie("10-10-2023", "22-03-2024"),
                    new Wypozyczenie("07-07-2024"),
                    new Wypozyczenie("08-08-2024")
            );

            wypozyczenia.get(0).setKsiazka(ksiazki.get(0)); wypozyczenia.get(0).setCzytelnik(czytelnicy.get(9));
            wypozyczenia.get(1).setKsiazka(ksiazki.get(1)); wypozyczenia.get(1).setCzytelnik(czytelnicy.get(8));
            wypozyczenia.get(2).setKsiazka(ksiazki.get(2)); wypozyczenia.get(2).setCzytelnik(czytelnicy.get(7));
            wypozyczenia.get(3).setKsiazka(ksiazki.get(3)); wypozyczenia.get(3).setCzytelnik(czytelnicy.get(6));
            wypozyczenia.get(4).setKsiazka(ksiazki.get(4)); wypozyczenia.get(4).setCzytelnik(czytelnicy.get(5));
            wypozyczenia.get(5).setKsiazka(ksiazki.get(5)); wypozyczenia.get(5).setCzytelnik(czytelnicy.get(4));
            wypozyczenia.get(6).setKsiazka(ksiazki.get(6)); wypozyczenia.get(6).setCzytelnik(czytelnicy.get(3));
            wypozyczenia.get(7).setKsiazka(ksiazki.get(7)); wypozyczenia.get(7).setCzytelnik(czytelnicy.get(2));
            wypozyczenia.get(8).setKsiazka(ksiazki.get(8)); wypozyczenia.get(8).setCzytelnik(czytelnicy.get(4));
            wypozyczenia.get(9).setKsiazka(ksiazki.get(9)); wypozyczenia.get(9).setCzytelnik(czytelnicy.get(2));
            wypozyczenia.get(10).setKsiazka(ksiazki.get(5)); wypozyczenia.get(10).setCzytelnik(czytelnicy.get(13));
            wypozyczenia.get(11).setKsiazka(ksiazki.get(11)); wypozyczenia.get(11).setCzytelnik(czytelnicy.get(19));
            wypozyczenia.get(12).setKsiazka(ksiazki.get(14)); wypozyczenia.get(12).setCzytelnik(czytelnicy.get(7));
            wypozyczenia.get(13).setKsiazka(ksiazki.get(2)); wypozyczenia.get(13).setCzytelnik(czytelnicy.get(0));
            wypozyczenia.get(14).setKsiazka(ksiazki.get(18)); wypozyczenia.get(14).setCzytelnik(czytelnicy.get(3));
            wypozyczenia.get(15).setKsiazka(ksiazki.get(6)); wypozyczenia.get(15).setCzytelnik(czytelnicy.get(9));
            wypozyczenia.get(16).setKsiazka(ksiazki.get(12)); wypozyczenia.get(16).setCzytelnik(czytelnicy.get(11));
            wypozyczenia.get(17).setKsiazka(ksiazki.get(1)); wypozyczenia.get(17).setCzytelnik(czytelnicy.get(2));
            wypozyczenia.get(18).setKsiazka(ksiazki.get(8)); wypozyczenia.get(18).setCzytelnik(czytelnicy.get(15));
            wypozyczenia.get(19).setKsiazka(ksiazki.get(4)); wypozyczenia.get(19).setCzytelnik(czytelnicy.get(5));


            wypozyczenia.forEach(wypozyczenie -> wypRepository.saveAndFlush(wypozyczenie));


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}



