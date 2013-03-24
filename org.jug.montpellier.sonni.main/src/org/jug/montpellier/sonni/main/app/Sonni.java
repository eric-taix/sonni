package org.jug.montpellier.sonni.main.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jug.montpellier.sonni.main.app.views.components.PerspectiveDisplayer;
import org.jug.montpellier.sonni.main.app.views.components.PerspectiveToolbar;
import org.jug.montpellier.sonni.main.services.IFooterViewContribution;
import org.jug.montpellier.sonni.main.services.IPerspectiveViewContribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The main entry point for Sonni
 * 
 * @author etaix
 */
public class Sonni extends Application implements PerspectiveDisplayer {

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(Sonni.class);

	private final List<IPerspectiveViewContribution> perspectiveContributions = Collections
			.synchronizedList(new ArrayList<IPerspectiveViewContribution>());
	private IFooterViewContribution footerViewContribution;

	private Window main;
	private VerticalLayout mainLayout;

	private volatile boolean initialized = false;

	private PerspectiveToolbar perspectiveToolbar;
	private VerticalLayout contentContainer;
	private HorizontalLayout footerContainer;
	private Label perspectiveLabel;
	private VerticalLayout perspectiveContent;

	// The panel where perspective's content is displayed

	@Override
	public void init() {
		logger.info("Dynamic Vaadin OSGi demo init...");
		setTheme("sonni");
		main = new Window("Sonni - Dynamic Vaadin OSGi Demo");
		setMainWindow(main);
		main.addStyleName("sonni");
		main.setSizeFull();

		AbsoluteLayout topContainer = new AbsoluteLayout();
		topContainer.setSizeFull();
		main.setContent(topContainer);

		mainLayout = new VerticalLayout();
		mainLayout.setMargin(false);
		mainLayout.setSizeFull();
		topContainer.addComponent(mainLayout);

//		Button detailBtn = new Button();
//		detailBtn.setIcon(new ThemeResource("img/detail.png"));
//		topContainer.addComponent(detailBtn,"top: 50%; right: 0px;");
		
		// The perspective toolbar and content layout
		HorizontalLayout toolbarLayout = new HorizontalLayout();
		toolbarLayout.setSizeFull();
		// contentLayout.setSpacing(true);
		mainLayout.addComponent(toolbarLayout);
		mainLayout.setExpandRatio(toolbarLayout, 1.0f);

		// The perspective toolbar which displays perspective icons
		perspectiveToolbar = new PerspectiveToolbar(this);
		perspectiveToolbar.setHeight("100%");
		toolbarLayout.addComponent(perspectiveToolbar);

		contentContainer = new VerticalLayout();
		contentContainer.setWidth("100%");
		contentContainer.addStyleName("perspective-content");
		contentContainer.setMargin(false);
		contentContainer.setSizeFull();
		toolbarLayout.addComponent(contentContainer);
		toolbarLayout.setExpandRatio(contentContainer, 1.0f);

		HorizontalLayout headerBar = new HorizontalLayout();
		headerBar.addStyleName("header-bar");
		headerBar.setWidth("100%");
		contentContainer.addComponent(headerBar);
		
		perspectiveLabel = new Label();
		perspectiveLabel.setSizeUndefined();
		perspectiveLabel.addStyleName("perspective-title");
		headerBar.addComponent(perspectiveLabel);
		headerBar.setComponentAlignment(perspectiveLabel, Alignment.MIDDLE_CENTER);

		perspectiveContent = new VerticalLayout();
		perspectiveContent.setSizeFull();
		perspectiveContent.setMargin(true);
		perspectiveContent.setSpacing(true);
		contentContainer.addComponent(perspectiveContent);
		contentContainer.setExpandRatio(perspectiveContent, 1.0f);

		footerContainer = new HorizontalLayout();
		footerContainer.addStyleName("footer");
		footerContainer.setWidth("100%");
		contentContainer.addComponent(footerContainer);

		// Add already binded perspective contributions
		for (IPerspectiveViewContribution perspective : perspectiveContributions) {
			perspectiveToolbar.addPerspective(perspective);
		}

		// Add already binder footer contribution
		if (footerViewContribution != null) {
			footerContainer.addComponent(footerViewContribution.getView(this));
		}

		// Create the indicator that is used as a poll mechanism. Manual
		// server-side starting/stopping of bundles has a direct effect on the
		// client UI
		ProgressIndicator indicator = new ProgressIndicator();
		indicator.setPollingInterval(1000);
		indicator.setWidth("1px");
		indicator.setHeight("1px");
		main.addComponent(indicator);
		initialized = true;
	}

