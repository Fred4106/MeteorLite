package meteor;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.owain.chinLogin.ChinLoginPlugin;
import com.owain.chinmanager.ChinManagerPlugin;
import java.util.ArrayList;
import java.util.List;

import com.questhelper.QuestHelperPlugin;
import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigManager;
import meteor.eventbus.EventBus;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDependency;
import meteor.plugins.achievementdiary.DiaryRequirementsPlugin;
import meteor.plugins.agility.AgilityPlugin;
import meteor.plugins.aoewarnings.AoeWarningPlugin;
import meteor.plugins.autorun.AutoRun;
import meteor.plugins.bank.BankPlugin;
import meteor.plugins.banktags.BankTagsPlugin;
import meteor.plugins.betterantidrag.BetterAntiDragPlugin;
import meteor.plugins.betterroguesden.BetterRougesDenPlugin;
import meteor.plugins.blastfurnace.BlastFurnacePlugin;
import meteor.plugins.boosts.BoostsPlugin;
import meteor.plugins.camera.CameraPlugin;
import meteor.plugins.cannon.CannonPlugin;
import meteor.plugins.cannonreloader.CannonReloaderPlugin;
import meteor.plugins.chatcommands.ChatCommandsPlugin;
import meteor.plugins.cluescrolls.ClueScrollPlugin;
import meteor.plugins.combatlevel.CombatLevelPlugin;
import meteor.plugins.defaultworld.DefaultWorldPlugin;
import meteor.plugins.devtools.DevToolsPlugin;
import meteor.plugins.discord.DiscordPlugin;
import meteor.plugins.environmentaid.EnvironmentAidPlugin;
import meteor.plugins.fairyring.FairyRingPlugin;
import meteor.plugins.fishing.FishingPlugin;
import meteor.plugins.fps.FpsPlugin;
import meteor.plugins.gpu.GpuPlugin;
import meteor.plugins.grounditems.GroundItemsPlugin;
import meteor.plugins.groundmarkers.GroundMarkerPlugin;
import meteor.plugins.herbiboars.HerbiboarPlugin;
import meteor.plugins.hunter.HunterPlugin;
import meteor.plugins.implings.ImplingsPlugin;
import meteor.plugins.interacthighlight.InteractHighlightPlugin;
import meteor.plugins.inventorygrid.InventoryGridPlugin;
import meteor.plugins.itemcharges.ItemChargePlugin;
import meteor.plugins.itemidentification.ItemIdentificationPlugin;
import meteor.plugins.itemprices.ItemPricesPlugin;
import meteor.plugins.itemstats.ItemStatPlugin;
import meteor.plugins.kourendlibrary.KourendLibraryPlugin;
import meteor.plugins.leftclickcast.LeftClickCastPlugin;
import meteor.plugins.lowdetail.LowDetailPlugin;
import meteor.plugins.menuentryswapper.MenuEntrySwapperPlugin;
import meteor.plugins.menuentryswapperextended.MenuEntrySwapperExtendedPlugin;
import meteor.plugins.minimap.MinimapPlugin;
import meteor.plugins.mining.MiningPlugin;
import meteor.plugins.motherlode.MotherlodePlugin;
import meteor.plugins.mousetooltips.MouseTooltipPlugin;
import meteor.plugins.mta.MTAPlugin;
import meteor.plugins.neverlog.NeverLogoutPlugin;
import meteor.plugins.npcindicators.NpcIndicatorsPlugin;
import meteor.plugins.npcstatus.NpcStatusPlugin;
import meteor.plugins.objectindicators.ObjectIndicatorsPlugin;
import meteor.plugins.oneclick.OneClickPlugin;
import meteor.plugins.oneclickagility.OneClickAgilityPlugin;
import meteor.plugins.oneclickdropper.OneClickDropperPlugin;
import meteor.plugins.oneclickthieving.OneClickThievingPlugin;
import meteor.plugins.playerattacktimer.PlayerAttackTimerPlugin;
import meteor.plugins.playerindicators.PlayerIndicatorsPlugin;
import meteor.plugins.poh.PohPlugin;
import meteor.plugins.prayerpotdrinker.PrayerPotDrinkerPlugin;
import meteor.plugins.puzzlesolver.PuzzleSolverPlugin;
import meteor.plugins.questlist.QuestListPlugin;
import meteor.plugins.randomevents.RandomEventPlugin;
import meteor.plugins.regenmeter.RegenMeterPlugin;
import meteor.plugins.reportbutton.ReportButtonPlugin;
import meteor.plugins.runecraft.RunecraftPlugin;
import meteor.plugins.runenergy.RunEnergyPlugin;
import meteor.plugins.runepouch.RunepouchPlugin;
import meteor.plugins.slayer.SlayerPlugin;
import meteor.plugins.smithing.SmithingPlugin;
import meteor.plugins.statusbars.StatusBarsPlugin;
import meteor.plugins.stretchedmode.StretchedModePlugin;
import meteor.plugins.tearsofguthix.TearsOfGuthixPlugin;
import meteor.plugins.ticktimers.TickTimersPlugin;
import meteor.plugins.tileindicators.TileIndicatorsPlugin;
import meteor.plugins.timers.TimersPlugin;
import meteor.plugins.timestamp.ChatTimestampPlugin;
import meteor.plugins.tithefarm.TitheFarmPlugin;
import meteor.plugins.void3tFishing.Void3tFishingPlugin;
import meteor.plugins.void3tteaks.Void3tTeaksPlugin;
import meteor.plugins.voidHunter.VoidHunterPlugin;
import meteor.plugins.voidagility.VoidTemporossPlugin;
import meteor.plugins.woodcutting.WoodcuttingPlugin;
import meteor.plugins.worldmap.WorldMapPlugin;
import meteor.plugins.xpglobes.XpGlobesPlugin;
import meteor.plugins.xptracker.XpTrackerPlugin;
import meteor.plugins.zulrah.ZulrahPlugin;

