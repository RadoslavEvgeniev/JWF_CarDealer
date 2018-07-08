package app.cardealer.services;

import app.cardealer.models.view.SupplierNumberOfPartsViewModel;

import java.util.List;

public interface SupplierService {

    List<SupplierNumberOfPartsViewModel> extractSuppliersWithNumberOfParts(String type);

    List<SupplierNumberOfPartsViewModel> extractSuppliersWithNumberOfParts();
}
