package view.elements.gestures;

import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;

import view.elements.SuggestedElement;
import controllers.SuggestableScene;

public class TrashSuggestedElementListener implements IGestureEventListener {
	private final SuggestedElement element;
	private final SuggestableScene scene;
	
	public TrashSuggestedElementListener (SuggestableScene scene, SuggestedElement element) {
		this.scene = scene;
		this.element = element;
	}
	
	@Override
	public boolean processGestureEvent(MTGestureEvent ge) {
		DragEvent de = (DragEvent) ge;
		
		if (de.getId() == DragEvent.GESTURE_UPDATED) {
	        scene.getOrbWidget().getTrashcan().setHovered(scene.getOrbWidget().getTrashcan().containsPointGlobal(de.getTo()));
		} else if (de.getId() == DragEvent.GESTURE_ENDED) {
	        if (scene.getOrbWidget().getTrashcan().containsPointGlobal(de.getTo())) {
	        	scene.getOrbWidget().getTrashcan().setHovered(false);
	        	scene.removeElement(element);
	        }
		}
		
		return false;
	}
}
