package org.skullforge.asteroidpush;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MatchGameStateTest {
   ClassMockery context;
   Simulator simulatorMock;
   MatchGameState testState;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      simulatorMock = context.mock(Simulator.class);
   }

   @Test
   public void testInit() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).initialize(with(aNonNull(Scenario.class)));
         }
      });
      testState = new MatchGameState(simulatorMock);
      testState.init(null, null);

      context.assertIsSatisfied();
   }

   @Test
   public void testRender() throws SlickException {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
         }
      });
      testState = new MatchGameState(simulatorMock);
      testState.render(null, null, null);

      context.assertIsSatisfied();
   }

   @Test
   public void testUpdate() throws SlickException {
      final Sequence steps = context.sequence("steps");
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(1);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(2);
            inSequence(steps);
            oneOf(simulatorMock).stepToFrame(12);
            inSequence(steps);
         }
      });
      testState = new MatchGameState(simulatorMock);
      testState.update(null, null, 20);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 5);
      testState.update(null, null, 160);

      context.assertIsSatisfied();
   }

   @Test
   public void testGetId() {
      context.checking(new Expectations() {
         {
            allowing(simulatorMock).getTimeStep();
            will(returnValue(0.016f));
         }
      });
      testState = new MatchGameState(simulatorMock);
      assertThat(testState.getID(), is(equalTo(2)));

      context.assertIsSatisfied();
   }
}
