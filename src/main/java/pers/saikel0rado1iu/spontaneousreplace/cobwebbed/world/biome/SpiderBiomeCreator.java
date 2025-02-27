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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.gen.feature.PlacedFeatures;

/**
 * <h2 style="color:FFC800">蜘蛛生物群系创建器</h2>
 * 蛛丝马迹的专属蜘蛛生物群系的创建器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface SpiderBiomeCreator {
	private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
		DefaultBiomeFeatures.addLandCarvers(generationSettings);
		DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
		DefaultBiomeFeatures.addDungeons(generationSettings);
		DefaultBiomeFeatures.addMineables(generationSettings);
		DefaultBiomeFeatures.addSprings(generationSettings);
		DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
	}
	
	/**
	 * 创建蜘蛛诡林
	 *
	 * @param featureLookup featureLookup
	 * @param carverLookup  carverLookup
	 * @return 蜘蛛诡林生物群系
	 */
	static Biome createCreepySpiderForest(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
		SpawnSettings.Builder builder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addCaveMobs(builder);
		GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
		addBasicFeatures(lookupBackedBuilder);
		lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.COBWEB);
		lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.STICKY_COMPACT_COBWEB);
		lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.SPIDER_CHRYSALIS);
		lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.SPIDER_EGG_COCOON);
		lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatures.CREEPY_SPIDER_FOREST_VEGETATION);
		DefaultBiomeFeatures.addDefaultOres(lookupBackedBuilder);
		DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);
		DefaultBiomeFeatures.addForestGrass(lookupBackedBuilder);
		DefaultBiomeFeatures.addDefaultMushrooms(lookupBackedBuilder);
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 1000, 1, 2));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CAVE_SPIDER, 1000, 1, 2));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityTypes.GUARD_SPIDER, 1000, 1, 2));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityTypes.SPRAY_POISON_SPIDER, 1000, 1, 2));
		builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityTypes.WEAVING_WEB_SPIDER, 1000, 1, 2));
		MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST);
		return new Biome.Builder()
				.precipitation(true)
				.temperature(0.7F)
				.downfall(0.8F)
				.effects(new BiomeEffects.Builder()
						.waterColor(0x617B64)
						.waterFogColor(0x232317)
						.fogColor(0x666F80)
						.skyColor(0x3D424D)
						.foliageColor(0x384D2E)
						.grassColor(0x204010)
						.moodSound(BiomeMoodSound.CAVE)
						.music(musicSound)
						.build())
				.spawnSettings(builder.build())
				.generationSettings(lookupBackedBuilder.build())
				.build();
	}
}
