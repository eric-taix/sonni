/**
 * 
 */
package org.jug.montpellier.sonni.sponsors;

import org.jug.montpellier.sonni.main.services.IFooterViewContribution;

import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author eric
 *
 */
public class SponsorsFooter implements IFooterViewContribution {

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IFooterViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		return new Label("This is my footer");
	}

}
