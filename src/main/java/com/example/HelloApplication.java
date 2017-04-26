package com.example;

import com.example.dto.RandomUser;
import com.example.dto.RandomUserApi;
import com.example.model.Pessoa;
import com.example.repo.PessoaRepository;
import com.example.controller.PetController;
import com.example.controller.PessoasController;

import com.google.common.base.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.ImplicitGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger1.annotations.EnableSwagger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.builders.PathSelectors.*;

@SpringBootApplication
//@EnableSwagger //Enable swagger 1.2 spec
@EnableSwagger2 //Enable swagger 2.0 spec
@ComponentScan(basePackageClasses = {
        PetController.class,
        PessoasController.class
})
public class HelloApplication {
	
	@Autowired
	PessoaRepository pessoas;
	
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(HelloApplication.class, args);
    }
    
    //@Bean
    public CommandLineRunner demo(){
    	
    	return (args) -> {
    		RestTemplate template = new RestTemplate();
    		String uri = "https://randomuser.me/api";
    		for (int i = 1; i <= 1090; i++ ){
    			try {
    				RandomUserApi resultado = template.getForObject(uri, RandomUserApi.class);
    				if ((resultado != null) && (resultado.results.length > 0)){
    					RandomUser o = resultado.results[0];
    					
    					Pessoa p = new Pessoa();
    					p.setNome(o.name.first);
    					p.setSobrenome(o.name.last);
    					p.setEndereco(o.location.state);
    					p.setCidade(o.location.city);
    					p.setEstado(o.location.state);
    					p.setCep(o.location.postcode);
    					p.setEmail(o.email);
    					pessoas.save(p);
    					
    					System.out.println(o.name.first + " " + o.email + " "+ o.location.street );
    				}
    			} catch(Exception e){
    				
    			}
    		}

    		
    		/*
    		for (int i = 1; i <= 10; i++ ){
    			System.out.println(cliente.getPessoa());
    		}
    		*/
    	};
    }
    
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-petstore-api")
                .apiInfo(apiInfo())
                .select()
                .paths(petstorePaths())
                .build();
                //.securitySchemes(newArrayList(oauth()))
                //.securityContexts(newArrayList(securityContext()));
    }

    /*
    @Bean
    public Docket categoryApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("category-api")
                .apiInfo(apiInfo())
                .select()
                .paths(categoryPaths())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .enableUrlTemplating(true);
    }
	
    
    @Bean
    public Docket multipartApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("multipart-api")
                .apiInfo(apiInfo())
                .select()
                .paths(multipartPaths())
                .build();
    }
    
    
    private Predicate<String> categoryPaths() {
        return regex("/category.*");
    }

    private Predicate<String> multipartPaths() {
        return regex("/upload.*");
    }
	*/
    
    /*
    @Bean
    public Docket userApi() {
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("read")
                .description("read access")
                .build();
        SecurityReference securityReference = SecurityReference.builder()
                .reference("test")
                .scopes(authScopes)
                .build();

        ArrayList<SecurityContext> securityContexts = newArrayList(SecurityContext.builder().securityReferences
                (newArrayList(securityReference)).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(newArrayList(new BasicAuth("test")))
                .securityContexts(securityContexts)
                .groupName("user-api")
                .apiInfo(apiInfo())
                .select()
                .paths(userOnlyEndpoints())
                .build();
    }
    */
    
    private Predicate<String> petstorePaths() {
        return or(
                regex("/api/pet.*"),
                regex("/api/pessoas.*")
        );
    }

    /*
    private Predicate<String> userOnlyEndpoints() {
        return new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.contains("user");
            }
        };
    }
	*/
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Springfox petstore API")
                .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum " +
                        "has been the industry's standard dummy text ever since the 1500s, when an unknown printer "
                        + "took a " +
                        "galley of type and scrambled it to make a type specimen book. It has survived not only five " +
                        "centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                        "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum " +
                        "passages, and more recently with desktop publishing software like Aldus PageMaker including " +
                        "versions of Lorem Ipsum.")
                .termsOfServiceUrl("http://springfox.io")
                .contact("springfox")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

    /*
    @Bean
    SecurityContext securityContext() {
        AuthorizationScope readScope = new AuthorizationScope("read:pets", "read your pets");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = readScope;
        SecurityReference securityReference = SecurityReference.builder()
                .reference("petstore_auth")
                .scopes(scopes)
                .build();

        return SecurityContext.builder()
                .securityReferences(newArrayList(securityReference))
                .forPaths(ant("/api/pet.*"))
                .build();
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("petstore_auth")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("api_key", "api_key", "header");
    }

    List<AuthorizationScope> scopes() {
        return newArrayList(
                new AuthorizationScope("write:pets", "modify pets in your account"),
                new AuthorizationScope("read:pets", "read your pets"));
    }
	
    List<GrantType> grantTypes() {
        GrantType grantType = new ImplicitGrantBuilder()
                .loginEndpoint(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog"))
                .build();
        return newArrayList(grantType);
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("abc", "123", "pets", "petstore", "123", ApiKeyVehicle.HEADER, "", ",");
    }
    */
}