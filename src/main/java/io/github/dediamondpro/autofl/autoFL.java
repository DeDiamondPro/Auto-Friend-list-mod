package io.github.dediamondpro.autofl;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

@Mod(modid = autoFL.MODID, version = autoFL.VERSION)
public class autoFL
{
    public static final String MODID = "autofl";
    public static final String VERSION = "1.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new Events());
    }
}

class Events {
    public boolean send = false;
    long ticks = 0;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        ticks++;
        if(!send && ticks % 20 == 0){
            if(isHypixel()){
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/fl");
                send = true;
            }
        }
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event){
        send = false;
    }

    public boolean isHypixel() {
        Minecraft mc = Minecraft.getMinecraft();

        if (mc != null && mc.theWorld != null && mc.thePlayer != null) {
            if (mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel")) {
                return true;
            }
        }
        return false;
    }
}
