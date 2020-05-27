package com.spintech.testtask.controller;

import com.spintech.testtask.dto.TvShowDto;
import com.spintech.testtask.module.PopularTvShowModule;
import com.spintech.testtask.module.TvShowPreviewModule;
import com.spintech.testtask.module.WatchedRequest;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.WatchedShowService;
import com.spintech.testtask.service.tmdb.TmdbApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Set;

@RestController
@RequestMapping("/tv")
public class TVController {
    @Autowired
    private UserService userService;

    @Autowired
    private TmdbApi tmdbApi;

    @Autowired
    private WatchedShowService watchedShowService;

    @GetMapping("/popular")
    public ResponseEntity<PopularTvShowModule> popular() {
        PopularTvShowModule popularMovies = tmdbApi.popularTVShows();

        for (TvShowPreviewModule tvShow : popularMovies.getResults()) {
            Link detailsLink = linkTo(TVController.class).slash(tvShow.getId()).withRel("details");
            tvShow.add(detailsLink);
        }

        return ResponseEntity.ok(popularMovies);
    }

    @GetMapping("/{tvId}")
    public ResponseEntity<TvShowDto> getDetailInfo(@PathVariable("tvId") Long tvId) {
        TvShowDto tvShowInfo = tmdbApi.getTvShowInfo(tvId);

        return ResponseEntity.ok(tvShowInfo);
    }

    @PostMapping("/watched")
    @ResponseStatus(HttpStatus.CREATED)
    public void markAsWatched(@RequestBody @Valid WatchedRequest watchedRequest) {
        watchedShowService.markWatched(1L, watchedRequest.getTvShowId());
    }

    @GetMapping("/watched")
    public ResponseEntity<Set<TvShowDto>> getWatchedShows() {
        Set<TvShowDto> watchedTvShows = watchedShowService.getWatchedShows(1L);

        for (TvShowDto tvShowDto : watchedTvShows) {
            Link details = linkTo(TVController.class).slash(tvShowDto.getId()).withRel("details");
            Link unwatched = linkTo(TVController.class).slash("watched").slash(tvShowDto.getId()).withRel("unwatched");

            tvShowDto.add(details, unwatched);
        }

        return ResponseEntity.ok(watchedTvShows);
    }

    @DeleteMapping("/watched/{tvId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unmarkedAsWatched(@PathVariable("tvId") Long tvId) {
        watchedShowService.unmarkedWatched(1L, tvId);
    }
}
