package net.kaupenjoe.tutorialmod.item.client;

import net.kaupenjoe.tutorialmod.item.custom.ToxicArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ToxicArmorRenderer extends GeoArmorRenderer<ToxicArmorItem> {
    public ToxicArmorRenderer() {
        super(new ToxicArmorModel());
    }
}
