package io.taraxacum.finaltech.core;

import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.taraxacum.finaltech.machine.standard.OrderedDustFactory;
import io.taraxacum.finaltech.util.ItemStackUtil;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import org.bukkit.inventory.ItemStack;

/**
 * @author Final_ROOT
 */
public class UnOrderedDustCraftingOperation extends CraftingOperation {

    private final ItemStack[] inputs;
    private final ItemStack[] outputs;
    private final MachineRecipe machineRecipe;

    private int matchCount = 0;
    private int inputCount = 0;

    public UnOrderedDustCraftingOperation(MachineRecipe machineRecipe) {
        super(machineRecipe);
        this.machineRecipe = machineRecipe;
        this.inputs = this.machineRecipe.getInput();
        this.outputs = this.machineRecipe.getOutput();
    }

    public void addItem(ItemStack item) {
        if(ItemStackUtil.isItemNull(item)) {
            return;
        }
        if(matchCount < inputs.length) {
            boolean match = true;
            for(int i = 0; i < matchCount; i++) {
                ItemStack input = inputs[i];
                if(input != null && ItemStackUtil.isItemSimilar(item, input)) {
                    match = false;
                    break;
                }
            }
            if(match == true) {
                inputs[matchCount++] = new ItemStack(item);
            }
        }
        this.inputCount += item.getAmount();
        if(this.inputCount > OrderedDustFactory.getInputDifficulty()) {
            this.inputCount = OrderedDustFactory.getInputDifficulty();
        }
    }

    public int getMatchCount() {
        return this.matchCount;
    }

    public int getInputCount() {
        return this.inputCount;
    }

    @Override
    public boolean isFinished() {
        return (this.inputCount >= OrderedDustFactory.getInputDifficulty() && this.matchCount >= OrderedDustFactory.getMatchDifficulty());
    }
}
