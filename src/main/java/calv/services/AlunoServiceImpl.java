package calv.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import calv.entities.Aluno;
import calv.repositories.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Optional<Aluno> findByEmail(String email)
    {
        return this.alunoRepository.findByEmail(email);
    }
}
