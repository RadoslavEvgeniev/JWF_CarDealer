package app.cardealer.services;

import app.cardealer.entites.Customer;
import app.cardealer.models.binding.CustomerCreateBindingModel;
import app.cardealer.models.view.*;
import app.cardealer.repositories.CustomerRepository;
import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

            if (saleDetailsViewModel.getCustomer().getYoungDriver()) {
                totalMoneySpent += moneySpentPerSale - ((saleDetailsViewModel.getDiscount() + 0.05) * moneySpentPerSale);
            } else {
                totalMoneySpent += moneySpentPerSale - (saleDetailsViewModel.getDiscount() * moneySpentPerSale);
            }
        }

        customerViewModel.setTotalMoneySpent(totalMoneySpent);
        return customerViewModel;
    }

    @Override
    public void insertCustomer(CustomerCreateBindingModel customerBindingModel) {
        Customer customer = this.prepareCustomerForDb(new Customer(), customerBindingModel);

        this.customerRepository.save(customer);
    }

    @Override
    public CustomerEditViewModel extractCustomerForEdit(Long id) {
        Customer customerFromDb = this.customerRepository.findById(id).orElse(null);
        CustomerEditViewModel customerViewModel = new CustomerEditViewModel();

        customerViewModel.setId(customerFromDb.getId());
        customerViewModel.setName(customerFromDb.getName());
        customerViewModel.setBirthDateString(String.valueOf(customerFromDb.getBirthDate()));

        return customerViewModel;
    }

    @Override
    public void insertEditedCustomer(Long id, CustomerCreateBindingModel customerBindingModel) {
        Customer customerFromDb = this.customerRepository.getOne(id);
        Customer customer = this.prepareCustomerForDb(customerFromDb, customerBindingModel);

        this.customerRepository.save(customer);
    }

    private boolean isCustomerYoungDriver(String date) {
        String[] dateParams = date.split("-");
        DateTime birthDatePlusTwentyYears = new DateTime(Integer.parseInt(dateParams[0]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[2]) ,0,0).plusYears(20);

        return !birthDatePlusTwentyYears.isBeforeNow();
    }

    private Customer prepareCustomerForDb(Customer customer, CustomerCreateBindingModel customerBindingModel) {
        customer.setName(customerBindingModel.getName());
        try {
            customer.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(customerBindingModel.getBirthDateString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        customer.setYoungDriver(this.isCustomerYoungDriver(customerBindingModel.getBirthDateString()));

        return customer;
    }
}
