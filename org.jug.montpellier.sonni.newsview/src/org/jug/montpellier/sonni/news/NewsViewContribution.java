/**
 * 
 */
package org.jug.montpellier.sonni.news;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.Application;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author eric
 *
 */
public class NewsViewContribution implements IPerspectiveViewContribution {

	@Override
	public Resource getIcon() {
		return new ThemeResource("img/news.png");
	}

	@Override
	public String getTitle() {
		return "News";
	}

	@Override
	public Component getView(Application application) {
		return new Label("Newview");
	}

	@Override
	public int getPreferedIndex() {
		return 1;
	}

}
