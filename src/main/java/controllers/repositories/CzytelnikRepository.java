package controllers.repositories;

import controllers.models.Czytelnik;
import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium dla encji {@link Czytelnik}.
 * Umożliwia dostęp do operacji dla tabeli przechowującej dane o czytelnikach.
 *   @author Michał Pasieka
 *   @version 1.0, 20.11.2024
 */
@Repository
public interface CzytelnikRepository extends JpaRepository<Czytelnik, Integer> {

    /**
     * Wyszukuje czytelnika po jego identyfikatorze.
     *
     * @param id identyfikator czytelnika
     * @return obiekt {@link Czytelnik} odpowiadający podanemu identyfikatorowi
     */
    public Czytelnik findById(int id);

}
