package org.yeongtae.game.services;

import org.yeongtae.game.entities.Rank;
import org.yeongtae.game.mapper.PointLogMapper;
import org.yeongtae.global.Service;

import java.util.List;

public class RankingInfoService implements Service<List<Rank>> {

    private final PointLogMapper mapper;

    public RankingInfoService(PointLogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Rank> process() {

        return mapper.getRank();
    }
}