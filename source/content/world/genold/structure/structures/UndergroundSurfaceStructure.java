/*
package net.tslat.aoa3.content.world.gen.structure.structures;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.tslat.aoa3.content.world.gen.feature.features.config.IntRangeConfig;

public class UndergroundSurfaceStructure extends AoAStructureBase<IntRangeConfig> {
	public UndergroundSurfaceStructure(GenerationStep.Decoration decorationStage, String templatePoolPath) {
		super(IntRangeConfig.CODEC, decorationStage, templatePoolPath);
	}

	@Override
	protected AoAStartFactory<IntRangeConfig> getStructureStart() {
		return (structure, chunkX, chunkZ, boundingBox, references, seed) -> new AoAStructureStart<IntRangeConfig>(structure, chunkX, chunkZ, boundingBox, references, seed) {
			@Override
			protected boolean shouldGenerateOnWorldSurface() {
				return false;
			}

			@Override
			protected boolean checkAndAdjustGeneration(ChunkGenerator chunkGenerator, BlockPos.MutableBlockPos chunkCenter, Biome biome, IntRangeConfig config) {
				BlockGetter blockReader = chunkGenerator.getBaseColumn(chunkCenter.getX(), chunkCenter.getZ());

				chunkCenter.setY(config.getValue(random));

				if (blockReader.getBlockState(chunkCenter).canBeReplaced()) {
					while (chunkCenter.getY() > 0 && blockReader.getBlockState(chunkCenter.move(Direction.DOWN)).canBeReplaced()) {
						;
					}

					chunkCenter.move(Direction.UP);

					return chunkCenter.getY() > 0;
				}

				return false;
			}
		};
	}
}
*/
