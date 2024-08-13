package calv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import calv.entities.Professor;
import calv.repositories.ProfessorRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController 
{

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            return ResponseEntity.ok(professor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @RequestBody Professor professorDetails) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            professor.setName(professorDetails.getName());
            professor.setUsername(professorDetails.getUsername());
            professor.setTelefone(professorDetails.getTelefone());
            professor.setEmail(professorDetails.getEmail());
            Professor updatedProfessor = professorRepository.save(professor);
            return ResponseEntity.ok(updatedProfessor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (optionalProfessor.isPresent()) {
            professorRepository.delete(optionalProfessor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}