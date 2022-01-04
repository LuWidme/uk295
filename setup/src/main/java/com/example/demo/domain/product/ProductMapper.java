package com.example.demo.domain.product;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTOWithPrice productToProductDTOWithPrice(Product product);
    ProductDTOWithoutPrice productToProductDTOWithoutPrice(Product product);

}


