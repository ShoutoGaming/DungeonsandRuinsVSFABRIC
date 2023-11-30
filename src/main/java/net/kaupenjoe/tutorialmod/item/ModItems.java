package net.kaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.kaupenjoe.tutorialmod.item.custom.AmethystArmorItem;
import net.kaupenjoe.tutorialmod.item.custom.AnimatedBlockItem;
import net.kaupenjoe.tutorialmod.item.custom.AnimatedItem;
import net.kaupenjoe.tutorialmod.item.custom.ToxicArmorItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CITRINE = registerItem("citrine",
            new Item(new FabricItemSettings()));
    public static final Item RAW_CITRINE = registerItem("raw_citrine",
            new Item(new FabricItemSettings()));

    public static final Item TIGER_SPAWN_EGG = registerItem("tiger_spawn_egg",
            new SpawnEggItem(ModEntities.TIGER, 0xD57E36, 0x1D0D00,
                    new FabricItemSettings()));

    public static final Item ROYAL_GUARD_SPAWN_EGG = registerItem("royal_guard_spawn_egg",
            new SpawnEggItem(ModEntities.ROYAL_GUARD, 999966, 0x1D0D00,
                    new FabricItemSettings()));
    public static final Item GREAT_HUNTER_SPAWN_EGG = registerItem("great_hunter_spawn_egg",
            new SpawnEggItem(ModEntities.GREAT_HUNTER, 909183, 0x1D0D00,
                    new FabricItemSettings()));

    public static final Item LIVINGTABLE_SPAWN_EGG = registerItem("livingtable_spawn_egg",
            new SpawnEggItem(ModEntities.LIVINGTABLE, 713434, 0x1D0D00,
                    new FabricItemSettings()));

    public static final Item WRAITH_SPAWN_EGG = registerItem("wraith_spawn_egg",
            new SpawnEggItem(ModEntities.WRAITH, 799997, 0x1D0D00,
                    new FabricItemSettings()));

    public static final Item ANIMATED_ITEM = registerItem("animated_item",
            new AnimatedItem(new FabricItemSettings()));

    public static final Item ANIMATED_BLOCK_ITEM = registerItem("animated_block",
            new AnimatedBlockItem(ModBlocks.ANIMATED_BLOCK, new FabricItemSettings()));
       public static final Item AMETHYST_HELMET = registerItem("amethyst_helmet",
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item AMETHYST_CHESTPLATE = registerItem("amethyst_chestplate",
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item AMETHYST_LEGGINGS = registerItem("amethyst_leggings",
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item AMETHYST_BOOTS = registerItem("amethyst_boots",
            new AmethystArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item TOXIC_HELMET = registerItem("toxic_helmet",
            new ToxicArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item TOXIC_CHESTPLATE = registerItem("toxic_chestplate",
            new ToxicArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item TOXIC_LEGGINGS = registerItem("toxic_leggings",
            new ToxicArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item TOXIC_BOOTS = registerItem("toxic_boots",
            new ToxicArmorItem(ModArmorMaterials.AMETHYST, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ItemGroups.INGREDIENTS, CITRINE);
        addToItemGroup(ItemGroups.INGREDIENTS, RAW_CITRINE);

        addToItemGroup(ModItemGroup.CITRINE, CITRINE);
        addToItemGroup(ModItemGroup.CITRINE, RAW_CITRINE);
        addToItemGroup(ModItemGroup.CITRINE, TIGER_SPAWN_EGG);
        addToItemGroup(ModItemGroup.CITRINE, ROYAL_GUARD_SPAWN_EGG);
        addToItemGroup(ModItemGroup.CITRINE, GREAT_HUNTER_SPAWN_EGG);
        addToItemGroup(ModItemGroup.CITRINE, LIVINGTABLE_SPAWN_EGG);
        addToItemGroup(ModItemGroup.CITRINE, WRAITH_SPAWN_EGG);
        addToItemGroup(ModItemGroup.CITRINE, ANIMATED_ITEM);
        addToItemGroup(ModItemGroup.CITRINE, ANIMATED_BLOCK_ITEM);

        addToItemGroup(ModItemGroup.CITRINE, AMETHYST_BOOTS);
        addToItemGroup(ModItemGroup.CITRINE, AMETHYST_LEGGINGS);
        addToItemGroup(ModItemGroup.CITRINE, AMETHYST_CHESTPLATE);
        addToItemGroup(ModItemGroup.CITRINE, AMETHYST_HELMET);
        addToItemGroup(ModItemGroup.CITRINE, TOXIC_BOOTS);
        addToItemGroup(ModItemGroup.CITRINE, TOXIC_LEGGINGS);
        addToItemGroup(ModItemGroup.CITRINE, TOXIC_CHESTPLATE);
        addToItemGroup(ModItemGroup.CITRINE, TOXIC_HELMET);
    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        addItemsToItemGroup();
    }
}
