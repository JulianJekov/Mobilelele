package org.softuni.mobilelele.service.impl;

import jakarta.transaction.Transactional;
import org.softuni.mobilelele.aop.WarnIfExecutionExceeds;
import org.softuni.mobilelele.exception.ModelNotFoundException;
import org.softuni.mobilelele.exception.OfferNotFoundException;
import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.model.entity.Offer;
import org.softuni.mobilelele.model.entity.Role;
import org.softuni.mobilelele.model.entity.UserEntity;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.repository.OfferRepository;
import org.softuni.mobilelele.repository.UserRepository;
import org.softuni.mobilelele.service.MonitoringService;
import org.softuni.mobilelele.service.OfferService;
import org.softuni.mobilelele.util.LoggedUserEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Long createOffer(CreateOfferDTO createOfferDTO, UserDetails seller) {

        UserEntity userEntity = this.userRepository.findByEmail(seller.getUsername())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email " + seller.getUsername() + " not found"));

        Offer offer = mapOffer(createOfferDTO);

        Model model = this.modelRepository.findById(createOfferDTO.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDTO.getModelId() + " not found")
        );

        offer.setSeller(userEntity);

        offer.setModel(model);
        this.offerRepository.save(offer);
        return offer.getId();
    }

    @WarnIfExecutionExceeds(timeInMillis = 1000L)
    @Override
    public List<OfferViewDTO> getAllOffers(UserDetails viewer) {
        return this.offerRepository.findAll().stream()
                .map(offer -> mapOfferView(offer, viewer))
                .collect(Collectors.toList());
    }

    @WarnIfExecutionExceeds(timeInMillis = 500L)
    @Override
    public OfferViewDTO getOfferDetails(Long id, UserDetails viewer) {
        Offer offer = this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
        return mapOfferView(offer, viewer);
    }

    @Override
    public Offer findById(Long id) {
        return this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
    }

    @Transactional
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
        mapUpdateOffer(updateOfferDTO, offer);
        this.offerRepository.save(offer);
    }

    @Override
    public UpdateOfferDTO getOfferForUpdate(Long id) {
        Offer offer = this.offerRepository.findById(id).orElseThrow(() ->
                new OfferNotFoundException("Offer with id " + id + " not found!"));
        return updateMap(offer);
    }



    private OfferViewDTO mapOfferView(Offer offer, UserDetails viewer) {
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
                .setSeller(offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName())
                .setViewerIsOwner(isOwner(offer, viewer != null ? viewer.getUsername() : null));
    }

    @Override
    public boolean isOwner(Long id, String username) {
        return isOwner(this.offerRepository.findById(id).orElse(null), username);
    }

    private boolean isOwner(Offer offer, String username) {
        if (offer == null || username == null) {
            return false;
        }

        UserEntity viewerEntity = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Unknown user..."));

        if (isAdmin(viewerEntity)) {
            return true;
        }

        return Objects.equals(offer.getSeller().getId(), viewerEntity.getId());
    }

    private boolean isAdmin(UserEntity userEntity) {
        return userEntity.getRoles().stream()
                .map(Role::getRole)
                .anyMatch(r -> UserRoleEnum.ADMIN == r);
    }

    private Offer mapOffer(CreateOfferDTO createOfferDTO) {
        return new Offer()
                .setDescription(createOfferDTO.getDescription())
                .setEngine(createOfferDTO.getEngine())
                .setTransmission(createOfferDTO.getTransmission())
                .setImageUrl(createOfferDTO.getImageUrl())
                .setMileage(createOfferDTO.getMileage())
                .setPrice(createOfferDTO.getPrice())
                .setYear(createOfferDTO.getYear());
    }

    private void mapUpdateOffer(UpdateOfferDTO updateOfferDTO, Offer offer) {
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
