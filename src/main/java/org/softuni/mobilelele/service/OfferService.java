package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.CreateOfferDTO;
import org.softuni.mobilelele.model.dto.OfferViewDTO;
import org.softuni.mobilelele.model.dto.UpdateOfferDTO;
import org.softuni.mobilelele.model.entity.Offer;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface OfferService {
    Long createOffer(CreateOfferDTO createOfferDTO, UserDetails seller);
    List<OfferViewDTO> getAllOffers(UserDetails viewer);

    OfferViewDTO findOfferViewById(Long id, UserDetails viewer);
    Offer findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(UpdateOfferDTO updateOfferDTO);

    UpdateOfferDTO getOfferForUpdate(Long id);

    boolean isOwner(Long id, String username);
}
