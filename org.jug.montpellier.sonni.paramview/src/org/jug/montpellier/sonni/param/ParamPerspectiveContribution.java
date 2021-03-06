/**
 * 
 */
package org.jug.montpellier.sonni.param;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;

/**
 * @author eric
 *
 */
public class ParamPerspectiveContribution implements
		IPerspectiveViewContribution {

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getIcon()
	 */
	@Override
	public Resource getIcon() {
		return new ThemeResource("img/parameter.png");
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Parameters";
	}

	@Override
	public String getButtonLabel() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		return new BundleView();
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getPreferedIndex()
	 */
	@Override
	public int getPreferedIndex() {
		return 900;
	}

}
