package bll.dtos.converters;

import bll.dtos.CartDTO;
import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import dal.entities.Cart;
import dal.entities.PurchasedPass;
import dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConverter {
    @Autowired
    private PurchasedPassConverter purchasedPassConverter;
    @Autowired
    private CartConverter cartConverter;
    private static UserConverter thisInstance;

    public static synchronized UserConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new UserConverter();
        return thisInstance;
    }

    public User dtoToEntity(UserDTO dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setPassword(dto.getPassword());
        entity.setIsAdmin(dto.getIsAdmin());
        entity.setPurchasedPasses(new ArrayList<PurchasedPass>());
        List<PurchasedPassDTO> purchasedPassDTOList = dto.getPurchasedPasses();
        if (purchasedPassDTOList != null) {
//            purchasedPassConverter = new PurchasedPassConverter();
            for (PurchasedPassDTO purchasedPassDTO : purchasedPassDTOList)
                entity.getPurchasedPasses().add(purchasedPassConverter.dtoToEntity(purchasedPassDTO));
        }
        CartDTO cartDTO = dto.getCart();
        if (cartDTO != null) {
//            cartConverter = CartConverter.getInstance();
            entity.setCart(cartConverter.dtoToEntity(dto.getCart()));
        } else {
            entity.setCart(null);
        }
        return entity;
    }

    public List<User> dtoListToEntityList(List<UserDTO> dtoList) {
        List<User> entityList = new ArrayList<User>();
        for (UserDTO dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public UserDTO entityToDto(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setPassword(entity.getPassword());
        dto.setIsAdmin(entity.getIsAdmin());
        dto.setPurchasedPasses(new ArrayList<PurchasedPassDTO>());
        List<PurchasedPass> purchasedPassList = entity.getPurchasedPasses();
        if (purchasedPassList != null) {
//            purchasedPassConverter = new PurchasedPassConverter();
            for (PurchasedPass purchasedPass : purchasedPassList)
                dto.getPurchasedPasses().add(purchasedPassConverter.entityToDto(purchasedPass));
        }
        Cart cart = entity.getCart();
        if (cart != null) {
//            cartConverter = CartConverter.getInstance();
            dto.setCart(cartConverter.entityToDto(entity.getCart()));
        }
        return dto;
    }

    public List<UserDTO> entityListToDtoList(List<User> entityList) {
        List<UserDTO> dtoList = new ArrayList<UserDTO>();
        for (User entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
