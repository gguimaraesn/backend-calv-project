package calv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import calv.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>
{   
}
