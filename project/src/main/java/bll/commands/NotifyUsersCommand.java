package bll.commands;

import bll.PassExpirationNotifier;
import bll.mailing.Mailer;

public class NotifyUsersCommand implements Command {
    @Override
    public Object execute() {
        PassExpirationNotifier passExpirationNotifier = new PassExpirationNotifier(new Mailer("peter.zavaczki.tucn@gmail.com", "P4$$word"));
        passExpirationNotifier.notifyEveryUser();
        return null;
    }
}
