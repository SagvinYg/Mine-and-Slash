package com.robertx22.onevent;

import com.robertx22.capability.EntityData;
import com.robertx22.customitems.MyItems;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.saving.Saving;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class OnLogin {

	@SubscribeEvent
	public void onLogin(PlayerLoggedInEvent event) {

		if (event.player.world.isRemote) {
			return;
		}

		EntityPlayer player = event.player;

		if (!player.hasCapability(EntityData.Data, null)) {

			return;
		}

		EntityData.IData data = player.getCapability(EntityData.Data, null);

		if (Saving.Load(data.getNBT(), Unit.class) == null) {
			data.setNBT(Saving.Save(null, new Unit()));
			System.out.println("Welcome!");
		}

		GearItemData gear = new GearItemData();

		ItemStack stack = new ItemStack(MyItems.epic_boots);

		Saving.SaveToItem(stack, gear);

		player.addItemStackToInventory(stack);

	}

}
