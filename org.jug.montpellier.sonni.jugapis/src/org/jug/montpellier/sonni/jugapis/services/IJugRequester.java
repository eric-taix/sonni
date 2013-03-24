package org.jug.montpellier.sonni.jugapis.services;

import java.io.IOException;
import java.util.List;

/**
 * Definition of the JUG APIs
 * @author eric
 *
 */
public interface IJugRequester {

	public List<Sponsor> getCurrentSponsor() throws IOException;
	
	public List<Conference> getAllConference() throws IOException;
	
}
