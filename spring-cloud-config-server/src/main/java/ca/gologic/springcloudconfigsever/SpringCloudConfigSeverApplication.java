package ca.gologic.springcloudconfigsever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigSeverApplication.class, args);
	}

}
