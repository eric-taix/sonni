package org.jug.montpellier.sonni.about;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

public class AboutPerspectiveContribution implements
		IPerspectiveViewContribution {


	@Override
	public Resource getIcon() {
		return new ThemeResource("img/info.png");
	}

	@Override
	public String getTitle() {
		return "About";
	}

	@Override
	public String getButtonLabel() {
		return null;
	}

	@Override
	public Component getView(Application application) {
		return getAboutDialog();
	}

	@Override
	public int getPreferedIndex() {
		return 1000;
	}

	private Component getAboutDialog() {
		CssLayout titleLayout = new CssLayout();
		titleLayout.addStyleName("transparent-frame");
		titleLayout.setWidth("90%");
		Label title = new Label("Sonni - Dynamic Vaadin OSGi Demo");
		title.setWidth("100%");
		title.addStyleName("about-title");
		titleLayout.addComponent(title);
		Label description = new Label(
				        "<br/>Copyright (c) Developer <a href='mailto:eric.taix@gmail.com'>Eric Taix</a> - <a href='http://www.montpellier-jug.org'>Montpellier JUG</a><br>"
				        + "<br/><br/>This application aims to demonstrate how to develop a dynamic application with <a href='http://www.vaadin.com'>Vaadin</a> and OSGi<br/>"
				        + "All buttons in the left panel are loaded dynamically from components stored in differents bundles. Play with the parameter view to activate or desactivate components."
				       // + "This application can be forked from <a href='https://github.com/eric-taix/sonni'>gitHub</a>."
						+ "<br/><br/><br/>The Montpellier Java User Group's goals are:"
								+ "<li>Create an independent community 'In Real Life' (the JUG is not intended to be another virtual community built around an electronic forum)</li>"
								+ "<li>Promote exchanges between industry, academia and students</li>"
								+ "<li>Share knowledge and experiences around Java</li>"
								+ "<li>Promote Java and associated technologies</li>"
								+ "All in a professional and user-friendly form.<br/><br><br/>"
								
						+ "Licensed under Apache Software Foundation 2.0 license (ASF)<br>"
						+ "This software contains modules licenced under the Apache Software Foundation 2.0 license (ASF) and EPL<br>"
						+ "Many thanks to Siemens AG, Kai Tödter, Chris Brind, Neil Bartlett, "
						+ " Petter Holmström and others for their OSGi and Vaadin"
						+ " related work, blogs, and bundles."
						+ "<br/><br/><br/>Why <b>Sonni</b>?<br/>'Sonni' is the finish translation for bulls. Finland has a lot of reinders and in our county we have a lot of bulls !<br/><br/>");
		description.addStyleName("about");
		description.setWidth("100%");
		description.setContentMode(Label.CONTENT_XHTML);

		titleLayout.addComponent(description);
		return titleLayout;
	}
}
