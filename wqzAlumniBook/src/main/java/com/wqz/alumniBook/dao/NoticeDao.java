package com.wqz.alumniBook.dao;

import com.wqz.alumniBook.bean.MessageBoard;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NoticeDao {

    List<MessageBoard> getAllNotice();

    List<MessageBoard> getNoticeBySender(String senderName);

    List<MessageBoard> getNoticeByReceiver(String receiverName);

    void addNotice(MessageBoard notice);

    void deleteNoticeByUsername(String username);

    void deleteNoticeById(Integer nid);


}
