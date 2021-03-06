package org.skullforge.asteroidpush.designer.data;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

public class PrimitiveData {
   private Material material;
   private Vec2[] shape;

   public FixtureDef getFixtureDef(Transform transform, float size) {
      FixtureDef def = new FixtureDef();
      def.density = material.density;
      def.friction = material.friction;
      def.restitution = material.restitution;
      def.shape = getShape(transform, size);
      return def;
   }

   public void setMaterial(Material material) {
      this.material = material;
   }

   public void setVertices(Vec2[] shape) {
      this.shape = shape;
   }

   public Vec2[] getVertices() {
      return this.shape;
   }

   public float[] getPointArray() {
      float points[] = new float[shape.length * 2];
      for (int i = 0; i < shape.length; ++i) {
         points[i * 2] = shape[i].x;
         points[i * 2 + 1] = shape[i].y;
      }
      return points;
   }

   public PolygonShape getShape(Transform transform, float size) {
      Vec2 vertices[] = new Vec2[shape.length];
      for (int i = 0; i < shape.length; ++i) {
         vertices[i] = shape[i].mul(size);
         vertices[i] = Transform.mul(transform, vertices[i]);
      }
      PolygonShape polygon = new PolygonShape();
      polygon.set(vertices, shape.length);
      return polygon;
   }

}
