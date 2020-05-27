package com.spintech.testtask.service.tmdb.impl;

import java.net.URI;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.module.PopularTvShowModule;
import com.spintech.testtask.service.tmdb.TmdbApi;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TmdbApiImpl implements TmdbApi {
    private static final String tvUrlPath = "/tv";
    private static final String popularUrlPath = "/popular";
    private static final String creditsKey = "credits";
    private static final String appendToResponseKey = "append_to_response";

    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PopularTvShowModule popularTVShows() throws IllegalArgumentException {
        String url = tmdbApiBaseUrl + tvUrlPath + popularUrlPath;

        ResponseEntity<PopularTvShowModule> response = restTemplate.getForEntity(url, PopularTvShowModule.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return response.getBody();
    }

    @Override
    public TvShowDto getTvShowInfo(Long id) {
        String url = tmdbApiBaseUrl + tvUrlPath + "/" + id;
        url = UriComponentsBuilder.fromHttpUrl(url).queryParam(appendToResponseKey, creditsKey).toUriString();

        ResponseEntity<TvShowDto> response = restTemplate.getForEntity(url, TvShowDto.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return response.getBody();
    }

}
