/**
 * 
 */
package org.jug.montpellier.sonni.param;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * A class to filter symbolic name
 * 
 * @author eric
 */
@SuppressWarnings("serial")
public class SymbolicNameFilter implements Filter {

	private String filterString;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Container.Filter#passesFilter(java.lang.Object, com.vaadin.data.Item)
	 */
	@Override
	public boolean passesFilter(Object itemId, Item item) throws UnsupportedOperationException {
		Property property = item.getItemProperty("Symbolic name");
		if (filterString == null || filterString.isEmpty()) {
			return true;
		}
		if (property != null && property.getValue() instanceof String && ((String) property.getValue()).contains(filterString)) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Container.Filter#appliesToProperty(java.lang.Object)
	 */
	@Override
	public boolean appliesToProperty(Object propertyId) {
		if (propertyId.equals("Symbolic name")) {
			return true;
		}
		return false;
	}

	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}
}
