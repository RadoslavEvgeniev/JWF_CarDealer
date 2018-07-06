package app.cardealer.models.view;

public class SaleDetailsViewModel {

    private CarDetailsPartsViewModel car;

    private Double discount;

    public SaleDetailsViewModel() {
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
}
