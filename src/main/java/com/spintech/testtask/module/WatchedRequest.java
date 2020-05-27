package com.spintech.testtask.module;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class WatchedRequest {
    @NotNull
    private Long tvShowId;
}