public class PluginManager {

  @Inject
  private EventBus eventBus;

  @Inject
  private ConfigManager configManager;

  @Inject
  private MeteorLiteClientModule meteorLiteClientModule;

  PluginManager() {
  }

  public static List<Plugin> plugins = new ArrayList<>();

  private void initPlugins() {
    plugins.add(new AgilityPlugin());
    plugins.add(new AoeWarningPlugin());
    //plugins.add(new AutoClickerPlugin());
    //plugins.add(new AutoLogHop());
    plugins.add(new AutoRun());
    plugins.add(new BankPlugin());
    plugins.add(new BankTagsPlugin());
    plugins.add(new BetterAntiDragPlugin());
    plugins.add(new BetterRougesDenPlugin());
    plugins.add(new BlastFurnacePlugin());
    plugins.add(new BoostsPlugin());
    plugins.add(new ChinManagerPlugin());
    plugins.add(new ChinLoginPlugin());
    plugins.add(new TickTimersPlugin());
    plugins.add(new CameraPlugin());
    plugins.add(new CannonPlugin());
    plugins.add(new CannonReloaderPlugin());
    plugins.add(new ChatCommandsPlugin());
    plugins.add(new ChatTimestampPlugin());
    plugins.add(new ClueScrollPlugin());
    plugins.add(new CombatLevelPlugin());
    plugins.add(new DefaultWorldPlugin());
    plugins.add(new DevToolsPlugin());
    plugins.add(new DiaryRequirementsPlugin());
    plugins.add(new DiscordPlugin());
    plugins.add(new EnvironmentAidPlugin());
    plugins.add(new FairyRingPlugin());
    plugins.add(new FishingPlugin());
    plugins.add(new FpsPlugin());
    plugins.add(new GroundItemsPlugin());
    plugins.add(new GroundMarkerPlugin());
    plugins.add(new HerbiboarPlugin());
    plugins.add(new HunterPlugin());
    plugins.add(new ImplingsPlugin());
    plugins.add(new InteractHighlightPlugin());
    plugins.add(new InventoryGridPlugin());
    plugins.add(new ItemChargePlugin());
    plugins.add(new ItemPricesPlugin());
    plugins.add(new ItemStatPlugin());
    plugins.add(new ItemIdentificationPlugin());
    plugins.add(new KourendLibraryPlugin());
    plugins.add(new LeftClickCastPlugin());
    plugins.add(new LowDetailPlugin());
    plugins.add(new MenuEntrySwapperPlugin());
    plugins.add(new MenuEntrySwapperExtendedPlugin());
    plugins.add(new MinimapPlugin());
    plugins.add(new MiningPlugin());
    plugins.add(new MotherlodePlugin());
    plugins.add(new MouseTooltipPlugin());
    plugins.add(new MTAPlugin());
    plugins.add(new NeverLogoutPlugin());
    plugins.add(new NpcIndicatorsPlugin());
    plugins.add(new NpcStatusPlugin());
    plugins.add(new ObjectIndicatorsPlugin());
    plugins.add(new OneClickPlugin());
    plugins.add(new OneClickAgilityPlugin());
    plugins.add(new OneClickDropperPlugin());
    plugins.add(new OneClickThievingPlugin());
    plugins.add(new PlayerAttackTimerPlugin());
    plugins.add(new PlayerIndicatorsPlugin());
    plugins.add(new PohPlugin());
    plugins.add(new PrayerPotDrinkerPlugin());
    plugins.add(new PuzzleSolverPlugin());
    plugins.add(new QuestListPlugin());
    plugins.add(new QuestHelperPlugin());
    plugins.add(new RandomEventPlugin());
    plugins.add(new RegenMeterPlugin());
    plugins.add(new ReportButtonPlugin());
    plugins.add(new RunEnergyPlugin());
    plugins.add(new RunepouchPlugin());
    plugins.add(new RunecraftPlugin());
    plugins.add(new SlayerPlugin());
    plugins.add(new SmithingPlugin());
    plugins.add(new StatusBarsPlugin());
    plugins.add(new StretchedModePlugin());
    plugins.add(new TileIndicatorsPlugin());
    plugins.add(new TimersPlugin());
    plugins.add(new TitheFarmPlugin());
    plugins.add(new TearsOfGuthixPlugin());
    plugins.add(new Void3tFishingPlugin());
    plugins.add(new Void3tTeaksPlugin());
    plugins.add(new VoidHunterPlugin());
    plugins.add(new VoidTemporossPlugin());
    plugins.add(new WoodcuttingPlugin());
    plugins.add(new WorldMapPlugin());
    plugins.add(new XpTrackerPlugin());
    plugins.add(new XpGlobesPlugin());
    plugins.add(new ZulrahPlugin());
    plugins.add(new GpuPlugin());
  }

