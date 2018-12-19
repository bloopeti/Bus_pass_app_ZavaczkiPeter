package bll.dtos.converters;

import bll.dtos.BusDTO;
import dal.entities.Bus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusConverter {
    private static BusConverter thisInstance;

    public static synchronized BusConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new BusConverter();
        return thisInstance;
    }

    public Bus dtoToEntity(BusDTO dto) {
        Bus entity = new Bus();
        entity.setId(dto.getId());
        entity.setLine(dto.getLine());
        entity.setRoute(dto.getRoute());
        return entity;
    }

    public List<Bus> dtoListToEntityList(List<BusDTO> dtoList) {
        List<Bus> entityList = new ArrayList<Bus>();
        for(BusDTO dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public BusDTO entityToDto(Bus entity) {
        BusDTO dto = new BusDTO();
        dto.setId(entity.getId());
        dto.setLine(entity.getLine());
        dto.setRoute(entity.getRoute());
        return dto;
    }

    public List<BusDTO> entityListToDtoList(List<Bus> entityList) {
        List<BusDTO> dtoList = new ArrayList<BusDTO>();
        for(Bus entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
