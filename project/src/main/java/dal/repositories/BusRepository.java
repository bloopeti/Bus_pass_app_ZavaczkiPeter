package dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import dal.entities.Bus;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus findByLine(String line);
}
