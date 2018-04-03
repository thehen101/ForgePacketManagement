package net.azurewebsites.thehen101.coremod.forgepacketmanagement.event;

import net.minecraft.network.Packet;

public class EventPacketQueued {
	private boolean cancelled;
	private Packet packet;

	public EventPacketQueued(Packet packet) {
		this.packet = packet;
	}

	public boolean getCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean state) {
		this.cancelled = state;
	}

	public Packet getPacket() {
		return this.packet;
	}

	public void setPacket(Packet newPacket) {
		this.packet = newPacket;
	}
}