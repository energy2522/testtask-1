package com.spintech.testtask.service;

import java.util.Set;

import com.spintech.testtask.dto.TvShowDto;

public interface WatchedShowService {

    Set<TvShowDto> getWatchedShows(Long userId);

    void unmarkedWatched(Long userId, Long tvId);

    void markWatched(Long userId, Long tvId);
}
