/**
 * 
 */
package org.jug.montpellier.sonni.main.app.views.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<IPerspectiveViewContribution> perspectives = new ArrayList<IPerspectiveViewContribution>();
	private Map<IPerspectiveViewContribution, Button> perspectiveToButtonMap = new HashMap<IPerspectiveViewContribution, Button>();

	public PerspectiveToolbar(PerspectiveDisplayer displayer) {
		this.displayer = displayer;
		setWidth("140px");
		setHeight("100%");
		mainLayout = new VerticalLayout();
		mainLayout.addStyleName("toolbar");
		mainLayout.setHeight("100%");

		Label logo = new Label();
		logo.addStyleName("logo");
		logo.setIcon(new ThemeResource("img/logo-small.png"));
		mainLayout.addComponent(logo);

		VerticalLayout expand = new VerticalLayout();
		mainLayout.addComponent(expand);
		mainLayout.setExpandRatio(expand, 1.0f);

		setCompositionRoot(mainLayout);
	}

	/**
	 * Add a perspective according to its preferred index
	 * 
	 * @param view
	 */
	public void addPerspective(IPerspectiveViewContribution view) {
		for (int iLoop = 0; iLoop < perspectives.size(); iLoop++) {
			if (view.getPreferedIndex() < perspectives.get(iLoop).getPreferedIndex()) {
				addPerspectiveView(iLoop, view);
				return;
			}
		}
		addPerspectiveView(perspectives.size(), view);
	}

	/**
	 * Add a perspective button add a specified index
	 * 
	 * @param index
	 * @param view
	 */
	private void addPerspectiveView(int index, IPerspectiveViewContribution view) {
		perspectives.add(index, view);
		// Create the associated button
		String btnLabel = view.getButtonLabel();
		if (btnLabel == null) {
			btnLabel = view.getTitle();
		}
		final Button btn = new Button(btnLabel.toUpperCase());
		btn.setStyleName(BaseTheme.BUTTON_LINK);
		btn.setIcon(view.getIcon());
		btn.setData(view);
		btn.setWidth("100%");
		btn.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Object data = event.getButton().getData();
				if (data != null && data instanceof IPerspectiveViewContribution) {
					diplayPerspective((IPerspectiveViewContribution)data, btn);
				}
			}
		});
		mainLayout.addComponent(btn, index + 1);
		perspectiveToButtonMap.put(view, btn);
		
		// If it is the forst perspective then switch to it immediately
		if (perspectives.size() == 1) {
			diplayPerspective(view, btn);
		}
	}

	private void diplayPerspective(IPerspectiveViewContribution perspective, Button btn) {
		displayer.displayPerspective(perspective);
		switchSelected(btn);
	}
	
	/**
	 * Remove a perspective
	 * 
	 * @param view
	 */
	public void removePerspective(IPerspectiveViewContribution view) {
		perspectives.remove(view);
		Button btn = perspectiveToButtonMap.get(view);
		if (btn != null) {
			mainLayout.removeComponent(btn);
		}
	}

	/**
	 * Swicth the selected style of a button
	 * @param btn
	 */
	private void switchSelected(Button btn) {
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
