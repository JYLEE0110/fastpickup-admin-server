package org.fktm.fastpickup.product.mapper;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.product.dto.ProductModifyDTO;
import org.fktm.fastpickup.product.dto.ProductReadDTO;
import org.fktm.fastpickup.product.dto.ProductRegistDTO;
import org.fktm.fastpickup.product.mappers.ProductImgMapper;
import org.fktm.fastpickup.product.mappers.ProductMapper;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductMapperTests {
    
    @Autowired(required = false)
    private ProductMapper productMapper;

    @Autowired(required = false)
    private ProductImgMapper productImgMapper;

    private static final Long TEST_PNO = 21L;
    private static final int TEST_CNO = 1;
    private static final int TEST_PRODUCT_PRICE = 1000;
    private static final String TEST_PRODUCT_NAME = "핫 라떼";
    private static final String TEST_PRODUCT_CONTENT = "맛있고 용암같이 뜨거운 라떼";
    private static final String TEST_IMG_UUID = "52c48e7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_IMG_UUID2 = "53c48e7d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_MODIFY_IMG_UUID = "Modify7d-e85a-4704-b47a-942e240ef675";
    private static final String TEST_MODIFY_IMG_UUID2 = "Modify8d-e85a-4704-b47a-942e240ef675";

    private static final String TEST_IMG_NAME = "postMan.jpg";
    private static final String TEST_IMG_NAME2 = "postMan2.jpg";
    private static final String TEST_PRODUCT_TYPE = "p";
    private static final String TEST_PRODUCT_KEYWORD = "수정";

    private ProductRegistDTO productRegistDTO;
    private ProductReadDTO productReadDTO;
    private ProductModifyDTO productModifyDTO;
    private List<ProductListDTO> productList;

    // 이미지
    private List<String> imgsNameList = new ArrayList<>();
    private List<String> modifyimgsNameList = new ArrayList<>();
    
    private PageRequestDTO pageRequestDTO;
    
    @BeforeEach
    public void init(){
        
        imgsNameList.add(TEST_IMG_UUID + "_" + TEST_IMG_NAME);
        imgsNameList.add(TEST_IMG_UUID2 + "_" + TEST_IMG_NAME2);

        modifyimgsNameList.add(TEST_MODIFY_IMG_UUID + "_" + TEST_IMG_NAME);
        modifyimgsNameList.add(TEST_MODIFY_IMG_UUID2 + "_" + TEST_IMG_NAME);
        
        productRegistDTO = ProductRegistDTO.builder()
        .cno(TEST_CNO)
        .productName(TEST_PRODUCT_NAME)
        .productPrice(TEST_PRODUCT_PRICE)
        .productContent(TEST_PRODUCT_CONTENT)
        .imgsName(imgsNameList)
        .build();

        productModifyDTO = ProductModifyDTO.builder()
        .pno(TEST_PNO)
        .productName("수정 된 상품 이름")
        .productPrice(999999)
        .productContent("수정 된 상품 내용")
        .imgsName(modifyimgsNameList)
        .build();

        pageRequestDTO = PageRequestDTO.builder()
        .type(TEST_PRODUCT_TYPE)
        .keyword(TEST_PRODUCT_KEYWORD)
        .build();

    }

    @DisplayName("상품 등록 매퍼 테스트")
    @Transactional
    @Test
    public void registProduct(){

        // GIVEN
        log.info("===== Start Regist Product Mapper Test=====");

        // WHEN
        productMapper.registProduct(productRegistDTO);
        Long pno = productRegistDTO.getPno();
        
        // 이미지 업로드 후 프론트 측에서 useState에 imgsName을 보관하고 있을 것
        // registProductDTO에서 imgsName을 뽑아온다.
        List<String> imgsName = productRegistDTO.getImgsName();

        if(imgsName != null && !imgsName.isEmpty()){

            AtomicInteger index = new AtomicInteger();

             // List형식(이미지가 여러개)으로 imgName, UUID, pno, ord등 을 Map 형식(key : value)로 받아온다. 
            List<Map<String, String>> imgList = imgsName.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String imgName = str.substring(37);
    
                return Map.of("uuid", uuid, "imgName", imgName, "pno", "" + pno, "imgOrd", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(imgList);

            // 이미지 등록
            productImgMapper.registProductImg(imgList);
        }

        // 이미지 확인
        log.info("===== productRegistDTO ImgsName =====");
        log.info(imgsName);

        // 상품 등록 DTO 확인
        log.info("===== productRegistDTO =====");
        log.info(productRegistDTO);

        //THEN
        Assertions.assertNotNull(pno,"등록에 실패 하였습니다.");
        log.info("===== End Regist Product Mapper Test=====");

    }

    @DisplayName("상품 상세 매퍼 테스트")
    @Transactional
    @Test
    public void readProduct(){

        // GIVEN
        log.info("===== Start Read Product Mapper Test=====");

        // WHEN
        productReadDTO = productMapper.readProduct(TEST_PNO);
        log.info("===== productReadDTO =====");
        log.info(productReadDTO);

        //THEN
        Assertions.assertNotNull(productReadDTO,"등록된 데이터가 없습니다.");
        log.info("===== End Read Product Mapper Test=====");

    }

    @DisplayName("상품 리스트 매퍼 테스트")
    @Transactional
    @Test
    public void getProductList(){

        // GIVEN
        log.info("===== Start Get Product List Mapper Test=====");

        // WHEN
        productList = productMapper.getProductList(pageRequestDTO);
        log.info("===== productListDTO =====");
        log.info(productList);

        //THEN
        Assertions.assertNotNull(productList,"등록된 데이터가 없습니다.");
        log.info("===== End Get Product List Mapper Test=====");

    }

    @DisplayName("상품 페이징을 위한 총 데이터 테스트")
    @Transactional
    @Test
    public void getTotal(){

        // GIVEN
        log.info("===== Start Get Product Total Mapper Test=====");

        // WHEN
        Long total = productMapper.getTotal(pageRequestDTO);
        log.info("===== productList Total =====");
        log.info(total);

        //THEN
        log.info("===== End Get Product Total Mapper Test=====");

    }

    @DisplayName("상품 삭제 매퍼 테스트")
    @Transactional
    @Test
    public void removeProduct(){

        // GIVEN
        log.info("===== Start Remove Product Mapper Test=====");

        // WHEN

        // 상품 삭제
        productMapper.removeProduct(TEST_PNO);
        // 상품 이미지 삭제
        productImgMapper.removeProductImg(TEST_PNO);

        productReadDTO = productMapper.readProduct(TEST_PNO);

        //THEN
        Assertions.assertEquals(productReadDTO, null,"삭제가 정상적으로 되지않았습니다.");
        log.info("===== End Remove Product Mapper Test=====");

    }

    @DisplayName("상품 수정 매퍼 테스트")
    @Transactional
    @Test
    public void modifyProdcut(){

        // GIVEN
        log.info("===== Start Modify Product Mapper Test=====");

        // WHEN
        // 이미지 부터 삭제 => 이미지는 순서(썸네일 때문에 삭제 후 다시 넣는다.)
        productReadDTO = productMapper.readProduct(TEST_PNO);
        Long pno = productReadDTO.getPno();
        productImgMapper.removeProductImg(pno);
        int result = productMapper.modifyProduct(productModifyDTO);

        // 이미지 업로드 후 프론트 측에서 useState에 imgsName을 보관하고 있을 것
        // registProductDTO에서 imgsName을 뽑아온다.
        List<String> imgsName = productModifyDTO.getImgsName();

        if(imgsName != null && !imgsName.isEmpty()){

            AtomicInteger index = new AtomicInteger();

             // List형식(이미지가 여러개)으로 imgName, UUID, pno, ord등 을 Map 형식(key : value)로 받아온다. 
            List<Map<String, String>> imgList = imgsName.stream().map(str -> {
                String uuid = str.substring(0, 36);
                String imgName = str.substring(37);
    
                return Map.of("uuid", uuid, "imgName", imgName, "pno", "" + pno, "imgOrd", "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(imgList);

            // 이미지 등록
            productImgMapper.registProductImg(imgList);


        //THEN
        Assertions.assertEquals(result, 1,"삭제가 정상적으로 되지않았습니다.");
        log.info("===== End Modify Product Mapper Test=====");

    }
}
}