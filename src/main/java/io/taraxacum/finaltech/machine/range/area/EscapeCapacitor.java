package io.taraxacum.finaltech.machine.range.area;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.taraxacum.finaltech.interfaces.RecipeItem;
import io.taraxacum.finaltech.menu.AbstractMachineMenu;
import io.taraxacum.finaltech.menu.StatusMenu;
import io.taraxacum.finaltech.util.ItemStackUtil;
import io.taraxacum.finaltech.util.MachineUtil;
import io.taraxacum.finaltech.util.SlimefunUtil;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Final_ROOT
 */
public class EscapeCapacitor extends AbstractCubeMachine implements EnergyNetComponent, RecipeItem {
    public final static int RANGE = 8;
    public static final int CAPACITOR = Integer.MAX_VALUE / 2;
    public final static double LOSS = 16;
    private final List<MachineRecipe> machineRecipeList = new ArrayList<>();
    public EscapeCapacitor(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.registerDefaultRecipes();
    }

    @Nonnull
    @Override
    protected BlockPlaceHandler onBlockPlace() {
        return MachineUtil.BLOCK_PLACE_HANDLER_PLACER_DENY;
    }

    @Nonnull
    @Override
    protected BlockBreakHandler onBlockBreak() {
        return MachineUtil.easyBlockBreakerHandler(this);
    }

    @Nonnull
    @Override
    protected AbstractMachineMenu setMachineMenu() {
        return new StatusMenu(this.getId(), this.getItemName(), this);
    }

    @Override
    protected void tick(@Nonnull Block block, @Nonnull SlimefunItem slimefunItem, @Nonnull Config config) {
        Location blockLocation = block.getLocation();
        int charge = this.getCharge(blockLocation);
        int count = 0;
        AtomicInteger maxEnergy = new AtomicInteger();
        AtomicInteger totalEnergy = new AtomicInteger();
        int validCharge = (int)(charge / LOSS);
        if(charge > 0) {
            count = this.function(block, RANGE, location -> {
                if(BlockStorage.hasBlockInfo(location)) {
                    Config locationInfo = BlockStorage.getLocationInfo(location);
                    if(locationInfo.contains(SlimefunUtil.KEY_ID)) {
                        String id = locationInfo.getString(SlimefunUtil.KEY_ID);
                        SlimefunItem item = SlimefunItem.getById(id);
                        if(item instanceof EnergyNetComponent && !EnergyNetComponentType.CAPACITOR.equals(((EnergyNetComponent) item).getEnergyComponentType())) {
                            int capacity = ((EnergyNetComponent) item).getCapacity();
                            if(capacity == 0) {
                                return 0;
                            }
                            int energy = Integer.parseInt(SlimefunUtil.getCharge(locationInfo));
                            int e = Math.min(capacity - energy, validCharge);
                            if(e > 0) {
                                SlimefunUtil.setCharge(location, String.valueOf(e + energy));
                                maxEnergy.set(Math.max(maxEnergy.get(), e));
                                totalEnergy.addAndGet(e);
                                return 1;
                            }
                        }
                    }
                }
                return 0;
            });
        }
        BlockMenu blockMenu = BlockStorage.getInventory(block);
        ItemStack item = blockMenu.getItemInSlot(StatusMenu.CENTER_SLOT);
        ItemStackUtil.setLore(item,
                "§7当前存储电量= " + charge,
                "§7当前生效的机器= " + count,
                "§7实际发电量= " + totalEnergy,
                "§7最大单机器发电量= " + maxEnergy);
        charge = charge - (int) (maxEnergy.get() * LOSS);
        charge = Math.max(charge, 0);
        SlimefunUtil.setCharge(blockLocation, String.valueOf(charge));
    }

    @Override
    protected boolean isSynchronized() {
        return false;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CAPACITOR;
    }

    @Override
    public int getCapacity() {
        return CAPACITOR;
    }

    @Override
    public List<MachineRecipe> getMachineRecipes() {
        return machineRecipeList;
    }

    @Override
    public void registerDefaultRecipes() {
        this.registerDescriptiveRecipe("&f工作原理",
                "",
                "&f搜索范围内的所有机器",
                "&f根据自身已存储的电量",
                "&f对其进行输电",
                "&f",
                "&f无法用于电容类机器");
        this.registerDescriptiveRecipe("&f电损耗率",
                "",
                "&f电力损耗率 " + LOSS * 100 + "%",
                "&f即传输时 电力额外消耗" + (LOSS * 100 - 100) + "%",
                "",
                "&f电力损耗只会计算输电量为最大的一次");
    }
}