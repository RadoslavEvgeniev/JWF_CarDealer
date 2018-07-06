package app.cardealer.models.view;

import java.util.List;

public class CustomerDetailsSalesViewModel extends CustomerDetailsViewModel {

    private List<SaleDetailsViewModel> sales;

    private Double totalMoneySpent;

    public CustomerDetailsSalesViewModel() {
    }

    public List<SaleDetailsViewModel> getSales() {
        return this.sales;
    }

    public void setSales(List<SaleDetailsViewModel> sales) {
        this.sales = sales;
    }

    public Double getTotalMoneySpent() {
        return this.totalMoneySpent;
    }

    public void setTotalMoneySpent(Double totalMoneySpent) {
        this.totalMoneySpent = totalMoneySpent;
    }
}
