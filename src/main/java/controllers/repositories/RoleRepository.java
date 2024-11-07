package controllers.repositories;

import controllers.models.DBRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<DBRole, Long> {}
