package ra.apisecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ra.apisecurity.model.entity.Role;
import ra.apisecurity.model.entity.RoleName;
import ra.apisecurity.model.entity.User;
import ra.apisecurity.repository.IRoleRepository;
import ra.apisecurity.repository.IUserRepository;
import ra.apisecurity.security.jwt.JWTProvider;
import ra.apisecurity.security.principle.UserDetailCustom;

@SpringBootApplication
public class ApiSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSecurityApplication.class, args);
    }
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
//    @Bean
//    public ObjectMapper getMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        return objectMapper;
//    }
    @Bean
    public CommandLineRunner runner(JWTProvider jwtProvider, UserDetailsService userDetailsService){
       return args -> {
           UserDetailCustom userDetails = (UserDetailCustom) userDetailsService.loadUserByUsername("hunghx");
           String token = jwtProvider.generateAccessToken(userDetails);
           System.out.println("token "+ token);

       };
    }

}
