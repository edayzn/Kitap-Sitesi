/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.converter;

import admin.dao.UserDao;
import admin.entity.User;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eda Yazan
 */
@FacesConverter(value = "userConverter")
public class userConverter implements Converter {

    private UserDao userDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getUserDao().detail(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        User r = (User) o;
        return r.getUser_id().toString();
    }

    public UserDao getUserDao() {
        if (this.userDao == null) {
            this.userDao = new UserDao();
        }
        return userDao;
    }

}
