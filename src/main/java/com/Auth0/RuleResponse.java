package com.Auth0;

/**
 *
 * @author Amit Tarneja
 */

public class RuleResponse {

	public final String id;
	public final boolean enabled;
	public final String script;
	public final String name;
	public final long order;
	public final String stage;

	public RuleResponse(String id, boolean enabled, String script, String name, long order, String stage) {
		this.name = name;
		this.id = id;
		this.enabled = enabled;
		this.script = script;
		this.order = order;
		this.stage = stage;
	}
}
