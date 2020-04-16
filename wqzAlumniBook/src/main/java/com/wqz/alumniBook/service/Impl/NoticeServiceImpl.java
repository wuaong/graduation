package com.wqz.alumniBook.service.Impl;

import com.wqz.alumniBook.bean.MessageBoard;
import com.wqz.alumniBook.dao.NoticeDao;
import com.wqz.alumniBook.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    @Override
    public List<MessageBoard> getAllNotice() {
        return noticeDao.getAllNotice();
    }

    @Override
    public List<MessageBoard> getNoticeBySender(String senderName) {
        return noticeDao.getNoticeBySender(senderName);
    }

    @Override
    public List<MessageBoard> getNoticeByReceiver(String receiverName) {
        return noticeDao.getNoticeByReceiver(receiverName);
    }


    @Override
    public void addNotice(MessageBoard notice) {
        noticeDao.addNotice(notice);
    }

    @Override
    public void deleteNoticeByUsername(String username) {
        noticeDao.deleteNoticeByUsername(username);
    }

    @Override
    public void deleteNoticeById(Integer nid) {
        noticeDao.deleteNoticeById(nid);
    }
}
