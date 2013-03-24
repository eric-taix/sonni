/**
 * 
 */
package org.jug.montpellier.sonni.events;

import org.jug.montpellier.sonni.jugapis.services.Conference;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * A panel which contains the conference informations
 * @author eric
 */
@SuppressWarnings("serial")
public class ConferencePanel extends CustomComponent {
	
	private Label confTitle;
	private Label confDescription;

	public ConferencePanel() {
		setWidth("350px");
		setHeight("200px");
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setWidth("95%");
		mainLayout.setHeight("95%");
		mainLayout.setStyleName("conference");
		confTitle = new Label("Title");
		confTitle.setStyleName("title");
		mainLayout.addComponent(confTitle);
		confDescription = new Label();
		confDescription.setStyleName("description");
		mainLayout.addComponent(confDescription);
		Button more = new Button("more...");
		more.setStyleName(Runo.BUTTON_LINK);
		mainLayout.addComponent(more);
		mainLayout.setComponentAlignment(more, Alignment.BOTTOM_RIGHT);
		setCompositionRoot(mainLayout);
	}
	
	public void setDataSource(Conference conf) {
		confTitle.setValue(conf.title);
		confDescription.setValue(conf.description);
	}
}
