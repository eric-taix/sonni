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

	/**
	 * Returns the icon to display in the perspective bar
	 * @return
	 */
	public Resource getIcon();
	
	/**
	 * Return the title which is display in the main content when the perspective is activated
	 * @return
	 */
	public String getTitle();
	
	/**
	 * Returns the button's label. If null value is returned then the title is used.<br/>
	 * The button label is capitalized before being displayed
	 * @return
	 */
	public String getButtonLabel();
	/**
	 * Create a new view
	 * @param application
	 * @return
	 */
	public Component getView(Application application);
	/**
	 * Get the preferred position in the perspective bar. We will do the best to respect this index!
	 * @return
	 */
	public int getPreferedIndex();
	
}
