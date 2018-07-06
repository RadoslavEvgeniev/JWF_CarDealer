package app.cardealer.services;

import app.cardealer.models.view.CustomerBirthDayViewModel;

import java.util.List;

public interface CustomerService {

    List<CustomerBirthDayViewModel> extractOrderedCustomersByBDay(String order);
}
