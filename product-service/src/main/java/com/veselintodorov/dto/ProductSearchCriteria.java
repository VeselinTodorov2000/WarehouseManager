package com.veselintodorov.dto;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;

public class ProductSearchCriteria {
    @Nullable
    private String name;
    @Nullable
    private String category;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    public ProductSearchCriteria() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }
}
