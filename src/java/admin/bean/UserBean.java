/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.UserDao;
import admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Eda Yazan
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 6486657837022691532L;

    private User user;
    private UserDao userDao;
    private ArrayList<User> userList;
    private ArrayList<User> userPageList;

    @Inject
    RoleBean roleBean;

    private int page = 1;
    private int listItemCount = 5;
    private int pageCount;

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void update() {
        this.getUserDao().update(this.user);
        this.user = new User();
    }

    public void clearForm() {
        this.user = new User();
    }

    public void deleteConfirm(User kat) {
        this.user = kat;
    }

    public void updateForm(User kat) {
        this.user = kat;
    }

    public void delete() {
        this.getUserDao().delete(this.user);
        this.user = new User();
    }

    public void create() {
        this.getUserDao().create(this.user);
        this.user = new User();
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

    public ArrayList<User> getUserList() {
        this.userList = new ArrayList();
        this.userList = this.getUserDao().list();
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<User> getUserPageList() {
        this.userPageList = new ArrayList();
        this.userPageList = this.getUserDao().pagedList(this.page, this.listItemCount);
        return userPageList;
    }

    public void setUserPageList(ArrayList<User> userPageList) {
        this.userPageList = userPageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getUserDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public RoleBean getRoleBean() {
        if (this.roleBean == null) {
            this.roleBean = new RoleBean();
        }
        return roleBean;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getListItemCount() {
        return listItemCount;
    }

    public void setListItemCount(int listItemCount) {
        this.listItemCount = listItemCount;
    }

}
