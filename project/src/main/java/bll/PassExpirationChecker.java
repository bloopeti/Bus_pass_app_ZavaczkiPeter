package bll;

import dal.entities.PurchasedPass;
import dal.entities.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PassExpirationChecker {
    public List<PurchasedPass> checkExpiredPasses(User user) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        List<PurchasedPass> expiredPasses = new ArrayList<PurchasedPass>();
        for(PurchasedPass pp : user.getPurchasedPasses()) {
            int ppMillis = Integer.parseInt(pp.getExpirationDate());
            if (now.after(new Timestamp(ppMillis)))
                expiredPasses.add(pp);
        }
        if (!(expiredPasses.isEmpty()))
            return expiredPasses;
        else
            return null;
    }
}
