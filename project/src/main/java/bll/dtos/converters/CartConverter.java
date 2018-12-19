package bll.dtos.converters;

import bll.crud.UserBll;
import bll.dtos.CartDTO;
import bll.dtos.PassDTO;
import dal.entities.Cart;
import dal.entities.Pass;
import dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartConverter {
    @Autowired
    private UserBll userBll;
    @Autowired
    private PassConverter passConverter;// = PassConverter.getInstance();
    @Autowired
    private UserConverter userConverter;
    private static CartConverter thisInstance;

    public static synchronized CartConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new CartConverter();
        return thisInstance;
    }

    public Cart dtoToEntity(CartDTO dto) {
        Cart entity = new Cart();
        entity.setId(dto.getId());
        //userConverter = UserConverter.getInstance();
//        entity.setUser(userConverter.dtoToEntity(userBll.getUserById(dto.getUserId())));
        User user = new User();
        user.setId(dto.getUserId());
        entity.setUser(user);
        entity.setPasses(new ArrayList<Pass>());
        List<PassDTO> passDTOList = dto.getPasses();
        for(PassDTO passDTO : passDTOList)
            entity.getPasses().add(passConverter.dtoToEntity(passDTO));
        return entity;
    }

    public List<Cart> dtoListToEntityList(List<CartDTO> dtoList) {
        List<Cart> entityList = new ArrayList<Cart>();
        for(CartDTO dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }

    public CartDTO entityToDto(Cart entity) {
        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setPasses(new ArrayList<PassDTO>());
        List<Pass> passList = entity.getPasses();
        for(Pass pass : passList)
            dto.getPasses().add(passConverter.entityToDto(pass));
        return dto;
    }

    public List<CartDTO> entityListToDtoList(List<Cart> entityList) {
        List<CartDTO> dtoList = new ArrayList<CartDTO>();
        for(Cart entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
