package view.listeners;

import org.mt4j.sceneManagement.IPreDrawAction;
import org.mt4j.util.math.Vector3D;

import view.universe.Suggestion;

public class UnrelatedListener implements IPreDrawAction {
	private final Suggestion component1;
	private final Suggestion component2;
	private static final float springK = 0.01f;
	private static final float targetLength = 250;
	
	public UnrelatedListener(Suggestion component1, Suggestion component2) {
		this.component1 = component1;
		this.component2 = component2;
	}
	
	@Override
	public void processAction() {
		Vector3D centerTN1 = component1.getCenterPointGlobal();
		Vector3D centerTN2 = component2.getCenterPointGlobal();
		
		double dx = centerTN1.x-centerTN2.x;
		double dy = centerTN1.y-centerTN2.y;
		
		double distance = Math.sqrt(dx*dx+dy*dy);
		
		double diffX = dx/distance*springK*(distance-targetLength);
		double diffY = dy/distance*springK*(distance-targetLength);
		
		if(distance > targetLength) {
			diffX = 0;
			diffY = 0;
		}
		
		Vector3D diff2 = new Vector3D((float)diffX,(float)diffY,0);
		Vector3D diff1 = new Vector3D(-(float)diffX,-(float)diffY,0);
		
		if (!component1.isDragged())
			component1.translate(diff1);
		if (!component2.isDragged())
			component2.translate(diff2);
	}

	@Override
	public boolean isLoop() {
		return true;
	}
}
