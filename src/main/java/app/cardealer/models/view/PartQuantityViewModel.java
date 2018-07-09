package app.cardealer.models.view;

public class PartQuantityViewModel extends PartViewModel {

    private Long quantity;

    public PartQuantityViewModel() {
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
