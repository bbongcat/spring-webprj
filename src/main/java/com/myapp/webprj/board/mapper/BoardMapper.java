package com.myapp.webprj.board.mapper;

import com.myapp.webprj.board.domain.Board;
import com.myapp.webprj.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //글 전체 목록 조회
    List<Board> getList();

    //글 목록 조회 (페이징 처리)
    List<Board> getListWithPaging(Criteria cri);

    //총 게시물 수 조회
    int getTotalCount();

    //제목으로 검색
    List<Board> getListByTitle(Criteria cri);
    //제목으로 검색 게시물 수 조회
    int getTotalCountByTitle(Criteria cri);

    //검색 처리 통합 조회
    List<Board> getSearchList(Criteria cri);
    int getSearchTotalCount(Criteria cri);

    //글 상세 조회
    Board findByBno(Long bno);

    //글 상세 조회 시 첨부파일명들 조회
    List<String> findFileNames(Long bno);

    //글 쓰기 기능
    void save(Board board);

    //글 수정에 성공하면 1 실패하면 0 리턴
    //글 수정 기능
    int update(Board board);

    //글 삭제 기능
    int delete(Long bno);

    //게시판 댓글 수 증가
    void increaseReplyCount(Long bno);
}
