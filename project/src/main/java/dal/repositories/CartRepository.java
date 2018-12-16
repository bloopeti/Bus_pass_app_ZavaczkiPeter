package dal.repositories;

import dal.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
