package com.spintech.testtask.module;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TvShowPreviewModule extends RepresentationModel<TvShowPreviewModule> {
    private Long id;

    @JsonAlias("original_name")
    @JsonProperty("original_name")
    private String originalName;

    private String name;

    private Double popularity;

    private String overview;
}
