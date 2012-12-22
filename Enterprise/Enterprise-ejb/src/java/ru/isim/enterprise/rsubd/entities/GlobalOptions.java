/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.isim.enterprise.rsubd.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "GLOBAL_OPTIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobalOptions.findAll", query = "SELECT g FROM GlobalOptions g"),
    @NamedQuery(name = "GlobalOptions.findByOptionName", query = "SELECT g FROM GlobalOptions g WHERE g.optionName = :optionName"),
    @NamedQuery(name = "GlobalOptions.findByOptionValue", query = "SELECT g FROM GlobalOptions g WHERE g.optionValue = :optionValue")})
public class GlobalOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "OPTION_NAME")
    private String optionName;
    @Size(max = 255)
    @Column(name = "OPTION_VALUE")
    private String optionValue;

    public GlobalOptions() {
    }

    public GlobalOptions(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (optionName != null ? optionName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlobalOptions)) {
            return false;
        }
        GlobalOptions other = (GlobalOptions) object;
        if ((this.optionName == null && other.optionName != null) || (this.optionName != null && !this.optionName.equals(other.optionName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.isim.enterprise.rsubd.entities.GlobalOptions[ optionName=" + optionName + " ]";
    }
    
}
