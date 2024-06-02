package controllers.repositories;

import controllers.models.Ksiazka;
import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KsiazkaRepository extends JpaRepository<Ksiazka, Integer> {


    public Ksiazka findById(int id);


}