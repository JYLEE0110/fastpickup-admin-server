package org.fktm.fastpickup.product.mappers;

import java.util.List;

import org.fktm.fastpickup.product.dto.ProductDTO;
import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface ProductMapper {
    
    // 상품 등록
    int registProduct(ProductRegistDTO productRegistDTO);

    // 상품 상세
    ProductReadDTO readProduct(Long pno);

    // 상품 리스트
    List<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO);
    

}
