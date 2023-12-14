package org.fktm.fastpickup.product.service;

import java.sql.SQLException;
import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductService {

    // 상품 등록
    Long registProduct(ProductRegistDTO productRegistDTO);

    // 상품 상세 보기
    ProductReadDTO readProduct(Long pno);

    // 상품 리스트
    PageResponseDTO<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO);

    // 상품 삭제
    void removeProduct(Long pno);

    // 상품 수정
    void modifyProduct(ProductModifyDTO productModifyDTO);
 
}
