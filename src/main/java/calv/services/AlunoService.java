package calv.services;

import java.util.Optional;
import calv.entities.Aluno;

public interface AlunoService
{
    Optional<Aluno> findByEmail(String email);
}
