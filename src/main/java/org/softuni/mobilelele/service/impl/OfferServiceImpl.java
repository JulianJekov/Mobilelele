package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.model.entity.Offer;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.repository.OfferRepository;
import org.softuni.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {
        Offer offer = map(createOfferDTO);

        Model model = this.modelRepository.findById(createOfferDTO.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDTO.getModelId() + " not found")
        );

        offer.setModel(model);
        this.offerRepository.save(offer);
        return offer.getUuid();
    }

    private Offer map (CreateOfferDTO createOfferDTO) {
        return new Offer()
                .setUuid(UUID.randomUUID())
                .setDescription(createOfferDTO.getDescription())
                .setEngine(createOfferDTO.getEngine())
                .setTransmission(createOfferDTO.getTransmission())
                .setImageUrl(createOfferDTO.getImageUrl())
                .setMileage(createOfferDTO.getMileage())
                .setPrice(createOfferDTO.getPrice())
                .setYear(createOfferDTO.getYear());

    }
}
