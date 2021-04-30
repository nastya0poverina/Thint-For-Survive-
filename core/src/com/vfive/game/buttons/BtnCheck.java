package com.vfive.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.vfive.game.Main;
import com.vfive.game.Tools.Point2D;
import com.vfive.game.actors.Player;
import com.vfive.game.graphisObj.WorldObj;
import com.vfive.game.screens.GameSc;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.rmi.runtime.Log;



public class BtnCheck extends Actor {
    private static Logger log = Logger.getLogger(BtnCheck.class.getName());

    private Texture img;
    WorldObj box1;
    WorldObj box2;
    Player player;

    public BtnCheck(Texture img, Main game, Point2D point2D, float height, float width, WorldObj box1, WorldObj box2, Player player) {
        addListener(new BtnCheckListener( game));
        this.img = img;
        setHeight(height);
        setWidth(width);
        setX(point2D.getX());
        setY(point2D.getY());
        this.box1 = box1;
        this.box2 = box2;
        this.player = player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, this.getX(), this.getY() , this.getWidth(), this.getHeight());
    }

    private class BtnCheckListener extends InputListener {

        private Main game;
        private boolean isTouch = false;

        public BtnCheckListener( Main game) {
            this.game = game;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            // проверяет осмотрели ли мы коробки, нужно что бы переключить InputProcessor на джостике
            isTouch = true;
            if (box1.isCheck(player, box1)){
                GameSc.box1Islooting = true;
                log.info("НАЖАЛОСЬ!!!!!");
            }
            if (box2.isCheck(player, box2)){
                GameSc.box2Islooting = true;
                log.info("НАЖАЛОСЬ2!!!!!");
            }
            return true;
        }

        public boolean isTouch() {
            return isTouch;
        }

        public void setTouch(boolean touch) {
            isTouch = touch;
        }
    }
}
