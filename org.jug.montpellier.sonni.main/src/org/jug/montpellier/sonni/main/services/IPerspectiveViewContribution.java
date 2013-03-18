/**
 * 
 */
package org.jug.montpellier.sonni.main.services;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Component;

/**
 * A perspective UI contribution
 * @author etaix
 */
public interface IPerspectiveViewContribution {

	public Resource getIcon();
	public String getTitle();
	public Component getView(Application application);
	public int getPreferedIndex();
	
}
