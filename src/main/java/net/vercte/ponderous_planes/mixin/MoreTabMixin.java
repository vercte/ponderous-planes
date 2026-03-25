package net.vercte.ponderous_planes.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;
import net.vercte.ponderous_planes.client.DevGameRulesButton;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.client.gui.screens.worldselection.CreateWorldScreen$MoreTab")
public class MoreTabMixin extends GridLayoutTab {
    @Shadow
    @Final
    CreateWorldScreen this$0;

    public MoreTabMixin(Component component) {
        super(component);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;", ordinal = 0))
    public <T extends LayoutElement> T addDevGameRulesButton(GridLayout.RowHelper instance, T child, Operation<T> original) {
        T t = original.call(instance, child);

        if(t instanceof Button button) this.layout.children.add(
                new DevGameRulesButton(button.getRight() + 4, button.getY(), this.this$0.getUiState())
        );
        return t;
    }
}
