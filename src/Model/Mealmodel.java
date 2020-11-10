/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daltonream
 */
@Entity
@Table(name = "MEALMODEL")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Mealmodel.findAll", query = "SELECT m FROM Mealmodel m")
    , @NamedQuery(name = "Mealmodel.findById", query = "SELECT m FROM Mealmodel m WHERE m.id = :id")
    , @NamedQuery(name = "Mealmodel.findByDietrayrestictions", query = "SELECT m FROM Mealmodel m WHERE m.dietrayrestictions = :dietrayrestictions")
    , @NamedQuery(name = "Mealmodel.findByCaloricintake", query = "SELECT m FROM Mealmodel m WHERE m.caloricintake = :caloricintake")
    , @NamedQuery(name = "Mealmodel.findByMealdescription", query = "SELECT m FROM Mealmodel m WHERE m.mealdescription = :mealdescription")
    , @NamedQuery(name = "Mealmodel.findByDietRestAndMealDesc", query = "SELECT m FROM Mealmodel m WHERE m.dietrayrestictions = :dietrayrestictions and m.mealdescription = :mealdescription")
    , @NamedQuery(name = "Mealmodel.findByIDAndCaloricIntake", query = "SELECT m FROM Mealmodel m WHERE m.id = :id and m.caloricintake = :caloricintake")})
public class Mealmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DIETRAYRESTICTIONS")
    private String dietrayrestictions;
    @Basic(optional = false)
    @Column(name = "CALORICINTAKE")
    private int caloricintake;
    @Basic(optional = false)
    @Column(name = "MEALDESCRIPTION")
    private String mealdescription;

    public Mealmodel() {
    }

    public Mealmodel(Integer id) {
        this.id = id;
    }

    public Mealmodel(Integer id, int caloricintake, String mealdescription) {
        this.id = id;
        this.caloricintake = caloricintake;
        this.mealdescription = mealdescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDietrayrestictions() {
        return dietrayrestictions;
    }

    public void setDietrayrestictions(String dietrayrestictions) {
        this.dietrayrestictions = dietrayrestictions;
    }

    public int getCaloricintake() {
        return caloricintake;
    }

    public void setCaloricintake(int caloricintake) {
        this.caloricintake = caloricintake;
    }

    public String getMealdescription() {
        return mealdescription;
    }

    public void setMealdescription(String mealdescription) {
        this.mealdescription = mealdescription;
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
        if (!(object instanceof Mealmodel)) {
            return false;
        }
        Mealmodel other = (Mealmodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Mealmodel[ id=" + id + " ]";
    }
    
}
