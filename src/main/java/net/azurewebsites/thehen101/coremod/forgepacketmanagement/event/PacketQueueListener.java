package net.azurewebsites.thehen101.coremod.forgepacketmanagement.event;

public abstract interface PacketQueueListener {
	public abstract void onPacketQueued(EventPacketQueued event);
}
