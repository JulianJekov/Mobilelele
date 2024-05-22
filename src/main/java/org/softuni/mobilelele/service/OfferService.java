package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Offer;

import java.util.List;

public interface OfferService {
    Long createOffer(CreateOfferDTO createOfferDTO);
    List<OfferViewDTO> getAllOffers();

    OfferViewDTO findViewById(Long id);
    Offer findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(UpdateOfferDTO updateOfferDTO);

    UpdateOfferDTO updateMap(Offer offer);
}
