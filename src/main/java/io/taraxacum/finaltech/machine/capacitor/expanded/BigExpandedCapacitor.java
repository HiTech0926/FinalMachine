package io.taraxacum.finaltech.machine.capacitor.expanded;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

/**
 * @author Final_ROOT
 */
public class BigExpandedCapacitor extends AbstractExpandedElectricCapacitor {
    public static final int CAPACITY = 1048576;
    public static final int STACK = 48;
    public BigExpandedCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getCapacity() {
        return CAPACITY * 2;
    }

    @Override
    public String getMaxStack() {
        return String.valueOf(STACK - 2);
    }
}