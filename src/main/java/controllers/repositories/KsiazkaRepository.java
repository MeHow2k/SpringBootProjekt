package controllers.repositories;

import controllers.models.Czytelnik;
import controllers.models.Ksiazka;
import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repozytorium dla encji {@link Ksiazka}.
 * Umożliwia dostęp do operacji dla tabeli przechowującej dane o książkach.
 *   @author Michał Pasieka
 *   @version 1.0, 20.11.2024
 */
@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, Integer> {

    /**
     * Wyszukuje książkę po jej identyfikatorze.
     *
     * @param id identyfikator książki
     * @return obiekt {@link Ksiazka} odpowiadający podanemu identyfikatorowi
     */
    public Ksiazka findById(int id);


}