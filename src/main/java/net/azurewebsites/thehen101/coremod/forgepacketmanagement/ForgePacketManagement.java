package net.azurewebsites.thehen101.coremod.forgepacketmanagement;

import net.azurewebsites.thehen101.coremod.forgepacketmanagement.event.EventPacketQueuedManager;

public enum ForgePacketManagement {
	INSTANCE;
	
	public static final String VERSION = "1.0";
	
	private final EventPacketQueuedManager packetQueueManager = new EventPacketQueuedManager();
	
	public EventPacketQueuedManager getPacketQueueManager() {
		return this.packetQueueManager;
	}
}
