package controllers.repositories;

import controllers.models.Czytelnik;
import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CzytelnikRepository extends JpaRepository<Czytelnik, Integer> {

    public Czytelnik findById(int id);

}