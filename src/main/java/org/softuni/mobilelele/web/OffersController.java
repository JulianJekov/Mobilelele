package org.softuni.mobilelele.web;

import org.softuni.mobilelele.service.OfferService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String allOffers(Model model, @AuthenticationPrincipal UserDetails viewer) {
        model.addAttribute("offers", this.offerService.getAllOffers(viewer));
        return "offers";
    }

    @GetMapping("/{id}/details")
    public String showOffer(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal UserDetails viewer) {
        model.addAttribute("offer", this.offerService.getOfferDetails(id, viewer));
        return "details";
    }

    @PreAuthorize("@offerServiceImpl.isOwner(#id, #principal.username)")
    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable Long id, @AuthenticationPrincipal UserDetails principal) {
        this.offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }
}
