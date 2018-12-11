package dal.repositories;

import dal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import dal.entities.PurchasedPass;

@Service
public interface PurchasedPassRepository extends JpaRepository<PurchasedPass, Integer> {
    PurchasedPass findByUser(User user);
}
