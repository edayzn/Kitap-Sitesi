/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.UserDao;
import admin.entity.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import utility.SessionUtility;

/**
 *
 * @author cypher
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private User user;
    private UserDao userDao;
    private boolean loggedIn = false;

    public String login() {
        User a = this.getUserDao().login(this.user);
        if (a != null) {
            this.loggedIn = true;
            HttpSession session = SessionUtility.getSession();
            session.setAttribute("user", a);
            this.user=a;
            if ("admin".equalsIgnoreCase(a.getRole().getRole_adi())) {
                return "/admin/index?faces-redirect=true";
            }
            return "/frontend/index?faces-redirect=true";
        } else {
             this.user=new User();
            return "/admin/login?faces-redirect=true";        
        }
    }

    public String doLogout() {
        this.setLoggedIn(false);
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.user=new User();
        return "/frontend/index?faces-redirect=true";
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.loggedIn = isLoggedIn;
    }
}
