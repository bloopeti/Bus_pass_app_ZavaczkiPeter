package bll.dtos.converters;

import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import dal.entities.PurchasedPass;
import dal.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    private PurchasedPassConverter purchasedPassConverter;
    private CartConverter cartConverter;

    public User dtoToEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setPassword(dto.getEmailAddress());
        entity.setIsAdmin(dto.getIsAdmin());
        entity.setPurchasedPasses(new ArrayList<PurchasedPass>());
        List<PurchasedPassDTO> purchasedPassDTOList = dto.getPurchasedPasses();
        for(PurchasedPassDTO purchasedPassDTO : purchasedPassDTOList)
            entity.getPurchasedPasses().add(purchasedPassConverter.dtoToEntity(purchasedPassDTO));
        entity.setCart(cartConverter.dtoToEntity(dto.getCart()));
        return entity;
    }

    public UserDTO entityToDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setPassword(entity.getPassword());
        dto.setIsAdmin(entity.getIsAdmin());
        dto.setPurchasedPasses(new ArrayList<PurchasedPassDTO>());
        List<PurchasedPass> purchasedPassList = entity.getPurchasedPasses();
        for(PurchasedPass purchasedPass : purchasedPassList)
            dto.getPurchasedPasses().add(purchasedPassConverter.entityToDto(purchasedPass));
        dto.setCart(cartConverter.entityToDto(entity.getCart()));
        return dto;
    }
}
