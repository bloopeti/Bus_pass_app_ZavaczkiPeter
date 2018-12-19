package bll.dtos.converters;

import bll.crud.UserBll;
import bll.dtos.PurchasedPassDTO;
import dal.entities.PurchasedPass;
import dal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchasedPassConverter {
    @Autowired
    private UserBll userBll;
    @Autowired
    private PassConverter passConverter;// = PassConverter.getInstance();
    @Autowired
    private UserConverter userConverter;
    private static PurchasedPassConverter thisInstance;

    public static synchronized PurchasedPassConverter getInstance() {
        if (thisInstance == null)
            thisInstance = new PurchasedPassConverter();
        return thisInstance;
    }

    public PurchasedPass dtoToEntity(PurchasedPassDTO dto) {
        PurchasedPass entity = new PurchasedPass();
        entity.setId(dto.getId());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setPass(passConverter.dtoToEntity(dto.getPass()));
//        userConverter = UserConverter.getInstance();
//        entity.setUser(userConverter.dtoToEntity(userBll.getUserById(dto.getUserId())));
        User user = new User();
        user.setId(dto.getUserId());
        entity.setUser(user);
        return entity;
    }

    public List<PurchasedPass> dtoListToEntityList(List<PurchasedPassDTO> dtoList) {
        List<PurchasedPass> entityList = new ArrayList<PurchasedPass>();
        for(PurchasedPassDTO dto : dtoList)
            entityList.add(dtoToEntity(dto));
        return entityList;
    }
    
    public PurchasedPassDTO entityToDto(PurchasedPass entity) {
        PurchasedPassDTO dto = new PurchasedPassDTO();
        dto.setId(entity.getId());
        dto.setExpirationDate(entity.getExpirationDate());
        dto.setPass(passConverter.entityToDto(entity.getPass()));
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    public List<PurchasedPassDTO> entityListToDtoList(List<PurchasedPass> entityList) {
        List<PurchasedPassDTO> dtoList = new ArrayList<PurchasedPassDTO>();
        for(PurchasedPass entity : entityList)
            dtoList.add(entityToDto(entity));
        return dtoList;
    }
}
