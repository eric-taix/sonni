package org.jug.montpellier.sonni.jugapis.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A conference model
 * @author eric
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conference {
	public String title;
	public String date;
	public String location;
	public String description;
	public String registrationURL;
	public boolean open;
	public List<Talk> talks;
}
