package app.cardealer.controllers;

import app.cardealer.models.view.CustomerBirthDayViewModel;
import app.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ModelAndView allCustomersIndex() {

        return super.view("customers/all");
    }

    @GetMapping("/all/{order}")
    public ModelAndView allCustomersOrderByBDate(@PathVariable String order, ModelAndView modelAndView) {
        List<CustomerBirthDayViewModel> customers = this.customerService.extractOrderedCustomersByBDay(order);

        modelAndView.addObject("customers", customers);

        return super.view("customers/list-customers", modelAndView);
    }
}
