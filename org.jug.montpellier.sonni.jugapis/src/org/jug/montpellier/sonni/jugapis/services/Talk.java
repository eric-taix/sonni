/**
 * 
 */
package org.jug.montpellier.sonni.jugapis.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Talk model
 * @author eric
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Talk {
	public int orderInEvent;
	public String title;
	public String time;
	public Speaker speaker;
}
