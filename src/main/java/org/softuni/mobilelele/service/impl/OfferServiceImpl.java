package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.repository.OfferRepository;
import org.softuni.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {

        throw new UnsupportedOperationException("Coming soon!");
    }
}
