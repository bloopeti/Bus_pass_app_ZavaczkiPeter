package bll;

import bll.crud.UserBll;
import bll.dtos.PurchasedPassDTO;
import bll.dtos.UserDTO;
import bll.mailing.Mailer;
import dal.entities.User;

import java.util.List;

public class PassExpirationNotifier {
    private Mailer mailer;
    private PassExpirationChecker passExpirationChecker;

    public PassExpirationNotifier(Mailer mailer) {
        this.mailer = mailer;
        passExpirationChecker = new PassExpirationChecker();
    }

    public void notifyOneUser(UserDTO user) {
        List<PurchasedPassDTO> expiredPasses = passExpirationChecker.checkExpiredPasses(user);
        if (!(expiredPasses.isEmpty())) {
            String subject = "Buss pass expired!";
            String content = "Hello" + user.getEmailAddress() + " !\n\nOne ore more of your bus passes are expired.\nPlease consider buying another pass.";
            mailer.sendSingleMail(user.getEmailAddress(), subject, content);
        }
    }

    public void notifyList(List<UserDTO> userList) {
        for(UserDTO user : userList)
            notifyOneUser(user);
    }

    public void notifyEveryUser() {
        UserBll userBll = new UserBll();
        notifyList(userBll.getAllUsers());
    }
}
