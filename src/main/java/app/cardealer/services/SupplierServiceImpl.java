package app.cardealer.services;

import app.cardealer.entites.Supplier;
import app.cardealer.models.view.SupplierNumberOfPartsViewModel;
import app.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SupplierNumberOfPartsViewModel> extractSuppliersWithNumberOfParts(String type) {
        List<Supplier> supplierFromDb;
        List<SupplierNumberOfPartsViewModel> suppliers = new ArrayList<>();
        if (type.equals("local")) {
            supplierFromDb = this.supplierRepository.findAll().stream().filter(s -> !s.getImporter()).collect(Collectors.toList());
        } else {
            supplierFromDb = this.supplierRepository.findAll().stream().filter(s -> s.getImporter()).collect(Collectors.toList());
        }

        for (Supplier supplier : supplierFromDb) {
            SupplierNumberOfPartsViewModel supplierViewModel = this.modelMapper.map(supplier, SupplierNumberOfPartsViewModel.class);
            supplierViewModel.setNumberOfParts(supplier.getParts().size());
            suppliers.add(supplierViewModel);
        }

        return suppliers;
    }

    @Override
    public List<SupplierNumberOfPartsViewModel> extractSuppliersWithNumberOfParts() {
        List<Supplier> supplierFromDb = this.supplierRepository.findAll();
        List<SupplierNumberOfPartsViewModel> suppliers = new ArrayList<>();

        for (Supplier supplier : supplierFromDb) {
            SupplierNumberOfPartsViewModel supplierViewModel = this.modelMapper.map(supplier, SupplierNumberOfPartsViewModel.class);
            supplierViewModel.setNumberOfParts(supplier.getParts().size());
            suppliers.add(supplierViewModel);
        }

        return suppliers;
    }
}
