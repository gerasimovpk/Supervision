/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import ru.isim.enterprise.rsubd.entities.Users;
import ru.isim.enterprise.rsubd.facades.UsersFacadeLocal;
import ru.isim.enterprise.rsubd.facades.UsersFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "adminUsers")
@RequestScoped
public class AdminUsers {

    /**
     * Creates a new instance of AdminTCO
     */
    public AdminUsers() {
    }

    @PostConstruct
    public void construct() {
        usersList = UserFacade.findAll();
    }
    private List<Users> usersList = new ArrayList<Users>();
    @EJB
    private UsersFacadeLocal UserFacade;
    private String name;
    private Users selectedUser;
    private String username;
    private String password;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> UsersList) {
        this.usersList = usersList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createUser() {
        System.out.println("start create User");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");
        Users user = new Users();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            UserFacade.create(user);
            context.addMessage(null, new FacesMessage("Сохранение", "Пользователь \"" + this.name + "\" успешно сохранен"));
            usersList = UserFacade.findAll();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при сохранении пользователя"));
        }
    }

    public void removeUser(Users User) {
        System.out.println("startremove");
        UserFacade.remove(User);
        usersList = UserFacade.findAll();
    }
}
