package com.robertx22.mine_and_slash.database.spells.aoe_projectile;

import com.robertx22.mine_and_slash.database.stats.stat_types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.database.spells.bases.DamageData;
import com.robertx22.mine_and_slash.database.spells.bases.EffectCalculation;
import com.robertx22.mine_and_slash.database.spells.bases.SpellEffectDamage;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class BaseAoeSpellProjectile extends BaseBoltAOE {

    @Override
    public int useTimeTicks() {
        return 12;
    }

    public BaseAoeSpellProjectile() {
        super();
    }

    @Override
    public SpellType Type() {
        return SpellType.Aoe_Projectile;
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {

        if (!world.isRemote) {

            EntityElementalBolt projectile = this.projectile(world);
            projectile.SpawnAndShoot(new SpellEffectDamage(this.Element()), new DamageData(caster, data), caster);

        }

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
        caster.swingArm(hand);
        return true;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(this.Element()), 0.4F);
    }

}