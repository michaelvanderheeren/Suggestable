package view.widgets.custom;

import java.awt.Image;
import java.io.IOException;

import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTRectangle.PositionAnchor;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTList;
import org.mt4j.components.visibleComponents.widgets.MTListCell;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PApplet;
import processing.core.PImage;
import view.widgets.AbstractWindow;
import bookshelf.apis.google.GoogleBook;

public class InformationWidget extends AbstractWindow {
	MTRoundRectangle container;
	private final GoogleBook book;
	private final PApplet pApplet;
	
	public InformationWidget(PApplet pApplet, float x, float y, float w, float h, GoogleBook book) {
		super(pApplet, x, y, w, h, "Information");
		this.pApplet = pApplet;
		this.book = book;
		
		container = new MTRoundRectangle(pApplet, 0, 0, 0, this.getWidthXYGlobal()-15, this.getHeightXYGlobal()-40, 5, 5);
		container.setPositionRelativeToParent(new Vector3D(7.5f,32,0).addLocal(container.getCenterOfMass2DLocal()));
		container.setFillColor(new MTColor(0, 0, 0, 150));
		container.setNoStroke(true);
		container.removeAllGestureEventListeners();
		this.addChild(container);
		try {
			Image image = getBook().getCover();
			MTImage cover = new MTImage(getpApplet(), new PImage(image));
			container.addChild(cover);
			float s = 100/cover.getHeightXY(TransformSpace.GLOBAL);
			cover.scaleGlobal(s, s, 1, cover.getCenterPointGlobal());
			cover.setPositionRelativeToParent(new Vector3D(7.5f,7.5f,0).addLocal(
					new Vector3D(cover.getWidthXY(TransformSpace.GLOBAL)/2, cover.getHeightXY(TransformSpace.GLOBAL)/2)
				));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IFont font = FontManager.getInstance().createFont(pApplet, "fonts/Trebuchet MS.ttf", 
				14, 	//Font size
				new MTColor(255,255,255));	//Font color
		
		MTTextArea title = new MTTextArea(getpApplet(),font);
		container.addChild(title);
		title.setAnchor(PositionAnchor.UPPER_LEFT);
		title.setPositionRelativeToParent(new Vector3D(100f,7.5f,0));
		title.setText(getBook().getTitle());
		title.setNoStroke(true);
		title.setNoFill(true);
		
		MTTextArea authors = new MTTextArea(getpApplet(),font);
		container.addChild(authors);
		authors.setAnchor(PositionAnchor.UPPER_LEFT);
		authors.setPositionRelativeToParent(new Vector3D(100f,23.5f,0));
		authors.setText(getBook().getAuthors().toString());
		authors.setNoStroke(true);
		authors.setNoFill(true);
		
		if (getBook().hasSummary()) {
			MTList summaryScrollList = new MTList(getpApplet(), 0, 0, w-15f, h-122.5f);
			container.addChild(summaryScrollList);
			summaryScrollList.setPositionRelativeToParent(new Vector3D(0f, 115f, 0).addLocal(summaryScrollList.getCenterOfMass2DLocal()));
			summaryScrollList.setNoFill(true);
			summaryScrollList.setNoStroke(true);
			
			MTTextArea summary = new MTTextArea(getpApplet(), 0, 0, summaryScrollList.getWidthXY(TransformSpace.GLOBAL), 1000, font);
			summary.setText(getBook().getSummary());
			summary.setNoStroke(true);
			summary.setNoFill(true);
			
			MTListCell summaryScrollCell = new MTListCell(getpApplet(), summaryScrollList.getWidthXY(TransformSpace.GLOBAL), 1000);
			summaryScrollList.addListElement(summaryScrollCell);
			summaryScrollCell.addChild(summary);
			summaryScrollCell.setNoFill(true);
			summaryScrollCell.setNoStroke(true);
		}
	}
	
	private PApplet getpApplet() {
		return this.pApplet;
	}
	
	private GoogleBook getBook() {
		return this.book;
	}
}
