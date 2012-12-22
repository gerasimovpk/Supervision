/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.ugadn.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "SPECIAL_PERMITS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecialPermits.findAll", query = "SELECT s FROM SpecialPermits s"),
    @NamedQuery(name = "SpecialPermits.findBySpId", query = "SELECT s FROM SpecialPermits s WHERE s.spId = :spId"),
    @NamedQuery(name = "SpecialPermits.findByRouteDescription", query = "SELECT s FROM SpecialPermits s WHERE s.routeDescription = :routeDescription"),
    @NamedQuery(name = "SpecialPermits.findByStartDate", query = "SELECT s FROM SpecialPermits s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SpecialPermits.findByFinishDate", query = "SELECT s FROM SpecialPermits s WHERE s.finishDate = :finishDate"),
    @NamedQuery(name = "SpecialPermits.findByStatus", query = "SELECT s FROM SpecialPermits s WHERE s.status = :status"),
    @NamedQuery(name = "SpecialPermits.findByTripsCount", query = "SELECT s FROM SpecialPermits s WHERE s.tripsCount = :tripsCount")})
public class SpecialPermits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SP_ID")
    private Integer spId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ROUTE_DESCRIPTION")
    private String routeDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FINISH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @Column(name = "STATUS")
    private Short status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TRIPS_COUNT")
    private BigDecimal tripsCount;
    @JoinColumn(name = "ORGANIZTION_ID ", referencedColumnName = "ORGANIZATION_ID")
    @ManyToOne(optional = false)
    private Organizations organiztionId;
    @JoinColumn(name = "CARGO_TYPE", referencedColumnName = "CODE")
    @ManyToOne(optional = false)
    private CargoTypes cargoType;

    public SpecialPermits() {
    }

    public SpecialPermits(Integer spId) {
        this.spId = spId;
    }

    public SpecialPermits(Integer spId, String routeDescription, Date startDate, Date finishDate) {
        this.spId = spId;
        this.routeDescription = routeDescription;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigDecimal getTripsCount() {
        return tripsCount;
    }

    public void setTripsCount(BigDecimal tripsCount) {
        this.tripsCount = tripsCount;
    }

    public Organizations getOrganiztionId() {
        return organiztionId;
    }

    public void setOrganiztionId(Organizations organiztionId) {
        this.organiztionId = organiztionId;
    }

    public CargoTypes getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoTypes cargoType) {
        this.cargoType = cargoType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spId != null ? spId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpecialPermits)) {
            return false;
        }
        SpecialPermits other = (SpecialPermits) object;
        if ((this.spId == null && other.spId != null) || (this.spId != null && !this.spId.equals(other.spId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.ugadn.entities.SpecialPermits[ spId=" + spId + " ]";
    }
    
}
