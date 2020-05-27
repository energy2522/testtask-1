package com.spintech.testtask.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.entity.WatchedShow;

@Component
public class TvShowDtoToWatchedShowConverter implements Converter<TvShowDto, WatchedShow> {

    @Override
    public WatchedShow convert(TvShowDto tvShowDto) {
        WatchedShow watchedShow = new WatchedShow();

        watchedShow.setId(tvShowDto.getId());
        watchedShow.setFirstAirDate(tvShowDto.getFirstAirDate());
        watchedShow.setName(tvShowDto.getName());
        watchedShow.setNumberOfEpisodes(tvShowDto.getNumberOfEpisodes());
        watchedShow.setNumberOfSeasons(tvShowDto.getNumberOfSeasons());
        watchedShow.setOverview(tvShowDto.getOverview());

        return watchedShow;
    }
}
