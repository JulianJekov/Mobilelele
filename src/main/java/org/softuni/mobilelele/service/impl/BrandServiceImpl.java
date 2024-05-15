package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.BrandDTO;
import org.softuni.mobilelele.model.dto.ModelDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.repository.BrandRepository;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {

        return this.brandRepository.findAll().stream()
                .map(brand -> new BrandDTO(
                        brand.getBrand(),
                        this.modelRepository.findAllByBrandId(brand.getId()).stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::getName))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDTO::getName))
                .collect(Collectors.toList());

    }
}
