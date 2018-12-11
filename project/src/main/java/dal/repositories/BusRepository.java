package dal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import dal.entities.Bus;

@Service
public interface BusRepository extends JpaRepository<Bus, Integer> {
    Bus findByLine(String line);
}
