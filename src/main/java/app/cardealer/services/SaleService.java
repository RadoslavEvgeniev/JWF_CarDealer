package app.cardealer.services;

import app.cardealer.models.view.SaleDetailsViewModel;

import java.util.List;

public interface SaleService {

    List<SaleDetailsViewModel> extractSales();

    SaleDetailsViewModel extractSaleById(Long id);
}
