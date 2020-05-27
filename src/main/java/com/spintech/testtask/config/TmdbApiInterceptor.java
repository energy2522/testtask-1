package com.spintech.testtask.config;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TmdbApiInterceptor implements ClientHttpRequestInterceptor {
    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution)
            throws IOException {
        if (httpRequest.getURI().toString().startsWith(tmdbApiBaseUrl)) {
            HttpRequest modifiedRequest = modifyHttpRequest(httpRequest);
            return clientHttpRequestExecution.execute(modifiedRequest, bytes);
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    private HttpRequest modifyHttpRequest(HttpRequest request) {
        URI uri = UriComponentsBuilder.fromHttpRequest(request)
                .queryParam("language", tmdbLanguage)
                .queryParam("api_key", tmdbApiKey)
                .build().toUri();

        return new HttpRequestWrapper(request) {
            @Override
            public URI getURI() {
                return uri;
            }
        };
    }
}
