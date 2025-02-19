package net.tslat.aoa3.content.loottable.modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nonnull;

public class HavenLootModifier extends LootModifier {
	public static final Codec<HavenLootModifier> CODEC = RecordCodecBuilder.create(builder -> codecStart(builder).apply(builder, HavenLootModifier::new));
	public static final ResourceLocation HAVEN_LOOT_TABLE = new ResourceLocation(AdventOfAscension.MOD_ID, "worlds/haven_passive");

	public HavenLootModifier(LootItemCondition[] conditions) {
		super(conditions);
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (WorldUtil.isWorld((ServerLevelAccessor)context.getLevel(), AoADimensions.HAVEN.key) && context.hasParam(LootContextParams.THIS_ENTITY) && context.hasParam(LootContextParams.ORIGIN) && !context.hasParam(LootContextParams.KILLER_ENTITY) && !context.hasParam(LootContextParams.DIRECT_KILLER_ENTITY)) {
			if (!context.hasParam(LootContextParams.BLOCK_STATE) || context.getRandom().nextInt(10) == 0) {
				LootContext newContext = new LootContext.Builder(context.getLevel())
						.withParameter(LootContextParams.THIS_ENTITY, context.getParamOrNull(LootContextParams.THIS_ENTITY))
						.withParameter(LootContextParams.ORIGIN, context.getParamOrNull(LootContextParams.ORIGIN))
						.create(LootContextParamSets.GIFT);

				context.getLootTable(HAVEN_LOOT_TABLE).getRandomItems(newContext, generatedLoot::add);
			}
		}

		return generatedLoot;
	}
}
