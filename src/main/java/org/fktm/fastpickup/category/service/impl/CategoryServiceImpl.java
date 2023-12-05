package org.fktm.fastpickup.category.service.impl;

import java.util.List;

import org.fktm.fastpickup.category.dto.CategoryDTO;
import org.fktm.fastpickup.category.mappers.CategoryMapper;
import org.fktm.fastpickup.category.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    // 의존성 주입
    private final CategoryMapper categoryMapper;

    // 카테고리 등록 서비스
    @Override
    public int registCategory(CategoryDTO categoryDTO) {

        log.info("===== Regist Category Service =====");
        
        return categoryMapper.registCategory(categoryDTO);
    }

    // 카테고리 읽기 서비스
    @Override
    public CategoryDTO readCategory(int cnt) {

        log.info("===== Read Category Service =====");

        return categoryMapper.readCategory(cnt);
    }

    // 카테고리 리스트 서비스
    @Override
    public List<CategoryDTO> getCategoryList() {

        log.info("===== Get CategoryList Service =====");

        return categoryMapper.getCategoryList();
    }

    // 카테고리 삭제 서비스
    @Override
    public int removeCategory(int cnt) {

        log.info("===== Remove Category Service =====");

        return  categoryMapper.removeCategory(cnt);
    }
    
}
