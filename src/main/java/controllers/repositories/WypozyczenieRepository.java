package controllers.repositories;

import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozytorium dla encji {@link Wypozyczenie}.
 * Umożliwia dostęp do operacji dla tabeli przechowującej dane o wypożyczeniach książek.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
@Repository
public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Integer> {

    /**
     * Wyszukuje wypożyczenie na podstawie jego identyfikatora.
     *
     * @param id identyfikator wypożyczenia
     * @return obiekt {@link Wypozyczenie} odpowiadający podanemu identyfikatorowi
     */
    public Wypozyczenie findById(int id);

    /**
     * Wyszukuje wszystkie wypożyczenia przypisane do konkretnego czytelnika.
     *
     * @param id identyfikator czytelnika
     * @return lista wypożyczeń powiązanych z czytelnikiem o podanym identyfikatorze
     */
    public List<Wypozyczenie> findByCzytelnikId(int id);

    /**
     * Wyszukuje wszystkie wypożyczenia przypisane do konkretnej książki.
     *
     * @param id identyfikator książki
     * @return lista wypożyczeń powiązanych z książką o podanym identyfikatorze
     */
    public List<Wypozyczenie> findByKsiazkaId(int id);

    /**
     * Wyszukuje wypożyczenia na podstawie daty zwrotu książki.
     *
     * @param text tekstowy ciąg daty zwrotu książki
     * @return lista wypożyczeń, które mają określoną datę zwrotu
     */
    public List<Wypozyczenie> findByReturndate(String text);
}
