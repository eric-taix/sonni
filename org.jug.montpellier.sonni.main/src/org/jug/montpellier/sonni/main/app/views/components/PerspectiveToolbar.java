/**
 * 
 */
package org.jug.montpellier.sonni.main.app.views.components;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

/**
 * A custom component to display perspective icons vertically
 * 
 * @author etaix
 */
@SuppressWarnings("serial")
public class PerspectiveToolbar extends CustomComponent {

	private PerspectiveDisplayer displayer;
	private VerticalLayout mainLayout;
	private TreeMap<Integer, IPerspectiveViewContribution> contributions = new TreeMap<Integer, IPerspectiveViewContribution>();

	public PerspectiveToolbar(PerspectiveDisplayer displayer) {
		this.displayer = displayer;
		setWidth("140px");
		setHeight("100%");
		mainLayout = new VerticalLayout();
		mainLayout.addStyleName("toolbar");
		mainLayout.setHeight("100%");
		setCompositionRoot(mainLayout);
		
		updatePerspective();

	}

	public void addPerspective(IPerspectiveViewContribution view) {
		contributions.put(view.getPreferedIndex(), view);
		updatePerspective();
	}

	private void updatePerspective() {
		mainLayout.removeAllComponents();

		Label logo = new Label();
		logo.addStyleName("logo");
		logo.setIcon(new ThemeResource("img/logo-small.png"));
		mainLayout.addComponent(logo);

		VerticalLayout expand = new VerticalLayout();
		mainLayout.addComponent(expand);
		mainLayout.setExpandRatio(expand, 1.0f);

		for (Entry<Integer, IPerspectiveViewContribution> entry : contributions
				.entrySet()) {
			IPerspectiveViewContribution view = entry.getValue();
			final Button btn = new Button(view.getTitle().toUpperCase());
			btn.setStyleName(BaseTheme.BUTTON_LINK);
			btn.setIcon(view.getIcon());
			btn.setData(view);
			btn.setWidth("100%");
			mainLayout.addComponent(btn, mainLayout.getComponentCount() - 1);
			btn.addListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					Object data = event.getButton().getData();
					if (data != null
							&& data instanceof IPerspectiveViewContribution) {
						displayer
								.displayPerspective((IPerspectiveViewContribution) data);
						setSelected(btn);
					}
				}
			});
		}
	}

	public void removePerspective(IPerspectiveViewContribution view) {
		contributions.remove(view.getPreferedIndex());
		updatePerspective();
	}

	private void setSelected(Button btn) {
		for (int iLoop = 0; iLoop < mainLayout.getComponentCount(); iLoop++) {
			Component comp = mainLayout.getComponent(iLoop);
			if (comp instanceof Button) {
				if (comp.equals(btn)) {
					comp.addStyleName("selected");
				} else {
					comp.removeStyleName("selected");
				}
			}
		}
	}
}
