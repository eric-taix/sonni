/**
 * 
 */
package org.jug.montpellier.sonni.jugapis.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * A sponsor model which will be filled by JUG api result
 * 
 * @author eric
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sponsor {
	public int id;
	public String name;
	public String url;
	public String description;
	public String logoURL;
	public String startDate;
	public String stopDate;
}
