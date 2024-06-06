package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.exception.ModelNotFoundException;
import org.softuni.mobilelele.exception.OfferNotFoundException;
import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.model.entity.Offer;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.repository.OfferRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long createOffer(CreateOfferDTO createOfferDTO) {

        UserEntity userEntity = this.userRepository.findByFirstName("test"); //TODO fix this

        Offer offer = map(createOfferDTO);

        Model model = this.modelRepository.findById(createOfferDTO.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDTO.getModelId() + " not found")
        );

        offer.setSeller(userEntity);

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
    public OfferViewDTO findOfferViewById(Long id) {
        Offer offer = this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
        return map(offer);
    }

    @Override
    public Offer findById(Long id) {
        return this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(UpdateOfferDTO updateOfferDTO) {
        Long id = updateOfferDTO.getId();
        Offer offer = this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));

        Model model = this.modelRepository.findById(updateOfferDTO.getModelId()).orElseThrow(() ->
                new ModelNotFoundException("Model with id " + updateOfferDTO.getModelId() + " not found")
        );

        offer.setModel(model);
        map(updateOfferDTO, offer);
        this.offerRepository.save(offer);
    }

    @Override
    public UpdateOfferDTO getOfferForUpdate(Long id) {
        Offer offer = this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
        return updateMap(offer);
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
                .setYear(createOfferDTO.getYear());
    }

    private void map(UpdateOfferDTO updateOfferDTO, Offer offer) {
        offer
                .setDescription(updateOfferDTO.getDescription())
                .setEngine(updateOfferDTO.getEngine())
                .setTransmission(updateOfferDTO.getTransmission())
                .setImageUrl(updateOfferDTO.getImageUrl())
                .setMileage(updateOfferDTO.getMileage())
                .setPrice(updateOfferDTO.getPrice())
                .setYear(updateOfferDTO.getYear());
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
