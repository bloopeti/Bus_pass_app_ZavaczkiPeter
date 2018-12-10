package repos;

import entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TestRepo extends JpaRepository<TestEntity, Integer> {
    TestEntity findByField(String field);
}
