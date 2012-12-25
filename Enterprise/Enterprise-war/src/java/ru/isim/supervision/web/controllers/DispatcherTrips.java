/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.supervision.web.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import ru.isim.enterprise.rsubd.EnterpriseSFSBLocal;
import ru.isim.enterprise.rsubd.SPTripsCountException;
import ru.isim.enterprise.rsubd.entities.Driver;
import ru.isim.enterprise.rsubd.entities.Tco;
import ru.isim.enterprise.rsubd.entities.Waybills;
import ru.isim.enterprise.rsubd.facades.DriverFacadeLocal;
import ru.isim.enterprise.rsubd.facades.TcoFacadeLocal;
import ru.isim.enterprise.rsubd.facades.WaybillsFacadeLocal;
import ru.isim.supervision.ugadn.entities.SpecialPermits;
import ru.isim.supervision.ugadn.facades.SpecialPermitsFacadeLocal;

/**
 *
 * @author User
 */
@Named(value = "dispatcherTrips")
@RequestScoped
public class DispatcherTrips {

    /**
     * Creates a new instance of DispatcherTrips
     */
    public DispatcherTrips() {
    }

    @PostConstruct
    public void construct() {
        waybills = waybillsFacade.findAll();
        drivers = driverFacadeLocal.findAll();
        vehicles = tcoFacadeLocal.findAll();
        SPList = enterpriseSFSB.getAvailableSP();
    }

    public List<Waybills> getWaybills() {
        return waybills;
    }

    public void setWaybills(List<Waybills> waybills) {
        this.waybills = waybills;
    }
    private List<Waybills> waybills = new ArrayList<Waybills>();
    @EJB
    private WaybillsFacadeLocal waybillsFacade;
    @EJB
    private DriverFacadeLocal driverFacadeLocal;
    @EJB
    private TcoFacadeLocal tcoFacadeLocal;
    @EJB
    private EnterpriseSFSBLocal enterpriseSFSB;
    private List<Driver> drivers;
    private List<Tco> vehicles;
    private List<SpecialPermits> SPList;
    private int driverId;
    private int sp;
    private int fuelGiven = 0;
    private int fuelLost = 0;
    private Long milleageStart = Long.parseLong("0");
    private Long milleageFinish = Long.parseLong("0");
    private String stateNumber;

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }
    
    public List<SpecialPermits> getSPList() {
        return SPList;
    }

    public void setSPList(List<SpecialPermits> SPList) {
        this.SPList = SPList;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public Long getMilleageFinish() {
        return milleageFinish;
    }

    public void setMilleageFinish(Long milleageFinish) {
        this.milleageFinish = milleageFinish;
    }

    public Long getMilleageStart() {
        return milleageStart;
    }

    public void setMilleageStart(Long milleageStart) {
        this.milleageStart = milleageStart;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public int getFuelGiven() {
        return fuelGiven;
    }

    public void setFuelGiven(int fuelGiven) {
        this.fuelGiven = fuelGiven;
    }

    public int getFuelLost() {
        return fuelLost;
    }

    public void setFuelLost(int fuelLost) {
        this.fuelLost = fuelLost;
    }

    

    public List<Tco> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Tco> vehicles) {
        this.vehicles = vehicles;
    }

    public void createTrip() {

        System.out.println("start create SP");
        RequestContext.getCurrentInstance().execute("createDialog.hide()");

        Waybills wb = new Waybills();
        wb.setDriver(driverFacadeLocal.find(driverId));
        wb.setFuelGiven(fuelGiven);
        wb.setFuelLost(fuelLost);
        wb.setMileageStart(milleageFinish);
        wb.setMileageFinish(milleageFinish);
        wb.setStatus(Short.valueOf("1"));
        wb.setTco(tcoFacadeLocal.find(stateNumber));

        FacesContext context = FacesContext.getCurrentInstance();

        try {
            enterpriseSFSB.createTrip(wb, sp);
            waybills = waybillsFacade.findAll();
            context.addMessage(null, new FacesMessage("Сохранение", "Перевозка успешно создана"));
        } catch (SPTripsCountException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "У данного спец.разрешения закончились доступные поездки"));
        }
            //        } catch (Exception e) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Произошла ошибка сервера при создании перевозки"));
//        }
    }
}
