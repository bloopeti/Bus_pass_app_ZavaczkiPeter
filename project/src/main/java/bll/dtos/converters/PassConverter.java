package bll.dtos.converters;

import bll.dtos.PassDTO;
import dal.entities.Pass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassConverter {
    private static PassConverter thisInstance;

    public static synchronized PassConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new PassConverter();
        return thisInstance;
    }

    public Pass dtoToEntity(PassDTO dto) {
        Pass entity = new Pass();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        return entity;
    }

    public List<Pass> dtoListToEntityList(List<PassDTO> dtoList) {
        List<Pass> entityList = new ArrayList<Pass>();
        for(PassDTO dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public PassDTO entityToDto(Pass entity) {
        PassDTO dto = new PassDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        return dto;
    }

    public List<PassDTO> entityListToDtoList(List<Pass> entityList) {
        List<PassDTO> dtoList = new ArrayList<PassDTO>();
        for(Pass entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
