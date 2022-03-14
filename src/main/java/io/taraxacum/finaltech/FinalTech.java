package io.taraxacum.finaltech;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.taraxacum.finaltech.item.CopyCardItem;
import io.taraxacum.finaltech.item.StorageCardItem;
import io.taraxacum.finaltech.item.UnOrderSword;
import io.taraxacum.finaltech.machine.capacitor.expanded.*;
import io.taraxacum.finaltech.machine.cargo.*;
import io.taraxacum.finaltech.machine.capacitor.*;
import io.taraxacum.finaltech.machine.*;
import io.taraxacum.finaltech.machine.standard.advanced.*;
import io.taraxacum.finaltech.machine.standard.basic.*;
import io.taraxacum.finaltech.machine.manual.*;
import io.taraxacum.finaltech.machine.standard.AllCompression;
import io.taraxacum.finaltech.machine.standard.AllFactory;
import io.taraxacum.finaltech.machine.standard.BasicFrameMachine;
import io.taraxacum.finaltech.machine.standard.OrderedDustFactory;
import io.taraxacum.finaltech.setup.FinalTechItems;
import io.taraxacum.finaltech.setup.FinalTechMenus;
import io.taraxacum.finaltech.setup.FinalTechRecipes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Final_ROOT
 */
public class FinalTech extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            // todo
            // You could start an Auto-Updater for example
        }

        FinalTechMenus.MAIN_MENU.setTier(0);
        FinalTechMenus.MAIN_MENU.register(this);

        FinalTechMenus.MENU_MATERIAL.setTier(0);
        FinalTechMenus.MENU_BASIC_MACHINE.setTier(0);
        FinalTechMenus.MENU_ADVANCED_MACHINE.setTier(0);
        FinalTechMenus.MENU_FINAL_MACHINE.setTier(0);
        FinalTechMenus.MENU_CARGO.setTier(0);
        FinalTechMenus.MENU_ELECTRIC.setTier(0);
        FinalTechMenus.MENU_TOOL.setTier(0);

//        FinalTechItems.CODE_CREATE.addUnsafeEnchantment(NullEnchantment.ENCHANTMENT, 0);
//        FinalTechItems.CREATION_GENEALOGY.addUnsafeEnchantment(NullEnchantment.ENCHANTMENT, 0);
//        FinalTechItems.IMAGINARY_TRUTH.addUnsafeEnchantment(NullEnchantment.ENCHANTMENT, 0);

        // register items
        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.GEARWHEEL, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.GEARWHEEL, new SlimefunItemStack(FinalTechItems.GEARWHEEL, 4)).register(this);
        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.UNORDERED_DUST,  FinalTechRecipes.RECIPE_TYPE_ORDERED_DUST_FACTORY, new ItemStack[] {}).register(this);
        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.QUANTITY_MODULE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.QUANTITY_MODULE).register(this);
        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.QUANTITY_MODULE_V2, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.QUANTITY_MODULE_V2).register(this);
        new CopyCardItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.COPY_CARD, FinalTechRecipes.RECIPE_TYPE_ALL_COMPRESSION, new ItemStack[] {null, null, null, null, new CustomItemStack(Material.BOOK, "&f合成方式", "&f压缩" + CopyCardItem.DIFFICULTY + "个相同物品以获得")}).register(this);
        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.SINGULARITY, FinalTechRecipes.RECIPE_TYPE_ALL_COMPRESSION, new ItemStack[] {null, null, null, null, new CustomItemStack(Material.BOOK, "&f合成方式", "&f对已经压缩过的物品", "&f再进行一次压缩")}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.BUG, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_ADDITION, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_SYMMETRY, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_RANDOM, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_NULL, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_CREATE, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.ORDERED_DUST, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CODE_FINAL, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.CREATION_GENEALOGY, RecipeType.NULL, new ItemStack[] {}).register(this);
