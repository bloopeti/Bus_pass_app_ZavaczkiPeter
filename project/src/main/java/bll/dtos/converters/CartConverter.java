package bll.dtos.converters;

import bll.crud.UserBll;
import bll.dtos.CartDTO;
import bll.dtos.PassDTO;
import dal.entities.Cart;
import dal.entities.Pass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartConverter {
    @Autowired
    private UserBll userBll;
    private PassConverter passConverter;

    public Cart dtoToEntity(CartDTO dto) {
        Cart entity = new Cart();
        entity.setId(dto.getId());
        entity.setUser(userBll.getUserById(dto.getUserId()));
        entity.setPasses(new ArrayList<Pass>());
        List<PassDTO> passDTOList = dto.getPasses();
        for(PassDTO passDTO : passDTOList)
            entity.getPasses().add(passConverter.dtoToEntity(passDTO));
        return entity;
    }

    public CartDTO entityToDto(Cart entity) {
        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setPasses(new ArrayList<PassDTO>());
        List<Pass> passList = entity.getPasses();
        for(Pass pass : passList)
            dto.getPasses().add(passConverter.entityToDTO(pass));
        return dto;
    }
}
