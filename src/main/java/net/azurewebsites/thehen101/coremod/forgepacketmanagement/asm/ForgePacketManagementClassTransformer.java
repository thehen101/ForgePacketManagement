package net.azurewebsites.thehen101.coremod.forgepacketmanagement.asm;

import static org.objectweb.asm.Opcodes.*;

import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FrameNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class ForgePacketManagementClassTransformer implements IClassTransformer {
	private static final String[] classesBeingTransformed = { "net.minecraft.client.network.NetHandlerPlayClient" };

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass) {
		boolean isObfuscated = !name.equals(transformedName);
		int index = Arrays.asList(classesBeingTransformed).indexOf(transformedName);
		return index != -1 ? transform(index, basicClass, isObfuscated) : basicClass;
	}

	private static byte[] transform(int index, byte[] basicClass, boolean obfuscated) {
		try {
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept(classNode, 0);

			switch (index) {
			case 0:
				transformAddToSendQueue(classNode, obfuscated);
				break;
			}

			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			return classWriter.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return basicClass;
	}

	private static void transformAddToSendQueue(ClassNode netHandlerPlayClientClass, boolean obfuscated) {
		final String NETMANAGER = obfuscated ? "field_147302_e" : "netManager";
		final String SENDPACKET = obfuscated ? "func_179290_a" : "sendPacket";
		final String ADD_TO_SEND_QUEUE = obfuscated ? "a" : "addToSendQueue";
		final String ADD_TO_SEND_QUEUE_DESC = obfuscated ? "(Lid;)V" : "(Lnet/minecraft/network/Packet;)V";

		for (MethodNode method : netHandlerPlayClientClass.methods) {
			if (method.name.equals(ADD_TO_SEND_QUEUE) && method.desc.equals(ADD_TO_SEND_QUEUE_DESC)) {
				AbstractInsnNode targetNode = null;
				for (AbstractInsnNode instruction : method.instructions.toArray()) {
					if (instruction.getOpcode() == ALOAD && ((VarInsnNode) instruction).var == 0 &&
							instruction instanceof VarInsnNode) {
						targetNode = instruction;						
						InsnList toInsert = new InsnList();
						toInsert.add(new TypeInsnNode(NEW, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued"));
						toInsert.add(new InsnNode(DUP));
						toInsert.add(new VarInsnNode(ALOAD, 1));
						toInsert.add(new MethodInsnNode(INVOKESPECIAL, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued", "<init>", "(Lnet/minecraft/network/Packet;)V", false));
						toInsert.add(new VarInsnNode(ASTORE, 2));
						toInsert.add(new FieldInsnNode(GETSTATIC, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/ForgePacketManagement", "INSTANCE", "Lnet/azurewebsites/thehen101/coremod/forgepacketmanagement/ForgePacketManagement;"));
						toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/ForgePacketManagement", "getPacketQueueManager", "()Lnet/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueuedManager;", false));
						toInsert.add(new VarInsnNode(ALOAD, 2));
						toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueuedManager", "callEvent", "(Lnet/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued;)V", false));
						toInsert.add(new VarInsnNode(ALOAD, 2));
						toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued", "getCancelled", "()Z", false));
						LabelNode l3 = new LabelNode();
						toInsert.add(new JumpInsnNode(IFNE, l3));
						toInsert.add(new VarInsnNode(ALOAD, 0));
						toInsert.add(new FieldInsnNode(GETFIELD, "net/minecraft/client/network/NetHandlerPlayClient", NETMANAGER, "Lnet/minecraft/network/NetworkManager;"));
						toInsert.add(new VarInsnNode(ALOAD, 2));
						toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued", "getPacket", "()Lnet/minecraft/network/Packet;", false));
						toInsert.add(new MethodInsnNode(INVOKEVIRTUAL, "net/minecraft/network/NetworkManager", SENDPACKET, "(Lnet/minecraft/network/Packet;)V", false));
						toInsert.add(l3);
						toInsert.add(new FrameNode(F_APPEND,1, new Object[] {"net/azurewebsites/thehen101/coremod/forgepacketmanagement/event/EventPacketQueued"}, 0, null));
						
						method.instructions.insertBefore(targetNode, toInsert);
						
						for (int i = 0; i < 4; i++) {
	                        targetNode = targetNode.getNext();
	                        method.instructions.remove(targetNode.getPrevious());
						}
					}
				}
			}
		}
	}
}