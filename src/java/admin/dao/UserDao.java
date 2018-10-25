/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.dao;

import admin.entity.User;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eda Yazan
 */
public class UserDao extends Connect implements DAO<User> {

    private RoleDao roleDao;

    @Override
    public void create(User o) {
        String password = null;
        try {
            password = encryptPassword(o.getPassword());
            PreparedStatement pst = this.getC().prepareStatement("insert into user (email, password, name_surname,role_id) values(?,?,?,?)");
            pst.setString(1, o.getEmail());
            pst.setString(2, password);
            pst.setString(3, o.getName_surname());
            pst.setLong(4, o.getRole().getRole_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User o) {
        String password = null;
        try {
            try {
                password = encryptPassword(o.getPassword());
                PreparedStatement pst = this.getC().prepareStatement("update user set name_surname=?,email=?,password=?,role_id=? where user_id=?");
                pst.setString(1, o.getName_surname());
                pst.setString(2, o.getEmail());
                pst.setString(3, password);
                pst.setLong(4, o.getRole().getRole_id());
                pst.setLong(5, o.getUser_id());
                pst.executeUpdate();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<User> list() {
        ArrayList<User> cList = new ArrayList();
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from user");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User tmp = new User();
                tmp.setUser_id(rs.getLong("user_id"));
                tmp.setName_surname(rs.getString("name_surname"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPassword(rs.getString("password"));
                tmp.setCreated(rs.getDate("created"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                cList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cList;
    }

    @Override
    public ArrayList<User> pagedList(int page, int count) {
        ArrayList<User> userList = new ArrayList();
        int start = (page - 1) * count;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select * from user order by user_id asc limit " + start + "," + count);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User tmp = new User();
                tmp.setUser_id(rs.getLong("user_id"));
                tmp.setName_surname(rs.getString("name_surname"));
                tmp.setEmail(rs.getString("email"));
                tmp.setPassword(rs.getString("password"));
                tmp.setCreated(rs.getDate("created"));
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
                userList.add(tmp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    @Override
    public void delete(User o) {
        try {
            PreparedStatement pst = this.getC().prepareStatement("delete from user where user_id=?");
            pst.setLong(1, o.getUser_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
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
                tmp.setRole(this.getRoleDao().detail(rs.getLong("role_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tmp;
    }

    public User login(User u) {
        User tmp = null;
        try {
            String np = UserDao.encryptPassword(u.getPassword());
            PreparedStatement pst = this.getC().prepareStatement("select * from user where email=? and password=?");
            pst.setString(1, u.getEmail());
            pst.setString(2, np);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                tmp = this.detail(rs.getLong("user_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }

    private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));

        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public int itemCount() {
        int count = 0;
        try {
            PreparedStatement pst = this.getC().prepareStatement("select count(user_id) as user_count from user");
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt("user_count");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public RoleDao getRoleDao() {
        if (this.roleDao == null) {
            this.roleDao = new RoleDao();
        }
        return roleDao;
    }

}
