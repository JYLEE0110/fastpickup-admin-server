package org.fktm.fastpickup.category.service;

import java.util.List;

import org.fktm.fastpickup.category.dto.CategoryDTO;
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
public class CategoryServiceTests {

    @Autowired(required = false)
    CategoryService categoryService;

    private static final int TEST_CNO = 2;
    private static final String TEST_CATEGORY_NAME = "라떼";

    private CategoryDTO categoryDTO;

    @BeforeEach
    public void init() {

        categoryDTO = CategoryDTO.builder()
                .categoryName(TEST_CATEGORY_NAME)
                .build();
    }

    @Test
    @Transactional
    @DisplayName("카테고리 등록 서비스 테스트")
    public void registCategory() {

        // GIVEN
        log.info("===== Start Regist Category Service Test =====");

        // When
        int result = categoryService.registCategory(categoryDTO);

        // THEN
        log.info(result);
        Assertions.assertEquals(result, 1, "카테고리 등록에 실패 하였습니다.");
        log.info("===== End Regist Category Service Test =====");

    }

    @Test
    @Transactional
    @DisplayName("카테고리 읽기 서비스 테스트")
    public void readCategory() {

        // GIVEN
        log.info("===== Start read Category Service Test =====");

        // When
        CategoryDTO categoryDTO = categoryService.readCategory(TEST_CNO);

        // THEN
        log.info(categoryDTO);
        Assertions.assertEquals(categoryDTO.getCno(), TEST_CNO, "카테고리를 찾을 수 없습니다.");
        log.info("===== End read Category Service Test =====");

    }

    @Test
    @Transactional
    @DisplayName("카테고리 리스트 서비스 테스트")
    public void getCategoryList() {

        // GIVEN
        log.info("===== Start Get CategoryList Service Test =====");

        // When
        List<CategoryDTO> list = categoryService.getCategoryList();

        // THEN
        log.info(list);
        Assertions.assertNotNull(list,"리스트가 비어있습니다.");
        log.info("===== End Get CategoryList Service Test Test =====");

    }

}
