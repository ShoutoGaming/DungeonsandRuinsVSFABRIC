package net.kaupenjoe.tutorialmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<TigerEntity> TIGER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "tiger"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TigerEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());

    public static final EntityType<GreatHunterEntity> GREAT_HUNTER = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "great_hunter"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GreatHunterEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());
    public static final EntityType<LivingTableEntity> LIVINGTABLE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "livingtable"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, LivingTableEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());
    public static final EntityType<WraithEntity> WRAITH = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "wraith"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WraithEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());

    public static final EntityType<Royal_GuardEntity> ROYAL_GUARD = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "royal_guard"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Royal_GuardEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f, 1.75f)).build());


}
