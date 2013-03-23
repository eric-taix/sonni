/**
 * 
 */
package org.jug.montpellier.sonni.home;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;

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
		return "Welcome";
	}

	@Override
	public String getButtonLabel() {
		return "Home";
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		CssLayout layout = new CssLayout();
		layout.addStyleName("center-parent");
		layout.setSizeFull();
		Embedded logo = new Embedded(null, new ThemeResource("img/logo-big2.png"));
		logo.addStyleName("center-child");
		layout.addComponent(logo);
		return layout;
	}

	@Override
	public int getPreferedIndex() {
		return 0;
	}

}
