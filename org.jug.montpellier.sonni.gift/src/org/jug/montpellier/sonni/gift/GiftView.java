/**
 * 
 */
package org.jug.montpellier.sonni.gift;

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
public class GiftView implements IPerspectiveViewContribution {

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getIcon()
	 */
	@Override
	public Resource getIcon() {
		return new ThemeResource("img/gift.png");
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Time to goodies ;-)";
	}

	@Override
	public String getButtonLabel() {
		return "Gift";
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		return new Label("Goodies");
	}

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getPreferedIndex()
	 */
	@Override
	public int getPreferedIndex() {
		return 800;
	}

}
