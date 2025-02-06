package org.softuni.mobilelele.web;

import jakarta.validation.Valid;
import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.enums.EngineEnum;
import org.softuni.mobilelele.model.enums.TransmissionEnum;
import org.softuni.mobilelele.service.BrandService;
import org.softuni.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;


    @Autowired
    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("createOfferDTO")) {
            model.addAttribute("createOfferDTO", new CreateOfferDTO());
        }
        model.addAttribute("brands", this.brandService.getAllBrandsModels());
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateOfferDTO createOfferDTO, BindingResult bindingResult,
                      RedirectAttributes rAtt, @AuthenticationPrincipal UserDetails seller) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("createOfferDTO", createOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.createOfferDTO", bindingResult);
            return "redirect:/offer/add";
        }

        Long newId = this.offerService.createOffer(createOfferDTO, seller);
        return "redirect:/offers/" + newId + "/details";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, Model model) {

        UpdateOfferDTO updateOfferDTO = this.offerService.getOfferForUpdate(id);

        if (!model.containsAttribute("updateOfferDTO")) {
            model.addAttribute("updateOfferDTO", updateOfferDTO);
        }
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("brands", this.brandService.getAllBrandsModels());

        return "update";
    }

    @PatchMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @Valid UpdateOfferDTO updateOfferDTO, BindingResult bindingResult,
                         RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("updateOfferDTO", updateOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferDTO", bindingResult);
            return "redirect:/offer/" + id + "/update";
        }

        this.offerService.updateOffer(updateOfferDTO);
        return "redirect:/offers/" + id + "/details";
    }

}
