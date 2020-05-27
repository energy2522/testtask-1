package com.spintech.testtask.module;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class Credits {
    @JsonAlias("cast")
    private List<ActorModule> actors;
}
