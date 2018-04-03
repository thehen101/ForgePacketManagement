# Forge Packet Management
This is a core mod for the Minecraft Forge Modding API that allows developers to manage and manipulate outgoing packets easily. This mod was designed for Minecraft 1.8, however other versions may work (but will require modification of the code as 1.8 is the only whitelisted version the mod will launch with).

##### Latest: Version 1.0 for Minecraft 1.8

## Using this mod
Whilst designing this mod I intended it to as simple as possible to use - to use it with your mod, download a compiled jar from the releases section or build one yourself and add the jar to your workspace. To use it in your code, create a class that implements `PacketQueueListener` and implement the method `onPacketQueued`:
```java
public class MyPacketListener implements PacketQueueListener {

  @Override
  public void onPacketQueued(EventPacketQueued event) {
    System.out.println(event.getPacket().getClass());
  }
  
  //add other methods here
}
```

When you would like to start listening for packets, register your listener with `EventPacketQueuedManager` (singleton):
```java
ForgePacketManagement.INSTANCE.getPacketQueueManager().addListener(this);
```
Your `onPacketQueued` method will now fire on every packet that your client sends. You can cancel, change, edit and manipulate these packets in any way that you would like.
When you don't need to listen for packets anymore, remove your listener:
```java
ForgePacketManagement.INSTANCE.getPacketQueueManager().removeListener(this);
```
Please report any bugs you find and tell me if you use this in your mod. I'd like to build up a list 😃
