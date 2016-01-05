package org.retzlaff.select2;

import java.util.List;

public abstract class ForwardingSelect2AjaxAdapter<T> implements ISelect2AjaxAdapter<T> {
	
	private static final long serialVersionUID = 5340400681850688335L;

	protected abstract ISelect2AjaxAdapter<T> delegate();

	@Override
	public String getChoiceId(T object) {
		return delegate().getChoiceId(object);
	}

	@Override
	public T getChoice(String id) {
		return delegate().getChoice(id);
	}

	@Override
	public List<T> getChoices(int start, int count, String filter) {
		return delegate().getChoices(start, count, filter);
	}

	@Override
	public Object getDisplayValue(T object) {
		return delegate().getDisplayValue(object);
	}

}
