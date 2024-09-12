/*
 * MIT License
 *
 * Copyright (c) 2023 GameGeek-Saikel
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;
import pers.saikel0rado1iu.silk.api.generate.world.PlacedFeatureEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

import java.util.List;

import static net.minecraft.world.gen.feature.PlacedFeatures.wouldSurvive;

/**
 * <h2 style="color:FFC800">已放置的地物集</h2>
 * 蛛丝网迹的所有已放置的地物
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface PlacedFeatures extends PlacedFeatureEntry {
	PlacedFeatures INSTANCE = new PlacedFeatures() {
	};
	
	RegistryKey<PlacedFeature> COBWEB = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "cobweb");
	RegistryKey<PlacedFeature> STICKY_COMPACT_COBWEB = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "sticky_compact_cobweb");
	RegistryKey<PlacedFeature> SPIDER_CHRYSALIS = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "spider_chrysalis");
	RegistryKey<PlacedFeature> SPIDER_EGG_COCOON = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "spider_egg_cocoon");
	RegistryKey<PlacedFeature> COBWEBBY_DARK_OAK = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "cobwebby_dark_oak");
	RegistryKey<PlacedFeature> CREEPY_SPIDER_FOREST_VEGETATION = PlacedFeatureEntry.of(SpontaneousReplace.INSTANCE, "creepy_spider_forest_vegetation");
	
	private static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
		featureRegisterable.register(key, new PlacedFeature(feature, List.of(modifiers)));
	}
	
	@Override
	default RegistryBuilder.BootstrapFunction<PlacedFeature> bootstrap() {
		return registerable -> {
			register(registerable, COBWEB, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.COBWEB),
					BiomePlacementModifier.of(), CountPlacementModifier.of(3), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
			register(registerable, STICKY_COMPACT_COBWEB, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.STICKY_COMPACT_COBWEB),
					BiomePlacementModifier.of(), CountPlacementModifier.of(3), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(1), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
			register(registerable, SPIDER_CHRYSALIS, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.SPIDER_CHRYSALIS),
					BiomePlacementModifier.of(), CountPlacementModifier.of(1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
			register(registerable, SPIDER_EGG_COCOON, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.SPIDER_EGG_COCOON),
					BiomePlacementModifier.of(), CountPlacementModifier.of(1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR));
			register(registerable, COBWEBBY_DARK_OAK, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.COBWEBBY_DARK_OAK), wouldSurvive(Blocks.DARK_OAK_SAPLING));
			register(registerable, CREEPY_SPIDER_FOREST_VEGETATION, registerable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeatures.CREEPY_SPIDER_FOREST_VEGETATION),
					CountPlacementModifier.of(8), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), net.minecraft.world.gen.feature.PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
		};
	}
}
