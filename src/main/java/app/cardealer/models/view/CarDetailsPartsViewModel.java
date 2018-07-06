package app.cardealer.models.view;

import java.util.List;

public class CarDetailsPartsViewModel extends CarDetailsViewModel {

    private List<PartViewModel> parts;

    public CarDetailsPartsViewModel() {

    }

    public List<PartViewModel> getParts() {
        return this.parts;
    }

    public void setParts(List<PartViewModel> parts) {
        this.parts = parts;
    }
}
