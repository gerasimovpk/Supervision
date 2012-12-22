/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "WAYBILLS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Waybills.findAll", query = "SELECT w FROM Waybills w"),
    @NamedQuery(name = "Waybills.findByWaybillId", query = "SELECT w FROM Waybills w WHERE w.waybillId = :waybillId"),
    @NamedQuery(name = "Waybills.findByFuelGiven", query = "SELECT w FROM Waybills w WHERE w.fuelGiven = :fuelGiven"),
    @NamedQuery(name = "Waybills.findByFuelLost", query = "SELECT w FROM Waybills w WHERE w.fuelLost = :fuelLost"),
    @NamedQuery(name = "Waybills.findByMileageStart", query = "SELECT w FROM Waybills w WHERE w.mileageStart = :mileageStart"),
    @NamedQuery(name = "Waybills.findByMileageFinish", query = "SELECT w FROM Waybills w WHERE w.mileageFinish = :mileageFinish"),
    @NamedQuery(name = "Waybills.findByStatus", query = "SELECT w FROM Waybills w WHERE w.status = :status")})
public class Waybills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WAYBILL_ID")
    private Long waybillId;
    @Column(name = "FUEL_GIVEN")
    private Integer fuelGiven;
    @Column(name = "FUEL_LOST")
    private Integer fuelLost;
    @Column(name = "MILEAGE_START")
    private Long mileageStart;
    @Column(name = "MILEAGE_FINISH")
    private Long mileageFinish;
    @Column(name = "STATUS")
    private Short status;
    @JoinColumn(name = "TCO", referencedColumnName = "STATE_NUMBER")
    @ManyToOne(optional = false)
    private Tco tco;
    @JoinColumn(name = "DRIVER", referencedColumnName = "DRIVER_ID")
    @ManyToOne
    private Driver driver;

    public Waybills() {
    }

    public Waybills(Long waybillId) {
        this.waybillId = waybillId;
    }

    public Long getWaybillId() {
        return waybillId;
    }

    public void setWaybillId(Long waybillId) {
        this.waybillId = waybillId;
    }

    public Integer getFuelGiven() {
        return fuelGiven;
    }

    public void setFuelGiven(Integer fuelGiven) {
        this.fuelGiven = fuelGiven;
    }

    public Integer getFuelLost() {
        return fuelLost;
    }

    public void setFuelLost(Integer fuelLost) {
        this.fuelLost = fuelLost;
    }

    public Long getMileageStart() {
        return mileageStart;
    }

    public void setMileageStart(Long mileageStart) {
        this.mileageStart = mileageStart;
    }

    public Long getMileageFinish() {
        return mileageFinish;
    }

    public void setMileageFinish(Long mileageFinish) {
        this.mileageFinish = mileageFinish;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Tco getTco() {
        return tco;
    }

    public void setTco(Tco tco) {
        this.tco = tco;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (waybillId != null ? waybillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Waybills)) {
            return false;
        }
        Waybills other = (Waybills) object;
        if ((this.waybillId == null && other.waybillId != null) || (this.waybillId != null && !this.waybillId.equals(other.waybillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.rsubd.entities.Waybills[ waybillId=" + waybillId + " ]";
    }
    
}
