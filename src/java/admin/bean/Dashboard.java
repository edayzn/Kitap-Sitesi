/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.bean;

import admin.dao.AuthorDao;
import admin.dao.BookDao;
import admin.dao.CategoryDao;
import admin.dao.CommentDao;

import admin.dao.FileDao;
import admin.dao.LikeDao;
import admin.dao.RoleDao;
import admin.dao.UserDao;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Eda Yazan
 */
@Named
@SessionScoped
public class Dashboard implements Serializable {

    private int kategoriCount;
    private int userCount;
    private int yorumCount;
    private int bookCount;
    private int begeniCount;
    private int fileCount;
    private int roleCount;
    private int authorCount;

    private CategoryDao kategoriDao;
    private UserDao userDao;
    private CommentDao yorumDao;
    private BookDao bookDao;
    private LikeDao begeniDao;
    private FileDao fileDao;
    private RoleDao roleDao;
    private AuthorDao authorDao;

    public int getKategoriCount() {
        this.kategoriCount = this.getKategoriDao().itemCount();
        return kategoriCount;
    }

    public void setKategoriCount(int kategoriCount) {
        this.kategoriCount = kategoriCount;
    }

    public int getUserCount() {
        this.userCount = this.getUserDao().itemCount();
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getYorumCount() {
        this.yorumCount = this.getYorumDao().itemCountTop();
        return yorumCount;
    }

    public void setYorumCount(int yorumCount) {
        this.yorumCount = yorumCount;
    }

    public int getBookCount() {
        this.bookCount = this.getBookDao().itemCount();
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getBegeniCount() {
        this.begeniCount = this.getBegeniDao().itemCount();
        return begeniCount;
    }

    public void setBegeniCount(int begeniCount) {
        this.begeniCount = begeniCount;
    }

    public int getFileCount() {
        this.fileCount = this.getFileDao().itemCountTop();
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public int getRoleCount() {
        this.roleCount = this.getRoleDao().itemCount();
        return roleCount;
    }

    public void setRoleCount(int roleCount) {
        this.roleCount = roleCount;
    }

    public int getAuthorCount() {
        this.authorCount = this.getAuthorDao().itemCount();
        return authorCount;
    }

    public void setAuthorCount(int authorCount) {
        this.authorCount = authorCount;
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

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public CommentDao getYorumDao() {
        if(this.yorumDao==null)
            this.yorumDao=new CommentDao();
        return yorumDao;
    }

    public void setYorumDao(CommentDao yorumDao) {
        this.yorumDao = yorumDao;
    }

    public BookDao getBookDao() {
        if(this.bookDao==null)
            this.bookDao=new BookDao();
        return bookDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public LikeDao getBegeniDao() {
        if(this.begeniDao==null)
            this.begeniDao=new LikeDao();
        return begeniDao;
    }

    public void setBegeniDao(LikeDao begeniDao) {
        this.begeniDao = begeniDao;
    }

    public FileDao getFileDao() {
        if(this.fileDao==null)
            this.fileDao=new FileDao();
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public RoleDao getRoleDao() {
        if(this.roleDao==null)
            this.roleDao=new RoleDao();
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public AuthorDao getAuthorDao() {
        if(this.authorDao==null)
            this.authorDao=new AuthorDao();
        return authorDao;
    }

    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

}
