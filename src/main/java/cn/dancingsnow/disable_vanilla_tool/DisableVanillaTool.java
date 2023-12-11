package cn.dancingsnow.disable_vanilla_tool;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(DisableVanillaTool.MODID)
public class DisableVanillaTool {

    public static final String MODID = "disable_vanilla_tool";
    private static final Logger LOGGER = LogUtils.getLogger();

    public DisableVanillaTool() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onPlayerLeftBlock(PlayerInteractEvent event) {
        if (event.isCancelable())
            event.setCanceled(Config.tool_list.contains(event.getItemStack().getItem()));
    }

    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent event) {
        event.setCanceled(Config.tool_list.contains(event.getEntity().getMainHandItem().getItem()));
    }

}
