package by.sender.boot.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//public class Main {
//
//    public static void main(String[] args) {
//
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:spring-config.xml"); //move from src.main.java to src.main.resources
//        ctx.refresh();
//
//        UserService service = ctx.getBean("jpaUserService", UserService.class);
//        List<UsersEntity> contacts = service.findAll();
//        printAll(contacts);
//
//        contacts = service.findByUsername("Name1");
//        printAll(contacts);
//
//        contacts = service.findByUserId(1);
//        printAll(contacts);
//    }
//
//    private static void printAll(List<UsersEntity> contacts) {
//        System.out.println("printAll: ");
//        for (UsersEntity contact : contacts) {
//            System.out.println(contact);
//        }
//    }
//}


@ImportResource("classpath:spring-config.xml")
//@ComponentScan(basePackages = "by.sender.boot")
//@Import(Config.class)
//@EnableAutoConfiguration
//@EnableJpaRepositories("by.sender.boot")
//@EntityScan("by.sender.boot")
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

//    @Bean
//    @ConfigurationProperties(prefix = "spring")
//    DataSource ds() {
//        return DataSourceBuilder.create().build();
//    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

