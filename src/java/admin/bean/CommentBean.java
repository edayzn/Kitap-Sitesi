/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.CommentDao;
import admin.entity.Comment;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omg.CORBA.BAD_CONTEXT;

/**
 *
 * @author essah
 */
@Named
@SessionScoped
public class CommentBean implements Serializable {

    private static final long serialVersionUID = -5471579182797012675L;

    private Comment yorum;
    private CommentDao yorumDao;
    private ArrayList<Comment> yorumList;
    private ArrayList<Comment> yorumPageList;

    @Inject
    private UserBean userBean;

    @Inject
    private BookBean bookBean;

    private int page = 1;

    private int listItemCount = 3;

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
        this.getYorumDao().update(this.yorum);
        this.yorum = new Comment();
    }

    public void clearForm() {
        this.yorum = new Comment();
    }

    public void deleteConfirm(Comment kat) {
        this.yorum = kat;
    }

    public void updateForm(Comment kat) {
        this.yorum = kat;
    }

    public void delete() {
        this.getYorumDao().delete(this.yorum);
        this.yorum = new Comment();
    }

    public void create() {
        this.getYorumDao().create(this.yorum);
        this.yorum = new Comment();
    }

    public Comment getYorum() {
        if (this.yorum == null) {
            this.yorum = new Comment();
        }
        return yorum;
    }

    public void setYorum(Comment yorum) {
        this.yorum = yorum;
    }

    public CommentDao getYorumDao() {
        if (this.yorumDao == null) {
            this.yorumDao = new CommentDao();
        }
        return yorumDao;
    }

    public void setYorumDao(CommentDao yorumDao) {
        this.yorumDao = yorumDao;
    }

    public ArrayList<Comment> getYorumList() {
        this.yorumList = new ArrayList();
        this.yorumList = this.getYorumDao().list();
        return yorumList;
    }

    public void setYorumList(ArrayList<Comment> yorumList) {
        this.yorumList = yorumList;
    }

    public ArrayList<Comment> getYorumPageList() {
        this.yorumPageList = new ArrayList();
        this.yorumPageList = this.getYorumDao().pagedList(this.page, this.listItemCount);
        return yorumPageList;
    }

    public void setYorumPageList(ArrayList<Comment> yorumPageList) {
        this.yorumPageList = yorumPageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getYorumDao().itemCountTop() / (double) listItemCount);
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public UserBean getUserBean() {
        if (this.userBean == null) {
            this.userBean = new UserBean();
        }
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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

    public BookBean getBookBean() {
        if (this.bookBean == null) {
            this.bookBean = new BookBean();
        }
        return bookBean;
    }

}
