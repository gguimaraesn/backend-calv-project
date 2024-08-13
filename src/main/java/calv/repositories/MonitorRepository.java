package calv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import calv.entities.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>
{   
}