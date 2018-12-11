package test.businessLogic;

import test.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.repos.TestRepo;

@Service
public class TestBll {
    @Autowired
    TestRepo testRepo;

    public void login(int i) {
        testRepo.findById(i);
    }

    public TestEntity finddd(String s) {
        return testRepo.findByField(s);
    }
}
