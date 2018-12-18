package bll.controllers;

import bll.crud.BusBll;
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
    public List<Bus> getAllBuses() {
        return busBll.getAllBuses();
    }

    @GetMapping(value = "/get/{id}")
    public Bus getBusById(@PathVariable("id") int id) {
        return busBll.getBusById(id);
    }

    @PostMapping(value = "/add")
    public void addBus(@RequestBody Bus bus) {
        busBll.addBus(bus);
    }

    @PostMapping(value = "/update")
    public String updateBus(@RequestBody Bus bus) {
        return busBll.updateBus(bus);
    }

    @PostMapping(value = "/delete")
    public void deleteBus(@RequestBody IdWrapper idWrapper) {
        busBll.deleteBus(idWrapper.getId());
    }
}