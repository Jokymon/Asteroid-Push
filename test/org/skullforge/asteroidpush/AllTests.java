package org.skullforge.asteroidpush;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.skullforge.asteroidpush.arena.BasicArenaTest;
import org.skullforge.asteroidpush.arena.entities.VesselTest;
import org.skullforge.asteroidpush.parts.StaticBoxTest;
import org.skullforge.asteroidpush.arena.viewports.StaticViewportTest;
import org.skullforge.asteroidpush.doodads.DoodadTest;

@RunWith(Suite.class)
@SuiteClasses({ VesselTest.class,
   ArenaGameStateTest.class,
   AsteroidPushTest.class,
   BasicStateFactoryTest.class,
   BasicArenaTest.class,
   StaticViewportTest.class,
   StaticBoxTest.class,
   DoodadTest.class
   })
public class AllTests {

}
