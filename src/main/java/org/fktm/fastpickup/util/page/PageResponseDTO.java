package org.fktm.fastpickup.util.page;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {

    private List<E> list; // 출력할 List
    private long total; //
    private int page; // 현재 page
    private int size; // 몇 개의 목록을 보여줄지에 대한 size
    private int startNum; // 현재 page 시작 번호 ex) 15일때 11 / 24일때 21...
    private int endNum; // 현재 page 마지막 번호
    private boolean nextBtn; // 다음 버튼 유무
    private boolean prevBtn; // 이전 버튼 유무
    private List<Integer> pageNums; // startNum과 endNum 사이 숫자 반환

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> list, long total, PageRequestDTO pageRequestDTO){
        this.list = list;
        this.total = total;
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

    //페이징 계산
    //시작 페이지 번호 계산
    this.startNum = ((int) (Math.ceil(this.page / 10.0) * 10) - 9);

    //끝 페이지 번호 계산
    this.endNum = this.startNum + 9;

    //끝 페이지가 last보다 크면 last로 끝페이지 수정
    // last가 실제 끝 번호
    // 그외는 현재 페이지에 대한 끝 번호
    int last = (int)(Math.ceil((total/(double)size)));
    this.endNum = endNum > last ? last : endNum;
    
    //시작페이지가 1보다 클 때만 이전버튼
    this.prevBtn = this.startNum > 1;
    
    //total이 끝 페이지 번호와 size를 곱한 값보다 크면 다음버튼
    this.nextBtn = total > this.endNum * this.size;

    this.pageNums = IntStream.rangeClosed(startNum, endNum).boxed().toList();

    }

}
