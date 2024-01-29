package potenday.pilsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PilsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PilsaApplication.class, args);
    }

}
