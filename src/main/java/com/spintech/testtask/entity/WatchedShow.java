package com.spintech.testtask.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "watched_show")
public class WatchedShow {
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    private String firstAirDate;
    private Integer numberOfEpisodes;
    private Integer numberOfSeasons;
    @Column(length = 1000)
    private String overview;

    @OneToMany
    private Set<UserWatched> users;
}
