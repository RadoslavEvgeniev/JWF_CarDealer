package app.cardealer.controllers;

import app.cardealer.models.view.SupplierNumberOfPartsViewModel;
import app.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController extends BaseController {

    private SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public ModelAndView suppliersIndex() {
        return super.view("suppliers/suppliers-index");
    }

    @GetMapping("/{type}")
    public ModelAndView suppliersDetails(@PathVariable String type, ModelAndView modelAndView) {
        List<SupplierNumberOfPartsViewModel> suppliers = this.supplierService.extractSuppliersWithNumberOfParts(type);

        modelAndView.addObject("suppliers", suppliers);

        return super.view("suppliers/suppliers-details", modelAndView);
    }
}
