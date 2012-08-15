package org.skullforge.asteroidpush.ui.layouts;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.skullforge.asteroidpush.ClassMockery;
import org.skullforge.asteroidpush.ui.Widget;

public class SimpleLayoutTest {
   Mockery context;
   Graphics graphicsMock;
   GameContainer containerMock;
   Widget backgroundWidgetMock;
   Widget infoWidgetMock;
   SimpleLayout testLayout;

   @Before
   public void setUp() throws Exception {
      context = new ClassMockery();
      graphicsMock = context.mock(Graphics.class);
      containerMock = context.mock(GameContainer.class);
      backgroundWidgetMock = context.mock(Widget.class, "background");
      infoWidgetMock = context.mock(Widget.class, "info");
      testLayout = new SimpleLayout();
   }

   @Test
   public void testDrawMissingWidgets() {
      context.checking(new Expectations() {
         {
            allowing(graphicsMock).drawRoundRect(with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(int.class)));
            allowing(graphicsMock).fillRoundRect(with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(float.class)),
                                                 with(any(int.class)));
            allowing(graphicsMock).setColor(with(aNonNull(Color.class)));
            allowing(graphicsMock).drawString(with(aNonNull(String.class)),
                                              with(any(float.class)),
                                              with(any(float.class)));

            allowing(containerMock).getWidth();
            will(returnValue(640));
            allowing(containerMock).getHeight();
            will(returnValue(480));
         }
      });
      testLayout.render(containerMock, graphicsMock);
      context.assertIsSatisfied();
   }

   @Test
   public void testDrawWidgets() {
      final Sequence drawOrder = context.sequence("drawOrder");
      final Rectangle fullScreen = new Rectangle(0.0f, 0.0f, 640.0f, 480.0f);
      context.checking(new Expectations() {
         {
            allowing(containerMock).getWidth();
            will(returnValue((int) fullScreen.getWidth()));
            allowing(containerMock).getHeight();
            will(returnValue((int) fullScreen.getHeight()));
            oneOf(backgroundWidgetMock).render(with(same(graphicsMock)),
                                               with(any(Rectangle.class)));
            inSequence(drawOrder);
            oneOf(infoWidgetMock).render(with(same(graphicsMock)),
                                         with(any(Rectangle.class)));
            inSequence(drawOrder);
         }
      });
      testLayout.setWidget("invalid element name", infoWidgetMock);
      testLayout.setWidget("info", infoWidgetMock);
      testLayout.setWidget("background", backgroundWidgetMock);
      testLayout.render(containerMock, graphicsMock);
      context.assertIsSatisfied();
   }
}
