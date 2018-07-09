package app.cardealer.models.binding;

import java.util.List;

public class CarCreateBindingModel {

    private String carMakeAndModel;
    private Long travelledDistance;
    private List<Long> carParts;

    public CarCreateBindingModel() {
    }

    public String getCarMakeAndModel() {
        return this.carMakeAndModel;
    }

    public void setCarMakeAndModel(String carMakeAndModel) {
        this.carMakeAndModel = carMakeAndModel;
    }

    public Long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Long> getCarParts() {
        return this.carParts;
    }

    public void setCarParts(List<Long> carParts) {
        this.carParts = carParts;
    }
}
