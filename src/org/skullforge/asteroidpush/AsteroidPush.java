package org.skullforge.asteroidpush;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class AsteroidPush extends StateBasedGame {

   public AsteroidPush(GameStateFactory stateFactory,
         ResourceLoader resourceLoader) {
      super("Asteroid Push");
      this.stateFactory = stateFactory;
      this.resourceLoader = resourceLoader;
      this.gameScenario = new Scenario(new Player());
   }

   @Override
   public void initStatesList(GameContainer container) throws SlickException {
      resourceLoader.setGameContainer(container);
      addState(stateFactory.createGameState(StateInfo.MATCH,
                                            resourceLoader,
                                            gameScenario));
      addState(stateFactory.createGameState(StateInfo.DESIGNER,
                                            resourceLoader,
                                            gameScenario));
   }

   private GameStateFactory stateFactory;
   private ResourceLoader resourceLoader;
   private Scenario gameScenario;
}
