package tests;

import main.bean.UserBean;
import main.model.UserRank;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UserBeanTest {


    @Test
    public void loginTest(){
        UserBean userBean = new UserBean();
        assert (userBean.isLogged() == false);
        userBean.setLogin("LOGIN");
        assert (userBean.isLogged() == true);

    }

    @Test
    public void isAdminTest(){
        UserBean userBean = new UserBean();
        assert (userBean.isAdmin() == false);
        userBean.setRank(UserRank.USER);
        assert (userBean.isAdmin() == false);
        userBean.setRank(UserRank.MOD);
        assert (userBean.isAdmin() == false);
        userBean.setRank(UserRank.ADMIN);
        assert (userBean.isAdmin() == true);
    }

    @Test
    public void isModTest(){
        UserBean userBean = new UserBean();
        assert (userBean.isMod() == false);
        userBean.setRank(UserRank.USER);
        assert (userBean.isMod() == false);
        userBean.setRank(UserRank.MOD);
        assert (userBean.isMod() == true);
        userBean.setRank(UserRank.ADMIN);
        assert (userBean.isMod() == false);
    }
}
