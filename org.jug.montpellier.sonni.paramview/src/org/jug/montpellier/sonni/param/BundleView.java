package org.jug.montpellier.sonni.param;

import java.util.Dictionary;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BundleView extends CustomComponent {

	private Table table;

	public BundleView() {
		setSizeFull();
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setMargin(true);
		verticalLayout.setSpacing(true);
		verticalLayout.setSizeFull();

		final SymbolicNameFilter symbolicFilter = new SymbolicNameFilter();
		TextField filterField = new TextField("Filter by symbolic name");
		filterField.setWidth("200px");
		verticalLayout.addComponent(filterField);
		filterField.addListener(new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				symbolicFilter.setFilterString(event.getText());
				IndexedContainer container = ((IndexedContainer)table.getContainerDataSource());
				container.removeAllContainerFilters();
				container.addContainerFilter(symbolicFilter);
			}
		});
		
		table = new Table();
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Vendor", String.class, null);
		table.addContainerProperty("Symbolic name", String.class, new ThemeResource("icons/bundle.png"));
		table.addContainerProperty("Version", String.class, null);
		table.addContainerProperty("State", String.class, null);
		table.addContainerProperty("Active", CheckBox.class, null);
		table.setSizeFull();
		table.setPageLength(0);
		table.setSortContainerPropertyId("Bundle Symbolic Name");
		table.setSortAscending(true);
		table.setImmediate(true);
		IndexedContainer container = ((IndexedContainer)table.getContainerDataSource());
		container.addContainerFilter(symbolicFilter);
		refreshTable();
		verticalLayout.addComponent(table);
		verticalLayout.setExpandRatio(table, 1.0f);

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);

		Button refreshButton = new Button("Refresh Table");
		refreshButton.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				refreshTable();
			}
		});
		horizontalLayout.addComponent(refreshButton);

		verticalLayout.addComponent(horizontalLayout);
		setCompositionRoot(verticalLayout);
	}

	private void refreshTable() {
		Bundle[] bundles = Activator.getBundles();
		table.removeAllItems();

		for (Bundle bundle : bundles) {
			final Bundle selectedBundle = bundle;
			CheckBox checkBox = new CheckBox();
			checkBox.setImmediate(true);
			checkBox.setValue(bundle.getState() == Bundle.ACTIVE);
			checkBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
					if (selectedBundle.getState() == Bundle.ACTIVE) {
						try {
							selectedBundle.stop();
							refreshTable();
						} catch (BundleException e1) {
							e1.printStackTrace();
						}
					} else if (selectedBundle.getState() == Bundle.RESOLVED) {
						try {
							selectedBundle.start();
							refreshTable();
						} catch (BundleException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			Dictionary<String, String> headers = bundle.getHeaders();
			table.addItem(
					new Object[] {headers.get("Bundle-Name"), headers.get("Bundle-Vendor"), bundle.getSymbolicName(), bundle.getVersion(), getStateString(bundle), checkBox },
					"parent-" + bundle.getSymbolicName() + "-" + bundle.getVersion());
		}
		table.sort();
	}

	String getStateString(Bundle bundle) {
		switch (bundle.getState()) {
		case Bundle.ACTIVE:
			return "ACTIVE";
		case Bundle.INSTALLED:
			return "INSTALLED";
		case Bundle.RESOLVED:
			return "RESOLVED";
		case Bundle.UNINSTALLED:
			return "UNINSTALLED";
		default:
			return "UNKNOWN";
		}
	}
}
