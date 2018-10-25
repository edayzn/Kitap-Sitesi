/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eda Yazan
 */
public class BookDao extends Connect implements DAO<Book> {

    private AuthorDao authorDao;
    private CategoryDao categoryDao;
    private CommentDao commentDao;
    private FileDao fileDao;
    private LikeDao likeDao;
    
    

    @Override
    public void create(Book o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into book(book_name,category_id,author_id) values(?,?,?)");
            pst.setString(1, o.getBook_name());
            pst.setLong(2, o.getCategory().getCategory_id());
            pst.setLong(3, o.getAuthor().getAuthor_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Book o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update book set book_name=?,category_id=?,author_id=? where book_id=?");
            pst.setString(1, o.getBook_name());
            pst.setLong(2, o.getCategory().getCategory_id());
            pst.setLong(3, o.getAuthor().getAuthor_id());
            pst.setLong(4, o.getBook_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> list() {
        ArrayList<Book> bookList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from book");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Book tmp = new Book();
                tmp.setBook_id(rs.getLong("book_id"));
                tmp.setBook_name(rs.getString("book_name"));
                tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                bookList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    @Override
    public ArrayList<Book> pagedList(int page, int count) {
        ArrayList<Book> bookList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from book order by book_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Book tmp = new Book();
                tmp.setBook_id(rs.getLong("book_id"));
                tmp.setBook_name(rs.getString("book_name"));
                tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
                bookList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    @Override
    public void delete(Book o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from book where book_id=?");
            pst.setLong(1, o.getBook_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Book detail(Long id) {
        Book tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from book where book_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Book();
                tmp.setBook_id(rs.getLong("book_id"));
                tmp.setBook_name(rs.getString("book_name"));
                tmp.setDate_of_issue(rs.getDate("date_of_issue"));
                tmp.setCategory(this.getCategoryDao().detail(rs.getLong("category_id")));
                tmp.setAuthor(this.getAuthorDao().detail(rs.getLong("author_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(book_id) as book_count from book");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("book_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public AuthorDao getAuthorDao() {
        if (this.authorDao == null) {
            this.authorDao = new AuthorDao();
        }
        return authorDao;
    }

    public CategoryDao getCategoryDao() {
        if (this.categoryDao == null) {
            this.categoryDao = new CategoryDao();
        }
        return categoryDao;
    }

}
