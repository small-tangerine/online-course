package com.course.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author panguangming
 * @since 2022-03-02
 */
@SpringBootApplication(scanBasePackages = {"com.course.server","com.course.component","com.course.service","com.course.commons"})
@MapperScan("com.course.service.mapper")
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
