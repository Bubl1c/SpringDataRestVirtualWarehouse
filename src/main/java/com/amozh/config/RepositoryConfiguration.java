package com.amozh.config;

import com.amozh.Api;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    public RepositoryRestConfiguration config(){
        RepositoryRestConfiguration config = super.config();
        config.setBasePath(Api.CONTEXT);

//        config.setPageParamName("p")
//                .setLimitParamName("l")
//                .setSortParamName("q");

        return config;
    }
}