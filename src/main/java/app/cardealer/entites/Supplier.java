package app.cardealer.entites;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {

    private Long id;
    private String name;
    private Boolean isImporter;
    private List<Part> parts;

    public Supplier() {
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

    @Column(name = "is_importer")
    public Boolean getImporter() {
        return this.isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }

    @OneToMany(mappedBy = "supplier", targetEntity = Part.class)
    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
