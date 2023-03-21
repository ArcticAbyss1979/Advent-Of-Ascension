package net.tslat.aoa3.common.registration.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.content.entity.base.AbstractLavaFishEntity;
import net.tslat.aoa3.content.entity.mob.nether.NethengeicBeastEntity;
import net.tslat.aoa3.util.WorldUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

import static net.minecraft.world.entity.SpawnPlacements.Type.*;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING;
import static net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;

public final class AoAEntitySpawnPlacements {
    private static final SpawnPlacements.Type AMPHIBIOUS = SpawnPlacements.Type.create("AMPHIBIOUS", ((level, pos, entityType) -> NaturalSpawner.canSpawnAtBody((level.getFluidState(pos).isEmpty() ? ON_GROUND : IN_WATER), level, pos, entityType)));

    public static void lateInit() {
        Logging.logStatusMessage("Setting entity spawn placements");

        setOverworldSpawnPlacements();
    }

    public static void setOverworldSpawnPlacements() {
        setSpawnPlacement(AoAMobs.ANCIENT_GOLEM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 65, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.BOMB_CARRIER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 55, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.BUSH_BABY.get(), ON_GROUND, MOTION_BLOCKING, SpawnPredicates.monsterPredicate(true, 65, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.CHARGER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.CHOMPER.get(), AMPHIBIOUS, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.CYCLOPS.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 55, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.EMBRAKE.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, rand) -> level.getDifficulty() != Difficulty.PEACEFUL && !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && Mob.checkMobSpawnRules(entityType, level, spawnType, pos, rand));
        setSpawnPlacement(AoAMobs.FLAMEWALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, rand) -> level.getDifficulty() != Difficulty.PEACEFUL && !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && Mob.checkMobSpawnRules(entityType, level, spawnType, pos, rand));
        setSpawnPlacement(AoAMobs.GHOST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(false, Integer.MIN_VALUE, 0));
        setSpawnPlacement(AoAMobs.GOBLIN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.ICE_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.INFERNAL.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, rand) -> level.getDifficulty() != Difficulty.PEACEFUL && !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && NethengeicBeastEntity.checkSpawnConditions(level, spawnType, pos, rand));
        setSpawnPlacement(AoAMobs.KING_CHARGER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.LEAFY_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.LITTLE_BAM.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, rand) -> level.getDifficulty() != Difficulty.PEACEFUL && !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && Mob.checkMobSpawnRules(entityType, level, spawnType, pos, rand));
        setSpawnPlacement(AoAMobs.NETHENGEIC_BEAST.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, (entityType, level, spawnType, pos, rand) -> level.getDifficulty() != Difficulty.PEACEFUL && !level.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK) && NethengeicBeastEntity.checkSpawnConditions(level, spawnType, pos, rand));
        setSpawnPlacement(AoAMobs.SAND_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.SASQUATCH.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 55, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.STONE_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.TREE_SPIRIT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 55, Integer.MAX_VALUE));
        setSpawnPlacement(AoAMobs.VOID_WALKER.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(false, Integer.MIN_VALUE, 0));
        setSpawnPlacement(AoAMobs.WOOD_GIANT.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.DEFAULT_DAY_MONSTER);
        setSpawnPlacement(AoAMobs.YETI.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.monsterPredicate(true, 45, Integer.MAX_VALUE));

        setSpawnPlacement(AoAAnimals.SHINY_SQUID.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, GlowSquid::checkGlowSquideSpawnRules);

        setSpawnPlacement(AoANpcs.LOTTOMAN.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true));
        setSpawnPlacement(AoANpcs.UNDEAD_HERALD.get(), ON_GROUND, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.npcPredicate(true));

        setSpawnPlacement(AoAAnimals.BLUE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.CANDLEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CHARRED_CHAR.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CHOCAW.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.CRIMSON_SKIPPER.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.CRIMSON_STRIPEFISH.get(), IN_LAVA, MOTION_BLOCKING_NO_LEAVES, AbstractLavaFishEntity::checkFishSpawnRules);
        setSpawnPlacement(AoAAnimals.DARK_HATCHETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.GREEN_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.HYDRONE.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.IRONBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.JAMFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PARAPIRANHA.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PEARL_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.PURPLE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RAINBOWFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RAZORFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.RED_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.REEFTOOTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.ROCKETFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SAILBACK.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SAPPHIRE_STRIDER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.SKELECANTH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.WHITE_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.YELLOW_GEMTRAP.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.TURQUOISE_STRIPEFISH.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
        setSpawnPlacement(AoAAnimals.VIOLET_SKIPPER.get(), IN_WATER, MOTION_BLOCKING_NO_LEAVES, SpawnPredicates.FISH);
    }

    private static <T extends Mob> void setSpawnPlacement(EntityType<T> entityType, SpawnPlacements.Type placementType, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<? extends Mob> spawnPredicate) {
        try {
            if (SpawnPlacements.getPlacementType(entityType) == NO_RESTRICTIONS)
                SpawnPlacements.register(entityType, placementType, heightmap, (SpawnPlacements.SpawnPredicate<T>)spawnPredicate);
        }
        catch (IllegalStateException ex) {
            Logging.logMessage(Level.WARN, "Caught duplicate spawn placement registration from: " + ForgeRegistries.ENTITY_TYPES.getKey(entityType).toString());
        }
    }

    private static final class SpawnPredicates {
        private static SpawnPlacements.SpawnPredicate<Mob> npcPredicate(boolean spawnsInDarkness) {
            return (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (!Mob.checkMobSpawnRules(type, world, reason, pos, rand))
                    return false;

                return spawnsInDarkness || WorldUtil.getLightLevel(world, pos, false, false) >= 8;
            };
        };

        private static final SpawnPlacements.SpawnPredicate<Mob> DEFAULT_MONSTER = monsterPredicate(false, Integer.MIN_VALUE, Integer.MAX_VALUE);
        private static final SpawnPlacements.SpawnPredicate<Mob> DEFAULT_DAY_MONSTER = monsterPredicate(true, Integer.MIN_VALUE, Integer.MAX_VALUE);

        private static SpawnPlacements.SpawnPredicate<Mob> monsterPredicate(boolean daySpawn, int minSpawnHeight, int maxSpawnHeight) {
            return (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (world.getDifficulty() == Difficulty.PEACEFUL)
                    return false;

                if (daySpawn) {
                    if (!world.getLevel().isDay())
                        return false;

                    if (rand.nextFloat() > 0.15f * world.getDifficulty().getId())
                        return false;

                    int blockLightLimit = world.dimensionType().monsterSpawnBlockLightLimit();

                    return blockLightLimit >= 15 || world.getBrightness(LightLayer.BLOCK, pos) <= blockLightLimit;
                }
                else if (!Monster.isDarkEnoughToSpawn(world, pos, rand)) {
                    return false;
                }

                if (pos.getY() < minSpawnHeight || pos.getY() > maxSpawnHeight)
                    return false;

                return Mob.checkMobSpawnRules(type, world, reason, pos, rand);
            };
        }

        private static <T extends Mob> SpawnPlacements.SpawnPredicate<T> animalPredicate(@Nullable TagKey<Block> blockTag, boolean spawnsInDarkness) {
            return (EntityType<T> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> {
                if (blockTag != null && !world.getBlockState(pos.below()).is(blockTag))
                    return false;

                return spawnsInDarkness || world.getRawBrightness(pos, 0) >= 8;
            };
        }

        private static final SpawnPlacements.SpawnPredicate<Mob> FISH = (EntityType<Mob> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource rand) -> world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
    }
}
