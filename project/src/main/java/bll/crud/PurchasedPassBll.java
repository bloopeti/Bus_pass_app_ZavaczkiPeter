package bll.crud;

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

    public List<PurchasedPass> getAllPurchasedPasses() {
        return purchasedPassRepository.findAll();
    }

    public PurchasedPass getPurchasedPassById(int id) {
        if (purchasedPassRepository.findById(id).isPresent())
            return purchasedPassRepository.findById(id).get();
        else
            return null;
    }

    public String addPurchasedPass(PurchasedPass purchasedPass) {
        String reason = "Pass";
        Pass dbPass = passBll.getPassById(purchasedPass.getPass().getId());
        if (dbPass != null) {
            reason = "User";
            User dbUser = userBll.getUserById(purchasedPass.getUser().getId());
            if (dbUser != null) {
                purchasedPassRepository.save(purchasedPass);
                return "PurchasedPass ADD SUCCESSFUL";
            }
        }
        return "PurchasedPass ADD FAILED: " + reason + " with this ID doesn't exist";
    }

    public String updatePurchasedPass(PurchasedPass purchasedPass) {
        PurchasedPass updatedPurchasedPass;

        String reason = "PurchasedPass";
        if (purchasedPassRepository.findById(purchasedPass.getId()).isPresent()) {
            updatedPurchasedPass = purchasedPassRepository.findById(purchasedPass.getId()).get();
            updatedPurchasedPass.setExpirationDate(purchasedPass.getExpirationDate());

            reason = "Pass";
            Pass dbPass = passBll.getPassById(purchasedPass.getPass().getId());
            if (dbPass != null) {
                updatedPurchasedPass.setPass(dbPass);

                reason = "User";
                User dbUser = userBll.getUserById(purchasedPass.getUser().getId());
                if (dbUser != null) {
                    updatedPurchasedPass.setUser(dbUser);
                    purchasedPassRepository.save(updatedPurchasedPass);
                    updatedPurchasedPass.setUser(purchasedPass.getUser());
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
