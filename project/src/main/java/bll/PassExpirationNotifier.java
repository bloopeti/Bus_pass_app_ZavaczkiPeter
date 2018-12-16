package bll;

import bll.crud.UserBll;
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

    public void notifyOneUser(User user) {
        if (!(passExpirationChecker.checkExpiredPasses(user).isEmpty())) {
            String subject = "Buss pass expired!";
            String content = "Hello!\n\nOne ore more of your bus passes are expired.\nPlease consider buying another pass.";
            mailer.sendSingleMail(user.getEmailAddress(), subject, content);
        }
    }

    public void notifyList(List<User> userList) {
        for(User user : userList)
            notifyOneUser(user);
    }

    public void notifyEveryUser() {
        UserBll userBll = new UserBll();
        notifyList(userBll.getAllUsers());
    }
}
