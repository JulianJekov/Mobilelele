package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;

import java.util.UUID;

public interface OfferService {
    UUID createOffer(CreateOfferDTO createOfferDTO);
}
