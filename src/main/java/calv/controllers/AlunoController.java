package calv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import calv.entities.Aluno;
import calv.repositories.AlunoRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController 
{
    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> getAllAlunos()
    {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id)
    {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) 
        {
            return ResponseEntity.ok(aluno.get());
        } else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno)
    {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails)
    {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) 
        {
            Aluno aluno = optionalAluno.get();
            aluno.setName(alunoDetails.getName());
            aluno.setUsername(alunoDetails.getUsername());
            aluno.setTelefone(alunoDetails.getTelefone());
            aluno.setEmail(alunoDetails.getEmail());
            Aluno updatedAluno = alunoRepository.save(aluno);
            return ResponseEntity.ok(updatedAluno);
        } else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id)
    {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) 
        {
            alunoRepository.delete(optionalAluno.get());
            return ResponseEntity.noContent().build();
        } else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}
