package app.cardealer.entites;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    private Long id;
    private String make;
    private String model;
    private Long travelledDistance;
    private List<Part> parts;

    public Car() {
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

    @Column(name = "make", nullable = false)
    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance", nullable = false)
    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany(mappedBy = "cars", targetEntity = Part.class)
    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
