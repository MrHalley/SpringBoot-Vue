package org.sang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 *
 */
@Configuration
@EnableAuthorizationServer //开启授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //该对象将用来支持password模式
    private final AuthenticationManager authenticationManager;
    //该对象将用来完成redis缓存。将令牌信息存储到redis缓存中
    private final RedisConnectionFactory redisConnectionFactory;
    //该对象将为刷新token提供支持
    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfig(AuthenticationManager authenticationManager,RedisConnectionFactory redisConnectionFactory, UserDetailsService userDetailsService) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("password")
                .authorizedGrantTypes("password","refresh_token")
                .accessTokenValiditySeconds(1800)
                //资源id
                .resourceIds("rid")
                .scopes("all")
                //明文 123
                .secret("$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //支持client_id和client_secret做登录认证
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //配置令牌的存储，AuthenticationManager和UserDetailService主要用于支持password模式以及令牌的刷新
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
