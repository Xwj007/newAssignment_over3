package com.njfu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.njfu.mapper")
//锁定xml的sql语句文件
public class NjfuApplication {

	public static void main(String[] args) {
		SpringApplication.run(NjfuApplication.class, args);
	}
}
