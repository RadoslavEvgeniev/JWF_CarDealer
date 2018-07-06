package app.cardealer.services;

import app.cardealer.entites.Customer;
import app.cardealer.models.view.CustomerBirthDayViewModel;
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
    public List<CustomerBirthDayViewModel> extractOrderedCustomersByBDay(String order) {
        List<Customer> customersFromDb = this.customerRepository.findAllOrderedByBirthDate();
        List<CustomerBirthDayViewModel> customersByBDate = new ArrayList<>();
        for (Customer customer : customersFromDb) {
            CustomerBirthDayViewModel customerViewModel = this.modelMapper.map(customer, CustomerBirthDayViewModel.class);
            customersByBDate.add(customerViewModel);
        }

        if (order.equals("descending")) {
            Collections.reverse(customersByBDate);
        }

        return customersByBDate;
    }
}
