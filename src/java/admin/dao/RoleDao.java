/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eda Yazan
 */
public class RoleDao extends Connect implements DAO<Role> {

    @Override
    public void create(Role o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("insert into role (role_adi) values(?)");
            pst.setString(1, o.getRole_adi());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Role o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("update role set role_adi=? where role_id=?");
            pst.setString(1, o.getRole_adi());
            pst.setLong(2, o.getRole_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Role> list() {
        ArrayList<Role> gList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from role");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Role tmp = new Role();
                tmp.setRole_id(rs.getLong("role_id"));
                tmp.setRole_adi(rs.getString("role_adi"));
                gList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gList;
    }

    @Override
    public ArrayList<Role> pagedList(int page, int count) {
        ArrayList<Role> grupList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from role order by role_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Role tmp = new Role();
                tmp.setRole_id(rs.getLong("role_id"));
                tmp.setRole_adi(rs.getString("role_adi"));
                grupList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return grupList;
    }

    @Override
    public void delete(Role o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from role where role_id=?");
            pst.setLong(1, o.getRole_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Role detail(Long id) {
        Role tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from role where role_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new Role();
                tmp.setRole_id(rs.getLong("role_id"));
                tmp.setRole_adi(rs.getString("role_adi"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(role_id) as role_count from role");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("role_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
