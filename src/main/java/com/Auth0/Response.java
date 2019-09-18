package com.Auth0;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@JsonIgnoreProperties({ "domainURI", "jwtKey" })
public class Response {

	// for capturing the header arguments
	@JsonProperty("domainURI")
	public String domainURI;
	@JsonProperty("jwtKey")
	public String jwtKey;

	// Variables to populate the response

	public String response;

	// setter for populating the response

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public List<ClientResponse> clientResponse;

	public String toString() {

		GsonBuilder setPrettyPrinting = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping();
		Gson gson = setPrettyPrinting.create();
		return gson.toJson(this);
	}

}
