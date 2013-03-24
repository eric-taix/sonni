package org.jug.montpellier.sonni.jugapis.impl;

import java.io.IOException;
import java.util.List;

import org.jug.montpellier.sonni.jugapis.impl.http.HttpRequester;
import org.jug.montpellier.sonni.jugapis.services.IJugRequester;
import org.jug.montpellier.sonni.jugapis.services.Sponsor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation to request JUG APIs
 * @author eric
 */
public class JugRequester implements IJugRequester {

	private HttpRequester requester;

	public JugRequester() {
		requester = new HttpRequester("http://www.jug-montpellier.org");
	}

	public List<Sponsor> getCurrentSponsor() throws IOException {
		String response = requester.get("/api/partners/currentyear.json", null);
		ObjectMapper mapper = new ObjectMapper();
		List<Sponsor> result = mapper.readValue(response, new TypeReference<List<Sponsor>>() {
		});
		return result;
	}
}
