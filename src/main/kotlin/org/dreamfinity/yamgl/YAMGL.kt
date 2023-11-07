package org.dreamfinity.yamgl

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager
import org.dreamfinity.yamgl.proxy.CommonProxy

@Mod(modid = YAMGL.MODID, name = YAMGL.NAME, version = YAMGL.VERSION)
class YAMGL {
    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit(event)
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init(event)
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit(event)
    }

    companion object {
        const val MODID = "yamgl"
        const val NAME = "YetAnotherMinecraftGuiLibrary"
        const val VERSION = "@version@"
        var logger = LogManager.getLogger(MODID)

        @Mod.Instance(MODID)
        lateinit var instance: YAMGL

        @SidedProxy(clientSide = "org.dreamfinity.yamgl.proxy.ClientProxy", serverSide = "org.dreamfinity.yamgl.proxy.CommonProxy")
        lateinit var proxy: CommonProxy
    }
}