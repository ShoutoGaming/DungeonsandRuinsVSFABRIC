package net.kaupenjoe.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ValvuleBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public ValvuleBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE_N= Stream.of(
            Block.createCuboidShape(-1, 9.5, 7, 1, 13.5, 9),
            Block.createCuboidShape(-1.03171, 14.9597, 7.6, -0.23171, 15.7097, 8.4),
            Block.createCuboidShape(-1.28171, 12.9597, 7.35, 0.01829, 14.9597, 8.65),
            Block.createCuboidShape(15, 9.5, 7, 17, 13.5, 9),
            Block.createCuboidShape(15.96829, 12.9597, 7.35, 17.26829, 14.9597, 8.65),
            Block.createCuboidShape(16.21829, 14.9597, 7.6, 17.01829, 15.7097, 8.4),
            Block.createCuboidShape(1.25, 5, 1.725, 1.75, 9, 2.225),
            Block.createCuboidShape(1.75, 5, 1.725, 4.25, 5.5, 2.225),
            Block.createCuboidShape(4.25, 5, 1.725, 4.75, 9, 2.225),
            Block.createCuboidShape(1.75, 8.5, 1.725, 4.25, 9, 2.225),
            Block.createCuboidShape(2.75, 6.75, 1.475, 3.25, 8.75, 2.225),
            Block.createCuboidShape(11.25, 5, 1.725, 11.75, 9, 2.225),
            Block.createCuboidShape(11.75, 5, 1.725, 14.25, 5.5, 2.225),
            Block.createCuboidShape(14.25, 5, 1.725, 14.75, 9, 2.225),
            Block.createCuboidShape(11.75, 8.5, 1.725, 14.25, 9, 2.225),
            Block.createCuboidShape(12.75, 6.75, 1.475, 13.25, 8.75, 2.225),
            Block.createCuboidShape(2, 6, 1.8, 4, 9.75, 2.5),
            Block.createCuboidShape(2, 17.98462, 4.04655, 4, 18.60962, 8.29655),
            Block.createCuboidShape(2, 12.21694, 4.76902, 4, 12.76694, 12.09402),
            Block.createCuboidShape(2, 0, 1.65, 4, 6, 2.025),
            Block.createCuboidShape(2, 5.975, 1.75, 4, 6.475, 2.4),
            Block.createCuboidShape(1.75, 0, 1.475, 4.25, 1.4, 2.525),
            Block.createCuboidShape(1.75, 0, 14.7, 4.25, 1.375, 15.05),
            Block.createCuboidShape(2, 0, 14.7, 4, 9.9, 15.025),
            Block.createCuboidShape(2, 17.99623, 8.63367, 4, 18.37123, 12.73367),
            Block.createCuboidShape(11.75, 0, 14.7, 14.25, 1.375, 15.25),
            Block.createCuboidShape(12, 0, 14.7, 14, 9.9, 15.025),
            Block.createCuboidShape(12, 17.99623, 8.63367, 14, 18.37123, 12.73367),
            Block.createCuboidShape(12, 12.21694, 4.76902, 14, 12.76694, 12.09402),
            Block.createCuboidShape(12, 17.98462, 4.04655, 14, 18.60962, 8.29655),
            Block.createCuboidShape(12, 6, 1.8, 14, 9.75, 2.5),
            Block.createCuboidShape(12, 5.975, 1.75, 14, 6.475, 2.4),
            Block.createCuboidShape(12, 0, 1.65, 14, 6, 2.025),
            Block.createCuboidShape(11.75, 0, 1.475, 14.25, 1.4, 2.45),
            Block.createCuboidShape(-0.8, 0, 2.3, 16.7, 2, 14.7),
            Block.createCuboidShape(-0.8, 4, 2.3, 16.7, 6, 14.7),
            Block.createCuboidShape(-0.3, 7, 2.8, 16.2, 7.5, 14.2),
            Block.createCuboidShape(-0.8, 8, 2.3, 16.7, 10, 14.7),
            Block.createCuboidShape(-0.02, 9.43, 4.88, 16.07, 12.25, 12.035),
            Block.createCuboidShape(0.0035, 8.863, 11.5135, 16.0465, 12.3085, 13.2525),
            Block.createCuboidShape(0.0035, 9.6035, 2.8285, 16.0465, 11.3425, 6.1315),
            Block.createCuboidShape(0, 6, 3, 16, 9, 14),
            Block.createCuboidShape(0, 2, 3, 16, 6, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_N;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx){

       return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation){

        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {

        builder.add(FACING);
    }

    //FIN DEL CODIGO
}
