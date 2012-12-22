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
@Table(name = "ORGANIZATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizations.findAll", query = "SELECT o FROM Organizations o"),
    @NamedQuery(name = "Organizations.findByOrganizationId", query = "SELECT o FROM Organizations o WHERE o.organizationId = :organizationId"),
    @NamedQuery(name = "Organizations.findByTitle", query = "SELECT o FROM Organizations o WHERE o.title = :title")})
public class Organizations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORGANIZATION_ID")
    private Integer organizationId;
    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organiztionId")
    private List<SpecialPermits> specialPermitsList;

    public Organizations() {
    }

    public Organizations(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
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
        hash += (organizationId != null ? organizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizations)) {
            return false;
        }
        Organizations other = (Organizations) object;
        if ((this.organizationId == null && other.organizationId != null) || (this.organizationId != null && !this.organizationId.equals(other.organizationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.ugadn.entities.Organizations[ organizationId=" + organizationId + " ]";
    }
    
}
