package com.Auth0;

/**
 *
 * @author Amit Tarneja
 */

public class ClientResponse {
	public final String tenant;
	public final boolean global;
	public final boolean is_token_endpoint_ip_header_trusted;
	public final String name;
	public final boolean is_first_party;
	public final boolean oidc_conformant;
	public final boolean sso_disabled;
	public final boolean cross_origin_auth;
	public final String client_id;
	public final boolean callback_url_template;
	public final String client_secret;
	public final String app_type;
	public final boolean custom_login_page_on;
	
	public RuleResponse ruleResponse;

	public ClientResponse(String tenant, boolean global, boolean is_token_endpoint_ip_header_trusted, String name,
			boolean is_first_party, boolean oidc_conformant, boolean sso_disabled, boolean cross_origin_auth,
			String client_id, boolean callback_url_template, String client_secret, String app_type,
			boolean custom_login_page_on) {
		this.tenant = tenant;
		this.global = global;
		this.is_token_endpoint_ip_header_trusted = is_token_endpoint_ip_header_trusted;
		this.name = name;
		this.is_first_party = is_first_party;
		this.oidc_conformant = oidc_conformant;
		this.sso_disabled = sso_disabled;
		this.cross_origin_auth = cross_origin_auth;
		this.client_id = client_id;
		this.callback_url_template = callback_url_template;
		this.client_secret = client_secret;
		this.app_type = app_type;
		this.custom_login_page_on = custom_login_page_on;
	}
}
