package fr.formation.jwtauthserver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
@EnableCaching
public class JwtAuthServerApplication {

    public static void main(String[] args) {
	SpringApplication.run(JwtAuthServerApplication.class, args);
    }

    /**
     * Declares a CacheManager. Stores clients.
     */
    @Bean
    public CacheManager createSimpleCacheManager() {
	SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
	List<Cache> caches = new ArrayList<>();
	caches.add(new ConcurrentMapCache("clients"));
	simpleCacheManager.setCaches(caches);
	return simpleCacheManager;
    }

    @Bean
    protected LocalValidatorFactoryBean validator(MessageSource messageSource) {
	// messageSource = messages.properties in src/main/resources
	LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	validatorFactoryBean.setValidationMessageSource(messageSource);
	return validatorFactoryBean;
    }
}
