package app.cardealer.models.view;

public class SaleDetailsViewModel {

    private Long id;

    private CarDetailsPartsViewModel car;

    private Double discount;

    private CustomerDetailsViewModel customer;

    public SaleDetailsViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDetailsPartsViewModel getCar() {
        return this.car;
    }

    public void setCar(CarDetailsPartsViewModel car) {
        this.car = car;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public CustomerDetailsViewModel getCustomer() {
        return this.customer;
    }

    public void setCustomer(CustomerDetailsViewModel customer) {
        this.customer = customer;
    }
}
