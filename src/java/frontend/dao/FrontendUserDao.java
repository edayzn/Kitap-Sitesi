/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.dao;

import admin.dao.Connect;
import admin.dao.UserDao;
import admin.entity.Role;
import admin.entity.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author essah
 */
public class FrontendUserDao extends Connect {

    public void create(User o) {
        String password = null;
        try {
            o.setRole(this.roleDetail());
            password = encryptPassword(o.getPassword());
            PreparedStatement pst = this.getC().prepareStatement("insert into user (email, password, name_surname,role_id) values(?,?,?,?)");
            pst.setString(1, o.getEmail());
            pst.setString(2, password);
            pst.setString(3, o.getName_surname());
            pst.setLong(4, o.getRole().getRole_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + o.getEmail());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(FrontendUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(User o) {
        String password = null;
        try {
            try {
                password = encryptPassword(o.getPassword());
                PreparedStatement pst = this.getC().prepareStatement("update user set name_surname=?,email=?,password=? where user_id=?");
                pst.setString(1, o.getName_surname());
                pst.setString(2, o.getEmail());
                pst.setString(3, password);
                pst.setLong(4, o.getUser_id());
                pst.executeUpdate();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(User o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from user where user_id=?");
            pst.setLong(1, o.getUser_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Role roleDetail() {
        Role tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from role where role_adi=?");
            pst.setString(1, "User");
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

    private String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);

    }

    public User detail(Long id) {
        User tmp = null;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from user  where user_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = new User();
                tmp.setUser_id(rs.getLong("user_id"));
                tmp.setName_surname(rs.getString("name_surname"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPassword(rs.getString("password"));
                tmp.setCreated(rs.getDate("created"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }
}
