package com.example.domain;

import java.io.Serializable;

/**
 *
 * @author Binnur Kurt
 */
public class Circle implements Serializable {
	private static final long serialVersionUID = 1L;
	private double x,y;
    private double radius;
    private String color;
    private int thickness;

    public Circle() {
    }

    public Circle(double x, double y, double radius, 
            String color, int thickness) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Circle{" + "x=" + x + ", y=" + y + ", radius=" + radius + ", color=" + color + ", thickness=" + thickness + '}';
    }
    
}
