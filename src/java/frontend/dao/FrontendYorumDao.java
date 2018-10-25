/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.dao;

import admin.dao.Connect;
import admin.entity.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FrontendYorumDao extends Connect {

    private FrontendUserDao userDao;
    private FrontendBookDao bookDao;

    public void create(Comment o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into comment(comment,user_id,book_id) values(?,?,?)");
            pst.setString(1, o.getComment());
            pst.setLong(2, o.getUser().getUser_id());
            pst.setLong(3, o.getBook().getBook_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("yorum create hatası =" + e.getMessage());
        }
    }

    public ArrayList<Comment> list() {
        ArrayList<Comment> cList = new ArrayList();
        try {
            Statement s = this.getC().createStatement();
            String sql = "select * from comment";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Comment tmp = new Comment();
                tmp.setComment_id(rs.getLong("comment_id"));
                tmp.setComment(rs.getString("comment"));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                cList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println("yorum list hatası=" + e.getMessage());
        }
        return cList;
    }

    public void delete(Comment o) {
        try {
            Statement s = this.getC().createStatement();
            String sql = "delete from comment where comment_id=" + o.getComment_id();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("yorum delete hatası=" + e.getMessage());
        }
    }

//frontend yorum kontrolu kullanıcıya ait
    public boolean kontrol(Comment o) {
        try {
            Statement s = this.getC().createStatement();
            String sql = "select * from comment where comment_id=" + o.getComment_id() + " and comment.user_id=" + o.getUser().getUser_id() + "";
            ResultSet rs = s.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("yorum kontrol hatası=" + e.getMessage());
        }
        return false;
    }

    public FrontendUserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new FrontendUserDao();
        }
        return userDao;
    }

    public FrontendBookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new FrontendBookDao();
        }
        return bookDao;
    }

}
