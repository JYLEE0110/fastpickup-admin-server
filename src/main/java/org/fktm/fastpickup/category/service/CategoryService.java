package org.fktm.fastpickup.category.service;

import java.util.List;
import java.util.Map;

import org.fktm.fastpickup.category.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryService {
    
    // 카테고리 등록
    int registCategory(CategoryDTO categoryDTO);

    // 카테고리 읽기
    CategoryDTO readCategory(int cnt);

    // 카테고리 리스트
    List<CategoryDTO> getCategoryList();

    // 카테고리 삭제
    int removeCategory(int cnt);
}
