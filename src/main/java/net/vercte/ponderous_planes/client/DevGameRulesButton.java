package net.vercte.ponderous_planes.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.GameRules;
import net.vercte.ponderous_planes.PonderousPlanes;

public class DevGameRulesButton extends Button {
    protected static final WidgetSprites SPRITES = new WidgetSprites(
            PonderousPlanes.at("widgets/dev_gamerules_selected"),
            PonderousPlanes.at("widgets/dev_gamerules"),
            PonderousPlanes.at("widgets/dev_gamerules_selected_hovered"),
            PonderousPlanes.at("widgets/dev_gamerules_hovered")
    );

    private static final Component DEV_GAME_RULES_LABEL = Component.translatable("ponderous_planes.selectWorld.gameRules");

    private final WorldCreationUiState uiState;
    private boolean state = false;

    public DevGameRulesButton(int x, int y, WorldCreationUiState uiState) {
        super(x, y, 20, 20, DEV_GAME_RULES_LABEL, DevGameRulesButton::onPress, Button.DEFAULT_NARRATION);
        this.uiState = uiState;

        Component tip = Component.translatable("ponderous_planes.selectWorld.gameRules.tooltip.disabled");
        this.setTooltip(Tooltip.create(tip));
    }

    private static void onPress(Button b) {
        assert b instanceof DevGameRulesButton;
        DevGameRulesButton button = (DevGameRulesButton)b;

        GameRules gameRules = button.uiState.getGameRules().copy();

        boolean current = gameRules.getRule(GameRules.RULE_DOMOBSPAWNING).get();

        button.setState(current);
        Component tip = current ? Component.translatable("ponderous_planes.selectWorld.gameRules.tooltip.enabled") : Component.translatable("ponderous_planes.selectWorld.gameRules.tooltip.disabled");
        button.setTooltip(Tooltip.create(tip));

        gameRules.getRule(GameRules.RULE_DOMOBSPAWNING).set(!current, null);
        gameRules.getRule(GameRules.RULE_DAYLIGHT).set(!current, null);
        gameRules.getRule(GameRules.RULE_WEATHER_CYCLE).set(!current, null);

        button.uiState.setGameRules(gameRules);
    }

    private void setState(boolean current) {
        this.state = current;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int p_282682_, int p_281714_, float p_282542_) {
        graphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        graphics.blitSprite(SPRITES.get(!this.state, this.isHovered()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
