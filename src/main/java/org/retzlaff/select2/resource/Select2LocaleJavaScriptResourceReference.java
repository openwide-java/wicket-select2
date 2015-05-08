package org.retzlaff.select2.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryPluginResourceReference;

public final class Select2LocaleJavaScriptResourceReference extends JQueryPluginResourceReference {

	private static final long serialVersionUID = -8922802197577810066L;
	
	private Select2LocaleJavaScriptResourceReference(Locale locale) {
		super(Select2LocaleJavaScriptResourceReference.class, "select2_locale.js", locale, null, null);
	}
	
	@Override
	public List<HeaderItem> getDependencies() {
		List<HeaderItem> dependencies = new ArrayList<HeaderItem>(1);
		dependencies.add(JavaScriptHeaderItem.forReference(Select2JavaScriptResourceReference.get()));
		
		return dependencies;
	}

	public static Select2LocaleJavaScriptResourceReference get(Locale locale) {
		return new Select2LocaleJavaScriptResourceReference(locale);
	}

}
