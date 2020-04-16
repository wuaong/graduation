package com.wqz.alumniBook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.wqz.alumniBook.dao")
@SpringBootApplication
public class AlumniBookApplication {

//    主程序
    public static void main(String[] args) {
        SpringApplication.run(AlumniBookApplication.class,args);
    }

}
