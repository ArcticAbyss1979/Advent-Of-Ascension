package net.tslat.aoa3.content.item.weapon.vulcane;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseVulcane extends Item {
	protected double baseDmg;

	public BaseVulcane(double dmg, int durability) {
		super(new Item.Properties().durability(durability));

		this.baseDmg = dmg;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	public double getDamage() {
		return baseDmg;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!(player instanceof ServerPlayer))
			return InteractionResultHolder.fail(stack);

		AoAResource.Instance rage = PlayerUtil.getResource(player, AoAResources.RAGE.get());

		if (!rage.hasAmount(50) || player.getLastHurtByMob() == null)
			return InteractionResultHolder.fail(stack);

		return activate(rage, stack, hand);
	}

	public InteractionResultHolder<ItemStack> activate(AoAResource.Instance rage, ItemStack vulcane, InteractionHand hand) {
		Player pl = rage.getPlayerDataManager().player();
		float targetHealth = pl.getLastHurtByMob().getHealth();
		float damage = (float)getDamage() * (1 + ((rage.getCurrentValue() - 50) / 100));

		if (DamageUtil.doVulcaneAttack(pl, pl.getLastHurtByMob(), damage)) {
			doAdditionalEffect(pl.getLastHurtByMob(), pl, Math.min(targetHealth, damage));
			pl.level.playSound(null, pl.getX(), pl.getY(), pl.getZ(), AoASounds.ITEM_VULCANE_USE.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(vulcane, pl, hand);
			rage.consume(rage.getCurrentValue(), true);

			return InteractionResultHolder.success(vulcane);
		}

		return InteractionResultHolder.fail(vulcane);
	}

	public void doAdditionalEffect(LivingEntity target, Player player, float damageDealt) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.true", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(baseDmg)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.vulcane.use", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.vulcane.target", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
