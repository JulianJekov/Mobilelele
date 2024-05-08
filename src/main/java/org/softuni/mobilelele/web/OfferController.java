package org.softuni.mobilelele.web;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;


    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String all() {
        return "offers";
    }

    @GetMapping("/add")
    public String add() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(CreateOfferDTO createOfferDTO) {
        this.offerService.createOffer(createOfferDTO);
        return "index";
    }

    @GetMapping("/{uuid}details")
    public String details(@PathVariable("uuid") UUID uuid) {
        return "details";
    }
}
