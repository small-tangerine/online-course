package com.course.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @since 2022-03-02
 */
@SpringBootApplication(scanBasePackages = {"com.course.admin","com.course.component","com.course.service","com.course.commons"})
@MapperScan("com.course.service.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
