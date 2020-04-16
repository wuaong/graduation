package com.wqz.alumniBook.service;

import com.wqz.alumniBook.bean.MessageBoard;

import java.util.List;

public interface NoticeService {

    List<MessageBoard> getAllNotice();

    List<MessageBoard> getNoticeBySender(String senderName);

    List<MessageBoard> getNoticeByReceiver(String receiverName);

    void addNotice(MessageBoard notice);

    void deleteNoticeByUsername(String username);

    void deleteNoticeById(Integer nid);
}
