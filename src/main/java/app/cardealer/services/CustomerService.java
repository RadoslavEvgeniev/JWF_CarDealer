package app.cardealer.services;

import app.cardealer.models.view.CustomerDetailsViewModel;

import java.util.List;

public interface CustomerService {

    List<CustomerDetailsViewModel> extractOrderedCustomersByBDay(String order);
}
