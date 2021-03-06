package me.minidigger.voxelgameslib.survivalgames;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.inject.Inject;

import me.minidigger.voxelgameslib.api.game.AbstractGame;
import me.minidigger.voxelgameslib.api.game.GameDefinition;
import me.minidigger.voxelgameslib.api.game.GameInfo;
import me.minidigger.voxelgameslib.api.map.MapInfo;
import me.minidigger.voxelgameslib.api.phase.phases.GracePhase;
import me.minidigger.voxelgameslib.api.phase.phases.LobbyPhase;
import me.minidigger.voxelgameslib.api.phase.phases.VotePhase;
import me.minidigger.voxelgameslib.api.world.WorldHandler;

/**
 * Created by Martin on 26.10.2016.
 */
@GameInfo(name = "SurvivalGames", author = "MiniDigger", version = "v1.0", description = "SurvivalGamesDescription")
public class SurvivalGamesGame extends AbstractGame {

    @Inject
    private WorldHandler worldHandler;

    public SurvivalGamesGame() {
        super(SurvivalGamesModule.GAMEMODE);
    }

    @Override
    public void initGameFromModule() {
        setMinPlayers(2);
        setMaxPlayers(14);

        LobbyPhase lobbyPhase = createPhase(LobbyPhase.class);
        VotePhase votePhase = createPhase(VotePhase.class);
        GracePhase gracePhase = createPhase(GracePhase.class);
        SurvivalGamesPhase survivalGamesPhase = createPhase(SurvivalGamesPhase.class);

        lobbyPhase.setNextPhase(votePhase);
        votePhase.setNextPhase(gracePhase);
        gracePhase.setNextPhase(survivalGamesPhase);

        activePhase = lobbyPhase;

        loadMap();
    }

    @Override
    public void initGameFromDefinition(@Nonnull GameDefinition gameDefinition) {
        super.initGameFromDefinition(gameDefinition);

        loadMap();
    }

    private void loadMap() {
        //TODO move this somewhere else (unify)
        // TODO this doesn't respect if a user changed the lobby in the config
        Optional<MapInfo> info = worldHandler.getMapInfo("Lobby");
        if (info.isPresent()) {
            putGameData("lobbymap", info.get());
        } else {
            abortGame();
        }
    }
}
