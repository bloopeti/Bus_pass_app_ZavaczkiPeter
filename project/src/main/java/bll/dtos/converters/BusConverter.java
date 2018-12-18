package bll.dtos.converters;

import bll.dtos.BusDTO;
import dal.entities.Bus;

public class BusConverter {
    public Bus dtoToEntity(BusDTO dto) {
        Bus entity = new Bus();
        entity.setId(dto.getId());
        entity.setLine(dto.getLine());
        entity.setRoute(dto.getRoute());
        return entity;
    }

    public BusDTO entityToDTO(Bus entity) {
        BusDTO dto = new BusDTO();
        dto.setId(entity.getId());
        dto.setLine(entity.getLine());
        dto.setRoute(entity.getRoute());
        return dto;
    }
}
