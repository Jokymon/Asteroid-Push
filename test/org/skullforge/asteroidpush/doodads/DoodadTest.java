package org.skullforge.asteroidpush.doodads;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.skullforge.asteroidpush.parts.Part;

public class DoodadTest {
   Mockery context;
   Part boxMock;
   Part blockMock;
   World testWorld;
   Doodad testDoodad;

   @Before
   public void setUp() {
      context = new Mockery();
      boxMock = context.mock(Part.class, "Box");
      blockMock = context.mock(Part.class, "Block");
      Vec2 testGravity = new Vec2();
      testWorld = new World(testGravity, true);
      testDoodad = new Doodad();
   }

   @Test
   public void testSpawnAndDespawn() {
      context.checking(new Expectations() {
         {
            oneOf(boxMock).spawn(testWorld);
            oneOf(blockMock).spawn(testWorld);
            oneOf(boxMock).despawn(testWorld);
            oneOf(blockMock).despawn(testWorld);
         }
      });

      testDoodad.addPart(boxMock);
      testDoodad.addPart(blockMock);
      testDoodad.spawn(testWorld);
      testDoodad.despawn(testWorld);

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() {
      testDoodad.addPart(boxMock);
      testDoodad.update(1);
      context.assertIsSatisfied();
   }
}
