/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.converter;

import admin.dao.CategoryDao;
import admin.dao.LikeDao;
import admin.entity.Category;
import admin.entity.Like;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eda Yazan
 */
@FacesConverter(value = "likeConverter")
public class likeConverter implements Converter {
    private LikeDao likeDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getLikeDao().detail(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        Like r = (Like) o;
        return r.getBegeni_id().toString();
    }

    public LikeDao getLikeDao() {
        if (this.likeDao == null) {
            this.likeDao = new LikeDao();
        }
        return likeDao;
    }

}
