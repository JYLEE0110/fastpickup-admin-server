package org.fktm.fastpickup.util.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type; // 검색type
    private String keyword; // 검색어

    // page번호 음수값 제외처리
    public void setPage(int page) {
        if (page < 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    // size 음수값, 과도한 값 제외처리
    public void setSize(int size) {
        if (size < 0 || size > 100) {
            this.size = 10;
        } else {
            this.size = size;
        }
    }

    // limit에 들어갈 skip 계산
    // 4page일 경우 데이터는 31 ~ 40 이므로 getSkip시 30 => 30까지 데이터를 건너 뛰고 31부터 size(10개)를 가져옴
    public int getSkip() {
        return (this.page - 1) * this.size;
    }

    // 다음페이지를 위한 count
    public int getCountEnd() {
        return ((int) Math.ceil(this.page / 10.0) * (10 * this.size)) + 1;
    }

    // type 배열로 반환 처리
    public String[] getTypes() {
        if (this.type == null || this.type.isEmpty()) {
            return null;
        }
        return this.type.split("");
    }

}
