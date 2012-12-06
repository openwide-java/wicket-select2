package org.retzlaff.select2;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.time.Duration;

/**
 * Select2 Settings
 * 
 * @author dan
 */
public class Select2Settings implements IClusterable {
	private static final long serialVersionUID = 5430777801377122502L;
	
	private Duration cacheDuration = Duration.NONE;
	private int pageLimit = 10;
	private int quietMillis;
	private boolean allowClear;
	private int minimumInputLength;
	private int minimumResultsForSearch;
	private int maximumSelectionSize;
	private String placeholderKey;
	private String noMatchesKey;
	private String inputTooShortKey;
	private String selectionTooBigKey;
	private boolean tagging;
	private String width = "resolve";
	private Map<String, String> containerCss = new HashMap<String, String>();
	private String containerCssClass;
	private Map<String, String> dropdownCss = new HashMap<String, String>();
	private String dropdownCssClass;
	private String matcher;
	private String formatResult;
	
	// AJAX settings
	
	/**
	 * For AJAX population, how long browser can cache results.
	 */
	public void setCacheDuration(Duration cacheDuration) {
		this.cacheDuration = cacheDuration;
	}
	public Duration getAjaxCacheDuration() {
		return cacheDuration;
	}

	/**
	 * For AJAX population, show this many choices per page. 
	 */
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getPageLimit() {
		return pageLimit;
	}

	/**
	 * For AJAX population, number of milliseconds to wait for the user to stop typing before issuing the request.
	 */
	public void setQuietMillis(int quietMillis) {
		this.quietMillis = quietMillis;
	}
	public int getQuietMillis() {
		return quietMillis;
	}
	
	// Shared settings
	
	/**
	 * Allow clear
	 */
	public void setAllowClear(boolean allowClear) {
		this.allowClear = allowClear;
	}
	public boolean isAllowClear() {
		return allowClear;
	}

	/**
	 * Number of character necessary to start a search.
	 */
	public void setMinimumInputLength(int minimumInputLength) {
		this.minimumInputLength = minimumInputLength;
	}
	public int getMinimumInputLength() {
		return minimumInputLength;
	}

	/**
	 * The minimum number of results that must be initially populated in order to keep the search field.
	 */
	public void setMinimumResultsForSearch(int minimumResultsForSearch) {
		this.minimumResultsForSearch = minimumResultsForSearch;
	}
	public int getMinimumResultsForSearch() {
		return minimumResultsForSearch;
	}

	/**
	 * The maximum number of selected elements for a multiple select component.
	 */
	public int getMaximumSelectionSize() {
		return maximumSelectionSize;
	}
	public void setMaximumSelectionSize(int maximumSelectionSize) {
		this.maximumSelectionSize = maximumSelectionSize;
	}

	/**
	 * Localization key for "choose one" message.
	 */
	public void setPlaceholderKey(String placeholderKey) {
		this.placeholderKey = placeholderKey;
	}
	public String getPlaceholderKey() {
		return placeholderKey;
	}

	/**
	 * Localization key for "no matches" message.
	 */
	public void setNoMatchesKey(String noMatchesKey) {
		this.noMatchesKey = noMatchesKey;
	}
	public String getNoMatchesKey() {
		return noMatchesKey;
	}

	/**
	 * Localization key for "input too short" message. The minimum length is available as ${}.
	 */
	public void setInputTooShortKey(String inputTooShortKey) {
		this.inputTooShortKey = inputTooShortKey;
	}
	public String getInputTooShortKey() {
		return inputTooShortKey;
	}

	/**
	 * Localization key for "selection too big" message. The maximum number of selected elements is available as ${}.
	 */
	public String getSelectionTooBigKey() {
		return selectionTooBigKey;
	}
	public void setSelectionTooBigKey(String selectionTooBigKey) {
		this.selectionTooBigKey = selectionTooBigKey;
	}
	
	/**
	 * If set, the search string will be available for selection.
	 * <p>
	 * Selection of a new tag name will cause {@link ISelect2AjaxAdapter#getChoice(String)} invocations with new IDs.
	 * <p>
	 * Only available with AJAX components.
	 */
	public void setTagging(boolean tagging) {
		this.tagging = tagging;
	}
	public boolean isTagging() {
		return tagging;
	}
	
	/**
	 * If set, defines the method used to set the width of the element.
	 * 
	 * <p>
	 * Can be either of, element, copy, resolve or another value used verbatim.
	 */
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	/**
	 * Contains the key-value pairs for inline css that will be added to select2's container.
	 */
	public Map<String, String> getContainerCss() {
		return containerCss;
	}
	public void addContainerCss(String key, String value) {
		containerCss.put(key, value);
	}
	
	/**
	 * Contains the css class that will be added to select2's container.
	 */
	public String getContainerCssClass() {
		return containerCssClass;
	}
	public void setContainerCssClass(String containerCssClass) {
		this.containerCssClass = containerCssClass;
	}
	
	/**
	 * Contains the key-value pairs for inline css that will be added to select2's dropdown container.
	 */
	public Map<String, String> getDropdownCss() {
		return dropdownCss;
	}
	public void addDropdownCss(String key, String value) {
		dropdownCss.put(key, value);
	}
	
	/**
	 * Contains the css class that will be added to select2's dropdown container.
	 */
	public String getDropdownCssClass() {
		return dropdownCssClass;
	}
	public void setDropdownCssClass(String dropdownCssClass) {
		this.dropdownCssClass = dropdownCssClass;
	}
	
	/**
	 * Used to determine whether or not the search term matches an option when a built-in query function is used.
	 */
	public String getMatcher() {
		return matcher;
	}
	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}
	
	/**
	 * Function used to render a result that the user can select.
	 */
	public String getFormatResult() {
		return formatResult;
	}
	public void setFormatResult(String formatResult) {
		this.formatResult = formatResult;
	}
	
}
