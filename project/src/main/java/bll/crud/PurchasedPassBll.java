package bll.crud;

import bll.dtos.PassDTO;
import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import bll.dtos.converters.PassConverter;
import bll.dtos.converters.PurchasedPassConverter;
import bll.dtos.converters.UserConverter;
import dal.entities.Pass;
import dal.entities.User;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.entities.PurchasedPass;
import dal.repositories.PurchasedPassRepository;

import java.util.List;

@Service
public class PurchasedPassBll {
    @Autowired
    PurchasedPassRepository purchasedPassRepository;
    @Autowired
    PassBll passBll;
    @Autowired
    UserBll userBll;
    @Autowired
    private PurchasedPassConverter converter;// = new PurchasedPassConverter();
    @Autowired
    private PassConverter passConverter;// = new PassConverter();
    @Autowired
    private UserConverter userConverter;// = new UserConverter();

    public List<PurchasedPassDTO> getAllPurchasedPasses() {
        return converter.entityListToDtoList(purchasedPassRepository.findAll());
    }

    public PurchasedPassDTO getPurchasedPassById(int id) {
        if (purchasedPassRepository.findById(id).isPresent())
            return converter.entityToDto(purchasedPassRepository.findById(id).get());
        else
            return null;
    }

    public String addPurchasedPass(PurchasedPassDTO purchasedPass) {
        String reason = "Pass";
        PassDTO dbPass = passBll.getPassById(purchasedPass.getPass().getId());
        if (dbPass != null) {
            reason = "User";
            UserDTO dbUser = userBll.getUserById(purchasedPass.getUserId());
            if (dbUser != null) {
                purchasedPassRepository.save(converter.dtoToEntity(purchasedPass));
                return "PurchasedPass ADD SUCCESSFUL";
            }
        }
        return "PurchasedPass ADD FAILED: " + reason + " with this ID doesn't exist";
    }

    public String updatePurchasedPass(PurchasedPassDTO purchasedPass) {
        PurchasedPass updatedPurchasedPass;

        String reason = "PurchasedPass";
        if (purchasedPassRepository.findById(purchasedPass.getId()).isPresent()) {
            updatedPurchasedPass = purchasedPassRepository.findById(purchasedPass.getId()).get();
            updatedPurchasedPass.setExpirationDate(purchasedPass.getExpirationDate());

            reason = "Pass";
            PassDTO dbPass = passBll.getPassById(purchasedPass.getPass().getId());
            if (dbPass != null) {
                updatedPurchasedPass.setPass(passConverter.dtoToEntity(dbPass));

                reason = "User";
                UserDTO dbUser = userBll.getUserById(purchasedPass.getUserId());
                if (dbUser != null) {
                    updatedPurchasedPass.setUser(userConverter.dtoToEntity(dbUser));
                    purchasedPassRepository.save(updatedPurchasedPass);
                    return "PurchasedPass UPDATE SUCCESSFUL";
                }
            }
        }
        return "PurchasedPass UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deletePurchasedPass(int purchasedPassId) {
        String reason = "PurchasedPass";
        if (purchasedPassRepository.findById(purchasedPassId).isPresent()) {
            purchasedPassRepository.deleteById(purchasedPassId);
            return "PurchasedPass DELETE SUCCESSFUL";
        }
        return "PurchasedPass DELETE FAILED: " + reason + " with this ID doesn't exist";
    }
}
