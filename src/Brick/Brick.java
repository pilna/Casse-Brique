package Brick;

import ball.Ball;
import ball.RisingState;
import color.Color;
import coordinate.Coordinate;
import effect.Effect;
import game.Hitable;
import racket.Racket;

import java.util.ArrayList;

public class Brick implements Hitable {
    private ArrayList<Effect> effects;
    private Coordinate coordinate;
    private char symbol = 'B';
    private Integer lifePoint;
    private String color;
    private boolean isAugmented = false;

    public Brick(Coordinate coordinate, Integer lifePoint, String color) {
        this.effects = new ArrayList<Effect>();
        this.coordinate = coordinate;
        this.lifePoint = lifePoint;
        this.color = color;
    }

    public void setSymbol(char newSymbol) {
        this.symbol = newSymbol;
    }

    public void addEffect(Effect effectToAdd) {
        this.effects.add(effectToAdd);
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setLifePoint(Integer newLifePoint) {
        if(newLifePoint < 0) {
            newLifePoint = 0;
        }
        this.lifePoint = newLifePoint;
    }

    public boolean isDestroy() {
        return this.lifePoint == 0;
    }

    public void takeDamage(Integer damage) {
        this.setLifePoint(this.lifePoint - damage);
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public String toString() {
        return this.color + this.getSymbol() + Color.ANSI_RESET.getColor();
    }

    public boolean canBeDisplay() {
        return !this.isDestroy();
    }

    public void setAugmentedToTrue() {
        this.isAugmented = true;
    }

    public ArrayList<Effect> getEffects() {
        return this.effects;
    }

    public void setColor(Color newColor) {
        this.color = newColor.getColor();
    }

    public void merge(Brick brick) {
        this.isAugmented = true;
        for(Effect e : brick.getEffects()) {
            this.addEffect(e);
        }
        this.setColor(Color.ANSI_WHITE_BACKGROUND);
    }

    public boolean isAugmented() {
        return this.isAugmented;
    }

    @Override
    public void collide(Hitable objectHit) {
        objectHit.collideBrick(this);
    }

    @Override
    public void collideBall(Ball ball) {
        if(ball.getDeplacement() instanceof RisingState) {
            this.takeDamage(ball.getDamage());
            for (Effect effect : this.effects) {
                if (effect.canBeApply()) {
                    effect.apply();
                }
            }
        }
    }

    @Override
    public void collideRacket(Racket racket) {
        // never arrive
    }

    @Override
    public void collideBrick(Brick brick) {
        // never arrive
    }
}