//        new SlimefunItem(FinalTechMenus.MENU_MATERIAL, FinalTechItems.IMAGINARY_TRUTH, RecipeType.NULL, new ItemStack[] {}).register(this);

        // register machines
        new BasicCobbleFactory(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.BASIC_COBBLE_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_COBBLE_FACTORY).register(this);
        new BasicOreFactory(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.BASIC_ORE_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_ORE_FACTORY).register(this);
        new BasicDustFactory(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.BASIC_DUST_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_DUST_FACTORY).register(this);
        new BasicFarmFactory(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.BASIC_FARM_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_FARM_FACTORY).register(this);
        new ManualEnhancedCraftingTable(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_ENHANCED_CRAFTING_TABLE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_ENHANCED_CRAFTING_TABLE).register(this);
        new ManualGrindStone(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_GRIND_STONE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_GRIND_STONE).register(this);
        new ManualArmorForge(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_ARMOR_FORGE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_ARMOR_FORGE).register(this);
        new ManualOreCrusher(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_ORE_CRUSHER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_ORE_CRUSHER).register(this);
        new ManualCompressor(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_COMPRESSOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_COMPRESSOR).register(this);
        new ManualSmeltery(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_SMELTERY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_SMELTERY).register(this);
        new ManualPressureChamber(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_PRESSURE_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_PRESSURE_CHAMBER).register(this);
        new ManualMagicWorkbench(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_MAGIC_WORKBENCH, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_MAGIC_WORKBENCH).register(this);
        new ManualJuicer(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_JUICER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_JUICER).register(this);
        new ManualAncientAltar(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_ANCIENT_ALTAR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_ANCIENT_ALTAR).register(this);
        new ManualHeatedPressureChamber(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.MANUAL_HEATED_PRESSURE_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MANUAL_HEATED_PRESSURE_CHAMBER).register(this);
        new OrderedDustFactory(FinalTechMenus.MENU_BASIC_MACHINE, FinalTechItems.ORDERED_DUST_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ORDERED_DUST_FACTORY).register(this);


        // register advanced machines
        new AdvancedElectricFurance(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_FURANCE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_FURANCE).register(this);
        new AdvancedGoldPan(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_GOLD_PAN, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_GOLD_PAN).register(this);
        new AdvancedElectricDustWasher(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_DUST_WASHER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_DUST_WASHER).register(this);
        new AdvancedElectricIngotFactory(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_INGOT_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_INGOT_FACTORY).register(this);
        new AdvancedElectricCrucible(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_CRUCIBLE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_CRUCIBLE).register(this);
        new AdvancedElectricOreGrinder(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_ORE_GRINDER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_ORE_GRINDER).register(this);
        new AdvancedHeatedPressureChamber(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_HEATED_PRESSURE_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_HEATED_PRESSURE_CHAMBER).register(this);
        new AdvancedElectricIngotPulverizer(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_INGOT_PULVERIZER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_INGOT_PULVERIZER).register(this);
        new AdvancedAutoDrier(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_AUTO_DRIER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_AUTO_DRIER).register(this);
        new AdvancedElectricPress(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_PRESS).register(this);
        new AdvancedFoodFactory(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_FOOD_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_FOOD_FACTORY).register(this);
        new AdvancedFreezer(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_FREEZER, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_FREEZER).register(this);
        new AdvancedCarbonPress(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_CARBON_PRESS, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_CARBON_PRESS).register(this);
        new AdvancedElectricSmeltery(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ELECTRIC_SMELTERY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ELECTRIC_SMELTERY).register(this);
        new AdvancedOreFactory(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_ORE_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_ORE_FACTORY).register(this);
        new AdvancedDustFactory(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_DUST_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_DUST_FACTORY).register(this);
        new AdvancedFarmFactory(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_FARM_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_FARM_FACTORY).register(this);
        new AdvancedAutoCraft(FinalTechMenus.MENU_ADVANCED_MACHINE, FinalTechItems.ADVANCED_AUTO_CRAFT, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_AUTO_CRAFT).register(this);

        // register best machines
        new AllCompression(FinalTechMenus.MENU_FINAL_MACHINE, FinalTechItems.ALL_COMPRESSION, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ALL_COMPRESSION).register(this);
        new AllFactory(FinalTechMenus.MENU_FINAL_MACHINE, FinalTechItems.ALL_FACTORY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ALL_FACTORY).register(this);
        new OverclockFrameMachine(FinalTechMenus.MENU_FINAL_MACHINE, FinalTechItems.OVERCLOCK_FRAME_MACHINE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.OVERCLOCK_FRAME_MACHINE).register(this);

        // register cargos
        new BasicFrameMachine(FinalTechMenus.MENU_CARGO, FinalTechItems.BASIC_FRAME_MACHINE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_FRAME_MACHINE).register(this);
        new BasicNormalStorageUnit(FinalTechMenus.MENU_CARGO, FinalTechItems.BASIC_NORMAL_STORAGE_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_NORMAL_STORAGE_UNIT).register(this);
        new BasicLinkedStorageUnit(FinalTechMenus.MENU_CARGO, FinalTechItems.BASIC_LINKED_STORAGE_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_LINKED_STORAGE_UNIT).register(this);
        new BasicChargeableStorageUnit(FinalTechMenus.MENU_CARGO, FinalTechItems.BASIC_CHARGEABLE_STORAGE_UNIT, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_CHARGEABLE_STORAGE_UNIT).register(this);
        new TransferPipe(FinalTechMenus.MENU_CARGO, FinalTechItems.TRANSFER_PIPE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.TRANSFER_PIPE).register(this);
        new TransferLine(FinalTechMenus.MENU_CARGO, FinalTechItems.TRANSFER_LINE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.TRANSFER_LINE).register(this);
        new TransferStation(FinalTechMenus.MENU_CARGO, FinalTechItems.TRANSFER_STATION, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.TRANSFER_STATION).register(this);
        new StorageInteractPort(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_INTERACT_PORT, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_INTERACT_PORT).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_WHITE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_WHITE).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_ORANGE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_ORANGE).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_MAGENTA, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_MAGENTA).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_LIGHT_BLUE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_LIGHT_BLUE).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_YELLOW, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_YELLOW).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_LIME, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_LIME).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_PINK, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_PINK).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_GRAY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_GRAY).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_LIGHT_GRAY, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_LIGHT_GRAY).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_CYAN, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_CYAN).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_PURPLE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_PURPLE).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_BLUE, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_BLUE).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_BROWN, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_BROWN).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_GREEN, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_GREEN).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_RED, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_RED).register(this);
        new StorageCardItem(FinalTechMenus.MENU_CARGO, FinalTechItems.STORAGE_ITEM_BLACK, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.STORAGE_ITEM_BLACK).register(this);

        // register electric machines
        new BasicChargeIncreaseCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BASIC_CHARGE_INCREASE_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_CHARGE_INCREASE_CAPACITOR).register(this);
        new BasicConsumeReduceCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BASIC_CONSUME_REDUCE_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_CONSUME_REDUCE_CAPACITOR).register(this);
        new BasicSelfGenerateCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BASIC_SELF_GENERATE_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_SELF_GENERATE_CAPACITOR).register(this);
        new BasicVoidGenerateCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BASIC_VOID_GENERATE_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_VOID_GENERATE_CAPACITOR).register(this);
        new SmallExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.SMALL_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.SMALL_EXPANDED_CAPACITOR).register(this);
        new MediumExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.MEDIUM_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MEDIUM_EXPANDED_CAPACITOR).register(this);
        new BigExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BIG_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BIG_EXPANDED_CAPACITOR).register(this);
        new LargeExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.LARGE_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.LARGE_EXPANDED_CAPACITOR).register(this);
        new CarbonadoExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.CARBONADO_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.CARBONADO_EXPANDED_CAPACITOR).register(this);
        new EnergizedExpandedCapacitor(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.ENERGIZED_EXPANDED_CAPACITOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ENERGIZED_EXPANDED_CAPACITOR).register(this);

//        new BasicGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.BASIC_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.BASIC_GENERATOR).register(this);
//        new AdvancedGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.ADVANCED_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ADVANCED_GENERATOR).register(this);
//        new ReinforcedGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.REINFORCED_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.REINFORCED_GENERATOR).register(this);
//        new CarbonadoGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.CARBONADO_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.CARBONADO_GENERATOR).register(this);
//        new EnergizedGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.ENERGIZED_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.ENERGIZED_GENERATOR).register(this);
//        new MatrixGenerator(FinalTechMenus.MENU_ELECTRIC, FinalTechItems.MATRIX_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.MATRIX_GENERATOR).register(this);

        // register tool
        new UnOrderSword(FinalTechMenus.MENU_TOOL, FinalTechItems.UNORDERED_SWORD, RecipeType.ENHANCED_CRAFTING_TABLE, FinalTechRecipes.UNORDERED_SWORD).register(this);
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }
}
