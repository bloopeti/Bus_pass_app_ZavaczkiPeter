package bll;

import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import dal.entities.PurchasedPass;
import dal.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PassExpirationChecker {
    public List<PurchasedPassDTO> checkExpiredPasses(UserDTO user) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<PurchasedPassDTO> expiredPasses = new ArrayList<PurchasedPassDTO>();
        List<PurchasedPassDTO> purchasedPassDTOList = user.getPurchasedPasses();
        if (!purchasedPassDTOList.isEmpty()) {
            for (PurchasedPassDTO pp : user.getPurchasedPasses()) {
                long ppMillis = Long.parseLong(pp.getExpirationDate());
                if (now.after(new Timestamp(ppMillis)))
                    expiredPasses.add(pp);
            }
        }
        return expiredPasses;
    }
}
