package org.sang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration  //java配置优先于application.properties配置
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setDefaultPageSize(2)
                .setPageParamName("page")
                .setLimitParamName("size")
                .setSortParamName("sort")
                .setBasePath("/api")
                .setReturnBodyOnCreate(true)
                .setReturnBodyOnUpdate(true);
    }
}
