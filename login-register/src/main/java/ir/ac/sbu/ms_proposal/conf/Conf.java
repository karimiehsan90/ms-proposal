package ir.ac.sbu.ms_proposal.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "login-register")
@Component
@Getter
@Setter
public class Conf {
    private Auth auth;
}
