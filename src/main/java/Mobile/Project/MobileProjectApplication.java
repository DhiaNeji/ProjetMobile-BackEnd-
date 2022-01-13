package Mobile.Project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import Mobile.Project.Entity.Item;
import Mobile.Project.Entity.User;
import Mobile.Project.Repository.ItemRepository;
import Mobile.Project.Repository.TradeRepository;
import Mobile.Project.Repository.UserRepository;

@SpringBootApplication
public class MobileProjectApplication {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(MobileProjectApplication.class, args);
	}

}
