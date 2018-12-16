package bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.entities.Bus;
import dal.repositories.BusRepository;

import java.util.List;

@Service
public class BusBll {
    @Autowired
    BusRepository busRepository;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(int id) {
        if (busRepository.findById(id).isPresent())
            return busRepository.findById(id).get();
        else
            return null;
    }

    public void addBus(Bus bus) {
        busRepository.save(bus);
    }

    public String updateBus(Bus bus) {
        Bus updatedBus;
        if (busRepository.findById(bus.getId()).isPresent()) {
            updatedBus = busRepository.findById(bus.getId()).get();
            updatedBus.setLine(bus.getLine());
            updatedBus.setRoute(bus.getRoute());
            busRepository.save(updatedBus);
            return "BUS UPDATE SUCCESSFUL";
        } else
            return "BUS UPDATE FAILED";
    }

    public void deleteBus(int busId) {
        busRepository.deleteById(busId);
    }
}
