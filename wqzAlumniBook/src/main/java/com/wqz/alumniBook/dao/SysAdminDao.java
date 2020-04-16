package com.wqz.alumniBook.dao;
import com.wqz.alumniBook.bean.SysAdmin;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAdminDao {

  SysAdmin getByUserName(String username);
}
