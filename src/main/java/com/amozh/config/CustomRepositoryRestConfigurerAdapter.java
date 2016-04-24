package com.amozh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;

/**
 * Created by Andrii Mozharovskyi on 23.04.2016.
 */
@Configuration
public class CustomRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {
    @Autowired
    ApplicationContext context;

    /**
     * Workaround for correct validation (within JSR-303 annotations) exception handling according to
     * Spring Data Rest  <a href="https://jira.spring.io/browse/DATAREST-285">JIRA issue</a>
     * @param listener
     */
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener listener) {
        Validator validator = context.getBean("mvcValidator", Validator.class);
        listener.addValidator("beforeSave", validator);
        listener.addValidator("beforeCreate", validator);
    }
}
