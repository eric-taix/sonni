package org.jug.montpellier.sonni.main.app.views.components;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

/**
 * This interface defines the way to display a Perspective's content
 * @author eric
 */
public interface PerspectiveDisplayer {

	public void displayPerspective(IPerspectiveViewContribution perspective);
}
