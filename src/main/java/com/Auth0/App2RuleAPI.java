package com.Auth0;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/")
public class App2RuleAPI {

	private static final Logger logger = Logger.getLogger(App2RuleAPI.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/app2rules")
	public String getHeader(@Context HttpHeaders headers) {

		Response response = new Response();
		List<ClientResponse> clientResponse = new ArrayList();
		List<RuleResponse> ruleResponse = new ArrayList();

		try {

			if (headers.getRequestHeader("Authorization").equals(null)) {

				response.setResponse("Missing JWTKey in the Headers. Please set the Authorization key-value pair ");

			} else if (headers.getRequestHeader("auth0domain").equals(null)) {

				response.setResponse("Missing Domain information. Please set the Domain information correctly");

			} else {

				String jwtKey = headers.getRequestHeader("Authorization").get(0);
				logger.log(Level.INFO, "jwtKey:" + jwtKey);

				String domainURI = headers.getRequestHeader("auth0domain").get(0);
				logger.log(Level.INFO, "domainURI:" + domainURI);

				Response resp = new Response();

				resp.domainURI = domainURI;
				resp.jwtKey = jwtKey;

				// call load rule method to call auth0 management api to retrieve all the rules
				// available for the tenant.
				ruleResponse = loadRules(resp);

				// call load client method to call auth0 management api to retrieve all the
				// apps/clients created by the tenant.
				clientResponse = loadClients(resp);

				for (ClientResponse cr : clientResponse) {

					for (RuleResponse rr : ruleResponse) {

						String toCheck = "context.clientName === '" + cr.name + "'";
						// if the rule contains above code then add that to the specific client
						// response.
						if (rr.script != null && rr.script.contains(toCheck)) {

							cr.ruleResponse = rr;
						} else {
						}
					}
				}
				response.clientResponse = clientResponse;
			}

		} catch (UnknownHostException ex) {
			response.setResponse("Check the Domain URL provided in the request");
			logger.log(Level.SEVERE, "Exception:" + ex.toString());
		} catch (JsonSyntaxException ex) {
			response.setResponse("Check the Token validity as the JWT token expires after 24 Hrs");
			logger.log(Level.SEVERE, "Exception:" + ex.toString());
		} catch (Exception ex) {
			response.setResponse("Exception in the flow, please refer the logs for more details");
			System.out.println(ex.toString());
			logger.log(Level.SEVERE, "Exception:" + ex.toString());
		}
		return response.toString();
	}

	public List<ClientResponse> loadClients(Response response) throws IOException {

		OkHttpClient okClient = new OkHttpClient();
		
		//Call the Read Clients API and filtered out some information from it.
		String url = "https://" + response.domainURI
				+ "/api/v2/clients?fields=signing_keys,jwt_configuration,grant_types&include_fields=false";

		Request request = new Request.Builder().url(url).get().addHeader("Authorization", "Bearer " + response.jwtKey)
				.build();

		//Get the JSON response of the request and convert that to a string.
		com.squareup.okhttp.Response resp = okClient.newCall(request).execute();
		String jsonResponse = resp.body().string();

		Gson gson = new Gson();
		//Deserialize the Json response into an object of ClientResponse class with the help of gson library.
		ClientResponse[] clientArray = gson.fromJson(jsonResponse, ClientResponse[].class);
		List<ClientResponse> clientList = Arrays.asList(clientArray);

		return clientList;
	}

	public List<RuleResponse> loadRules(Response response) throws IOException {

		OkHttpClient client = new OkHttpClient();
		//Call the Read Rule API.
		String url = "https://" + response.domainURI + "/api/v2/rules";

		Request request = new Request.Builder().url(url).get().addHeader("Authorization", "Bearer " + response.jwtKey)
				.build();
		//Get the JSON response of the request and convert that to a string.
		com.squareup.okhttp.Response resp = client.newCall(request).execute();
		String jsonResponse = resp.body().string();

		Gson gson = new Gson();
		//Deserialize the Json response into an object of ClientResponse class with the help of gson library.
		RuleResponse[] ruleArray = gson.fromJson(jsonResponse, RuleResponse[].class);
		List<RuleResponse> ruleList = Arrays.asList(ruleArray);

		return ruleList;
	}

}
