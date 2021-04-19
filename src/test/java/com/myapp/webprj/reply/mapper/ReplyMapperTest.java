package com.myapp.webprj.reply.mapper;

import com.myapp.webprj.common.Criteria;
import com.myapp.webprj.reply.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired
    private ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물 번호에 해당하는 20개의 댓글이 정상적으로 삽입되어야 한다.")
    void replyInsertTest() {
        for (int i = 1; i <= 50; i++) {
            Reply reply = new Reply();
            reply.setBno(14L);
            reply.setReply("14번 게시물 댓글 no" + i);
            reply.setReplyer("테스터" + i);

            replyMapper.insert(reply);
        }
        assertTrue(replyMapper.getCount(14L) == 50);
    }

    @Test
    @DisplayName("해당 번호를 가진 댓글을 삭제하여야 한다.")
    //삭제테스트 시 아래 두 옵션을 넣으면 삭제 테스트 후 다시 롤백시킴
    @Transactional
    @Rollback
    void replyDeleteTest() {

        //when
        replyMapper.delete(4L);

        //then
        assertNull(replyMapper.read(4L));
    }

    @Test
    @DisplayName("총 댓글 수 조회 테스트")
    void replyCountTest() {

        assertTrue(replyMapper.getCount(1L) == 19);
    }

    @Test
    @DisplayName("댓글이 정상적으로 수정되어야 한다.")
    void replyUpdateTest() {
        Reply reply = new Reply();

        reply.setRno(3L);
        reply.setReply("ㅋㅋㅋ");
        reply.setReplyer("비빔면");

        replyMapper.update(reply);

        assertEquals(replyMapper.read(3L).getReply(), "ㅋㅋㅋ");
    }

    @Test
    @DisplayName("선생님의 수정 테스트")
    void replyModifyTest() {
        Reply reply = replyMapper.read(6L);
        reply.setReply("댓글을 수정했지~");

        replyMapper.update(reply);

        Reply modifiedReply = replyMapper.read(6L);

        assertNotEquals(modifiedReply.getUpdateDate(), modifiedReply.getReplyDate());
    }

    @Test
    @DisplayName("선생님) 댓글 목록을 페이징해서 조회할 수 있어야 한다.")
    void replyListTest() {

        Criteria cri = new Criteria(1, 10);
        List<Reply> replies = replyMapper.getList(1L, cri);

        for (Reply reply : replies) {
            System.out.println("reply = " + reply);

            assertEquals(replies.size(), 10);
            assertEquals(replies.get(0).getRno(), replyMapper.read(1L).getRno());
        }

    }
}