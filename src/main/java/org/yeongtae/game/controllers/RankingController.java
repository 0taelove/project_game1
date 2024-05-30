package org.yeongtae.game.controllers;

import org.yeongtae.game.constants.SubMenu;
import org.yeongtae.game.entities.Rank;
import org.yeongtae.game.services.GameServiceLocator;
import org.yeongtae.global.AbstractController;
import org.yeongtae.global.Service;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.main.MainRouter;
import org.yeongtae.template.Templates;

import java.util.List;
import java.util.stream.Collectors;

public class RankingController extends AbstractController {
    @Override
    public void show() {

        Service<List<Rank>> service = GameServiceLocator.getInstance().find(SubMenu.RANKING);

        List<Rank> ranks = service.process();
        String ranking = ranks.stream()
                .map(r -> String.format("%d등 - %s(%s)/%d점", r.getRank(), r.getUserNm(), r.getUserId(), r.getTotal()))
                .collect(Collectors.joining("\n"));

        Templates.getInstance().render(SubMenu.RANKING, () -> ranking);

        // 랭킹 출력 후 게임 메뉴 이동
        MainRouter.getInstance().change(MainMenu.GAME);
    }

}