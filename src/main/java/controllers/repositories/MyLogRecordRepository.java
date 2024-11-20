package controllers.repositories;

import controllers.models.MyLogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium dla encji {@link MyLogRecord}.
 * Umożliwia dostęp do operacji dla tabeli przechowującej dane logów.
 */
public interface MyLogRecordRepository extends JpaRepository<MyLogRecord, Long> {
    // Interfejs nie wymaga dodatkowych metod, ponieważ JpaRepository zapewnia wszystkie standardowe operacje CRUD
}
