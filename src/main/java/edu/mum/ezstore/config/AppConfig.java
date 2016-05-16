/**
 * 
 */
package edu.mum.ezstore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Sam
 *
 */
@Configuration
@ComponentScan(basePackages={"edu.mum.ezstore"},
		excludeFilters=@ComponentScan.Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class))
@PropertySource(value = {"classpath:application.properties" })
//@EnableScheduling
@EnableAspectJAutoProxy
//@EnableCaching
public class AppConfig {

//	@Autowired
//	private Environment env;
//
//	Get Property from property files
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
    }
//	Message source for this context, loaded from localized "messages_xx" files.
//  Files are stored inside src/main/resources
	@Bean 
	public ReloadableResourceBundleMessageSource messageSource(){
	    ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
	    reloadableResourceBundleMessageSource.setBasename("classpath:/messages/messages");
	    return reloadableResourceBundleMessageSource;
	}
	@Bean
	public MessageSourceAccessor messageSourceAccessor(){
		MessageSourceAccessor msg=new MessageSourceAccessor(messageSource());
		return msg;
	}
	
//	@Bean(name = "validator")
//	public LocalValidatorFactoryBean validator()
//	{
//	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//	    bean.setValidationMessageSource(messageSource());
//	    return bean;
//	}
//
//	@Bean
//	public CacheManager cacheManager()
//	{
//		return new ConcurrentMapCacheManager();
//	}
//	
//	@Bean
//	public JavaMailSenderImpl javaMailSenderImpl() {
//		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//		mailSenderImpl.setHost(env.getProperty("smtp.host"));
//		mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
//		mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
//		mailSenderImpl.setUsername(env.getProperty("smtp.username"));
//		mailSenderImpl.setPassword(env.getProperty("smtp.password"));
//
//		Properties javaMailProps = new Properties();
//		javaMailProps.put("mail.smtp.auth", true);
//		javaMailProps.put("mail.smtp.starttls.enable", true);
//
//		mailSenderImpl.setJavaMailProperties(javaMailProps);
//
//		return mailSenderImpl;
//	}
}
