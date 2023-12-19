package org.fktm.fastpickup.product.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.product.service.ProductService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Map<Long, String>> registProduct(
        @Valid @RequestBody ProductRegistDTO productRegistDTO){

        log.info("===== /api/product/regist | Post =====");
        
        Long pno = productService.registProduct(productRegistDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(pno,"Success RegistProduct"));
    }

}
