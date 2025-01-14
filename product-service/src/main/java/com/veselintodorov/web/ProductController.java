package com.veselintodorov.web;

import com.veselintodorov.converter.ProductMapper;
import com.veselintodorov.dto.ProductDto;
import com.veselintodorov.dto.ProductSearchCriteria;
import com.veselintodorov.entity.Product;
import com.veselintodorov.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getProductsByCriteria(@RequestBody ProductSearchCriteria searchCriteria) {
        List<Product> products = productService.findByCriteria(searchCriteria);
        List<ProductDto> productDtoList = products.stream()
                .map(productMapper::mapEntityToDto)
                .toList();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        productService.create(productMapper.mapDtoToEntity(productDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestParam("id") Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        productService.update(productMapper.mapDtoToEntity(productDto));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@RequestParam("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
