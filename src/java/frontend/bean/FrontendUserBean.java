package frontend.bean;


import admin.bean.LoginBean;
import admin.entity.User;
import frontend.dao.FrontendUserDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendUserBean implements Serializable {

    private static final long serialVersionUID = 5287891247316511292L;

    private User users;
    private FrontendUserDao userDao;
    
    @Inject
    private LoginBean loginBean;

    public void update() {
        this.getUserDao().update(this.users);
        this.users = new User();
    }

    public void clearForm() {
        this.users = new User();
    }

    public void deleteConfirm(User kat) {
        this.users = kat;
    }

    public void updateForm(User kat) {
        this.users = kat;
    }

    public String delete() {
        this.getUserDao().delete(this.users);
        this.users = new User();
        this.getLoginBean().doLogout();
        return "/admin/login?faces-redirect=true";
    }

    public void create() {
        this.getUserDao().create(this.users);
        this.users = new User();
    }

    public User getUsers() {
        if (this.users == null) {
            this.users = new User();
        }
        return users;
    }

    public void setUsers(User user) {
        this.users = user;
    }

    public FrontendUserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new FrontendUserDao();
        }
        return userDao;
    }

    public void setUserDao(FrontendUserDao userDao) {
        this.userDao = userDao;
    }

    public LoginBean getLoginBean() {
        if (this.loginBean == null) {
            this.loginBean = new LoginBean();
        }
        return loginBean;
    }

}
