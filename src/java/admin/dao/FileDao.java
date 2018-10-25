/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.File1;
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
public class FileDao extends Connect implements DAO<File1> {

    private BookDao bookDao;

    @Override
    public void create(File1 o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into file(name,path,type,book_id) values(?,?,?,?)");
            pst.setString(1, o.getName());
            pst.setString(2, o.getPath());
            pst.setString(3, o.getType());
            pst.setLong(4, o.getBook().getBook_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Dao hata" + e.getMessage());
        }
    }

    @Override
    public void update(File1 o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<File1> list() {
        ArrayList<File1> kList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from file");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                File1 tmp = new File1();
                tmp.setFile_id(rs.getLong("file_id"));
                tmp.setName(rs.getString("name"));
                tmp.setPath(rs.getString("path"));
                tmp.setType(rs.getString("type"));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println("Dao hata" + e.getMessage());
        }
        return kList;
    }

    @Override
    public ArrayList<File1> pagedList(int page, int count) {
        ArrayList<File1> resimList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from file  order by file_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                File1 tmp = new File1();
                tmp.setFile_id(rs.getLong("file_id"));
                tmp.setName(rs.getString("name"));
                tmp.setPath(rs.getString("path"));
                tmp.setType(rs.getString("type"));
                tmp.setBook(this.getBookDao().detail(rs.getLong("book_id")));
                resimList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resimList;
    }

    @Override
    public void delete(File1 o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from file where file_id=?");
            pst.setLong(1, o.getFile_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public File1 detail(Long id) {
        File1 tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from file where file_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new File1();
                tmp.setFile_id(rs.getLong("file_id"));
                tmp.setName(rs.getString("name"));
                tmp.setPath(rs.getString("path"));
                tmp.setType(rs.getString("type"));
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
            PreparedStatement pst = this.getC().prepareStatement("select count(file_id) as resim_count from file");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("resim_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public BookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new BookDao();
        }
        return bookDao;
    }
    
      public ArrayList<File1> bookResimler(long aLong) {
        List<File1> flist = new ArrayList();
        try {
            Statement s = this.getC().createStatement();
            String sql = "select * from file where book_id=" + aLong;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                flist.add(this.detail(rs.getLong("file_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (ArrayList<File1>) flist;
    }

    public ArrayList<File1> bookResimler1(long aLong) {
        List<File1> flist = new ArrayList();
        try {
            Statement s = this.getC().createStatement();
            String sql = "select min(file_id) from file where book_id=" + aLong;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                flist.add(this.detail(rs.getLong("min(file_id)")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (ArrayList<File1>) flist;
    }

}
