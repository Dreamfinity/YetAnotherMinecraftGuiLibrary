package org.dreamfinity.yamgl.proxy

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer

class ClientProxy : CommonProxy() {
    override fun preInit(event: FMLPreInitializationEvent) {}
    override fun init(event: FMLInitializationEvent) {}
    override fun postInit(event: FMLPostInitializationEvent) {}
}