package de.alberteinholz.ehtech.blocks.guis.guis.machines;

import de.alberteinholz.ehmooshroom.registry.RegistryHelper;
import de.alberteinholz.ehtech.blocks.components.container.InventoryWrapper;
import de.alberteinholz.ehtech.util.Helper;
import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandlerType;

public class OreGrowerGui extends MachineGui {
    protected WItemSlot oreInputSlot;

    @SuppressWarnings("unchecked")
    public OreGrowerGui(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
        this((ScreenHandlerType<SyncedGuiDescription>) RegistryHelper.getEntry(Helper.makeId("ore_grower")).screenHandlerType, syncId, playerInv, buf);
    }

    public OreGrowerGui(ScreenHandlerType<SyncedGuiDescription> type, int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
        super(type, syncId, playerInv, buf);
    }

    @Override
    protected void initWidgetsDependencies() {
        super.initWidgetsDependencies();
        progressBarBG = Helper.makeId("textures/gui/container/machine/oregrower/elements/progress_bar_bg.png");
        progressBarFG = Helper.makeId("textures/gui/container/machine/oregrower/elements/progress_bar_fg.png");
    }

    @Override
    protected void initWidgets() {
        super.initWidgets();
        oreInputSlot = WItemSlot.of(blockInventory, ((InventoryWrapper) blockInventory).getContainerInventoryComponent().getNumber("seed_input"));
    }

    @Override
    public void drawDefault() {
        super.drawDefault();
        ((WGridPanel) root).add(oreInputSlot, 2, 3);
        ((WGridPanel) root).add(progressBar, 3, 3, 2, 1);
    }
}