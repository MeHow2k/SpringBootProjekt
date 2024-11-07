package controllers.repositories;


import controllers.models.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DBUser, Long> {
	DBUser findByUsername(String name);
}
