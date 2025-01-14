package com.veselintodorov.converter;

import com.veselintodorov.dto.ProductDto;
import com.veselintodorov.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductMapper {
    public ProductDto mapEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public Product mapDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setUpdatedAt(LocalDate.now());
        return product;
    }
}
