package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.util.BlockUtil;

import java.util.function.Supplier;

public class CropBlock extends net.minecraft.world.level.block.CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[] {
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D)),
			Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D))};
	private final Supplier<Item> seedItem;

	public CropBlock(MaterialColor colour, Supplier<Item> seedItem) {
		super(new BlockUtil.CompactProperties(Material.PLANT, colour).sound(SoundType.CROP).noClip().randomTicks().get());

		this.seedItem = seedItem;
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return seedItem.get();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
