/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.converters;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import ru.isim.enterprise.rsubd.entities.Roles;
import ru.isim.enterprise.rsubd.facades.RolesFacadeLocal;
import ru.isim.supervision.web.controllers.AdminUsers;

/**
 *
 * @author User
 */
@FacesConverter(forClass = Roles.class, value = "roleConverter")
public class RoleConverter implements javax.faces.convert.Converter {

    private AdminUsers ctrl;
    @EJB
    private RolesFacadeLocal rolesFacadeLocal;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        ctrl = (AdminUsers) context.getApplication().getELResolver().getValue(
                context.getELContext(), null, "adminUsers");

        Roles category = rolesFacadeLocal.findbyName(value);
        return category;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return "" + ((Roles) value).getRoleName();
    }
}