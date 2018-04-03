package net.azurewebsites.thehen101.coremod.forgepacketmanagement.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventPacketQueuedManager {
	private final List<PacketQueueListener> listeners = new CopyOnWriteArrayList();

	public final void addListener(PacketQueueListener listener) {
		if (this.listeners.contains(listener))
			return;
		this.listeners.add(listener);
	}

	public final void removeListener(PacketQueueListener listener) {
		if (!this.listeners.contains(listener))
			return;
		this.listeners.remove(listener);
	}

	public final void callEvent(EventPacketQueued event) {
		for (PacketQueueListener listeners : this.listeners)
			if (listeners != null)
				listeners.onPacketQueued(event);
	}

	public final List<PacketQueueListener> getListeners() {
		return this.listeners;
	}
}
