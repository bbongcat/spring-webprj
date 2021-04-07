package com.myapp.webprj.board.service;


import com.myapp.webprj.board.domain.Board;
import com.myapp.webprj.common.Criteria;

import java.util.List;

public interface BoardService {

    //레파지토리(여기선 BoardMapper)의 기능과 1:1로 매칭해주는 것이 좋음

    //게시글 등록 과정
    void register(Board board);

    //게시글 상세 조회 과정
    Board get(Long bno);

    //게시글 수정 과정
    boolean modify(Board board);

    //게시글 삭제 과정
    boolean remove(Long bno);

    //게시물 전체 조회 과정
    List<Board> getList(Criteria cri);

    int getTotal();

}
