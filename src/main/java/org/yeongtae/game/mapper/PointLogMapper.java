package org.yeongtae.game.mapper;

import org.yeongtae.game.entities.PointLog;
import org.yeongtae.game.entities.Rank;

import java.util.List;

public interface PointLogMapper {
    int register(PointLog pointLog);
    List<Rank> getRank();
}