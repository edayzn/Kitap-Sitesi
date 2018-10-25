/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.converter;

import admin.dao.CategoryDao;
import admin.dao.CommentDao;
import admin.entity.Category;
import admin.entity.Comment;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Eda Yazan
 */
@FacesConverter(value = "commentConverter")
public class commentConverter implements Converter {
    private CommentDao commentDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getCommentDao().detail(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object o) {
        Comment r = (Comment) o;
        return r.getComment_id().toString();
    }

    public CommentDao getCommentDao() {
        if (this.commentDao == null) {
            this.commentDao = new CommentDao();
        }
        return commentDao;
    }

}
