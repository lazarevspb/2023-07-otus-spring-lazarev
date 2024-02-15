package ru.lazarev.springcourse.library.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import ru.lazarev.springcourse.library.feign.AuthServiceProxy;
import ru.lazarev.springcourse.library.mapper.UserProfileMapper;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig {
    @Bean
    BeanPostProcessor beanPostProcessor(UserProfileMapper userProfileMapper, AuthServiceProxy authServiceProxy) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof RequestMappingHandlerAdapter) {
                    final RequestMappingHandlerAdapter handlerAdapter = (RequestMappingHandlerAdapter) bean;
                    List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(handlerAdapter.getArgumentResolvers());
                    argumentResolvers.add(0, new UserProfileArgumentResolver(userProfileMapper, authServiceProxy));
                    handlerAdapter.setArgumentResolvers(argumentResolvers);
                }
                return bean;
            }
        };
    }
}
