/**
 * 
 */
package org.jug.montpellier.sonni.jugapis.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The speaker model
 * @author eric
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Speaker {
	public String fullName;
	public String activity;
	public String company;
	public String url;
	public String personalUrl;
	public String email;
	public String description;
	public String photoUrl;
}
