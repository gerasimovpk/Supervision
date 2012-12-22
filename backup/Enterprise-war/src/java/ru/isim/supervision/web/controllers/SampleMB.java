/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sample.NewSessionBeanLocal;

/**
 *
 * @author User
 */
@Named(value = "sampleMB")
@RequestScoped
public class SampleMB {

    @EJB
    NewSessionBeanLocal sessionFacade;

    /**
     * Creates a new instance of SampleMB
     */
    public SampleMB() {
    }

    public String getSampleString() {
        return "sample string";

    }

    public void create() {
        System.out.println("start MB");
        sessionFacade.addRecord();
        System.out.println("finish MB");
    }
}
