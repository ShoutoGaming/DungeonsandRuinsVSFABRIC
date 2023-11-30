package net.kaupenjoe.tutorialmod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.server.command.CommandManager.literal;

public class DropBlockCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("dropblock")
                        .then(CommandManager.argument("target", EntityArgumentType.entity())
                                .executes(context -> dropBlock(context.getSource(), EntityArgumentType.getEntity(context, "target"))))
        );
    }

    private static int dropBlock(ServerCommandSource source, Entity targetEntity) {
        World world = source.getWorld();

        // Utilizar tu bloque personalizado LOOTCHEST
        Block block = ModBlocks.LOOTCHEST;

        // Obtener la posición del jugador
        BlockPos playerPos = targetEntity.getBlockPos();

        // Ajustar la posición para que esté 10 bloques por encima del jugador
        BlockPos targetPos = playerPos.up(20);

        // Crear un FallingBlockEntity con el bloque que quieres soltar
        FallingBlockEntity fallingBlock = FallingBlockEntity.spawnFromBlock(world, targetPos.up(), block.getDefaultState());
        world.spawnEntity(fallingBlock);

        // Marcar el éxito del comando
        return 1;
    }
}
