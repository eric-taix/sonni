/**
 * 
 */
package org.jug.montpellier.sonni.home;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author etaix
 *
 */
public class HomePerspectiveContribution implements IPerspectiveViewContribution {

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getIcon()
	 */
	@Override
	public Resource getIcon() {
		return new ThemeResource("img/home.png");
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Home";
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		return new Label("Home page");
	}

	@Override
	public int getPreferedIndex() {
		return 0;
	}

}
