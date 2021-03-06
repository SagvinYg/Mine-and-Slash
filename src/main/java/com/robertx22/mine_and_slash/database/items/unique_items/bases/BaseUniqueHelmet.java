package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.Helmet;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.items.gearitems.armor.ItemHelmet;
import com.robertx22.mine_and_slash.uncommon.interfaces.IBaseAutoLoc;

public abstract class BaseUniqueHelmet extends ItemHelmet implements IUnique {

    public BaseUniqueHelmet() {
        super(new UniqueItem().Rank());

    }

    @Override
    public GearItemSlot getGearSlot() {
        return new Helmet();
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public IBaseAutoLoc.AutoLocGroup locNameGroup() {
        return IBaseAutoLoc.AutoLocGroup.Unique_Items;
    }

}