  public void startInternalPlugins() {
    initPlugins();
    for (Plugin plugin : plugins) {
      Injector parent = meteorLiteClientModule.instanceInjector;
      List<Module> depModules = new ArrayList<>();
      if (plugin.getClass().getAnnotation(PluginDependency.class) != null) {
        Class<? extends Plugin> depClass = plugin.getClass().getAnnotation(PluginDependency.class).value();
        Module depModule = (Binder binder) ->
        {
          try {
            Plugin depInstance = depClass.getDeclaredConstructor().newInstance();
            binder.bind((Class<Plugin>) depInstance.getClass()).toInstance(depInstance);
            binder.install(depInstance);
          } catch (Exception e) {
            e.printStackTrace();
          }
        };
        depModules.add(depModule);
        parent = parent.createChildInjector(depModules);
      }

      Module pluginModule = (Binder binder) ->
      {
        // Since the plugin itself is a module, it won't bind itself, so we'll bind it here
        binder.bind((Class<Plugin>) plugin.getClass()).toInstance(plugin);
        binder.install(plugin);
      };
      Injector pluginInjector = parent.createChildInjector(pluginModule);
      pluginInjector.injectMembers(plugin);
      plugin.setInjector(pluginInjector);
      Config finalConfig = null;
      for (Key<?> key : plugin.getInjector().getBindings().keySet())
      {
        Class<?> type = key.getTypeLiteral().getRawType();
        if (Config.class.isAssignableFrom(type))
        {
          Config config = (Config) plugin.getInjector().getInstance(key);
          finalConfig = config;
          configManager.setDefaultConfiguration(plugin, config, false);
        }
      }
      if (finalConfig != null)
      if (Boolean.parseBoolean(configManager.getConfiguration(finalConfig.getClass().getInterfaces()[0].getAnnotation(ConfigGroup.class).value(), "pluginEnabled")))
        plugin.toggle();
    }
  }

  public static<T extends Plugin> T getInstance(Class<? extends Plugin> type)
  {
    for (Plugin p : PluginManager.plugins)
    {
      if (type.isInstance(p))
      {
        return (T) p;
      }
    }
    return null;
  }


}
