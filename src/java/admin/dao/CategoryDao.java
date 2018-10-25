/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eda Yazan
 */
public class CategoryDao extends Connect implements DAO<Category> {

    @Override
    public void create(Category o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into category (category_name) values(?)");
            pst.setString(1, o.getCategory_name());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Category o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update category set category_name=? where category_id=?");
            pst.setString(1, o.getCategory_name());
            pst.setLong(2, o.getCategory_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Category> list() {
        ArrayList<Category> kList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from category");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category tmp = new Category();
                tmp.setCategory_id(rs.getLong("category_id"));
                tmp.setCategory_name(rs.getString("category_name"));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    @Override
    public ArrayList<Category> pagedList(int page, int count) {
        ArrayList<Category> kList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from category order by category_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category tmp = new Category();
                tmp.setCategory_id(rs.getLong("category_id"));
                tmp.setCategory_name(rs.getString("category_name"));
                kList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    @Override
    public void delete(Category o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from category where category_id=?");
            pst.setLong(1, o.getCategory_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Category detail(Long id) {
        Category tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from category where category_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Category();
                tmp.setCategory_id(rs.getLong("category_id"));
                tmp.setCategory_name(rs.getString("category_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(category_id) as category_count from category");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("category_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
