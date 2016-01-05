package org.retzlaff.select2.resource;

import java.util.List;
import java.util.Locale;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.request.parameter.EmptyRequestParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.convert.IConverter;
import org.retzlaff.select2.ISelect2AjaxAdapter;

public abstract class AjaxSearchResourceReference<E> extends ResourceReference {

	private static final long serialVersionUID = -4563673330173469315L;
	
	private final IConverter<E> idToChoiceConverter;
	private final IConverter<? super E> renderer;
	
	private transient ISelect2AjaxAdapter<E> adapterCache;
	private transient IChoiceRenderer<? super E> choiceRendererCache;
	
	public AjaxSearchResourceReference(Class<?> scope, String name, IConverter<E> idToChoiceConverter, IConverter<? super E> renderer) {
		super(scope, name);
		this.idToChoiceConverter = idToChoiceConverter;
		this.renderer = renderer;
	}
	
	@Override
	public abstract AjaxSearchResource<E> getResource();

	public ISelect2AjaxAdapter<E> getAdapter() {
		/* Don't care about synchronization here: worst case, the adapter gets erased
		 * with another object that acts exactly the same)
		 */
		if (adapterCache == null) {
			adapterCache = new ISelect2AjaxAdapter<E>() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(E object) {
					return renderer.convertToString(object, Locale.ROOT);
				}

				@Override
				public String getChoiceId(E object) {
					return idToChoiceConverter.convertToString(object, Locale.ROOT);
				}

				@Override
				public final E getChoice(String id) {
					return idToChoiceConverter.convertToObject(id, Locale.ROOT);
				}

				@Override
				public List<E> getChoices(int start, int count, String filter) {
					return getResource().getChoices(EmptyRequestParameters.INSTANCE, start, count, filter);
				}
			};
		}
		return adapterCache;
	}

	public IChoiceRenderer<? super E> getChoiceRenderer() {
		/* Don't care about synchronization here: worst case, the choiceRenderer gets erased
		 * with another object that acts exactly the same)
		 */
		if (choiceRendererCache == null) {
			choiceRendererCache = new ChoiceRenderer<E>() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(E object) {
					return renderer.convertToString(object, Locale.ROOT);
				}

				@Override
				public String getIdValue(E object, int index) {
					return idToChoiceConverter.convertToString(object, Locale.ROOT);
				}
			};
		}
		return choiceRendererCache;
	}

}
