package de.alberteinholz.ehtech.blocks.guis.guis.machines;

import de.alberteinholz.ehtech.blocks.components.container.InventoryWrapper;
import de.alberteinholz.ehtech.blocks.components.container.machine.CoalGeneratorDataProviderComponent;
import de.alberteinholz.ehtech.blocks.guis.widgets.Bar;
import de.alberteinholz.ehtech.registry.BlockRegistry;
import de.alberteinholz.ehtech.util.Ref;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WBar.Direction;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class CoalGeneratorGui extends MachineGui {
    protected Identifier heatBarBG;
    protected Identifier heatBarFG;
    protected Bar heatBar;
    protected WItemSlot coalInputSlot;

    public CoalGeneratorGui(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
        this(BlockRegistry.COAL_GENERATOR.screenHandlerType, syncId, playerInv, buf);
    }

    public CoalGeneratorGui(ScreenHandlerType<SyncedGuiDescription> type, int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
        super(type, syncId, playerInv, buf);
    }

    @Override
    protected void initWidgetsDependencies() {
        super.initWidgetsDependencies();
        heatBarBG = new Identifier(Ref.MOD_ID, "textures/gui/container/machine/coalgenerator/elements/heat_bar/background.png");
        heatBarFG = new Identifier(Ref.MOD_ID, "textures/gui/container/machine/coalgenerator/elements/heat_bar/foreground.png");
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        heatBar = new Bar(heatBarBG, heatBarFG, ((CoalGeneratorDataProviderComponent) getDataProviderComponent()).heat, Direction.UP);
        coalInputSlot = WItemSlot.of(blockInventory, ((InventoryWrapper) blockInventory).component.getNumber("coal_input"));
    }

    @Override
    public void drawDefault() {
        super.drawDefault();
        heatBar.addDefaultTooltip("tooltip.ehtech.coal_generator.heat_bar");
        ((WGridPanel) root).add(heatBar, 5, 2, 3, 3);
        ((WGridPanel) root).add(coalInputSlot, 2, 3);
        ((WGridPanel) root).add(progressBar, 3, 3, 2, 1);
    }
}