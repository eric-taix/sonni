/**
 * 
 */
package org.jug.montpellier.sonni.sponsors.jug;

import java.io.IOException;
import java.util.List;

import org.jug.montpellier.sonni.sponsors.http.HttpRequester;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JUG Montpellier APIs requester
 * 
 * @author eric
 */
public class JugApis {

	private HttpRequester requester;

	public JugApis() {
		requester = new HttpRequester("http://www.jug-montpellier.org");
	}

	public List<Sponsor> getCurrentSponsor() throws IOException {
			String response = requester.get("/api/partners/currentyear.json", null);
			ObjectMapper mapper = new ObjectMapper();
			List<Sponsor> result =  mapper.readValue(response, new TypeReference<List<Sponsor>>() {});
			return result;
	}
}
