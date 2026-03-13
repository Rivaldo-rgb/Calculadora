package villa_olimpica.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import villa_olimpica.demo.model.docentes;

@Repository
public interface docentesRepository extends JpaRepository<docentes, Long> {

}