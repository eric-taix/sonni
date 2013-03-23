/**
 * 
 */
package org.jug.montpellier.sonni.sponsors;

import java.io.IOException;
import java.util.List;

import org.jug.montpellier.sonni.main.services.IFooterViewContribution;
import org.jug.montpellier.sonni.sponsors.jug.JugApis;
import org.jug.montpellier.sonni.sponsors.jug.Sponsor;

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

	/* (non-Javadoc)
	 * @see org.jug.montpellier.sonni.main.services.IFooterViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		HorizontalLayout container = new HorizontalLayout();
		container.setMargin(true);
		container.setSpacing(true);
		container.setWidth("100%");
		container.setHeight("150px");
		try {
			JugApis api = new JugApis();
			List<Sponsor> sponsors = api.getCurrentSponsor();
			for (Sponsor sponsor : sponsors) {
				Embedded logo = new Embedded(null, new ExternalResource(sponsor.logoURL));
				logo.setHeight("80px");
				logo.setWidth("150px");
				container.addComponent(logo);
				container.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return container;
	}

}
