package net.kaupenjoe.tutorialmod;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.ModFlammableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.kaupenjoe.tutorialmod.entity.custom.GreatHunterEntity;
import net.kaupenjoe.tutorialmod.entity.custom.LivingTableEntity;
import net.kaupenjoe.tutorialmod.entity.custom.Royal_GuardEntity;
import net.kaupenjoe.tutorialmod.entity.custom.TigerEntity;
import net.kaupenjoe.tutorialmod.item.ModItemGroup;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.world.gen.ModWorldGeneration;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

// Very important comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger("tutorialmod");

	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModWorldGeneration.generateModWorldGen();

		ModBlockEntities.registerAllBlockEntities();

		GeckoLib.initialize();

		ModFlammableBlockRegistry.registerFlammableBlocks();
		StrippableBlockRegistry.register(ModBlocks.RED_MAPLE_LOG, ModBlocks.STRIPPED_RED_MAPLE_LOG);
		StrippableBlockRegistry.register(ModBlocks.RED_MAPLE_WOOD, ModBlocks.STRIPPED_RED_MAPLE_WOOD);

		FabricDefaultAttributeRegistry.register(ModEntities.TIGER, TigerEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ROYAL_GUARD, Royal_GuardEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GREAT_HUNTER, GreatHunterEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.LIVINGTABLE, LivingTableEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WRAITH, LivingTableEntity.setAttributes());

		// Registrar el comando dropblock
		CommandRegistrationCallback.EVENT.register(new CommandRegistrationCallback() {
			@Override
			public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
				DropBlockCommand.register(dispatcher);
			}
		});

	}

}