package com.spintech.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spintech.testtask.entity.WatchedShow;

public interface WatchedShowRepository extends JpaRepository<WatchedShow, Long> {

}
