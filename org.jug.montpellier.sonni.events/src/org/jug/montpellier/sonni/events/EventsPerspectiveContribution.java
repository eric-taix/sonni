/**
 * 
 */
package org.jug.montpellier.sonni.events;

import java.io.IOException;
import java.util.List;

import org.jug.montpellier.sonni.jugapis.services.Conference;
import org.jug.montpellier.sonni.jugapis.services.IJugRequester;
import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * @author eric
 * 
 */
public class EventsPerspectiveContribution implements IPerspectiveViewContribution {

	private IJugRequester requester;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getIcon()
	 */
	@Override
	public Resource getIcon() {
		return new ThemeResource("img/calendar2.png");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Events";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getButtonLabel()
	 */
	@Override
	public String getButtonLabel() {
		return "Events";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getView(com.vaadin.Application)
	 */
	@Override
	public Component getView(Application application) {
		Panel view = new Panel();
		view.setStyleName(Runo.PANEL_LIGHT);
		view.addStyleName("transparent-bg");
		view.setSizeFull();
		VerticalLayout v = new VerticalLayout();
		view.addComponent(v);
		
		try {
			List<Conference> conferences = requester.getAllConference();
			GridLayout cssLayout = new GridLayout(2, (conferences.size() / 2) + 1);
			cssLayout.setSpacing(true);
			cssLayout.setSizeUndefined();
			for (Conference conference : conferences) {
				ConferencePanel confPane = new ConferencePanel();
				confPane.setDataSource(conference);
				cssLayout.addComponent(confPane);
			}
			v.addComponent(cssLayout);
			v.setComponentAlignment(cssLayout, Alignment.TOP_CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution#getPreferedIndex()
	 */
	@Override
	public int getPreferedIndex() {
		return 5;
	}

	public void setRequester(IJugRequester requester) {
		this.requester = requester;
	}

	public void unsetRequester(IJugRequester requester) {
		requester = null;
	}
}
