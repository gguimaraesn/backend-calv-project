package calv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import calv.entities.Aluno;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>
{
    Optional<Aluno> findByEmail(String email);
}
