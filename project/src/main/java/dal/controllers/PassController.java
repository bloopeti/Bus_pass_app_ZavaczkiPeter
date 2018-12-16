package dal.controllers;

import bll.crud.PassBll;
import dal.entities.IdWrapper;
import dal.entities.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pass")
public class PassController {
    @Autowired
    PassBll passBll;

    @GetMapping(value = "/getAll")
    public List<Pass> getAllPasses() {
        return passBll.getAllPasses();
    }

    @GetMapping(value = "/get/{id}")
    public Pass getPassById(@PathVariable("id") int id) {
        return passBll.getPassById(id);
    }

    @PostMapping(value = "/add")
    public void addPass(@RequestBody Pass pass) {
        passBll.addPass(pass);
    }

    @PostMapping(value = "/update")
    public String updatePass(@RequestBody Pass pass) {
        return passBll.updatePass(pass);
    }

    @PostMapping(value = "/delete")
    public void deletePass(@RequestBody IdWrapper idWrapper) {
        passBll.deletePass(idWrapper.getId());
    }
}
