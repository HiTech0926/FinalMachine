package io.taraxacum.finaltech.core.helper;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.taraxacum.finaltech.FinalTech;
import io.taraxacum.libs.slimefun.dto.BlockStorageHelper;
import io.taraxacum.libs.slimefun.dto.BlockStorageLoreHelper;
import io.taraxacum.finaltech.core.item.machine.AbstractMachine;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.xzavier0722.mc.plugin.slimefun4.storage.controller.SlimefunBlockData;
import com.xzavier0722.mc.plugin.slimefun4.storage.util.StorageCacheUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * @author Final_ROOT
 */
public final class MachineMaxStack {
    public static final String KEY = "mms";

    public static final ItemStack ICON = new CustomItemStack(Material.CHEST, FinalTech.getLanguageString("helper", "MACHINE_MAX_STACK", "icon", "name"));

    public static final BlockStorageLoreHelper HELPER = new BlockStorageLoreHelper(BlockStorageHelper.ID_CARGO, new LinkedHashMap<>() {{
        this.put("0", FinalTech.getLanguageStringList("helper", "MACHINE_MAX_STACK", "0", "lore"));
        for (int i = 1; i <= 54; i++) {
            this.put(String.valueOf(i), FinalTech.getLanguageManager().replaceStringList(FinalTech.getLanguageStringList("helper", "MACHINE_MAX_STACK", "value", "lore"), String.valueOf(i)));
        }
    }}) {
        @Nonnull
        @Override
        public String getKey() {
            return KEY;
        }

        @Override
        public boolean setIcon(@Nonnull ItemStack iconItem, @Nullable String value) {
            if (Objects.equals(this.defaultValue(), value)) {
                iconItem.setType(Material.CHEST);
                iconItem.setAmount(1);
            } else if (value != null) {
                iconItem.setType(Material.HOPPER);
                iconItem.setAmount(Integer.parseInt(value));
            } else {
                return false;
            }
            return super.setIcon(iconItem, value);
        }

        @Nonnull
        @Override
        public String nextOrDefaultValue(@Nullable String value) {
            return this.defaultValue();
        }

        @Nonnull
        @Override
        public String previousOrDefaultValue(@Nullable String value) {
            return this.defaultValue();
        }

        @Nonnull
        @Override
        public ChestMenu.MenuClickHandler getHandler(@Nonnull Inventory inventory, @Nonnull Location location, @Nonnull SlimefunItem slimefunItem, int slot) {
            if (slimefunItem instanceof AbstractMachine) {
                return (player, i, itemStack, clickAction) -> {
                	SlimefunBlockData blockData = StorageCacheUtils.getBlock(location);
                    int quantity = Integer.parseInt(blockData.getData(MachineMaxStack.KEY));
                    if (clickAction.isShiftClicked()) {
                        quantity = 0;
                    } else {
                        if (clickAction.isRightClicked()) {
                            quantity = (quantity + ((AbstractMachine) slimefunItem).getInputSlot().length) % (((AbstractMachine) slimefunItem).getInputSlot().length + 1);
                        } else {
                            quantity = (quantity + 1) % (((AbstractMachine) slimefunItem).getInputSlot().length + 1);
                        }
                    }
                    MachineMaxStack.HELPER.setIcon(inventory.getItem(slot), String.valueOf(quantity));
                    blockData.setData(MachineMaxStack.KEY, String.valueOf(quantity));
                    return false;
                };
            } else {
                return super.getHandler(inventory, location, slimefunItem, slot);
            }
        }
    };
}
