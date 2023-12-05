package org.fktm.fastpickup.category.mapper;

import java.util.List;

import org.fktm.fastpickup.category.dto.CategoryDTO;
import org.fktm.fastpickup.category.mappers.CategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class CategoryMapperTests {

    // 의존성 주입
    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    // static 상수 로 미리 테스트할 데이터를 정의
    private static final int TEST_CNO = 2;
    private static final String TEST_CATEGORY_NAME = "라떼";

    private CategoryDTO categoryDTO;

    @BeforeEach
    public void init() {

        categoryDTO = CategoryDTO.builder()
                .categoryName(TEST_CATEGORY_NAME)
                .build();

    }

    // 카테고리 등록 매퍼 테스트
    @Test
    @Transactional
    @DisplayName("카테고리 등록 매퍼 테스트")
    public void registCategory() {

        //GIVEN
        log.info("===== Start RegistCategory Mapper Test =====");

        //WHEN
        int result = categoryMapper.registCategory(categoryDTO);

        //THEN
        log.info("=========");
        log.info(result);
        log.info("=========");
        Assertions.assertEquals(categoryDTO.getCategoryName(), TEST_CATEGORY_NAME, "카테고리 명을 찾을 수 없습니다.");
        log.info("===== End RegistCategory Mapper Test =====");

    }

    @Test
    @Transactional
    @DisplayName("카테고리 읽기 매퍼 테스트")
    public void readCategory(){

        //GIVEN
        log.info("===== Start ReadCategory Mapper Test =====");

        //WHEN
        CategoryDTO categoryDTO = categoryMapper.readCategory(TEST_CNO);

        //THEN
        log.info(categoryDTO);
        Assertions.assertEquals(categoryDTO.getCno(), TEST_CNO);

    }

    @Test
    @Transactional
    @DisplayName("카테고리 리스트 매퍼 테스트")
    public void getCategoryList(){

        
        //GIVEN
        log.info("===== Start CategoryList Mapper Test =====");

        //WHEN
        List<CategoryDTO> list = categoryMapper.getCategoryList();

        //THEN
        log.info(list);
        Assertions.assertNotNull(list,"카테고리 리스트가 비어있습니다.");
        log.info("===== End CategoryList Mapper Test =====");

    }

    @Test
    @Transactional
    @DisplayName("카테고리 삭제 매퍼 테스트")
    public void removeCategory(){

        //GIVEN
        log.info("===== Start RemoveCategory Mapper Test =====");

        //WHEN
        categoryMapper.removeCategory(TEST_CNO);
        CategoryDTO categoryDTO = categoryMapper.readCategory(TEST_CNO);

        //THEN
        Assertions.assertEquals(categoryDTO.isDeleted(), true, "삭제가 되지 않았습니다.");
        log.info("===== End RemoveCategory Mapper Test =====");

    }

}
