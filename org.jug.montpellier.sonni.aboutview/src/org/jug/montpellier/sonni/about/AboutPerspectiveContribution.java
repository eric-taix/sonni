package org.jug.montpellier.sonni.about;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

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
	public Component getView(Application application) {
		return getAboutDialog();
	}

	@Override
	public int getPreferedIndex() {
		return 10;
	}

	private Component getAboutDialog() {
		VerticalLayout container = new VerticalLayout();
		container.setSizeFull();
		CssLayout titleLayout = new CssLayout();
		titleLayout.addStyleName("transparent-frame");
		titleLayout.setWidth("1000px");
		Label title = new Label("Sonni - Dynamic Vaadin OSGi Demo");
		title.setWidth("400px");
		title.addStyleName("about-title");
		titleLayout.addComponent(title);
		Label description = new Label(
				        "<br/>Copyright (c) <a href='http://www.montpellier-jug.org'>Montpellier JUG</a> - Developer <a href='mailto:eric.taix@gmail.com'>Eric Taix</a><br>"
				        + "<br/><br/>This application aims to demonstrate how to develop a dynamic application with <a href='http://www.vaadin.com'>Vaadin</a> and OSGi<br/>"
				        + "All buttons in the left panel are loaded dynamically from components stored in differents bundles. Play with the parameter view to activate or desactivate components."
				        + "This application can be forked from gitHub: "
						+ "<br/><br/><br/>The Montpellier Java User Group's goals are:"
								+ "<li>Create an independent community 'In Real Life' (the JUG is not intended to be another virtual community built around an electronic forum)</li>"
								+ "<li>Promote exchanges between industry, academia and students</li>"
								+ "<li>Share knowledge and experiences around Java ª</li>"
								+ "<li>Promote Java ª and associated technologies</li>"
								+ "All in a professional and user-friendly form.<br/><br><br/>"
								
						+ "Licensed under Apache Software Foundation 2.0 license (ASF)<br>"
						+ "This software contains modules licenced under the Apache Software Foundation 2.0 license (ASF) and EPL<br>"
						+ "Many thanks to Siemens AG, Kai TÃ¶dter, Chris Brind, Neil Bartlett, "
						+ " Petter HolmstrÃ¶m and others for their OSGi and Vaadin"
						+ " related work, blogs, and bundles."
						+ "<br/><br/><br/>Why <b>Sonni</b>?<br/>'Sonni' is the finish translation for bull. Finland has a lot of reinder and in our county we have a lot of bull !<br/><br/>");
		description.addStyleName("about");
		description.setWidth("100%");
		description.setContentMode(Label.CONTENT_XHTML);

		titleLayout.addComponent(description);
		container.addComponent(titleLayout);
		container.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);
		return container;
	}
}
