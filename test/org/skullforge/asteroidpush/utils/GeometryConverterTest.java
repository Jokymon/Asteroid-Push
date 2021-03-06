package org.skullforge.asteroidpush.utils;

import static org.junit.Assert.*;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class GeometryConverterTest {
   Shape testShape;

   @Before
   public void setUp() throws Exception {
   }

   @Test
   public void testConvertToSlickShape() {
      testShape = GeometryConverter.convertToSlickShape(null);
      assertEquals(Circle.class, testShape.getClass());

      PolygonShape polyShape = new PolygonShape();
      polyShape.setAsBox(1.0f, 1.0f);
      testShape = GeometryConverter.convertToSlickShape(polyShape);
      assertEquals(Polygon.class, testShape.getClass());
      float[] points = { 0.99f, 0.99f, -0.99f, 0.99f, -0.99f, -0.99f, 0.99f,
            -0.99f };
      Shape referenceBox = new Polygon(points);
      assertTrue(testShape.contains(referenceBox));

      CircleShape circleShape = new CircleShape();
      circleShape.m_radius = 5.0f;
      circleShape.m_p.set(1.0f, 1.0f);
      testShape = GeometryConverter.convertToSlickShape(circleShape);
      assertEquals(Circle.class, testShape.getClass());
      Shape referenceCircle = new Circle(1.0f, 1.0f, 4.99f);
      // Shape.contains does not work for circles, since the test checks whether
      // two shapes fail to intersect. The intersection algorithm for circles
      // fails to recognize when one circle is contained completely within the
      // other and does not actually intersect - it basically checks for
      // overlap. Hence we convert everything to polygons first and then
      // check intersecting.
      Shape referencePoly = new Polygon(referenceCircle.getPoints());
      Shape testPoly = new Polygon(testShape.getPoints());
      assertTrue(testPoly.contains(referencePoly));
   }

}
