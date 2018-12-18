package bll.dtos.converters;

import bll.dtos.PassDTO;
import dal.entities.Pass;

public class PassConverter {
    public Pass dtoToEntity(PassDTO dto) {
        Pass entity = new Pass();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        return entity;
    }

    public PassDTO entityToDTO(Pass entity) {
        PassDTO dto = new PassDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        return dto;
    }
}
