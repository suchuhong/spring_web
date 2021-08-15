package spring.personalSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        System.out.println("运行入口");
        SpringApplication.run(WebApplication.class, args);
    }
}
