package io.github.dediamondpro.autofl;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = autoFL.MODID, version = autoFL.VERSION)
public class autoFL
{
    public static final String MODID = "autoFL";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new Events());
    }
}

class Events {
    public boolean send = false;

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if(!send){
            if(isHypixel()){
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/fl");
                send = true;
            }
        }
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
