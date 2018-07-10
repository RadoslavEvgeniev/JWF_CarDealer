package app.cardealer.controllers;

import app.cardealer.models.binding.PartCreateBindingModel;
import app.cardealer.models.view.PartQuantityViewModel;
import app.cardealer.models.view.PartViewModel;
import app.cardealer.models.view.SupplierNumberOfPartsViewModel;
import app.cardealer.services.PartService;
import app.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("")
    public ModelAndView partsIndex(ModelAndView modelAndView) {
        List<PartViewModel> parts = this.partService.extractParts();

        modelAndView.addObject("parts", parts);

        return super.view("parts/parts-index", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView addPart(ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") == null) {
            return super.redirect("/users/login");
        }

        List<SupplierNumberOfPartsViewModel> suppliers = this.supplierService.extractSuppliersWithNumberOfParts();

        modelAndView.addObject("suppliers", suppliers);

        return super.view("parts/parts-create", modelAndView);
    }

    @PostMapping("/add")
    public ModelAndView addPartConfirm(PartCreateBindingModel partBindingModel) {
        this.partService.importPart(partBindingModel);

        return super.redirect("/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePart(@PathVariable Long id, ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") == null) {
            return super.redirect("/users/login");
        }

        PartViewModel part = this.partService.extractPartById(id);

        modelAndView.addObject("part", part);

        return view("parts/parts-delete", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePartConfirm(@PathVariable Long id) {
        this.partService.removePartById(id);

        return super.redirect("/");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPart(@PathVariable Long id, ModelAndView modelAndView, HttpServletRequest request) {
        if (request.getSession().getAttribute("user-id") == null) {
            return super.redirect("/users/login");
        }

        PartQuantityViewModel part = this.partService.extractPartQuantityById(id);

        modelAndView.addObject("part", part);

        return super.view("parts/parts-edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPartConfirm(@PathVariable Long id, @ModelAttribute PartCreateBindingModel partBindingModel) {
        this.partService.editPart(id, partBindingModel);

        return super.redirect("/");
    }
}
