package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OfferService {
    Long createOffer(CreateOfferDTO createOfferDTO, UserDetails seller);

    Page<OfferViewDTO> getAllOffers(Pageable pageable, UserDetails viewer);

    OfferViewDTO getOfferDetails(Long id, UserDetails viewer);

    Offer findById(Long id);


    void deleteOffer(Long id);

    void updateOffer(UpdateOfferDTO updateOfferDTO);

    UpdateOfferDTO getOfferForUpdate(Long id);

    boolean isOwner(Long id, String username);
}
