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

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.treedecorator.TrunkVineTreeDecorator;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import pers.saikel0rado1iu.silk.api.generate.world.ConfiguredFeatureEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.gen.foliage.CobwebbyOakFoliagePlacer;

import java.util.List;
import java.util.OptionalInt;

import static pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.feature.PlacedFeatures.EERIE_TREE;
import static pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.feature.PlacedFeatures.TREACHEROUS_TREE;

/**
 * <h2 style="color:FFC800">已配置的地物集</h2>
 * 蛛丝网迹的所有已配置的地物
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface ConfiguredFeatures extends ConfiguredFeatureEntry {
	ConfiguredFeatures INSTANCE = new ConfiguredFeatures() {
	};
	
	RegistryKey<ConfiguredFeature<?, ?>> COBWEB = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "cobweb");
	RegistryKey<ConfiguredFeature<?, ?>> STICKY_COMPACT_COBWEB = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "sticky_compact_cobweb");
	RegistryKey<ConfiguredFeature<?, ?>> SPIDER_CHRYSALIS = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "spider_chrysalis");
	RegistryKey<ConfiguredFeature<?, ?>> SPIDER_EGG_COCOON = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "spider_egg_cocoon");
	RegistryKey<ConfiguredFeature<?, ?>> COBWEBBY_DARK_OAK = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "cobwebby_dark_oak");
	RegistryKey<ConfiguredFeature<?, ?>> CREEPY_SPIDER_FOREST_VEGETATION = ConfiguredFeatureEntry.of(SpontaneousReplace.INSTANCE, "creepy_spider_forest_vegetation");
	
	private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		registerable.register(key, new ConfiguredFeature<>(feature, config));
	}
	
	@Override
	default RegistryBuilder.BootstrapFunction<ConfiguredFeature<?, ?>> bootstrap() {
		return registerable -> {
			register(registerable, COBWEB, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(net.minecraft.block.Blocks.COBWEB)));
			register(registerable, STICKY_COMPACT_COBWEB, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.STICKY_COMPACT_COBWEB)));
			register(registerable, SPIDER_CHRYSALIS, Features.SIMPLE_COBWEBBY_BLOCK, new SimpleCobwebbyBlockFeature.Config(BlockStateProvider.of(Blocks.SPIDER_CHRYSALIS)));
			register(registerable, SPIDER_EGG_COCOON, Features.SIMPLE_COBWEBBY_BLOCK, new SimpleCobwebbyBlockFeature.Config(BlockStateProvider.of(Blocks.SPIDER_EGG_COCOON)));
			register(registerable, COBWEBBY_DARK_OAK, Feature.TREE, new TreeFeatureConfig.Builder(
					BlockStateProvider.of(net.minecraft.block.Blocks.DARK_OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1),
					BlockStateProvider.of(net.minecraft.block.Blocks.DARK_OAK_LEAVES), new CobwebbyOakFoliagePlacer(ConstantIntProvider.create(0),
					ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
					.decorators(List.of(new TrunkVineTreeDecorator(), new LeavesVineTreeDecorator(0.25F)))
					.build());
			register(registerable, CREEPY_SPIDER_FOREST_VEGETATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
					new RandomFeatureEntry(registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(EERIE_TREE), 0.1F),
					new RandomFeatureEntry(registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(TREACHEROUS_TREE), 0.1F),
					new RandomFeatureEntry(registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(PlacedFeatures.COBWEBBY_DARK_OAK), 0.8F)),
					registerable.getRegistryLookup(RegistryKeys.PLACED_FEATURE).getOrThrow(PlacedFeatures.COBWEBBY_DARK_OAK)));
		};
	}
}
