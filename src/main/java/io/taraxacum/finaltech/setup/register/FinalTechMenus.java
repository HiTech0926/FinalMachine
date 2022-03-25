package io.taraxacum.finaltech.setup.register;

import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.taraxacum.finaltech.FinalTech;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

/**
 * @author Final_ROOT
 */
public final class FinalTechMenus {
    public static final NestedItemGroup MAIN_MENU = new NestedItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CATEGORY_MAIN"), new CustomItemStack(Material.OBSERVER, "&f乱序技艺"));
    public static final SubItemGroup MENU_MATERIAL = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CATEGORY_MATERIAL"), MAIN_MENU , new CustomItemStack(Material.AMETHYST_SHARD, "&f材料"));
    public static final SubItemGroup MENU_BASIC_MACHINE = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CATEGORY_BASIC_MACHINE"), MAIN_MENU, new CustomItemStack(Material.PISTON, "&f基础机器"));
    public static final SubItemGroup MENU_ADVANCED_MACHINE = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CATEGORY_ADVANCED_MACHINE"), MAIN_MENU, new CustomItemStack(Material.STICKY_PISTON, "&f高级机器"));
    public static final SubItemGroup MENU_FINAL_MACHINE = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CATEGORY_FINAL_MACHINE"), MAIN_MENU, new CustomItemStack(Material.DIRT, "&f终极机器"));
    public static final SubItemGroup MENU_CARGO = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_CARGOS"), MAIN_MENU, new CustomItemStack(Material.END_ROD, "&f物流"));
    public static final SubItemGroup MENU_ELECTRIC = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_ELECTRIC"), MAIN_MENU, new CustomItemStack(Material.MAGMA_BLOCK, "&f电力"));
    public static final SubItemGroup MENU_TOOL = new SubItemGroup(new NamespacedKey(FinalTech.getPlugin(FinalTech.class), "FINALTECH_TOOL"), MAIN_MENU, new CustomItemStack(Material.KNOWLEDGE_BOOK, "&f工具"));
}