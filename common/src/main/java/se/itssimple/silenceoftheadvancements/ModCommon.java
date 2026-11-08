package se.itssimple.silenceoftheadvancements;

import se.itssimple.silenceoftheadvancements.data.Constants;
import se.itssimple.silenceoftheadvancements.util.Reference;

/**
 * ModCommon contains all the configuration info for the mod
 */
public class ModCommon {
	/** Default constructor */
	public ModCommon() {}

	/**
	 * The init method for the ModCommon class, telling us which mod and version we've loaded
	 */
	public static void init() {
		Constants.LOG.info("Loading {} (ID: {}), version {}", Reference.NAME, Reference.MOD_ID, Reference.VERSION);
	}
}