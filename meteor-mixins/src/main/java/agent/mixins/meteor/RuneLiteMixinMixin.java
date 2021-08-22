package agent.mixins.meteor;

import meteor.MeteorLiteClientLauncher;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.rs.api.RSClient;
import net.runelite.rs.api.RSNPC;
import org.sponge.util.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("unused")
@Mixin(targets = "Client", remap = false)
public abstract class RuneLiteMixinMixin implements RSClient {

  @Shadow
  private static RSClient clientInstance;

  @Shadow
  private static int viewportWidth;

  @Shadow
  private static int viewportHeight;

  private static Client client;

  private static final Logger spongeLogger = new Logger("Agent");

  //This is a test hook and also serves to init MeteorLite.client for other SpongeMixins (cant shadow across classes)
  @Inject(method = "onGameStateChanged", at = @At("RETURN"), require = 1)
  private static void onOnGameStateChanged(int gamestate, CallbackInfo callbackInfo) {
    if (client == null) {
      client = MeteorLiteClientLauncher.clientInstance = clientInstance;
    }
    spongeLogger.info("gamestate {}, viewport ({}, {})", client.getGameState(), viewportWidth, viewportHeight);
  }

//  //This is a test hook and also serves to init MeteorLite.client for other SpongeMixins (cant shadow across classes)
//  @Inject(method = "setHintArrow", at = @At("HEAD"), require = 1)
//  private void onSetHintArrow(RSNPC npc, CallbackInfo callbackInfo) {
//    if(npc != null && npc.getConvexHull() != null && npc.getConvexHull().getBounds2D() != null) {
//      Shape hull = npc.getConvexHull();
//      Rectangle2D rect = hull.getBounds2D();
//      spongeLogger.info("setHintArrow: bounds({})", rect);
//    } else {
//      spongeLogger.info("setHintArrow: npc({})", npc);
//    }
//  }

}
