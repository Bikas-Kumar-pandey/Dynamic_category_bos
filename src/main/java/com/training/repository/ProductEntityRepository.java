package com.training.repository;

import com.training.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
}
