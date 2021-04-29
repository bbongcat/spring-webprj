create sequence seq_reply;

create table tbl_reply
(
    rno         NUMBER(10),
    bno         NUMBER(10)     not null,
    reply       VARCHAR2(1000) not null,
    replyer     VARCHAR2(50)   not null,
    reply_date  DATE default SYSDATE,
    update_date DATE default SYSDATE
);

alter table tbl_reply
    add constraint pk_reply
        primary key (rno);

alter table tbl_reply
    add constraint fk_reply_free_board
        foreign key (bno)
            references TBL_FREE_BOARD (bno);

select * from tbl_reply
where bno=1;

select * from TBL_REPLY;

-- tbl_free_board 댓글 수 컬럼 추가
alter table TBL_FREE_BOARD
    add reply_cnt number(10) default 0;

select * from TBL_FREE_BOARD;

select * from TBL_REPLY;

delete from TBL_REPLY where rno > 100;

update member
set auth = 'admin'
where account = 'watermelon';

commit;