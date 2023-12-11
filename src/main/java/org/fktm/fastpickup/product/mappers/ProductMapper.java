package org.fktm.fastpickup.product.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fktm.fastpickup.product.dto.ProductDTO;
import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
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

    // 상품 삭제
    int removeProduct(Long pno);

    // 상품 수정
    int modifyProduct(ProductModifyDTO productModifyDTO);
    
}
