package org.retzlaff.select2.resource;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.request.IRequestParameters;

public abstract class AjaxSearchResource<E> extends AbstractAjaxSearchResource<E> {
	
	private static final long serialVersionUID = 1L;
	
	final AjaxSearchResourceReference<E> reference;
	
	public AjaxSearchResource(AjaxSearchResourceReference<E> reference) {
		super();
		this.reference = reference;
	}
	
	@Override
	protected IChoiceRenderer<? super E> getChoiceRenderer(IRequestParameters params) {
		return reference.getChoiceRenderer();
	}

}
