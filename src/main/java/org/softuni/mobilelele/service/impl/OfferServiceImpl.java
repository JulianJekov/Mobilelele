package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.model.entity.Offer;
import org.softuni.mobilelele.model.entity.User;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.repository.OfferRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.OfferService;
import org.softuni.mobilelele.util.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public Long createOffer(CreateOfferDTO createOfferDTO) {

        User user = this.userRepository.findByFirstName(this.currentUser.getFirstName());

        Offer offer = map(createOfferDTO);

        Model model = this.modelRepository.findById(createOfferDTO.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDTO.getModelId() + " not found")
        );

        offer.setSeller(user);

        offer.setModel(model);
        this.offerRepository.save(offer);
        return offer.getId();
    }

    public List<OfferViewDTO> getAllOffers() {
        return this.offerRepository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public OfferViewDTO findViewById(Long id) {
        return this.offerRepository.findById(id).map(this::map).get();
    }

    @Override
    public Offer findById(Long id) {
        return this.offerRepository.findById(id).get();
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(UpdateOfferDTO updateOfferDTO) {
        Offer offer = this.offerRepository.findById(updateOfferDTO.getId()).get();

        Model model = this.modelRepository.findById(updateOfferDTO.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + updateOfferDTO.getModelId() + " not found")
        );

        offer.setModel(model);
        map(updateOfferDTO, offer);
        this.offerRepository.save(offer);
    }

    private OfferViewDTO map(Offer offer) {
        return new OfferViewDTO()
                .setId(offer.getId())
                .setDescription(offer.getDescription())
                .setEngine(offer.getEngine())
                .setImageUrl(offer.getImageUrl())
                .setMileage(offer.getMileage())
                .setPrice(offer.getPrice())
                .setTransmission(offer.getTransmission())
                .setYear(offer.getYear())
                .setModel(offer.getModel().getName())
                .setBrand(offer.getModel().getBrand().getName())
                .setCreated(offer.getCreated())
                .setModified(offer.getModified() != null ? offer.getModified() : offer.getCreated())
                .setSeller(offer.getSeller() != null ?
                        offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName() : "Anonymous");
    }

    private Offer map(CreateOfferDTO createOfferDTO) {
        return new Offer()
                .setDescription(createOfferDTO.getDescription())
                .setEngine(createOfferDTO.getEngine())
                .setTransmission(createOfferDTO.getTransmission())
                .setImageUrl(createOfferDTO.getImageUrl())
                .setMileage(createOfferDTO.getMileage())
                .setPrice(createOfferDTO.getPrice())
                .setYear(createOfferDTO.getYear())
                .setCreated(LocalDateTime.now());
    }

    private void map(UpdateOfferDTO updateOfferDTO, Offer offer) {
        offer
                .setDescription(updateOfferDTO.getDescription())
                .setEngine(updateOfferDTO.getEngine())
                .setTransmission(updateOfferDTO.getTransmission())
                .setImageUrl(updateOfferDTO.getImageUrl())
                .setMileage(updateOfferDTO.getMileage())
                .setPrice(updateOfferDTO.getPrice())
                .setYear(updateOfferDTO.getYear())
                .setModified(LocalDateTime.now());
    }

    public UpdateOfferDTO updateMap(Offer offer) {
        return new UpdateOfferDTO().setModelId(offer.getModel().getId())
                .setId(offer.getId())
                .setPrice(offer.getPrice())
                .setEngine(offer.getEngine())
                .setTransmission(offer.getTransmission())
                .setYear(offer.getYear())
                .setMileage(offer.getMileage())
                .setDescription(offer.getDescription())
                .setImageUrl(offer.getImageUrl());
    }

}
