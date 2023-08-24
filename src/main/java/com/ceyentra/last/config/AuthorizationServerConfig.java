package com.ceyentra.last.config;

import com.ceyentra.last.dao.UserDao;
import com.ceyentra.last.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import static com.ceyentra.last.constant.OAuth2Constant.*;

@Component
@EnableAuthorizationServer
@PropertySource("classpath:application.properties")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Environment env;

//    static final String CLIENT_ID = "oauth-client";
//    //	private String CLIENT_ID = env.getProperty("security.oauth2.client.client-id");
//    static final String CLIENT_SECRET = "oauth-secret";
//    static final String GRANT_TYPE = "password";
//    static final String AUTHORIZATION_CODE = "authorization_code";
//    static final String REFRESH_TOKEN = "refresh_token";
//    static final String IMPLICIT = "implicit";
//    static final String SCOPE_READ = "read";
//    static final String SCOPE_WRITE = "write";
//    static final String TRUST = "trust";
//    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 40;
//    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 80;
    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(ET_CLIENT_ID)
                .secret(bCryptPasswordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS)
                .and()


                .withClient(ADMIN_CLIENT_ID)
                .secret(bCryptPasswordEncoder.encode(CLIENT_SECRET))
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);



    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
      }

}
