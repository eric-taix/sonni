package org.jug.montpellier.sonni.param;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

/**
 * Tracker to add manage activated and desactivated bundles
 * @author eric
 */
public class Activator implements BundleActivator, SynchronousBundleListener {

	private static Bundle[] bundles;

	public static synchronized Bundle[] getBundles() {
		return bundles;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		Bundle[] allBundles = context.getBundles();
		ArrayList<Bundle> bundleList = new ArrayList<Bundle>();

		for (Bundle bundle : allBundles) {
			bundleList.add(bundle);
		}
		bundles = bundleList.toArray(new Bundle[] {});
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

	@Override
	public void bundleChanged(BundleEvent event) {
		if (BundleEvent.STARTED == event.getType()) {

		} else if (BundleEvent.STOPPED == event.getType()) {

		}
	}

}
