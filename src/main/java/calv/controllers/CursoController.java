package calv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import calv.entities.Curso;
import calv.entities.Monitor;
import calv.entities.Professor;
import calv.repositories.CursoRepository;
import calv.repositories.MonitorRepository;
import calv.repositories.ProfessorRepository; 
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController
{
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        // Validate and set Monitor
        if (curso.getMonitor() != null) {
            Optional<Monitor> monitor = monitorRepository.findById(curso.getMonitor().getId());
            if (monitor.isPresent()) {
                curso.setMonitor(monitor.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        if (curso.getProfessor() != null) {
            Optional<Professor> professor = professorRepository.findById(curso.getProfessor().getId());
            if (professor.isPresent()) {
                curso.setProfessor(professor.get());
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        Curso savedCurso = cursoRepository.save(curso);
        return ResponseEntity.status(201).body(savedCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso cursoDetails) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNome(cursoDetails.getNome());
            curso.setMateria(cursoDetails.getMateria());

            if (cursoDetails.getMonitor() != null) {
                Optional<Monitor> monitor = monitorRepository.findById(cursoDetails.getMonitor().getId());
                if (monitor.isPresent()) {
                    curso.setMonitor(monitor.get());
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }

            if (cursoDetails.getProfessor() != null) {
                Optional<Professor> professor = professorRepository.findById(cursoDetails.getProfessor().getId());
                if (professor.isPresent()) {
                    curso.setProfessor(professor.get());
                } else {
                    return ResponseEntity.badRequest().build();
                }
            }

            Curso updatedCurso = cursoRepository.save(curso);
            return ResponseEntity.ok(updatedCurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            cursoRepository.delete(optionalCurso.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
