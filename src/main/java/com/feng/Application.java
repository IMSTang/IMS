package com.feng;

import com.feng.common.utils.ServiceInfoUtil;
import com.feng.common.utils.security.ShiroUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author feng
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
        int port = ServiceInfoUtil.getPort();
        System.out.println("open URL : http://localhost:"+port);

    }
}