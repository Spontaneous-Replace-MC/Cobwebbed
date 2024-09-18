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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.gen.chunk;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.BlockState;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSource;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterLists;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorCustomEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorInstanceEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorUpgradableEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyDefaultBiomeParametersEvents;
import pers.saikel0rado1iu.silk.api.generate.world.WorldPresetEntry;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome.BiomeKeys;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.BiomeSourceParamLists;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.util.ClassicBiomeParameters;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ChunkGeneratorSetting;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ClassicChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <h2 style="color:FFC800">蛛丝网迹经典区块生成器</h2>
 * 此接口用于修改 {@link ClassicChunkGenerator} 的方法
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface CobwebbedClassicChunkGenerator {
	Map<ClassicChunkGenerator, GenerateSetting> SETTING_MAP = Maps.newHashMap();
	String VERSION = ClassicChunkGenerator.DEFAULT_VERSION;
	int VERSION_INDEX = 2;
	
	static void register() {
		ModifyDefaultBiomeParametersEvents.MODIFY_NON_VANILLA_GENERATED_BIOME.register((parameters, biomes) -> {
			if (!(parameters instanceof ClassicBiomeParameters) || !parameters.getClass().equals(ClassicBiomeParameters.class)) {
				return Map.entry(ActionResult.PASS, biomes);
			}
			ImmutableList.Builder<RegistryKey<Biome>> builder = new ImmutableList.Builder<>();
			return Map.entry(ActionResult.PASS, builder.addAll(biomes).add(BiomeKeys.CREEPY_SPIDER_FOREST).build());
		});
		ModifyChunkGeneratorInstanceEvents.MODIFY_DATA_GEN_INSTANCE.register(new ModifyChunkGeneratorInstanceEvents.ModifyDataGenInstance() {
			@Override
			public <T extends ChunkGenerator> T getInstance(T originGenerator, DynamicRegistryManager registryManager) {
				if (!(originGenerator instanceof ClassicChunkGenerator generator) || !originGenerator.getClass().equals(ClassicChunkGenerator.class)) {
					return originGenerator;
				}
				RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
						.getEntry(MultiNoiseBiomeSourceParameterLists.OVERWORLD).orElseThrow();
				RegistryEntry<Biome> biome = registryManager.get(RegistryKeys.BIOME).getEntry(BiomeKeys.CREEPY_SPIDER_FOREST).orElseThrow();
				RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(ChunkGeneratorSetting.CLASSIC).orElseThrow();
				//noinspection unchecked
				return (T) new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(new FixedBiomeSource(biome)), settings, generator.version()) {
				};
			}
		});
		ModifyChunkGeneratorInstanceEvents.MODIFY_REGISTER_INSTANCE.register(new ModifyChunkGeneratorInstanceEvents.ModifyRegisterInstance() {
			@Override
			public <T extends ChunkGenerator> T getInstance(T originGenerator, Registerable<WorldPreset> registerable, WorldPresetEntry.Registrar registrar) {
				if (!(originGenerator instanceof ClassicChunkGenerator generator) || !originGenerator.getClass().equals(ClassicChunkGenerator.class)) {
					return originGenerator;
				}
				RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup.getOrThrow(BiomeSourceParamLists.CLASSIC);
				RegistryEntry<Biome> biome = registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(BiomeKeys.CREEPY_SPIDER_FOREST);
				RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSetting.CLASSIC);
				//noinspection unchecked
				return (T) new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(new FixedBiomeSource(biome)), settings, generator.version()) {
				};
			}
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_VERSION.register((upgradable, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator) || !upgradable.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, version);
			}
			List<String> versions = Lists.newArrayList(Arrays.stream(version.split(ClassicChunkGenerator.VERSION_DELIMITER)).toList());
			if (versions.size() < VERSION_INDEX) {
				for (int count = VERSION_INDEX - versions.size(); count > 0; count--) {
					if (1 == count) versions.add(VERSION);
					else versions.add(ClassicChunkGenerator.DEFAULT_VERSION);
				}
			} else {
				versions.set(VERSION_INDEX - 1, VERSION);
			}
			return Map.entry(ActionResult.PASS, String.join(ClassicChunkGenerator.VERSION_DELIMITER, versions));
		});
		ModifyChunkGeneratorCustomEvents.MODIFY_GET_BIOME_SOURCE.register((custom, source, pos) -> {
			if (!(custom instanceof ClassicChunkGenerator generator) || !custom.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, source);
			}
			if (!SETTING_MAP.containsKey(generator)) SETTING_MAP.put(generator, new GenerateSetting(generator));
			return Map.entry(ActionResult.PASS, SETTING_MAP.get(generator).isInBiome(pos) ? generator.getAdditionalBiomeSource(BiomeKeys.CREEPY_SPIDER_FOREST).orElseThrow() : generator.getBiomeSource());
		});
		ModifyChunkGeneratorCustomEvents.MODIFY_GET_TERRAIN_NOISE.register((custom, state, pos, originBlock, estimateSurfaceHeight) -> {
			if (!(custom instanceof ClassicChunkGenerator generator) || !custom.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, state);
			}
			if (!SETTING_MAP.containsKey(generator)) SETTING_MAP.put(generator, new GenerateSetting(generator));
			return Map.entry(ActionResult.PASS, SETTING_MAP.get(generator).setTerrainNoise(pos, originBlock, estimateSurfaceHeight));
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_REFRESH.register((upgradable, needRefresh, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator) || !upgradable.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, needRefresh);
			}
			return Map.entry(ActionResult.PASS, false);
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_UPGRADE.register((upgradable, needUpgrade, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator) || !upgradable.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, needUpgrade);
			}
			return Map.entry(ActionResult.PASS, false);
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_DOWNGRADE.register((upgradable, needDowngrade, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator) || !upgradable.getClass().equals(ClassicChunkGenerator.class)) {
				return Map.entry(ActionResult.PASS, needDowngrade);
			}
			return Map.entry(ActionResult.PASS, false);
		});
	}
	
	class GenerateSetting {
		public static final int POS_BASE_RADIUS = 2000;
		public static final int POS_SHIFTING_RADIUS = 200;
		public static final int SIZE_BASE_RADIUS = 200;
		public static final int SIZE_SHIFTING_RADIUS = 30;
		public final int baseCenterX = 0;
		public final int baseCenterZ = 0;
		public final int centerX;
		public final int centerZ;
		public final int posRadius;
		public final int biomeRadius;
		public final int biomeBaseHeight;
		public final ClassicChunkGenerator generator;
		public final ChunkGeneratorSettings settings;
		
		GenerateSetting(ClassicChunkGenerator generator) {
			long seed = generator.seed();
			this.generator = generator;
			this.settings = generator.getSettings().value();
			this.biomeBaseHeight = settings.seaLevel() + 3;
			this.posRadius = (int) (POS_BASE_RADIUS + (seed % POS_SHIFTING_RADIUS));
			this.biomeRadius = (int) (SIZE_BASE_RADIUS + (seed % SIZE_SHIFTING_RADIUS));
			double radians = Math.toRadians(seed % 360);
			this.centerX = (int) (baseCenterX + Math.cos(radians) * posRadius);
			this.centerZ = (int) (baseCenterZ + Math.sin(radians) * posRadius);
		}
		
		public boolean isInBiome(BlockPos pos) {
			// 计算点与圆心的距离
			double distance = Math.sqrt(Math.pow(pos.getX() - centerX, 2) + Math.pow(pos.getZ() - centerZ, 2));
			// 如果距离小于等于半径，则该点在圆内
			return distance <= biomeRadius;
		}
		
		public Optional<BlockState> setTerrainNoise(BlockPos pos, Optional<BlockState> originBlock, int estimateSurfaceHeight) {
			if (!isInBiome(pos)) return originBlock;
			if (estimateSurfaceHeight < biomeBaseHeight
					&& pos.getY() > estimateSurfaceHeight
					&& pos.getY() < biomeBaseHeight
					&& originBlock.isPresent()
					&& originBlock.get().isAir())
				return Optional.of(settings.defaultBlock());
			return originBlock;
		}
	}
}
