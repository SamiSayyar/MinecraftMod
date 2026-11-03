package name.modid;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GhostState implements ModInitializer {
    public static final String MOD_ID = "ghoststate";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceKey<Item> PHASE_SHARD_KEY =
            ResourceKey.create(Registries.ITEM, id("phase_shard"));

    public static final Item PHASE_SHARD = Registry.register(
            BuiltInRegistries.ITEM,
            PHASE_SHARD_KEY,
            new PhaseShardItem(new Item.Properties().setId(PHASE_SHARD_KEY).stacksTo(16))
    );

    @Override
    public void onInitialize() {
        LOGGER.info("GhostState mod initialized! Phase Shard registered.");
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
