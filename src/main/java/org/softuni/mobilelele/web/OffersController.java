package org.softuni.mobilelele.web;

import org.softuni.mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("offers")
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/{id}/details")
    public String showOffer(@PathVariable Long id, Model model) {
        model.addAttribute("offer", this.offerService.findViewById(id));
        return "details";
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id) {
        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
