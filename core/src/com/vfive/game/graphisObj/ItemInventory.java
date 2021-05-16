package com.vfive.game.graphisObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.screens.GameSc;
import com.vfive.game.screens.InventorySc;

public class ItemInventory extends Actor {

    WorldObj box;
    String name;
    public boolean equipped, inInterBox;
    Texture img;
    Point2D pos;
    InventorySc inventorySc;


    public ItemInventory(String name, boolean equipped, Texture img, WorldObj box, InventorySc inventorySc) {
        this.box = box;
        this.inventorySc = inventorySc;
        this.name = name;
        this.equipped = equipped;
        this.img = img;
        this.setWidth(box.getWidth() - box.getWidth() / 10f * 2);
        this.setHeight(box.getHeight() - box.getHeight() / 10f * 2);
        this.setX(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f);
        this.setY(box.getY() - box.getHeight() / 2 + box.getHeight() / 10f);
        addListener(new ItemListener(this, box));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void drawPicture(Batch batch){
        batch.draw(img, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        setX(pos.getX());
        setY(pos.getY());
    }

    public boolean isCheckItem(Player player, ItemInventory itemInventory) {
        if (player.position.getX() > itemInventory.getX() - itemInventory.getWidth() - 50 &&
                player.position.getX() < itemInventory.getX() + itemInventory.getWidth() / 2 + 150 &&
                player.position.getY() > itemInventory.getY() - itemInventory.getHeight() - 50 &&
                player.position.getY() < itemInventory.getY() + itemInventory.getHeight() / 2 + 50)
            return true;
        return false;
    }

    public class ItemListener extends InputListener {
        ItemInventory item;
        WorldObj box;

        public ItemListener(ItemInventory item, WorldObj box) {
            this.item = item;
            this.box = box;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //if (inventorySc.getFreeInterBox() == null && item.inInterBox == false)
            //Main.logger.info("коробка занята");

/*            if (inventorySc.getFreeInterBox() != null && item.inInterBox == false) {
                item.setEquipped(false);
                Main.logger.info("мы коснулись чего то2");
                Point2D point2D = new Point2D(inventorySc.getFreeInterBox().getPos());
                float width = inventorySc.getFreeInterBox().getWidth();
                float height = inventorySc.getFreeInterBox().getWidth();
                item.setPos(new Point2D(point2D.getX() - width / 2 + width / 10f, point2D.getY() - height / 2 + height / 10f));
                inventorySc.getFreeInterBox().setEmpty(false);
                item.inInterBox = true;
                box.setEmpty(true);
            }*/

            if (item.inInterBox == false && inventorySc.getBoxInterFirst().getEmpty() == true) {
                Main.logger.info("ьы в 1 коробке с результатом");
                //item.setEquipped(false);
                Point2D point2D = new Point2D(inventorySc.getBoxInterFirst().getX() - inventorySc.getBoxInterFirst().getWidth() / 2 + inventorySc.getBoxInterFirst().getWidth() / 10f,
                        inventorySc.getBoxInterFirst().getY() - inventorySc.getBoxInterFirst().getHeight() / 2 + inventorySc.getBoxInterFirst().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterFirst().setEmpty(false);
                item.inInterBox = true;
                box.setEmpty(true);
                inventorySc.getBoxInterFirst().itemInventory = item;
            }

            if (item.inInterBox == false && inventorySc.getBoxInterSecond().getEmpty() == true) {
                Main.logger.info("ьы во 2 коробке с результатом");
                //item.setEquipped(false);
                Point2D point2D = new Point2D(inventorySc.getBoxInterSecond().getX() - inventorySc.getBoxInterSecond().getWidth() / 2 + inventorySc.getBoxInterSecond().getWidth() / 10f,
                        inventorySc.getBoxInterSecond().getY() - inventorySc.getBoxInterSecond().getHeight() / 2 + inventorySc.getBoxInterSecond().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterSecond().setEmpty(false);
                item.inInterBox = true;
                box.setEmpty(true);
            }

            if (item.equipped == true && inventorySc.getBoxInterResult().getEmpty() == false) {
                Main.logger.info("мы в коробке результата");
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterResult().setEmpty(true);
                inventorySc.getFreeBox().setEmpty(false);
                item.inInterBox = false;
                inventorySc.getBoxInterFirst().setEmpty(true);
                inventorySc.getBoxInterSecond().setEmpty(true);
            }

            if (inventorySc.getBoxInterFirst().getEmpty() == false && item.equipped == false && item.inInterBox == true) {
                Main.logger.info("все круто, нажатие в 1 коробке сложения есть");
                item.setEquipped(true);
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterFirst().setEmpty(true);
                item.inInterBox = false;
                inventorySc.getFreeBox().setEmpty(false);
            }

            if (inventorySc.getBoxInterSecond().getEmpty() == false && item.equipped == false && item.inInterBox == true) {
                Main.logger.info(" нажатие в 2 коробке сложения есть");
                item.setEquipped(true);
                Point2D point2D = new Point2D(inventorySc.getFreeBox().getX() - inventorySc.getFreeBox().getWidth() / 2 + inventorySc.getFreeBox().getWidth() / 10f,
                        inventorySc.getFreeBox().getY() - inventorySc.getFreeBox().getHeight() / 2 + inventorySc.getFreeBox().getHeight() / 10f);
                item.setPos(point2D);
                inventorySc.getBoxInterSecond().setEmpty(true);
                item.inInterBox = false;
                inventorySc.getFreeBox().setEmpty(false);
            }

            if (item == inventorySc.picture && item.inInterBox == true) {
                Main.logger.info(" картина на стене ");
                item.setEquipped(true);
                GameSc.isPictureOnWall = true;
            }
            return true;
        }
    }
}

/*inventorySc.getFreeInterBox().getX() - inventorySc.getFreeInterBox().getWidth() / 2 + inventorySc.getFreeInterBox().getWidth() / 10f,
        inventorySc.getFreeInterBox().getY() - inventorySc.getFreeInterBox().getHeight() / 2 + inventorySc.getFreeInterBox().getHeight() / 10f*/

//Point2D point2D = new Point2D(box.getX() - box.getWidth() / 2 + box.getWidth() / 10f, box.getY() - box.getHeight() / 2 + box.getHeight() / 10f);
