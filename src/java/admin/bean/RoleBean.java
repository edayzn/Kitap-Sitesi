/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.RoleDao;
import admin.entity.Role;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Eda Yazan
 */
@Named
@SessionScoped

public class RoleBean implements Serializable {

    private Role role;
    private RoleDao roleDao;
    private ArrayList<Role> roleList;
    private ArrayList<Role> rolePageList;

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
        this.getRoleDao().update(this.role);
        this.role = new Role();
    }

    public void clearForm() {
        this.role = new Role();
    }

    public void deleteConfirm(Role kat) {
        this.role = kat;
    }

    public void updateForm(Role kat) {
        this.role = kat;
    }

    public void delete() {
        this.getRoleDao().delete(this.role);
        this.role = new Role();
    }

    public void create() {
        this.getRoleDao().create(role);
        this.role = new Role();
    }

    public Role getRole() {
        if (this.role == null) {
            this.role = new Role();
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RoleDao getRoleDao() {
        if (this.roleDao == null) {
            this.roleDao = new RoleDao();
        }
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public ArrayList<Role> getRoleList() {
        this.roleList = new ArrayList();
        this.roleList = this.getRoleDao().list();
        return roleList;
    }

    public void setRoleList(ArrayList<Role> roleList) {
        this.roleList = roleList;
    }

    public ArrayList<Role> getRolePageList() {
        this.rolePageList = new ArrayList();
        this.rolePageList = this.getRoleDao().pagedList(this.page, this.listItemCount);
        return rolePageList;
    }

    public void setRolePageList(ArrayList<Role> rolePageList) {
        this.rolePageList = rolePageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getRoleDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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
