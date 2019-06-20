package com.rexijie.webflixstreamingservice.api.v1;

import com.rexijie.webflixstreamingservice.api.v1.errorHandlers.ErrorHandler;
import com.rexijie.webflixstreamingservice.api.v1.handlers.VideoRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class VideoRoutes {

    @Bean
    RouterFunction<ServerResponse> videoEndPoint(VideoRouteHandler videoRouteHandler) {


        return route(GET("/video"), videoRouteHandler::returnPath)
                .andRoute(GET("/video/{name}"), videoRouteHandler::getPartialVideoByName)
                .andRoute(GET("/video/{name}/full"), videoRouteHandler::getFullLengthVideo)
                .filter((request, next) -> next.handle(request)
                        .onErrorResume(ErrorHandler::handleError));
    }
}
