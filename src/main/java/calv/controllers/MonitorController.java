package calv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import calv.entities.Monitor;
import calv.repositories.MonitorRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monitores")
public class MonitorController 
{
    @Autowired
    private MonitorRepository monitorRepository;

    @GetMapping
    public List<Monitor> getAllMonitores() {
        return monitorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monitor> getMonitorById(@PathVariable Long id) {
        Optional<Monitor> monitor = monitorRepository.findById(id);
        if (monitor.isPresent()) {
            return ResponseEntity.ok(monitor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Monitor createMonitor(@RequestBody Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monitor> updateMonitor(@PathVariable Long id, @RequestBody Monitor monitorDetails) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(id);
        if (optionalMonitor.isPresent()) {
            Monitor monitor = optionalMonitor.get();
            monitor.setName(monitorDetails.getName());
            monitor.setUsername(monitorDetails.getUsername());
            monitor.setTelefone(monitorDetails.getTelefone());
            monitor.setEmail(monitorDetails.getEmail());
            Monitor updatedMonitor = monitorRepository.save(monitor);
            return ResponseEntity.ok(updatedMonitor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable Long id) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(id);
        if (optionalMonitor.isPresent()) {
            monitorRepository.delete(optionalMonitor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}