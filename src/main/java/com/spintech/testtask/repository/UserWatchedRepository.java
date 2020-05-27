package com.spintech.testtask.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spintech.testtask.entity.UserWatched;

@Repository
public interface UserWatchedRepository extends JpaRepository<UserWatched, Long> {

    void deleteByWatchedShow_IdAndUser_Id(Long tvId, Long userId);

    Set<UserWatched> findAllByUser_Id(Long userId);
}
