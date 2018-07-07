package app.cardealer.controllers;

import app.cardealer.models.binding.CustomerCreateBindingModel;
import app.cardealer.models.view.CustomerDetailsSalesViewModel;
import app.cardealer.models.view.CustomerDetailsViewModel;
import app.cardealer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController extends BaseController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView allCustomersIndex() {

        return super.view("customers/customers-index");
    }

    @GetMapping("/all/{order}")
    public ModelAndView allCustomersOrderByBDate(@PathVariable String order, ModelAndView modelAndView) {
        List<CustomerDetailsViewModel> customers = this.customerService.extractOrderedCustomersByBDay(order);

        modelAndView.addObject("customers", customers);

        return super.view("customers/customers-list", modelAndView);
    }

    @GetMapping("/{id}")
    public ModelAndView customerTotalSales(@PathVariable Long id, ModelAndView modelAndView) {
        CustomerDetailsSalesViewModel customer = this.customerService.extractCustomerAndSales(id);

        modelAndView.addObject("customer", customer);

        return super.view("customers/customers-details", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView addCustomer() {
        return super.view("customers/customers-create");
    }

    @PostMapping("/add")
    public ModelAndView addCustomerConfirm(CustomerCreateBindingModel customerBindingModel) {
        this.customerService.insertCustomer(customerBindingModel);

        return super.redirect("/");
    }
}
