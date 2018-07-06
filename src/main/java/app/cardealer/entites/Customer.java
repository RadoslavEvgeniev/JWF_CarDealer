package app.cardealer.entites;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    private Long id;
    private String name;
    private Date birthDate;
    private Boolean isYoungDriver;
    private List<Sale> sales;

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "is_young_driver")
    public Boolean getYoungDriver() {
        return this.isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    @OneToMany(mappedBy = "customer", targetEntity = Sale.class)
    public List<Sale> getSales() {
        return this.sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
