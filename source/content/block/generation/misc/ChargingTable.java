package net.tslat.aoa3.content.block.generation.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.content.block.WaterloggableBlock;

public class ChargingTable extends WaterloggableBlock {
	private static final VoxelShape BOTTOM_SHAPE = box(0, 0, 0, 16, 2, 16);
	private static final VoxelShape MID_SHAPE = box(3, 2, 3, 13, 10, 13);
	private static final VoxelShape TOP_SHAPE = box(5, 10, 5, 11, 16, 11);
	private static final VoxelShape SHAPE = Shapes.or(BOTTOM_SHAPE, MID_SHAPE, TOP_SHAPE);

	public ChargingTable(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}
}
