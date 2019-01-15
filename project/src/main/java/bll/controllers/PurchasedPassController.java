package bll.controllers;

import bll.dtos.PurchasedPassDTO;
import bll.wrappers.IdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bll.crud.PurchasedPassBll;
import dal.entities.PurchasedPass;

import java.util.List;

@RestController
@RequestMapping(value = "/purchasedPass")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchasedPassController {
    @Autowired
    PurchasedPassBll purchasedPassBll;

    @GetMapping(value = "/getAll")
    public List<PurchasedPassDTO> getAllPurchasedPasses() {
        return purchasedPassBll.getAllPurchasedPasses();
    }

    @GetMapping(value = "/get/{id}")
    public PurchasedPassDTO getPurchasedPassById(@PathVariable("id") int id) {
        return purchasedPassBll.getPurchasedPassById(id);
    }

    @PostMapping(value = "/add")
    public String addPurchasedPass(@RequestBody PurchasedPassDTO purchasedPass) {
        return purchasedPassBll.addPurchasedPass(purchasedPass);
    }

    @PostMapping(value = "/update")
    public String updatePurchasedPass(@RequestBody PurchasedPassDTO purchasedPass) {
        return purchasedPassBll.updatePurchasedPass(purchasedPass);
    }

    @PostMapping(value = "/delete")
    public String deletePurchasedPass(@RequestBody IdWrapper idWrapper) {
        return purchasedPassBll.deletePurchasedPass(idWrapper.getId());
    }
}
