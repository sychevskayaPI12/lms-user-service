package com.anast.lms.configuration;

import com.anast.lms.model.ErrorResponse;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableSwagger2
@Profile("!prod")
public class SwaggerConfig {

    private final TypeResolver typeResolver;

    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Value("${spring.application.name}")
    private String apiTitle;

    @Value("${spring.application.version}")
    private String version;

    private static final String[] HEADERS = new String[]{"iv-user", "display-name", "want-to-synch", "iv-groups"};

    private final String OK_MESSAGE = "Запрос успешно обработан";
    private final String BAD_REQUEST_MESSAGE = "Сервер обнаружил в запросе клиента синтаксическую ошибку";
    private final String UNAUTHORIZED_MESSAGE = "Не предоставлены корректные данные для авторизации";
    private final String FORBIDDEN_MESSAGE = "Недостаточно прав для доступа к ресурсу";
    private final String NOT_FOUND_MESSAGE = "Данный URI не связан с ни с одним ресурсом";
    private final String INTERNAL_SERVER_ERROR_MSG = "При обработке запроса на стороне сервера произошла непредвиденная ошибка";
    private final String API_DESCRIPTION = "REST API сервиса протоколов";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.anast.lms.controller"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PATCH, getCustomizedResponseMessages())
                .apiInfo(apiEndPointsInfo())
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .globalOperationParameters(headerPermissionsParam());
    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(getResponseBuilder(HttpStatus.OK.value(), OK_MESSAGE));
        responseMessages.add(getResponseBuilder(HttpStatus.BAD_REQUEST.value(), BAD_REQUEST_MESSAGE));
        responseMessages.add(getResponseBuilder(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED_MESSAGE));
        responseMessages.add(getResponseBuilder(HttpStatus.FORBIDDEN.value(), FORBIDDEN_MESSAGE));
        responseMessages.add(getResponseBuilder(HttpStatus.NOT_FOUND.value(), NOT_FOUND_MESSAGE));
        responseMessages.add(getResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR_MSG));
        return responseMessages;
    }

    private ResponseMessage getResponseBuilder(int code, String message) {
        return new ResponseMessageBuilder().code(code).message(message).build();
    }

    private List<Parameter> headerPermissionsParam() {
        return Arrays.stream(HEADERS).map(h -> {
            ParameterBuilder aParameterBuilder = new ParameterBuilder();
            return aParameterBuilder.name(h).modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        }).collect(Collectors.toList());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title(apiTitle)
                .description(API_DESCRIPTION)
                .version(version)
                .build();
    }
}