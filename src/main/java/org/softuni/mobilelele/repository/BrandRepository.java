package org.softuni.mobilelele.repository;

import jakarta.persistence.NamedEntityGraph;
import org.softuni.mobilelele.model.entity.Brand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

//    @Query("select b from Brand b join fetch b.models")
    @EntityGraph(value = "brandWithModels", attributePaths = "models")
    @Query("select b from Brand b")
    List<Brand>getAllBrands();
}
