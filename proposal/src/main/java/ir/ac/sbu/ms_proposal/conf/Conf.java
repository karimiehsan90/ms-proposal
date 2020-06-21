package ir.ac.sbu.ms_proposal.conf;

import ir.ac.sbu.ms_proposal.common.conf.Auth;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "proposal")
@Component
@Getter
@Setter
public class Conf {
    private Auth auth;
}
