package org.skullforge.asteroidpush;

import org.jbox2d.common.Vec2;
import org.skullforge.asteroidpush.designer.Blueprint;
import org.skullforge.asteroidpush.designer.catalogue.ClockworkSpinnerFactory;
import org.skullforge.asteroidpush.designer.catalogue.MetalBlockFactory;
import org.skullforge.asteroidpush.designer.catalogue.MetalSpikeFactory;
import org.skullforge.asteroidpush.designer.catalogue.MetalWedgeFactory;
import org.skullforge.asteroidpush.designer.catalogue.SteamThrusterFactory;
import org.skullforge.asteroidpush.designer.data.ModuleData;
import org.skullforge.asteroidpush.designer.grid.Facing;
import org.skullforge.asteroidpush.designer.grid.Placement;
import org.skullforge.asteroidpush.entities.Entity;
import org.skullforge.asteroidpush.ui.PositionTracker;

public class Player implements PositionTracker {

   private int designNumber;

   public Player() {
      this.controlDescription = new StringBuffer("No Control");
      this.currentShip = null;
      this.shipDesign = new Blueprint();
      this.controller = new SignalController();
      this.designNumber = 0;

      loadDesignOne();
   }

   public void cycleShipDesign() {
      shipDesign.clear();

      ++designNumber;
      switch (designNumber) {
      default:
         designNumber = 0;
         // Intentional fall-through
      case 0:
         loadDesignOne();
         break;
      case 1:
         loadDesignTwo();
         break;
      case 2:
         loadDesignThree();
         break;
      }
   }

   private void loadDesignOne() {
      ModuleData thruster = SteamThrusterFactory.createData();
      ModuleData rotator = ClockworkSpinnerFactory.createData();
      ModuleData block = MetalBlockFactory.createData();
      ModuleData wedge = MetalWedgeFactory.createData();
      ModuleData spike = MetalSpikeFactory.createData();

      shipDesign.addModule(new Placement(0, 0, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, 2, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, 4, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(1, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 3, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 4, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 0, Facing.RIGHT), wedge);
      shipDesign.addModule(new Placement(2, 1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 2, Facing.FORWARD), rotator);
      shipDesign.addModule(new Placement(2, 3, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, 4, Facing.FORWARD), wedge);
      shipDesign.addModule(new Placement(3, 2, Facing.FORWARD), spike);
   }

   private void loadDesignTwo() {
      ModuleData thruster = SteamThrusterFactory.createData();
      ModuleData rotator = ClockworkSpinnerFactory.createData();
      ModuleData block = MetalBlockFactory.createData();
      ModuleData wedge = MetalWedgeFactory.createData();
      ModuleData spike = MetalSpikeFactory.createData();

      shipDesign.addModule(new Placement(0, 1, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, 0, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(0, -1, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(1, 1, Facing.FORWARD), wedge);
      shipDesign.addModule(new Placement(1, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, -1, Facing.RIGHT), wedge);
      shipDesign.addModule(new Placement(2, 1, Facing.RIGHT), thruster);
      shipDesign.addModule(new Placement(2, 0, Facing.FORWARD), rotator);
      shipDesign.addModule(new Placement(2, -1, Facing.LEFT), thruster);
      shipDesign.addModule(new Placement(3, 1, Facing.LEFT), spike);
      shipDesign.addModule(new Placement(3, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(3, -1, Facing.RIGHT), spike);
      shipDesign.addModule(new Placement(4, 0, Facing.FORWARD), spike);
   }

   private void loadDesignThree() {
      ModuleData thruster = SteamThrusterFactory.createData();
      ModuleData rotator = ClockworkSpinnerFactory.createData();
      ModuleData block = MetalBlockFactory.createData();
      ModuleData wedge = MetalWedgeFactory.createData();
      ModuleData spike = MetalSpikeFactory.createData();

      // Center piece
      shipDesign.addModule(new Placement(-3, 1, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(-3, -1, Facing.FORWARD), thruster);

      shipDesign.addModule(new Placement(-2, 2, Facing.LEFT), wedge);
      shipDesign.addModule(new Placement(-2, 1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(-2, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(-2, -1, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(-2, -2, Facing.BACKWARD), wedge);

      shipDesign.addModule(new Placement(-1, 3, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(-1, 2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(-1, 1, Facing.RIGHT), wedge);
      shipDesign.addModule(new Placement(-1, 0, Facing.FORWARD), thruster);
      shipDesign.addModule(new Placement(-1, -1, Facing.FORWARD), wedge);
      shipDesign.addModule(new Placement(-1, -2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(-1, -3, Facing.FORWARD), thruster);

      shipDesign.addModule(new Placement(0, 3, Facing.FORWARD), wedge);
      shipDesign.addModule(new Placement(0, 2, Facing.FORWARD), rotator);
      shipDesign.addModule(new Placement(0, 0, Facing.FORWARD), rotator);
      shipDesign.addModule(new Placement(0, -2, Facing.FORWARD), rotator);
      shipDesign.addModule(new Placement(0, -3, Facing.RIGHT), wedge);

      shipDesign.addModule(new Placement(1, 2, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(1, 1, Facing.BACKWARD), wedge);
      shipDesign.addModule(new Placement(1, 0, Facing.FORWARD), spike);
      shipDesign.addModule(new Placement(1, -1, Facing.LEFT), wedge);
      shipDesign.addModule(new Placement(1, -2, Facing.FORWARD), block);

      shipDesign.addModule(new Placement(2, 2, Facing.FORWARD), wedge);
      shipDesign.addModule(new Placement(2, 1, Facing.BACKWARD), block);
      shipDesign.addModule(new Placement(2, 0, Facing.FORWARD), block);
      shipDesign.addModule(new Placement(2, -1, Facing.LEFT), block);
      shipDesign.addModule(new Placement(2, -2, Facing.RIGHT), wedge);

      shipDesign.addModule(new Placement(3, 0, Facing.FORWARD), spike);
   }

   @Override
   public Vec2 getCenter() {
      if (currentShip == null) {
         return new Vec2(0.0f, 0.0f);
      } else {
         return currentShip.getCenterOfInterest();
      }
   }

   @Override
   public float getRadius() {
      if (currentShip == null) {
         return 75.0f;
      } else {
         return currentShip.getRadiusOfInterest();
      }
   }

   public Blueprint getShipDesign() {
      return shipDesign;
   }

   public void setShip(Entity ship) {
      currentShip = ship;
   }

   public String getName() {
      return "LocalPlayer";
   }

   public StringBuffer getControls() {
      return controlDescription;
   }

   public SignalController getController() {
      return controller;
   }

   public void handleKeyUp(int key) {
      controller.keyUp(key);
      controlDescription.delete(0, controlDescription.length());
      controlDescription.append(controller.getControllerDescription());
   }

   public void handleKeyDown(int key) {
      controller.keyDown(key);
      controlDescription.delete(0, controlDescription.length());
      controlDescription.append(controller.getControllerDescription());
   }

   private SignalController controller;
   private Blueprint shipDesign;
   private Entity currentShip;
   private StringBuffer controlDescription;
}
