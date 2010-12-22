package view.widgets;

import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.util.MTColor;

import processing.core.PApplet;

public abstract class AbstractWidget extends MTRoundRectangle {
	public AbstractWidget(float x, float y, float w, float h, PApplet pApplet) {
		super(x, y,  0, w, h, 5, 5, pApplet);
		
		this.setFillColor(new MTColor(0, 0, 0, 180));
		this.setStrokeWeight(10);
		this.setStrokeColor(new MTColor(0, 0, 0, 100));
	}
}