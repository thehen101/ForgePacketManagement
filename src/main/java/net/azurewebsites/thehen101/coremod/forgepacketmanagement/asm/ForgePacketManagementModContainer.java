package net.azurewebsites.thehen101.coremod.forgepacketmanagement.asm;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import net.azurewebsites.thehen101.coremod.forgepacketmanagement.ForgePacketManagement;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;

public class ForgePacketManagementModContainer extends DummyModContainer {
	
	public ForgePacketManagementModContainer() {
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.name = "Forge Packet Management";
		meta.modId = "ForgePacketManagement";
		meta.credits = "thehen101";
		meta.description = "A coremod that enables package management in forge.";
		meta.version = ForgePacketManagement.VERSION;
		meta.authorList = Arrays.asList("thehen101");
		meta.url = "https://github.com/thehen101/ForgePacketManagement";
		ArrayList<ModContainer> children = new ArrayList<ModContainer>();
		ModMetadata m = new ModMetadata();
		m.name = "BridgeBuilder";
		children.add(new DummyModContainer(m));
		meta.childMods = children;
	}
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}
}