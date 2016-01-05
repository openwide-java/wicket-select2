package org.retzlaff.select2.resource;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.util.time.Duration;

public abstract class AbstractAjaxSearchResource<E> extends AbstractResource {

	private static final long serialVersionUID = 1L;
	
	private String textEncoding = "UTF-8";
	private Duration ajaxCacheDuration = Duration.NONE;
	private int pageLimit = 10;

	public AbstractAjaxSearchResource() {
		super();
	}

	protected void setTextEncoding(String textEncoding) {
		this.textEncoding = textEncoding;
	}

	protected void setAjaxCacheDuration(Duration ajaxCacheDuration) {
		this.ajaxCacheDuration = ajaxCacheDuration;
	}

	protected void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		ResourceResponse response = new ResourceResponse();
		response.setContentType("text/javascript");
		if (textEncoding != null) {
			response.setTextEncoding(textEncoding);
		}
		if (ajaxCacheDuration != null) {
			response.setCacheDuration(ajaxCacheDuration);
		}
		response.setWriteCallback(new WriteCallback() {
			@Override
			public void writeData(Attributes attributes) {
				IRequestParameters params = RequestCycle.get().getRequest().getQueryParameters();
				int page = params.getParameterValue("page").toInt(1) - 1;
				String filter = params.getParameterValue("q").toString();
				String callback = params.getParameterValue("callback").toString();
				
				List<E> choices = getChoices(params, page * pageLimit, pageLimit + 1, filter);
				
				boolean more = choices.size() > pageLimit;
				if (more) {
					choices = choices.subList(0, pageLimit);
				}
				CharSequence results = renderChoices(params, choices, page * pageLimit);
				
				StringBuilder sb = new StringBuilder();
				sb.append(callback).append("(\n");
				sb.append("{results:").append(results).append(",more:").append(more).append('}');
				sb.append("\n)");
				
				attributes.getResponse().write(sb);
			}
		});
		return response;
	}

	public abstract List<E> getChoices(IRequestParameters params, int start, int count, String filter);

	private CharSequence escape(Object object) {
		return "'" + object.toString().replace("'", "\\'") + "'";
	}

	private CharSequence renderChoices(IRequestParameters params, List<? extends E> choices, int index) {
		if (choices == null || choices.isEmpty()) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		IChoiceRenderer<? super E> choiceRenderer = getChoiceRenderer(params);
		for (E object : choices) {
			String id = choiceRenderer.getIdValue(object, index++);
			Object value = choiceRenderer.getDisplayValue(object);
			
			sb.append("{id:").append(escape(id)).append(",text:").append(escape(value)).append("},");
		}
		
		sb.setLength(sb.length() - 1); // trailing comma
		sb.append(']');
		return sb;
	}
	

	protected abstract IChoiceRenderer<? super E> getChoiceRenderer(IRequestParameters params);

}