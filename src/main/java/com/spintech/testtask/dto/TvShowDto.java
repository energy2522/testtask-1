package com.spintech.testtask.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spintech.testtask.module.Credits;

import lombok.Data;

@Data
public class TvShowDto extends RepresentationModel<TvShowDto> {
    private Long id;

    @JsonAlias("first_air_date")
    @JsonProperty("first_air_date")
    private String firstAirDate;

    private String name;

    @JsonAlias("number_of_episodes")
    @JsonProperty("number_of_episodes")
    private Integer numberOfEpisodes;

    @JsonAlias("number_of_seasons")
    @JsonProperty("number_of_seasons")
    private Integer numberOfSeasons;

    private String overview;

    private Credits credits;

}
