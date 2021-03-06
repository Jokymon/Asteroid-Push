package org.skullforge.asteroidpush.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class DesignerLayout extends BasicWidget {

   Widget catalogue;
   Widget blueprint;
   Widget selection;
   final float blueprintRatio = 0.75f;
   final float catalogueRatio = 0.75f;

   public DesignerLayout() {
      this.catalogue = null;
      this.blueprint = null;
      this.selection = null;
   }

   public void setCatalogueWidget(Widget widget) {
      this.catalogue = widget;
   }

   public void setBlueprintWidget(Widget widget) {
      this.blueprint = widget;
   }

   public void setSelectionWidget(Widget widget) {
      this.selection = widget;
   }

   @Override
   public void resize(Rectangle frame) {
      super.resize(frame);

      if (blueprint != null) {
         blueprint.resize(getBlueprintFrame(getFrame()));
      }
      if (catalogue != null) {
         catalogue.resize(getCatalogueFrame(getFrame()));
      }
      if (selection != null) {
         selection.resize(getSelectionFrame(getFrame()));
      }
   }

   @Override
   public void setHover(float x, float y) {
      super.setHover(x, y);
      if (blueprint != null) {
         if (getBlueprintFrame(getFrame()).contains(x, y)) {
            blueprint.setHover(x, y);
         } else {
            blueprint.resetHover();
         }
      }
      if (catalogue != null) {
         if (getCatalogueFrame(getFrame()).contains(x, y)) {
            catalogue.setHover(x, y);
         } else {
            catalogue.resetHover();
         }
      }
   }

   @Override
   public void resetHover() {
      super.resetHover();
      if (blueprint != null) {
         blueprint.resetHover();
      }
      if (catalogue != null) {
         catalogue.resetHover();
      }
   }

   @Override
   public void render(Graphics g) {
      if (blueprint != null) {
         blueprint.render(g);
      }
      if (catalogue != null) {
         catalogue.render(g);
      }
      if (selection != null) {
         selection.render(g);
      }
   }

   private Rectangle getBlueprintFrame(Rectangle frame) {
      return new Rectangle(frame.getX(), frame.getY(), frame.getWidth()
            * blueprintRatio, frame.getHeight());
   }

   private Rectangle getCatalogueFrame(Rectangle frame) {
      float x = frame.getWidth() * blueprintRatio;
      float y = frame.getY();
      float w = frame.getWidth() * (1.0f - blueprintRatio);
      float h = frame.getHeight() * catalogueRatio;
      return new Rectangle(x, y, w, h);
   }

   private Rectangle getSelectionFrame(Rectangle frame) {
      Rectangle catalogueFrame = getCatalogueFrame(frame);
      float x = catalogueFrame.getX();
      float y = catalogueFrame.getY() + catalogueFrame.getHeight();
      float w = catalogueFrame.getWidth();
      float h = frame.getHeight() - catalogueFrame.getHeight();
      return new Rectangle(x, y, w, h);
   }

   @Override
   public void mousePressed(int button, int x, int y) {
      if (blueprint != null && getBlueprintFrame(getFrame()).contains(x, y)) {
         blueprint.mousePressed(button, x, y);
      }
      if (catalogue != null && getCatalogueFrame(getFrame()).contains(x, y)) {
         catalogue.mousePressed(button, x, y);
      }
   }

}
