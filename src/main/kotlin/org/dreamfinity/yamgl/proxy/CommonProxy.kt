package org.dreamfinity.yamgl.proxy

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import net.minecraft.entity.player.EntityPlayer

open class CommonProxy {
    open fun preInit(event: FMLPreInitializationEvent) {}
    open fun init(event: FMLInitializationEvent) {}
    open fun postInit(event: FMLPostInitializationEvent) {}
}