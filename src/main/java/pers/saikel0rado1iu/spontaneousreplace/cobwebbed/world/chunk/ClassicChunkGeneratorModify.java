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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.chunk;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
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
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionTypes;
import net.minecraft.world.gen.WorldPreset;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import pers.saikel0rado1iu.silk.api.base.common.noise.PerlinNoise;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorCustomEvents;
import pers.saikel0rado1iu.silk.api.event.modplus.ModifyChunkGeneratorUpgradableEvents;
import pers.saikel0rado1iu.silk.api.generate.world.WorldPresetEntry;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome.BiomeKeys;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source.BiomeSourceParamLists;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ChunkGeneratorSetting;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ClassicChunkGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <h2 style="color:FFC800">经典区块生成器修改</h2>
 * 此接口用于修改 {@link ClassicChunkGenerator} 的方法
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface ClassicChunkGeneratorModify {
	String VERSION_DELIMITER = ":";
	String DEFAULT_VERSION = "00";
	String VERSION = "00";
	int VERSION_INDEX = 2;
	
	static ClassicChunkGenerator getInstance(DynamicRegistryManager registryManager) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registryManager.get(RegistryKeys.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST)
				.getEntry(MultiNoiseBiomeSourceParameterLists.OVERWORLD).orElseThrow();
		RegistryEntry<Biome> biome = registryManager.get(RegistryKeys.BIOME).getEntry(BiomeKeys.CREEPY_SPIDER_FOREST).orElseThrow();
		RegistryEntry<ChunkGeneratorSettings> settings = registryManager.get(RegistryKeys.CHUNK_GENERATOR_SETTINGS).getEntry(ChunkGeneratorSetting.CLASSIC).orElseThrow();
		return new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(new FixedBiomeSource(biome)), settings, VERSION) {
		};
	}
	
	private static ClassicChunkGenerator getInstance(Registerable<WorldPreset> registerable, WorldPresetEntry.Registrar registrar) {
		RegistryEntry<MultiNoiseBiomeSourceParameterList> parameters = registrar.multiNoisePresetLookup.getOrThrow(BiomeSourceParamLists.CLASSIC);
		RegistryEntry<Biome> biome = registerable.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(BiomeKeys.CREEPY_SPIDER_FOREST);
		RegistryEntry<ChunkGeneratorSettings> settings = registrar.chunkGeneratorSettingsLookup.getOrThrow(ChunkGeneratorSetting.CLASSIC);
		return new ClassicChunkGenerator(MultiNoiseBiomeSource.create(parameters), ImmutableList.of(new FixedBiomeSource(biome)), settings, VERSION) {
		};
	}
	
	static void register(RegistryKey<WorldPreset> worldPreset, Registerable<WorldPreset> registerable) {
		WorldPresetEntry.Registrar registrar = new WorldPresetEntry.Registrar(registerable);
		registrar.register(worldPreset, new DimensionOptions(registerable.getRegistryLookup(RegistryKeys.DIMENSION_TYPE).getOrThrow(DimensionTypes.OVERWORLD), getInstance(registerable, registrar)));
	}
	
	static void register() {
		ModifyChunkGeneratorUpgradableEvents.MODIFY_VERSION.register((upgradable, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, version);
			List<String> versions = Lists.newArrayList(Arrays.stream(version.split(VERSION_DELIMITER)).toList());
			if (versions.size() < VERSION_INDEX) {
				for (int count = VERSION_INDEX - versions.size(); count > 0; count--) {
					if (1 == count) versions.add(VERSION);
					else versions.add(DEFAULT_VERSION);
				}
			} else {
				versions.set(VERSION_INDEX - 1, VERSION);
			}
			return Map.entry(ActionResult.PASS, String.join(VERSION_DELIMITER, versions));
		});
		ModifyChunkGeneratorCustomEvents.MODIFY_GET_BIOME_SOURCE.register((custom, source, pos) -> {
			if (!(custom instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, source);
			return Map.entry(ActionResult.PASS, Data.canSetBiome(generator, pos) ? getVariantBiomeSource(generator, BiomeKeys.CREEPY_SPIDER_FOREST) : generator.getBiomeSource());
		});
		ModifyChunkGeneratorCustomEvents.MODIFY_GET_TERRAIN_NOISE.register((custom, state, pos, originBlock, estimateSurfaceHeight) -> {
			if (!(custom instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, state);
			ChunkGeneratorSettings settings = generator.getSettings().value();
			final int biomeBaseHeight = settings.seaLevel() + 3;
			final long seed = generator.seed();
			if (!Data.isInBiome(pos.getX(), pos.getY(), pos.getZ(), seed)) return Map.entry(ActionResult.PASS, originBlock);
			if (estimateSurfaceHeight < biomeBaseHeight) {
				int noiseX = (int) Math.abs(Data.sizeRadius + (pos.getX() - Data.posX));
				int noiseZ = (int) Math.abs(Data.sizeRadius + (pos.getZ() - Data.posZ));
				final double ratio = Data.getBiomeRatio(pos.getX(), pos.getZ(), seed, Data.getNoise(seed)[noiseX][noiseZ]);
				if (pos.getY() < (ratio * biomeBaseHeight + (1 - ratio) * estimateSurfaceHeight)) {
					return Map.entry(ActionResult.PASS, Optional.ofNullable(settings.defaultBlock()));
				}
			}
			return Map.entry(ActionResult.PASS, originBlock);
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_REFRESH.register((upgradable, needRefresh, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, needRefresh);
			return Map.entry(ActionResult.PASS, false);
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_UPGRADE.register((upgradable, needUpgrade, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, needUpgrade);
			return Map.entry(ActionResult.PASS, false);
		});
		ModifyChunkGeneratorUpgradableEvents.MODIFY_NEED_DOWNGRADE.register((upgradable, needDowngrade, pos, chunk, version) -> {
			if (!(upgradable instanceof ClassicChunkGenerator generator)) return Map.entry(ActionResult.PASS, needDowngrade);
			return Map.entry(ActionResult.PASS, false);
		});
	}
	
	private static FixedBiomeSource getVariantBiomeSource(ClassicChunkGenerator generator, RegistryKey<Biome> biome) {
		FixedBiomeSource fixedBiomeSource = null;
		for (FixedBiomeSource biomeSource : generator.additionalBiomeSources()) {
			fixedBiomeSource = biomeSource;
			for (RegistryEntry<Biome> entry : biomeSource.getBiomes()) {
				if (biome.equals(entry.getKey().orElseThrow())) return biomeSource;
			}
		}
		return fixedBiomeSource;
	}
	
	class Data {
		public static final int POS_BASE_RADIUS = 2000;
		public static final int POS_SHIFTING_RADIUS = 200;
		public static final int SIZE_BASE_RADIUS = 200;
		public static final int SIZE_SHIFTING_RADIUS = 30;
		public static final int NOISE_OCTAVES = (int) (Math.log((SIZE_BASE_RADIUS + SIZE_SHIFTING_RADIUS) * 2) / Math.log(2)) + 1;
		public static final int NOISE_SIZE = (int) Math.pow(2, NOISE_OCTAVES);
		public static final float NOISE_BIAS = 0.01f;
		public static final int CENTER_X = 0;
		public static final int CENTER_Z = 0;
		public static long seed = 0;
		private static double[][] noise = null;
		private static int posX = 0;
		private static int posZ = 0;
		private static long sizeRadius = 0;
		
		private static double[][] getNoise(long seed) {
			if (null == noise) noise = new PerlinNoise(NOISE_SIZE / 10, NOISE_SIZE / 10, 0, 2, 1, 1, seed).scale(NOISE_SIZE, NOISE_SIZE).noiseMap();
			return noise;
		}
		
		private static double getBiomeRatio(int x, int z, long seed, double origin) {
			float ratio = 0;
			float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
			if (distance > 0.3) {
				float r = (1 - distance) / 0.7F;
				ratio = r * 0.3F + (1 - r) * 0.5F;
			}
			if (origin > ratio) return 1;
			return (origin / ratio) * 0.9F + (1 - (origin / ratio)) * 0.1F;
		}
		
		private static boolean canSetNoise(int x, int z, long seed) {
			int noiseX = (int) Math.abs(sizeRadius + (x - posX));
			int noiseZ = (int) Math.abs(sizeRadius + (z - posZ));
			float ratio = 0;
			float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
			if (distance > 0.3) {
				float r = (1 - distance) / 0.7F;
				ratio = r * 0.3F + (1 - r) * 0.5F;
			}
			return !(noiseX < 0 || noiseX >= NOISE_SIZE)
					&& !(noiseZ < 0 || noiseZ >= NOISE_SIZE)
					&& getNoise(seed)[noiseX][noiseZ] > ratio;
		}
		
		private static float distanceFromBiomeCenterRatio(int x, int y, int z, long seed) {
			if (0 == posX && 0 == posZ && 0 == sizeRadius) {
				long posRadius = seed > 0 ? POS_BASE_RADIUS + seed % POS_SHIFTING_RADIUS
						: POS_BASE_RADIUS - seed % POS_SHIFTING_RADIUS;
				double radian = Math.toRadians(seed % 360);
				posX = (int) (CENTER_X + posRadius * Math.cos(radian));
				posZ = (int) (CENTER_Z + posRadius * Math.sin(radian));
				sizeRadius = seed > 0 ? SIZE_BASE_RADIUS + seed % SIZE_SHIFTING_RADIUS
						: SIZE_BASE_RADIUS - seed % SIZE_SHIFTING_RADIUS;
			}
			return (float) ((Math.pow(x - posX, 2) + Math.pow(z - posZ, 2)) / Math.pow(sizeRadius, 2));
		}
		
		private static boolean isInBiome(int x, int y, int z, long seed) {
			return distanceFromBiomeCenterRatio(x, y, z, seed) <= 1;
		}
		
		private static boolean canSetBiome(ClassicChunkGenerator generator, BlockPos pos) {
			final int x = pos.getX();
			final int y = pos.getY();
			final int z = pos.getZ();
			final long seed = generator.seed();
			return isInBiome(x, y, z, seed) && canSetNoise(x, z, seed);
		}
	}
}
