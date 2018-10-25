/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.bean;

import admin.bean.LoginBean;
import admin.entity.User;
import admin.entity.Comment;
import frontend.dao.FrontendYorumDao;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class FrontendYorumBean implements Serializable {

    private static final long serialVersionUID = 4385396906257279416L;
    private User user;
    private Comment yorum;
    private FrontendYorumDao frontendYorumDao;
    private ArrayList<Comment> yorumList;

    @Inject
    private LoginBean lgb;

    @Inject
    private FrontendBookBean ugb;

    public void clearForm() {
        this.yorum = new Comment();
    }

    public void updateForm(Comment kat) {
        Comment tmp = new Comment();
        tmp.setUser(this.getUser());
        tmp.setComment_id(kat.getComment_id());
        if (this.getFrontendYorumDao().kontrol(tmp)) {
            this.yorum = kat;
        }
    }

    public void delete() {
        this.getFrontendYorumDao().delete(this.yorum);
        this.yorum = new Comment();
    }

    public void create() {
        if (this.getUgb().getBook().getBook_id()!= null) {
            this.yorum.setUser(this.getUser());
            this.yorum.setBook(this.getUgb().getBook());
            this.getFrontendYorumDao().create(this.yorum);
            this.yorum = new Comment();
        } else {
            System.out.println("Hata  Frontend YorumBean Create Degerleri Gözden Geçirin");
            this.yorum = new Comment();
        }
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

    public ArrayList<Comment> getYorumList() {
        this.yorumList = new ArrayList();
        this.yorumList = this.getFrontendYorumDao().list();
        return yorumList;
    }

    public void setYorumList(ArrayList<Comment> yorumList) {
        this.yorumList = yorumList;
    }

    public FrontendYorumDao getFrontendYorumDao() {
        if (this.frontendYorumDao == null) {
            this.frontendYorumDao = new FrontendYorumDao();
        }
        return frontendYorumDao;
    }

    public void setFrontendYorumDao(FrontendYorumDao frontendYorumDao) {
        this.frontendYorumDao = frontendYorumDao;
    }

    public LoginBean getLgb() {
        if (this.lgb == null) {
            this.lgb = new LoginBean();
        }
        return lgb;
    }

    public FrontendBookBean getUgb() {
        if (this.ugb == null) {
            this.ugb = new FrontendBookBean();
        }
        return ugb;
    }

    public void setUgb(FrontendBookBean ugb) {
        this.ugb = ugb;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        if (this.getLgb().getUser() != null && this.getLgb().getUser().getUser_id() != null) {
            this.user = this.getLgb().getUser();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
