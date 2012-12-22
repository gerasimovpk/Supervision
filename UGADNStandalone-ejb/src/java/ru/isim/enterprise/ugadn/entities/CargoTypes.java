/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.ugadn.entities;

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
@Table(name = "CARGO_TYPES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoTypes.findAll", query = "SELECT c FROM CargoTypes c"),
    @NamedQuery(name = "CargoTypes.findByCode", query = "SELECT c FROM CargoTypes c WHERE c.code = :code"),
    @NamedQuery(name = "CargoTypes.findByTitle", query = "SELECT c FROM CargoTypes c WHERE c.title = :title")})
public class CargoTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE")
    private Integer code;
    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoType")
    private List<SpecialPermits> specialPermitsList;

    public CargoTypes() {
    }

    public CargoTypes(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public List<SpecialPermits> getSpecialPermitsList() {
        return specialPermitsList;
    }

    public void setSpecialPermitsList(List<SpecialPermits> specialPermitsList) {
        this.specialPermitsList = specialPermitsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoTypes)) {
            return false;
        }
        CargoTypes other = (CargoTypes) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.ugadn.entities.CargoTypes[ code=" + code + " ]";
    }
    
}
