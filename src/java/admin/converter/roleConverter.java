package admin.converter;

import admin.dao.RoleDao;
import admin.entity.Role;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "roleConverter")

public class roleConverter implements Converter {

    private RoleDao roleDao;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return this.getRoleDao().detail(Long.valueOf(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Role r = (Role) o;
        return r.getRole_id().toString();
    }

    public RoleDao getRoleDao() {
        if (this.roleDao == null) {
            this.roleDao = new RoleDao();
        }
        return roleDao;
    }

}
