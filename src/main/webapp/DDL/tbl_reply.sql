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
