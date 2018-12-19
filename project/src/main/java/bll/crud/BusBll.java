package bll.crud;

import bll.dtos.BusDTO;
import bll.dtos.converters.BusConverter;
import dal.entities.Bus;
import dal.repositories.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusBll {
    @Autowired
    BusRepository busRepository;
    @Autowired
    private BusConverter converter;// = new BusConverter();

    public List<BusDTO> getAllBuses() {
        return converter.entityListToDtoList(busRepository.findAll());
    }

    public BusDTO getBusById(int id) {
        if (busRepository.findById(id).isPresent())
            return converter.entityToDto(busRepository.findById(id).get());
        return null;
    }

    public String addBus(BusDTO bus) {
        busRepository.save(converter.dtoToEntity(bus));
        return "BUS ADD SUCCESSFUL";
    }

    public String updateBus(BusDTO bus) {
        Bus updatedBus;
        String reason = "Bus";
        if (busRepository.findById(bus.getId()).isPresent()) {
            updatedBus = busRepository.findById(bus.getId()).get();
            updatedBus.setLine(bus.getLine());
            updatedBus.setRoute(bus.getRoute());
            busRepository.save(updatedBus);
            return "BUS UPDATE SUCCESSFUL";
        }
        return "BUS UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deleteBus(int busId) {
        String reason = "Bus";
        if (busRepository.findById(busId).isPresent()) {
            busRepository.deleteById(busId);
            return "BUS DELETE SUCCESSFUL";
    }
        return "BUS DELETE FAILED: " + reason + " with this ID doesn't exist";
    }
}
