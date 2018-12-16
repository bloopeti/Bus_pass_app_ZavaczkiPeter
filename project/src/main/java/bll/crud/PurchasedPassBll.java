package bll.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.entities.PurchasedPass;
import dal.repositories.PurchasedPassRepository;

import java.util.List;

@Service
public class PurchasedPassBll {
    @Autowired
    PurchasedPassRepository purchasedPassRepository;

    public List<PurchasedPass> getAllPurchasedPasses() {
        return purchasedPassRepository.findAll();
    }

    public PurchasedPass getPurchasedPassById(int id) {
        if (purchasedPassRepository.findById(id).isPresent())
            return purchasedPassRepository.findById(id).get();
        else
            return null;
    }

    public void addPurchasedPass(PurchasedPass purchasedPass) {
        purchasedPassRepository.save(purchasedPass);
    }

    public String updatePurchasedPass(PurchasedPass purchasedPass) {
        PurchasedPass updatedPurchasedPass;
        if (purchasedPassRepository.findById(purchasedPass.getId()).isPresent()) {
            updatedPurchasedPass = purchasedPassRepository.findById(purchasedPass.getId()).get();
            updatedPurchasedPass.setExpirationDate(purchasedPass.getExpirationDate());
            updatedPurchasedPass.setPass(purchasedPass.getPass());
            updatedPurchasedPass.setUser(purchasedPass.getUser());
            purchasedPassRepository.save(updatedPurchasedPass);
            return "PurchasedPass UPDATE SUCCESSFUL";
        } else
            return "PurchasedPass UPDATE FAILED";
    }

    public void deletePurchasedPass(int purchasedPassId) {
        purchasedPassRepository.deleteById(purchasedPassId);
    }
}
