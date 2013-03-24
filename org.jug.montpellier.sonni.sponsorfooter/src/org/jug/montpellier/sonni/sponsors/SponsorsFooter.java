/**
 * 
 */
package org.jug.montpellier.sonni.sponsors;

import java.io.IOException;
import java.util.List;

import org.jug.montpellier.sonni.jugapis.services.IJugRequester;
import org.jug.montpellier.sonni.jugapis.services.Sponsor;
import org.jug.montpellier.sonni.main.services.IFooterViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author eric
 *
 */
public class SponsorsFooter implements IFooterViewContribution {

	private IJugRequester requester;
	
	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IFooterViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		HorizontalLayout container = new HorizontalLayout();
		container.setMargin(true);
		container.setSpacing(true);
		container.setWidth("100%");
		container.setHeight("100px");
		try {
			List<Sponsor> sponsors = requester.getCurrentSponsor();
			for (Sponsor sponsor : sponsors) {
				Embedded logo = new Embedded(null, new ExternalResource(sponsor.logoURL));
				logo.setHeight("60px");
				logo.setWidth("100px");
				container.addComponent(logo);
				container.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return container;
	}

	
	public void setRequester(IJugRequester requester) {
		this.requester = requester;
	}
	
	public void unsetRequester(IJugRequester requester) {
		this.requester = null;
	}
}
