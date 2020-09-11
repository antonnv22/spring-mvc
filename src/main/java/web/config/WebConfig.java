package web.config;

import model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import service.ServiceCar;
import service.ServiceCarImpl;

import java.util.List;
import java.util.Locale;


@Configuration
@EnableWebMvc
@ComponentScan("web")
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public List<Car> getListCar(){
        return new ServiceCarImpl().findSome();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("locale");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setDefaultEncoding("UTF-8");
        source.setBasenames("messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

// -----------------------------------------------------------------------------------------------
//@Bean(name = "messageSource")
//public MessageSource getMessageSource() {
//    ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
//    // Set the base name for the messages properties file.
//    ret.setBasename("classpath:messages");
//    ret.setCacheSeconds(1);
//    ret.setUseCodeAsDefaultMessage(true);
//    ret.setDefaultEncoding("UTF-8");
//    return ret;
//}

//
//    /* The localeResolver is used to resolve user locale data. The CookieLocaleResolver object is used to save user locale information in browser cookie.
//     * This way user locale info can be transferred between request. If user disable cookie then you can use SessionLocaleResolver instead. */
//    @Bean(name = "localeResolver")
//    public CookieLocaleResolver getCookieLocaleResolver(){
//        // Create a CookieLocaleResolver object.
//        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
//        // Set cookie name
//        localeResolver.setCookieName("cookie-locale-info");
//        // Set default locale value.
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        // Set cookie max exist time.
//        localeResolver.setCookieMaxAge(3600);
//
//        return localeResolver;
//    }
//
//        /* If user disable cookie then you can use SessionLocaleResolver instead. */
//   /*@Bean(name = "localeResolver")
//    public SessionLocaleResolver getSessionLocaleResolver(){
//      // Create a SessionLocaleResolver object.
//      SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        // Set default locale in session.
//      localeResolver.setDefaultLocale(Locale.ENGLISH);
//        return localeResolver;
//    }*/
//    /* The LocaleChangeInterceptor is a interceptor that will intercept user locale change by a parameter value.
//     * For example, if we set the locale change parameter name is locale, then request url http://localhost:8088/index.jsp?locale=en will change
//     * user locale to en and display messages in src/main/resources/config/messages_en.properties.
//     *  */
//    @Bean(name="localeInterceptor")
//    public LocaleChangeInterceptor getLocaleInterceptor(){
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("locale");
//        return interceptor;
//    }
//
//    /* Also need to register above locale interceptor in spring then it will intercept user request url and parse out the lang query string to get user request locale.*/
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getLocaleInterceptor());
//    }
}