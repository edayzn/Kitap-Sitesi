/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.LikeDao;
import admin.entity.Book;
import admin.entity.Like;
import admin.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author essah
 */
@Named
@SessionScoped
public class LikeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Like like;
    private LikeDao likeDao;
    private ArrayList<Like> likeList;
    private ArrayList<Like> likePageList;
    private int page = 1;
    private int listItemCount = 5;
    private int pageCount;

    @Inject
    UserBean userBean;

    @Inject
    BookBean bookBean;

    private User users;

    public User getUsers() {
        if (this.users == null) {
            this.users = new User();
            this.users = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().getOrDefault("user", null);
        }
        return users;
    }

    public void replace(Like p) {
        this.getLikeDao().updateOne(p);
    }

    public String replace1(Book p, boolean k) {
        if (this.getUsers() != null && this.getUsers().getUser_id() != null && p != null) {
            this.getLikeDao().updateOne1(p, this.getUsers(), k);
            return null;
        } else {
            return "/admin/login?faces-redirect=true";
        }
    }

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
        this.getLikeDao().update(this.like);
        this.like = new Like();
    }

    public void clearForm() {
        this.like = new Like();
    }

    public void deleteConfirm(Like kat) {
        this.like = kat;
    }

    public void updateForm(Like kat) {
        this.like = kat;
    }

    public void delete() {
        this.getLikeDao().delete(this.like);
        this.like = new Like();
    }

    public void create() {
        this.getLikeDao().create(this.like);
        this.like = new Like();
    }

    public ArrayList<Like> getLikeList() {
        this.likeList = new ArrayList();
        this.likeList = this.getLikeDao().list();
        return likeList;
    }

    public void setLikeList(ArrayList<Like> likeList) {
        this.likeList = likeList;
    }

    public ArrayList<Like> getLikePageList() {
        this.likePageList = new ArrayList();
        this.likePageList = this.getLikeDao().pagedList(this.page, this.listItemCount);
        return likePageList;
    }

    public void setLikePageList(ArrayList<Like> likePageList) {
        this.likePageList = likePageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getLikeDao().itemCount() / (double) listItemCount);
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

    public Like getLike() {
        if (this.like == null) {
            this.like = new Like();
        }
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public LikeDao getLikeDao() {
        if (this.likeDao == null) {
            this.likeDao = new LikeDao();
        }
        return likeDao;
    }

    public void setLikeDao(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    public UserBean getUserBean() {
        if (this.userBean == null) {
            this.userBean = new UserBean();
        }
        return userBean;
    }

    public BookBean getBookBean() {
        if (this.bookBean == null) {
            this.bookBean = new BookBean();
        }
        return bookBean;
    }

}
