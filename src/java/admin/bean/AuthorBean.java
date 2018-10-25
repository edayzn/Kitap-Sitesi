/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.AuthorDao;
import admin.entity.Author;
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
public class AuthorBean implements Serializable {

    private Author author;
    private AuthorDao authorDao;
    private ArrayList<Author> authorList;
    private ArrayList<Author> authorPageList;

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
        this.getAuthorDao().update(this.author);
        this.author = new Author();
    }

    public void clearForm() {
        this.author = new Author();
    }

    public void deleteConfirm(Author kat) {
        this.author = kat;
    }

    public void updateForm(Author kat) {
        this.author = kat;
    }

    public void delete() {
        this.getAuthorDao().delete(this.author);
        this.author = new Author();
    }

    public void create() {
        this.getAuthorDao().create(this.author);
        this.author = new Author();
    }

    public Author getAuthor() {
        if (this.author == null) {
            this.author = new Author();
        }
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public AuthorDao getAuthorDao() {
        if (this.authorDao == null) {
            this.authorDao = new AuthorDao();
        }
        return authorDao;
    }

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public ArrayList<Author> getAuthorList() {
        this.authorList = new ArrayList();
        this.authorList = this.getAuthorDao().list();
        return authorList;
    }

    public void setAuthorList(ArrayList<Author> authorList) {
        this.authorList = authorList;
    }

    public ArrayList<Author> getAuthorPageList() {
        this.authorPageList = new ArrayList();
        this.authorPageList = this.getAuthorDao().pagedList(this.page, this.listItemCount);
        return authorPageList;
    }

    public void setAuthorPageList(ArrayList<Author> authorPageList) {
        this.authorPageList = authorPageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getAuthorDao().itemCount() / (double) listItemCount);
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
