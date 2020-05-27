package com.spintech.testtask.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.entity.WatchedShow;

@Component
public class WatchedShowToTvShowDtoConverter implements Converter<WatchedShow, TvShowDto> {

    @Override
    public TvShowDto convert(WatchedShow watchedShow) {
        TvShowDto tvShowDto = new TvShowDto();

        tvShowDto.setId(watchedShow.getId());
        tvShowDto.setFirstAirDate(watchedShow.getFirstAirDate());
        tvShowDto.setName(watchedShow.getName());
        tvShowDto.setNumberOfEpisodes(watchedShow.getNumberOfEpisodes());
        tvShowDto.setNumberOfSeasons(watchedShow.getNumberOfSeasons());

        return tvShowDto;
    }
}
