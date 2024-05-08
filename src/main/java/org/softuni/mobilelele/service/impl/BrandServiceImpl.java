package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.dto.BrandDTO;
import org.softuni.mobilelele.model.entity.Model;
import org.softuni.mobilelele.repository.ModelRepository;
import org.softuni.mobilelele.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;

    @Autowired
    public BrandServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {

        for (Model model : this.modelRepository.findAll()) {

        }
        return null;
    }
}
