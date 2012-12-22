/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.ejbEE;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "LOGGER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logger.findAll", query = "SELECT l FROM Logger l"),
    @NamedQuery(name = "Logger.findByMessage", query = "SELECT l FROM Logger l WHERE l.message = :message"),
    @NamedQuery(name = "Logger.findById", query = "SELECT l FROM Logger l WHERE l.id = :id")})
public class Logger implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "MESSAGE")
    private String message;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;

    public Logger() {
    }

    public Logger(BigDecimal id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logger)) {
            return false;
        }
        Logger other = (Logger) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.ejbEE.Logger[ id=" + id + " ]";
    }
    
}
