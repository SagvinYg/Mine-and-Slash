package com.robertx22.mine_and_slash.database.items.currency.infusions;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.database.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.saveclasses.gearitem.InfusionData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseInfusionItem extends CurrencyItem implements ICurrencyItemEffect {

    public BaseInfusionItem(String name) {
        super(name);

    }

    public void createInfusion(GearItemData gear) {

        gear.infusion = new InfusionData();

        List<StatMod> possible = new ArrayList();

        if (gear.GetBaseGearType().slotType().equals(GearItemSlot.GearSlotType.Armor)) {
            possible = this.armorInfusions();
        } else if (gear.GetBaseGearType()
                .slotType()
                .equals(GearItemSlot.GearSlotType.Weapon)) {
            possible = this.weaponInfusions();
        } else if (gear.GetBaseGearType()
                .slotType()
                .equals(GearItemSlot.GearSlotType.Jewerly)) {
            possible = this.jewerlyInfusions();
        } else {
            possible = this.jewerlyInfusions();
        }

        StatMod random = RandomUtils.weightedRandom(possible);

        gear.infusion.Mods = new ArrayList();

        gear.infusion.Mods.add(StatModData.Load(random, 0));

    }

    public abstract List<StatMod> weaponInfusions();

    public abstract List<StatMod> armorInfusions();

    public abstract List<StatMod> jewerlyInfusions();

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        this.createInfusion(gear);

        Gear.Save(stack, gear);
        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.INFUSIONS);
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Luck is Etheral and yet affects everything.");
    }

}