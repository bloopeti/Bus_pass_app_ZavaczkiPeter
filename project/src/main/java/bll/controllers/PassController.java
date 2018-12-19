package bll.controllers;

import bll.crud.PassBll;
import bll.dtos.PassDTO;
import bll.wrappers.IdWrapper;
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
    public List<PassDTO> getAllPasses() {
        return passBll.getAllPasses();
    }

    @GetMapping(value = "/get/{id}")
    public PassDTO getPassById(@PathVariable("id") int id) {
        return passBll.getPassById(id);
    }

    @PostMapping(value = "/add")
    public String addPass(@RequestBody PassDTO pass) {
        return passBll.addPass(pass);
    }

    @PostMapping(value = "/update")
    public String updatePass(@RequestBody PassDTO pass) {
        return passBll.updatePass(pass);
    }

    @PostMapping(value = "/delete")
    public String deletePass(@RequestBody IdWrapper idWrapper) {
        return passBll.deletePass(idWrapper.getId());
    }
}
