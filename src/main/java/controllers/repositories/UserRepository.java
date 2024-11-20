package controllers.repositories;

import controllers.models.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozytorium dla encji {@link DBUser}.
 * Umożliwia dostęp do operacji CRUD dla tabeli przechowującej dane o użytkownikach.
 * @author Michał Pasieka
 * @version 1.0, 20.11.2024
 */
public interface UserRepository extends JpaRepository<DBUser, Long> {

	/**
	 * Znajduje użytkownika na podstawie jego nazwy użytkownika (username).
	 *
	 * @param name Nazwa użytkownika.
	 * @return Użytkownik o podanej nazwie użytkownika, lub null, jeśli użytkownik nie istnieje.
	 */
	DBUser findByUsername(String name);
}
