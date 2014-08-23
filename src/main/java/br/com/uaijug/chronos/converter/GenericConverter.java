/*
 * 
 */
package br.com.uaijug.chronos.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;


/**
 * The Class GenericConverter.
 *
 * @author Rogerio Fontes
 * How use: <f:converter converterId="br.com.uaijug.converter.GenericConverter" />
 */
//@FacesConverter("converter.GenericConverter")
public class GenericConverter implements Converter {

	/** The index. */
	private int index = -1;

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {

		SelectItem selectedItem = this.getSelectedItemByIndex(component,
				Integer.parseInt(value));
		if (selectedItem != null) {
			Object obj = selectedItem.getValue();
			return obj;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {

		index++;
		return String.valueOf(index);

	}

	/**
	 * Obtem o SelecItem de acordo com a oplicacaoo selecionada pelo usuario.
	 *
	 * @param component the component
	 * @param index the index
	 * @return the selected item by index
	 */
	protected SelectItem getSelectedItemByIndex(UIComponent component, int index) {

		List<SelectItem> items = this.getSelectItems(component);
		int size = items.size();

		if (index > -1 && size > index) {
			return items.get(index);
		}

		return null;
	}

	/**
	 * Gets the select items.
	 *
	 * @param component the component
	 * @return the select items
	 */
	protected List<SelectItem> getSelectItems(UIComponent component) {

		List<SelectItem> items = new ArrayList<SelectItem>();

		int childCount = component.getChildCount();
		if (childCount == 0)
			return items;

		List<UIComponent> children = component.getChildren();
		for (UIComponent child : children) {
			if (child instanceof UISelectItem) {
				this.addSelectItem((UISelectItem) child, items);
			} else if (child instanceof UISelectItems) {
				this.addSelectItems((UISelectItems) child, items);
			}
		}

		return items;
	}

	/**
	 * Adds the select item.
	 *
	 * @param uiItem the ui item
	 * @param items the items
	 */
	protected void addSelectItem(UISelectItem uiItem, List<SelectItem> items) {

		boolean isRendered = uiItem.isRendered();
		if (!isRendered) {
			items.add(null);
			return;
		}

		Object value = uiItem.getValue();
		SelectItem item;

		if (value instanceof SelectItem) {
			item = (SelectItem) value;
		} else {
			Object itemValue = uiItem.getItemValue();
			String itemLabel = uiItem.getItemLabel();
			// JSF throws a null pointer exception for null values and labels,
			// which is a serious problem at design-time.
			item = new SelectItem(itemValue == null ? "" : itemValue,
					itemLabel == null ? "" : itemLabel, uiItem
							.getItemDescription(), uiItem.isItemDisabled());
		}

		items.add(item);
	}

	/**
	 * Adds the select items.
	 *
	 * @param uiItems the ui items
	 * @param items the items
	 */
	@SuppressWarnings("unchecked")
	protected void addSelectItems(UISelectItems uiItems, List<SelectItem> items) {

		boolean isRendered = uiItems.isRendered();
		if (!isRendered) {
			items.add(null);
			return;
		}

		Object value = uiItems.getValue();
		if (value instanceof SelectItem) {
			items.add((SelectItem) value);
		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			for (int i = 0; i < array.length; i++) {
				// TODO test - this section is untested
				if (array[i] instanceof SelectItemGroup) {
					resolveAndAddItems((SelectItemGroup) array[i], items);
				} else {
					items.add((SelectItem) array[i]);
				}
			}
		} else if (value instanceof Collection) {
			Iterator<SelectItem> iter = ((Collection<SelectItem>) value)
					.iterator();
			SelectItem item;
			while (iter.hasNext()) {
				item = iter.next();
				if (item instanceof SelectItemGroup) {
					resolveAndAddItems((SelectItemGroup) item, items);
				} else {
					items.add(item);
				}
			}
		} else if (value instanceof Map) {
			for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) value)
					.entrySet()) {
				Object label = entry.getKey();
				SelectItem item = new SelectItem(entry.getValue(),
						label == null ? (String) null : label.toString());
				// TODO test - this section is untested
				if (item instanceof SelectItemGroup) {
					resolveAndAddItems((SelectItemGroup) item, items);
				} else {
					items.add(item);
				}
			}
		}

	}

	/**
	 * Resolve and add items.
	 *
	 * @param group the group
	 * @param items the items
	 */
	protected void resolveAndAddItems(SelectItemGroup group,
			List<SelectItem> items) {

		for (SelectItem item : group.getSelectItems()) {
			if (item instanceof SelectItemGroup) {
				resolveAndAddItems((SelectItemGroup) item, items);
			} else {
				items.add(item);
			}
		}

	}
}
