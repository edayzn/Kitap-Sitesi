/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Book;
import admin.entity.Like;
import admin.entity.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eda Yazan
 */
public class LikeDao extends Connect implements DAO<Like> {

    private UserDao userDao;
    private BookDao bookDao;

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

    public BookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new BookDao();
        }
        return bookDao;
    }

    public boolean kontrol(Like o) {

        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from begen where book_id=? and user_id=?");
            pst.setLong(1, o.getBook().getBook_id());
            pst.setLong(2, o.getUser().getUser_id());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                PreparedStatement pst1 = this.getC().prepareStatement("update begen set begenme=? where begeni_id=?");
                pst1.setBoolean(1, o.isBegenme());
                pst1.setLong(2, rs.getLong("begeni_id"));
                pst1.executeUpdate();
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public void create(Like o) {
        try {
            if (this.kontrol(o)) {
                PreparedStatement pst = this.getC().prepareStatement("insert into begen(begenme,user_id,book_id) values(?,?,?)");
                pst.setBoolean(1, o.isBegenme());
                pst.setLong(2, o.getUser().getUser_id());
                pst.setLong(3, o.getBook().getBook_id());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Dao hata" + e.getMessage());
        }
    }

    @Override
    public void update(Like o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update begen set begenme=?,user_id=?,book_id=? where begeni_id=?");
            pst.setBoolean(1, o.isBegenme());
            pst.setLong(2, o.getUser().getUser_id());
            pst.setLong(3, o.getBook().getBook_id());
            pst.setLong(4, o.getBegeni_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Like> list() {
        ArrayList<Like> gList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from begen");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Like tmp = new Like();
                tmp.setBegeni_id(rs.getLong("begeni_id"));
                tmp.setBegenme(rs.getBoolean("begenme"));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                gList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gList;
    }

    @Override
    public ArrayList<Like> pagedList(int page, int count) {
        ArrayList<Like> List = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from begen order by begeni_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Like tmp = new Like();
                tmp.setBegeni_id(rs.getLong("begeni_id"));
                tmp.setBegenme(rs.getBoolean("begenme"));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
                List.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return List;
    }

    @Override
    public void delete(Like o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from begen where begeni_id=?");
            pst.setLong(1, o.getBegeni_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Like detail(Long id) {
        Like tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from begen where begeni_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tmp = new Like();
                tmp.setBegeni_id(rs.getLong("begeni_id"));
                tmp.setBegenme(rs.getBoolean("begenme"));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                tmp.setUser(this.getUserDao().detail(rs.getLong("user_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(begeni_id) as b_count from begen");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("b_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public void updateOne(Like p) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update begen set begenme=?  where begeni_id=?");
            pst.setBoolean(1, !p.isBegenme());
            pst.setLong(2, p.getBegeni_id());
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateOne1(Book p, User s, boolean k) {
        try {
            Like tmp = new Like();
            tmp.setBegenme(k);
            tmp.setBook(p);
            tmp.setUser(s);
            if (this.kontrol(tmp)) {
                PreparedStatement pst = this.getC().prepareStatement("insert into begen(book_id,user_id,begenme) values(?,?,?)");
                pst.setLong(1, p.getBook_id());
                pst.setLong(2, s.getUser_id());
                pst.setBoolean(3, k);
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int likeCount(Long book_id) {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(begeni_id) as b_count from begen where book_id=? and begenme=?");
            pst.setLong(1, book_id);
            pst.setBoolean(2, true);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("b_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public int disLikeCount(Long book_id) {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(begeni_id) as b_count from begen where book_id=? and begenme=?");
            pst.setLong(1, book_id);
            pst.setBoolean(2, false);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("b_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
