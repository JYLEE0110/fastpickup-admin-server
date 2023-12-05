package org.fktm.fastpickup.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class CategoryDTO {

    private int cno;             // 카테고리 번호
    private String categoryName; // 카테고리 명
    private boolean isDeleted;   // 삭제여부
}
