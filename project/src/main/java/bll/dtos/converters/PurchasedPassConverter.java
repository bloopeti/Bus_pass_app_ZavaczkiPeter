package bll.dtos.converters;

import bll.crud.UserBll;
import bll.dtos.PurchasedPassDTO;
import dal.entities.PurchasedPass;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchasedPassConverter {
    @Autowired
    private UserBll userBll;
    private PassConverter passConverter = new PassConverter();

    public PurchasedPass dtoToEntity(PurchasedPassDTO dto) {
        PurchasedPass entity = new PurchasedPass();
        entity.setId(dto.getId());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setPass(passConverter.dtoToEntity(dto.getPass()));
        entity.setUser(userBll.getUserById(dto.getUserId()));
        return entity;
    }
    
    public PurchasedPassDTO entityToDto(PurchasedPass entity) {
        PurchasedPassDTO dto = new PurchasedPassDTO();
        dto.setId(entity.getId());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setPass(passConverter.entityToDTO(entity.getPass()));
        dto.setUserId(entity.getUser().getId());
        return dto;
    }
}
