package org.skullforge.asteroidpush.designer;

import java.util.Collection;

import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Grid;
import org.skullforge.asteroidpush.designer.grid.GridVector;
import org.skullforge.asteroidpush.designer.grid.Placement;

public class Blueprint {
   private Grid<ModuleToken> grid;

   public Blueprint() {
      grid = new Grid<ModuleToken>();
   }

   public void addModule(Placement placement, ModuleData data) {
      if (canAddModule(placement, data)) {
         ModuleToken token = new ModuleToken(data);
         token.setPlacement(placement);
         grid.put(placement.getCoordinate(), token);
      }
   }

   public boolean canAddModule(Placement placement, ModuleData data) {
      if (grid.get(placement.getCoordinate()) != null) {
         return false;
      } else {
         return true;
      }
   }
   
   public void removeModule(GridVector coordinate) {
      grid.remove(coordinate);
   }

   public void clear() {
      grid.clear();
   }

   public GridVector getMin() {
      return grid.getMin();
   }

   public GridVector getMax() {
      return grid.getMax();
   }

   public int getWidth() {
      return getMax().getX() - getMin().getX() + 1;
   }

   public int getHeight() {
      return getMax().getY() - getMin().getY() + 1;
   }

   public Collection<ModuleToken> getTokens() {
      return grid.getAll();
   }
}
