/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eda Yazan
 */
public class CommentDao extends Connect implements DAO<Comment> {

    private UserDao userdao;
    private BookDao bookDao;

    public UserDao getUserdao() {
        if (this.userdao == null) {
            this.userdao = new UserDao();
        }
        return userdao;
    }

    public BookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new BookDao();
        }
        return bookDao;
    }

    @Override
    public void create(Comment o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into comment(comment,user_id,book_id) values(?,?,?)");
            pst.setString(1, o.getComment());
            pst.setLong(2, o.getUser().getUser_id());
            pst.setLong(3, o.getBook().getBook_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Comment o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update comment set comment=?,user_id=?,book_id=? where comment_id=?");
            pst.setString(1, o.getComment());
            pst.setLong(2, o.getUser().getUser_id());
            pst.setLong(3, o.getBook().getBook_id());
            pst.setLong(4, o.getComment_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Comment> list() {
        ArrayList<Comment> cList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from comment");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Comment tmp = new Comment();
                tmp.setComment_id(rs.getLong("comment_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setUser(this.getUserdao().detail(rs.getLong("user_id")));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                cList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cList;
    }

    @Override
    public ArrayList<Comment> pagedList(int page, int count) {
        ArrayList<Comment> yorumList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from comment order by comment_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Comment tmp = new Comment();
                tmp.setComment_id(rs.getLong("comment_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setUser(this.getUserdao().detail(rs.getLong("user_id")));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                yorumList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return yorumList;
    }

    @Override
    public void delete(Comment o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from comment where comment_id=?");
            pst.setLong(1, o.getComment_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Comment detail(Long id) {
        Comment tmp = null;
        try {
            Statement s = this.getC().createStatement();
            String sql = "select * from comment where comment_id=" + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                tmp = new Comment();
                tmp.setComment_id(rs.getLong("comment_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setUser(this.getUserdao().detail(rs.getLong("user_id")));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCountTop() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(comment_id) as yorum_count from comment");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("yorum_count");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public ArrayList<Comment> BookYorums(long id) {
        List<Comment> bookYorumlar = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from comment where book_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bookYorumlar.add(this.detail(rs.getLong("comment_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (ArrayList<Comment>) bookYorumlar;
    }

    public List<Comment> getUserYorums(long id) {
        List<Comment> userYorumlar = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from comment where user_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userYorumlar.add(this.detail(rs.getLong("comment_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userYorumlar;
    }

    public Comment detail1(Long id) {
        Comment tmp = null;
        try {
            Statement s = this.getC().createStatement();
            String sql = "select * from comment where comment_id=" + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                tmp = new Comment();
                tmp.setComment_id(rs.getLong("comment_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setUser(this.getUserdao().detail(rs.getLong("user_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int commentCount(Long book_id) {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(comment_id) as yorum_count from comment where book_id=?");
            pst.setLong(1, book_id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("yorum_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
