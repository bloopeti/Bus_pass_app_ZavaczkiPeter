package test.controller;

import test.businessLogic.TestBll;
import test.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.repos.TestRepo;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    TestRepo testRepo;

    @Autowired
    TestBll testBll;

    @GetMapping(value = "/getAll")
    public List<TestEntity> getAllTestEntities() {
        return testRepo.findAll();
    }

    @PostMapping(value = "/addStuff")
    public void addStuff(@RequestBody TestEntity testEntity) {
        testRepo.save(testEntity);
    }

    @GetMapping(value = "/pula")
    public TestEntity getttt(@RequestParam(value = "fild") String s) {
        return testBll.finddd(s);
    }

    @GetMapping(value = "/caca/{id}")
    public TestEntity reqPartEx(@PathVariable("id") int id) {
        return testRepo.findById(id).get();
    }
}
