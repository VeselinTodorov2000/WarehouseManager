package com.veselintodorov.service;

import com.veselintodorov.dto.ProductSearchCriteria;
import com.veselintodorov.entity.Product;
import com.veselintodorov.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findByCriteria(ProductSearchCriteria searchCriteria) {
        Specification<Product> specification = ((root, query, builder) -> {
            if (searchCriteria.getName() != null) {
                return builder.and(builder.like(root.get("name"), "%" + searchCriteria.getName() + "%"));
            }
            if (searchCriteria.getCategory() != null) {
                return builder.and(builder.equal(root.get("category"), searchCriteria.getCategory()));
            }
            if (searchCriteria.getPriceFrom() != null && searchCriteria.getPriceTo() != null) {
                return builder.and(builder.between(root.get("price"), searchCriteria.getPriceFrom(), searchCriteria.getPriceTo()));
            } else {
                return null;
            }
        });

        return productRepository.findAll(specification);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public boolean create(Product product) {
        product.setCreatedAt(LocalDate.now());
        productRepository.save(product);
        return true;
    }

    public boolean update(Product product) {
        product.setUpdatedAt(LocalDate.now());
        productRepository.save(product);
        return true;
    }

    public boolean delete(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
