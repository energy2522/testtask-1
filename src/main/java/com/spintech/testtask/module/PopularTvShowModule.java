package com.spintech.testtask.module;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PopularTvShowModule extends RepresentationModel<PopularTvShowModule> {
    private Integer page;

    @JsonAlias("total_pages")
    @JsonProperty("total_pages")
    private Integer totalPages;

    private List<TvShowPreviewModule> results;
}
