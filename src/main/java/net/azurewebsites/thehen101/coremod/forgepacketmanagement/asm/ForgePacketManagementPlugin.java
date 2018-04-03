package net.azurewebsites.thehen101.coremod.forgepacketmanagement.asm;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("ForgePacketManagement")
@IFMLLoadingPlugin.MCVersion("1.8")
public class ForgePacketManagementPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { "net.azurewebsites.thehen101.coremod.forgepacketmanagement.asm.ForgePacketManagementClassTransformer" };
	}

	@Override
	public String getModContainerClass() {
		return "net.azurewebsites.thehen101.coremod.forgepacketmanagement.asm.ForgePacketManagementModContainer";
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> arg0) {
	}
	
	@Override
	public String getAccessTransformerClass() {
		return null;
	}
}
