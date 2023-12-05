package org.fktm.fastpickup.category.mappers;

import java.util.List;

import org.fktm.fastpickup.category.dto.CategoryDTO;

public interface CategoryMapper {
    
    // 카테고리 등록
    int registCategory(CategoryDTO categoryDTO);

    // 카테고리 선택
    CategoryDTO readCategory(int cno);

    // 카테고리 삭제
    int removeCategory(int cno);

    // 카테고리 목록 리스트
    List<CategoryDTO> getCategoryList();
}
