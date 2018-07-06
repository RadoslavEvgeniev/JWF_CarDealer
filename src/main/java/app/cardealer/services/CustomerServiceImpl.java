package app.cardealer.services;

import app.cardealer.entites.Customer;
import app.cardealer.models.view.CustomerDetailsSalesViewModel;
import app.cardealer.models.view.CustomerDetailsViewModel;
import app.cardealer.models.view.PartViewModel;
import app.cardealer.models.view.SaleDetailsViewModel;
import app.cardealer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerDetailsViewModel> extractOrderedCustomersByBDay(String order) {
        List<Customer> customersFromDb = this.customerRepository.findAllOrderedByBirthDate();
        List<CustomerDetailsViewModel> customersByBDate = new ArrayList<>();
        for (Customer customer : customersFromDb) {
            CustomerDetailsViewModel customerViewModel = this.modelMapper.map(customer, CustomerDetailsViewModel.class);
            customersByBDate.add(customerViewModel);
        }

        if (order.equals("descending")) {
            Collections.reverse(customersByBDate);
        }

        return customersByBDate;
    }

    @Override
    public CustomerDetailsSalesViewModel extractCustomerAndSales(Long id) {
        Customer customerFromDb = this.customerRepository.findById(id).orElse(null);
        if (customerFromDb == null) {
            return null;
        }

        CustomerDetailsSalesViewModel customerViewModel = this.modelMapper.map(customerFromDb, CustomerDetailsSalesViewModel.class);

        double totalMoneySpent = 0;
        for (SaleDetailsViewModel saleDetailsViewModel : customerViewModel.getSales()) {
            double moneySpentPerSale = 0;
            for (PartViewModel partViewModel : saleDetailsViewModel.getCar().getParts()) {
                moneySpentPerSale += partViewModel.getPrice();
            }

            totalMoneySpent += moneySpentPerSale - (saleDetailsViewModel.getDiscount() * moneySpentPerSale);
        }

        customerViewModel.setTotalMoneySpent(totalMoneySpent);
        return customerViewModel;
    }


}
