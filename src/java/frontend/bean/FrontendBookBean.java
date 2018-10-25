/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.bean;

import admin.entity.Book;
import admin.entity.Category;
import admin.entity.User;
import frontend.dao.FrontendBookDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author edayzn
 */
@Named
@SessionScoped
public class FrontendBookBean implements Serializable {

    private static final long serialVersionUID = -6112338345310132497L;
    private Category category;
    private User user;
    private Book book;
    private FrontendBookDao FrontendBookDao;
    private List<Book> bookList;

    public void clearForm() {
        this.book = new Book();
        this.category = new Category();
    }

    public Book detail() {
        return this.getFrontendBookDao().detail1(this.book);
    }

    public String updateForm(Book ur) {
        this.book = ur;
        return "/frontend/Book/book?faces-redirect=true";
    }

    public String updateKategori(Category k) {
        this.setCategory(k);
        return "/frontend/index?faces-redirect=true";
    }

    public String updateKategori1() {
        this.setCategory(new Category());
        return "/frontend/index?faces-redirect=true";
    }

    public Category getCategory() {
        if (this.category == null) {
            this.category = new Category();
        }
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Book> getBookList() {
        this.bookList = new ArrayList<>();
        this.bookList = this.getFrontendBookDao().list(this.getCategory());
        return bookList;
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

    public Book getBook() {
        if (this.book == null) {
            this.book = new Book();
        }

        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public FrontendBookDao getFrontendBookDao() {
        if (this.FrontendBookDao == null) {
            this.FrontendBookDao = new FrontendBookDao();
        }
        return FrontendBookDao;
    }

    public void setFrontendBookDao(FrontendBookDao FrontendBookDao) {
        this.FrontendBookDao = FrontendBookDao;
    }
}
