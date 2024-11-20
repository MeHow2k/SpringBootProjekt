package controllers.repositories;

import controllers.models.DBRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium dla encji {@link DBRole}.
 * Umożliwia dostęp do operacji dla tabeli przechowującej dane o rolach użytkowników.
 */
public interface RoleRepository extends JpaRepository<DBRole, Long> {
    // Dodatkowe metody do wyszukiwania ról, jeśli zajdzie potrzeba, mogą być dodane tutaj.
}
