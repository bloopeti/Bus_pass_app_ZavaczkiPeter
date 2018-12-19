package bll.controllers;

import bll.crud.BusBll;
import bll.dtos.BusDTO;
import bll.wrappers.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dal.entities.Bus;

import java.util.List;

@RestController
@RequestMapping(value = "/bus")
public class BusController {
    @Autowired
    BusBll busBll;

    @GetMapping(value = "/getAll")
    public List<BusDTO> getAllBuses() {
        return busBll.getAllBuses();
    }

    @GetMapping(value = "/get/{id}")
    public BusDTO getBusById(@PathVariable("id") int id) {
        return busBll.getBusById(id);
    }

    @PostMapping(value = "/add")
    public String addBus(@RequestBody BusDTO bus) {
        return busBll.addBus(bus);
    }

    @PostMapping(value = "/update")
    public String updateBus(@RequestBody BusDTO bus) {
        return busBll.updateBus(bus);
    }

    @PostMapping(value = "/delete")
    public String deleteBus(@RequestBody IdWrapper idWrapper) {
        return busBll.deleteBus(idWrapper.getId());
    }
}