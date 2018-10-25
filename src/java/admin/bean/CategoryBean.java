/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.CategoryDao;
import admin.entity.Category;
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
public class CategoryBean implements Serializable {

    private Category kategori;
    private CategoryDao kategoriDao;
    private ArrayList<Category> kategoriList;
    private ArrayList<Category> kategoriPageList;

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
        this.getKategoriDao().update(this.kategori);
        this.kategori = new Category();
    }

    public void clearForm() {
        this.kategori = new Category();
    }

    public void deleteConfirm(Category kat) {
        this.kategori = kat;
    }

    public void updateForm(Category kat) {
        this.kategori = kat;
    }

    public void delete() {
        this.getKategoriDao().delete(this.kategori);
        this.kategori = new Category();
    }

    public void create() {
        this.getKategoriDao().create(kategori);
        this.kategori = new Category();
    }

    public Category getKategori() {
        if (this.kategori == null) {
            this.kategori = new Category();
        }
        return kategori;
    }

    public void setKategori(Category kategori) {
        this.kategori = kategori;
    }

    public CategoryDao getKategoriDao() {
        if (this.kategoriDao == null) {
            this.kategoriDao = new CategoryDao();
        }
        return kategoriDao;
    }

    public void setKategoriDao(CategoryDao kategoriDao) {
        this.kategoriDao = kategoriDao;
    }

    public ArrayList<Category> getKategoriList() {
        this.kategoriList = new ArrayList();
        this.kategoriList = this.getKategoriDao().list();
        return kategoriList;
    }

    public void setKategoriList(ArrayList<Category> kategoriList) {
        this.kategoriList = kategoriList;
    }

    public ArrayList<Category> getKategoriPageList() {
        this.kategoriPageList = new ArrayList();
        this.kategoriPageList = this.getKategoriDao().pagedList(this.page, this.listItemCount);
        return kategoriPageList;
    }

    public void setKategoriPageList(ArrayList<Category> kategoriPageList) {
        this.kategoriPageList = kategoriPageList;
    }

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getKategoriDao().itemCount() / (double) listItemCount);
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
