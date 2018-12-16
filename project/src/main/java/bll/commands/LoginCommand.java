package bll.commands;

import bll.crud.UserBll;
import dal.entities.User;

public class LoginCommand implements Command {
    private User user;

    public LoginCommand(User user) {
        this.user = user;
    }

    @Override
    public Object execute() {
        UserBll userBll = new UserBll();
        User dbUser = userBll.getUserByEmailAddress(user);
        if (!(dbUser == null))
            if (user.getPassword().equals(dbUser.getPassword()))
                return dbUser.getIsAdmin();
        return -1;
    }
}
