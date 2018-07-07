package app.cardealer.services;

import app.cardealer.entites.Sale;
import app.cardealer.models.view.SaleDetailsViewModel;
import app.cardealer.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SaleDetailsViewModel> extractSales() {
        List<Sale> salesFromDb = this.saleRepository.findAll();
        List<SaleDetailsViewModel> sales = new ArrayList<>();
        for (Sale sale : salesFromDb) {
            SaleDetailsViewModel saleViewModel = this.modelMapper.map(sale, SaleDetailsViewModel.class);
            sales.add(saleViewModel);
        }

        return sales;
    }

    @Override
    public SaleDetailsViewModel extractSaleById(Long id) {
        Sale saleFromDb = this.saleRepository.findById(id).orElse(null);
        if (saleFromDb == null) {
            return null;
        }

        SaleDetailsViewModel sale = this.modelMapper.map(saleFromDb, SaleDetailsViewModel.class);

        return sale;
    }
}