	@Override
	public void displayPerspective(IPerspectiveViewContribution perspective) {
		if (perspective.getTitle() != null) {
			perspectiveLabel.setPropertyDataSource(new ObjectProperty<String>(perspective.getTitle(), String.class));
			perspectiveLabel.setVisible(true);
		} else {
			perspectiveLabel.setVisible(false);
		}
		Component comp = perspective.getView(this);
		if (comp != null) {
			perspectiveContent.removeAllComponents();
			perspectiveContent.addComponent(comp);
			perspectiveContent.setComponentAlignment(comp, Alignment.MIDDLE_CENTER);
		}
	}

	// ================== OSGI bind / unbind methods ===================

	/**
	 * Callback called but OSGI framework when a Perspective is binded
	 * 
	 * @param viewContribution
	 */
	public void bindPerspectiveContribution(IPerspectiveViewContribution perspectiveContribution) {
		logger.debug("bindPerspectiveContribution()");
		perspectiveContributions.add(perspectiveContribution);
		if (initialized) {
			perspectiveToolbar.addPerspective(perspectiveContribution);
		}
	}

	public void unbindPerspectiveContribution(IPerspectiveViewContribution perspectiveContribution) {
		logger.debug("unbindPerspectiveContribution()");
		perspectiveContributions.add(perspectiveContribution);
		if (initialized) {
			perspectiveToolbar.removePerspective(perspectiveContribution);
		}
	}

	public void bindFooterContribution(IFooterViewContribution footerContribution) {
		logger.debug("bindFooterContribution()");
		footerViewContribution = footerContribution;
		if (initialized) {
			footerContainer.removeAllComponents();
			Component component = footerViewContribution.getView(this);
			footerContainer.addComponent(component);
			footerContainer.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
		}
	}

	public void unbindFooterContribution(IFooterViewContribution footerContribution) {
		logger.debug("unbindFooterContribution()");
		footerViewContribution = null;
		if (initialized) {
			footerContainer.removeAllComponents();
		}
	}

	// /**
	// * Callback called but OSGI framework when a View is binded
	// *
	// * @param viewContribution
	// */
	// public void bindViewContribution(IViewContribution viewContribution) {
	// logger.debug("bindViewContribution()");
	// viewContributions.add(viewContribution);
	// if (initialized) {
	// // tabSheet.addTab(viewContribution.getView(this),
	// // viewContribution.getName(), new ThemeResource(
	// // viewContribution.getIcon()));
	// }
	// }
	//
	// /**
	// * Callback called by OSGI framework when a View is unbinded
	// *
	// * @param viewContribution
	// */
	// public void unbindViewContribution(IViewContribution viewContribution) {
	// logger.debug("unbindViewContribution()");
	// viewContributions.remove(viewContribution);
	// if (initialized) {
	// // tabSheet.removeComponent(viewContribution.getView(this));
	// }
	// }
	//
	// public void bindActionContribution(
	// final IActionContribution actionContribution) {
	// logger.debug("bindActionContribution()");
	// if (initialized) {
	// addActionContribution(actionContribution);
	// }
	// actionContributions.add(actionContribution);
	// }
	//
	// private void addActionContribution(
	// final IActionContribution actionContribution) {
	// // final Application application = this;
	// // Button button = new Button(actionContribution.getText());
	// // button.setIcon(new ThemeResource(actionContribution.getIcon()));
	// // button.addListener(new ClickListener() {
	// // private static final long serialVersionUID = 1L;
	// //
	// // @Override
	// // public void buttonClick(ClickEvent event) {
	// // actionContribution.execute(application);
	// // }
	// // });
	// // getToolbar().addComponent(button);
	// // buttonActionMap.put(actionContribution, button);
	// //
	// // @SuppressWarnings("serial")
	// // MenuItem menuItem = actionMenu.addItem(actionContribution.getText(),
	// // new Command() {
	// // @Override
	// // public void menuSelected(MenuItem selectedItem) {
	// // actionContribution.execute(application);
	// // }
	// // });
	// // menuItem.setIcon(new ThemeResource(actionContribution.getIcon()));
	// // menuActionMap.put(actionContribution, menuItem);
	// }
	//
	// public void unbindActionContribution(IActionContribution
	// actionContribution) {
	// logger.debug("unbindActionContribution()");
	// Button button = buttonActionMap.get(actionContribution);
	// toolbar.removeComponent(button);
	// buttonActionMap.remove(actionContribution);
	//
	// MenuItem menuItem = menuActionMap.get(actionContribution);
	// actionMenu.removeChild(menuItem);
	// menuActionMap.remove(actionContribution);
	//
	// actionContributions.remove(actionContribution);
	// }

}