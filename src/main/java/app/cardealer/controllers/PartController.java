package app.cardealer.controllers;

import app.cardealer.models.binding.PartCreateBindingModel;
import app.cardealer.models.view.SupplierNumberOfPartsViewModel;
import app.cardealer.services.PartService;
import app.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController extends BaseController {

    private SupplierService supplierService;
    private PartService partService;

    @Autowired
    public PartController(SupplierService supplierService, PartService partService) {
        this.supplierService = supplierService;
        this.partService = partService;
    }

    @GetMapping("/add")
    public ModelAndView addPart(ModelAndView modelAndView) {
        List<SupplierNumberOfPartsViewModel> suppliers = this.supplierService.extractSuppliersWithNumberOfParts();

        modelAndView.addObject("suppliers", suppliers);

        return super.view("parts/parts-create", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addPartConfirm(PartCreateBindingModel partBindingModel) {
        this.partService.importPart(partBindingModel);

        return super.redirect("/");
    }


}
