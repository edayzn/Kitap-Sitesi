/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Author;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eda Yazan
 */
public class AuthorDao extends Connect implements DAO<Author> {

    @Override
    public void create(Author o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into author (name_surname) values(?)");
            pst.setString(1, o.getName_surname());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Author o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update author set name_surname=? where author_id=?");
            pst.setString(1, o.getName_surname());
            pst.setLong(2, o.getAuthor_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Author> list() {
        ArrayList<Author> kList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from author");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Author tmp = new Author();
                tmp.setAuthor_id(rs.getLong("author_id"));
                tmp.setName_surname(rs.getString("name_surname"));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    @Override
    public ArrayList<Author> pagedList(int page, int count) {
        ArrayList<Author> kList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from author order by author_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Author tmp = new Author();
                tmp.setAuthor_id(rs.getLong("author_id"));
                tmp.setName_surname(rs.getString("name_surname"));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    @Override
    public void delete(Author o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from author where author_id=?");
            pst.setLong(1, o.getAuthor_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Author detail(Long id) {
        Author tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from author where author_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Author();
                tmp.setAuthor_id(rs.getLong("author_id"));
                tmp.setName_surname(rs.getString("name_surname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(author_id) as author_count from author");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("author_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
