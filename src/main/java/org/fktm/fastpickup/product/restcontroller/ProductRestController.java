package org.fktm.fastpickup.product.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.product.service.ProductService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Log4j2
public class ProductRestController {

    // DI 생성자(RequiredArgsConstructor로 의존성 주입
    private final ProductService productService;

    // 상품 등록
    @PostMapping("/regist")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<Long, String>> registProduct(
            @Valid @RequestBody ProductRegistDTO productRegistDTO) {

        log.info("===== /api/product/regist | Post =====");

        Long pno = productService.registProduct(productRegistDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(pno, "Success RegistProduct"));
    }

    // 상품 상세보기
    @GetMapping("/read/{pno}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProductReadDTO> readProduct(
            @PathVariable("pno") Long pno) {
        log.info("===== /api/product/read/" + pno + " | GET =====");

        ProductReadDTO productReadDTO = productService.readProduct(pno);

        return ResponseEntity.status(HttpStatus.CREATED).body(productReadDTO);
    }

    // 상품 리스트
    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER') or permitAll()")
    public ResponseEntity<PageResponseDTO<ProductListDTO>> getProductList(
        PageRequestDTO pageRequestDTO
    ){
        log.info("===== /api/product/list/ | GET =====");

        PageResponseDTO<ProductListDTO> productList = productService.getProductList(pageRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(productList);
    }

    // 상품 삭제
    @PutMapping("/remove/{pno}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String,String>> removeProduct(
        @PathVariable("pno") Long pno
    ){
        log.info("===== /api/product/remove/" + pno + "| PUT =====");
        
        productService.removeProduct(pno);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result" , "Success Remove Product"));

    }

    // 상품 수정
    @PutMapping("/modify")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> modifyProduct(
        @Valid @RequestBody ProductModifyDTO productModifyDTO
    ){
        log.info("===== /api/product/modify/ | PUT =====");

        productService.modifyProduct(productModifyDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result" , "Success Modify Product"));

    }
}
