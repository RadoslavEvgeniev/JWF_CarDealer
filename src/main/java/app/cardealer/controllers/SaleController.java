package app.cardealer.controllers;

import app.cardealer.models.view.SaleDetailsViewModel;
import app.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sales")
public class SaleController extends BaseController {

    private SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("")
    public ModelAndView salesIndex(ModelAndView modelAndView) {
        List<SaleDetailsViewModel> sales = this.saleService.extractSales();

        modelAndView.addObject("sales", sales);

        return super.view("sales/sales-index", modelAndView);
    }

    @GetMapping("/{id}")
    public ModelAndView salesDetails(@PathVariable Long id, ModelAndView modelAndView) {
        SaleDetailsViewModel sale = this.saleService.extractSaleById(id);

        modelAndView.addObject("sale", sale);

        return super.view("sales/sales-details", modelAndView);
    }

    @GetMapping("/discounted")
    public ModelAndView salesDiscounted(ModelAndView modelAndView) {
        List<SaleDetailsViewModel> sales = this.saleService.extractSales().stream().filter(s -> s.getDiscount() != 0).collect(Collectors.toList());

        modelAndView.addObject("sales", sales);

        return super.view("sales/sales-index-discounted", modelAndView);
    }

//    @GetMapping("/discounted/{percent}")
//    public ModelAndView salesDiscountedByPercent(@RequestParam(name = "percent") Double percent, ModelAndView modelAndView) {
//
//        List<SaleDetailsViewModel> sales = this.saleService.extractSales().stream().filter(s -> s.getDiscount() == percent).collect(Collectors.toList());
//
//        modelAndView.addObject("sales", sales);
//
//        return super.view("sales/sales-index-discounted", modelAndView);
//    }
}
