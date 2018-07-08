package app.cardealer.models.view;

public class SupplierNumberOfPartsViewModel {

    private Long id;
    private String name;
    private Integer numberOfParts;

    public SupplierNumberOfPartsViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfParts() {
        return this.numberOfParts;
    }

    public void setNumberOfParts(Integer numberOfParts) {
        this.numberOfParts = numberOfParts;
    }
}
