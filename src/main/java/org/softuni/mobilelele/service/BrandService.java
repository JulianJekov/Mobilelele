package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.dto.BrandDTO;
import org.softuni.mobilelele.model.dto.BrandViewDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> getAllBrandsModels();
    List<BrandViewDTO> getAllBrandsView();
}
