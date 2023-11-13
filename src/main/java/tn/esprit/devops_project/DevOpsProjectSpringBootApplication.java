package tn.esprit.devops_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles("test")
public class DevOpsProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOpsProjectSpringBootApplication.class, args);
    }

}
