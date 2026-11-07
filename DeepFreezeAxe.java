package com.example;

import com.example.item.DeepFreezeAxeItem;
import com.example.item.FrostGemItem;
import com.example.item.FrostbiteSwordItem;
import java.util.function.Function;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeepFreezeAxe implements ModInitializer {

    public static final String MOD_ID = "deepfreezeaxe";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item FROST_GEM = register("frost_gem",
            FrostGemItem::new,
            new Item.Properties().stacksTo(1));

    public static final Item FROSTBITE_SWORD = register("frostbite_sword",
            FrostbiteSwordItem::new,
            new Item.Properties().sword(ToolMaterial.DIAMOND, 3.0F, -2.4F));

    public static final Item DEEP_FREEZE_AXE = register("deep_freeze_axe",
            props -> new DeepFreezeAxeItem(ToolMaterial.DIAMOND, 5.0F, -3.0F, props),
            new Item.Properties());

    @Override
    public void onInitialize() {
        LOGGER.info("Deep Freeze loaded");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties props) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id(name));
        return Registry.register(BuiltInRegistries.ITEM, key, factory.apply(props.setId(key)));
    }
}
