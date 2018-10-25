/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.converter;

import admin.dao.AuthorDao;
import admin.dao.CategoryDao;
import admin.entity.Author;
import admin.entity.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eda Yazan
 */
@FacesConverter(value = "authorConverter")
public class authorConverter implements Converter {

    private AuthorDao authorDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAuthorDao().detail(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        Author r = (Author) o;
        return r.getAuthor_id().toString();
    }

    public AuthorDao getAuthorDao() {
        if (this.authorDao == null) {
            this.authorDao = new AuthorDao();
        }
        return authorDao;
    }

}
