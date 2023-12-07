package org.fktm.fastpickup.product.mappers;

import org.fktm.fastpickup.product.dto.ProductDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;

public interface ProductMapper {
    
    // 상품 등록
    int registProduct(ProductRegistDTO productRegistDTO);

    // 상품 상세
    ProductDTO readProduct(Long pno);

    // 상품 리스트
    

}
