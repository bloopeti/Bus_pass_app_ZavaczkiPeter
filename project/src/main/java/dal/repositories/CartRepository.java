package dal.repositories;

import dal.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
