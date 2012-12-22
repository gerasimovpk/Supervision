/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "TCO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tco.findAll", query = "SELECT t FROM Tco t"),
    @NamedQuery(name = "Tco.findByStateNumber", query = "SELECT t FROM Tco t WHERE t.stateNumber = :stateNumber"),
    @NamedQuery(name = "Tco.findByAtId", query = "SELECT t FROM Tco t WHERE t.atId = :atId"),
    @NamedQuery(name = "Tco.findByMark", query = "SELECT t FROM Tco t WHERE t.mark = :mark"),
    @NamedQuery(name = "Tco.findByModel", query = "SELECT t FROM Tco t WHERE t.model = :model"),
    @NamedQuery(name = "Tco.findByWeight", query = "SELECT t FROM Tco t WHERE t.weight = :weight")})
public class Tco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "STATE_NUMBER")
    private String stateNumber;
    @Size(max = 20)
    @Column(name = "AT_ID")
    private String atId;
    @Size(max = 50)
    @Column(name = "MARK")
    private String mark;
    @Size(max = 50)
    @Column(name = "MODEL")
    private String model;
    @Column(name = "WEIGHT")
    private Integer weight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tco")
    private List<Waybills> waybillsList;

    public Tco() {
    }

    public Tco(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @XmlTransient
    public List<Waybills> getWaybillsList() {
        return waybillsList;
    }

    public void setWaybillsList(List<Waybills> waybillsList) {
        this.waybillsList = waybillsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateNumber != null ? stateNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tco)) {
            return false;
        }
        Tco other = (Tco) object;
        if ((this.stateNumber == null && other.stateNumber != null) || (this.stateNumber != null && !this.stateNumber.equals(other.stateNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.rsubd.entities.Tco[ stateNumber=" + stateNumber + " ]";
    }
    
}
