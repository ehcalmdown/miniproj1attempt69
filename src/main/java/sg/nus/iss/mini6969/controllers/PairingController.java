package sg.nus.iss.mini6969.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.mini6969.models.ProductMatches;
import sg.nus.iss.mini6969.services.PairingService;



@Controller
@RequestMapping("/productMatches")
public class PairingController {

    @Autowired
    private PairingService pairSvc;

    @GetMapping
    public String getProductMatches(Model model, @RequestParam String food){
        List<ProductMatches> productMatches = pairSvc.getProductMatches(food);
        model.addAttribute("food", food.toUpperCase());
        model.addAttribute("productMatches",productMatches);
        return "productMatches";
    }
    
}
