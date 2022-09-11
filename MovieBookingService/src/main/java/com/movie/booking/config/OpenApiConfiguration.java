package com.movie.booking.config;

import java.net.URL;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguration {
	@Value("${SWAGGER_ENDPOINT_BASE:#{null}}")
	private URL swaggerEndpointBase;

	@Value("${app.version}")
	private String appVersion;

	private static final String SCHEME_NAME = "Bearer";
	private static final String SCHEME = "Bearer";

	@Bean
	public OpenAPI api(ServletContext servletContext) {
		Info info = new Info().title("Movie Booking Service").description("Movie Booking application")
				.version(appVersion).termsOfService("").contact(new Contact())
				.license(new License().name("Copyright (c) Movie Booking Service.").url(""));

		OpenAPI openAPI = new OpenAPI().info(info).components(createComponents())
				.addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME));
		if (swaggerEndpointBase != null) {
			Server server = new Server().url(swaggerEndpointBaseHost()).description("Main Backend");
			openAPI.addServersItem(server);
		}

		return openAPI;
	}

	private Components createComponents() {
		Components components = new Components();
		components.addSecuritySchemes(SCHEME_NAME, createSecurityScheme());
		return components;
	}

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name(SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme(SCHEME);
    }

	private String swaggerEndpointBaseHost() {
		String host = swaggerEndpointBase.getHost();
		if (swaggerEndpointBase.getPort() >= 0) {
			host = host + ":" + swaggerEndpointBase.getPort();
		}
		return host;
	}
}
