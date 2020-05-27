package com.spintech.testtask.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.entity.UserWatched;
import com.spintech.testtask.entity.WatchedShow;
import com.spintech.testtask.repository.UserWatchedRepository;
import com.spintech.testtask.repository.WatchedShowRepository;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.WatchedShowService;
import com.spintech.testtask.service.tmdb.TmdbApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WatchedShowServiceImpl implements WatchedShowService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserWatchedRepository userWatchedRepository;

    @Autowired
    private WatchedShowRepository watchedShowRepository;

    @Autowired
    private TmdbApi tmdbApi;

    @Autowired
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    @Override
    public Set<TvShowDto> getWatchedShows(Long userId) {
        return userWatchedRepository.findAllByUser_Id(userId).stream().map(UserWatched::getWatchedShow)
                .map(ws -> conversionService.convert(ws, TvShowDto.class)).collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void unmarkedWatched(Long userId, Long tvId) {
        log.debug("Unmarked user with id {} and tv show with id {}", userId, tvId);

        userWatchedRepository.deleteByWatchedShow_IdAndUser_Id(tvId, userId);
    }

    @Override
    @Transactional
    public void markWatched(Long userId, Long tvId) {
        log.debug("Mark tv show with {} as watched for user with id {}", tvId, userId);
        WatchedShow watchedShow;
        Optional<WatchedShow> watchedShowOpt = watchedShowRepository.findById(tvId);

        if (watchedShowOpt.isEmpty()) {
            log.info("Tv show with id {} doesn't exist in local db. Trying to get it from remote server", tvId);
            TvShowDto tvShowDto = tmdbApi.getTvShowInfo(tvId);
            WatchedShow show = conversionService.convert(tvShowDto, WatchedShow.class);

            watchedShow = watchedShowRepository.save(show);
        } else {
            watchedShow = watchedShowOpt.get();
        }

        User user = userService.findById(userId);
        UserWatched userWatched = new UserWatched();
        userWatched.setUser(user);
        userWatched.setWatchedShow(watchedShow);

        userWatchedRepository.save(userWatched);
    }
}
