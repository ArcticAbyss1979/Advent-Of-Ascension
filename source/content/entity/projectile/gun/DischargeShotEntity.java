package net.tslat.aoa3.content.entity.projectile.gun;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.HardProjectile;
import net.tslat.aoa3.content.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.WorldUtil;

public class DischargeShotEntity extends BaseBullet implements HardProjectile {
	public DischargeShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public DischargeShotEntity(Level world) {
		super(AoAProjectiles.DISCHARGE_SHOT.get(), world);
	}

	public DischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, float dmgMultiplier, int piercingValue, float xMod, float yMod, float zMod) {
		super(AoAProjectiles.DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, dmgMultiplier, piercingValue, xMod, yMod, zMod);
	}

	public DischargeShotEntity(LivingEntity shooter, BaseGun gun, InteractionHand hand, int maxAge, int piercingValue) {
		super(AoAProjectiles.DISCHARGE_SHOT.get(), shooter, gun, hand, maxAge, 1.0f, piercingValue);
	}

	public DischargeShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.DISCHARGE_SHOT.get(), world, x, y, z);
	}

	@Override
	public void doBlockImpact(Vec3 impactLocation, Direction face, BlockPos blockPos) {
		explode(impactLocation);
	}

	@Override
	public void doEntityImpact(Entity target, Vec3 impactLocation) {
		explode(impactLocation);
	}

	protected void explode(Vec3 position) {
		WorldUtil.createExplosion(getOwner(), level, this, 1.8f);
	}
}
