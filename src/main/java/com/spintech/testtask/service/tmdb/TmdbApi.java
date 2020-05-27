package com.spintech.testtask.service.tmdb;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.module.PopularTvShowModule;

public interface TmdbApi {
    PopularTvShowModule popularTVShows();

    TvShowDto getTvShowInfo(Long id);
}
