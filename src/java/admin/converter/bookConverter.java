/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.converter;

import admin.dao.BookDao;
import admin.entity.Book;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eda Yazan
 */
@FacesConverter(value = "bookConverter")
public class bookConverter implements Converter {

    private BookDao bookDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBookDao().detail(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        Book r = (Book) o;
        return r.getBook_id().toString();
    }

    public BookDao getBookDao() {
        if (this.bookDao == null) {
            this.bookDao = new BookDao();
        }
        return bookDao;
    }

}
