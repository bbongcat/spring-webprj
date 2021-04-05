package com.myapp.webprj.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//sql 테이블과 1:1로 매칭되는 도메인 클래스를 만들 것
@Setter @Getter
@ToString
public class Board {

    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
