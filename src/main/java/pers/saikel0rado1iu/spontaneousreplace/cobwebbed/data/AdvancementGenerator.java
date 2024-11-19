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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.*;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.Potions;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import pers.saikel0rado1iu.silk.api.generate.data.AdvancementGenUtil;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.Cobwebbed;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.effect.StatusEffects;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome.BiomeKeys;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.item.Items.TIPPED_ARROW;
import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items.*;

/**
 * <h2 style="color:FFC800">进度生成器</h2>
 * 蛛丝网迹的进度生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
final class AdvancementGenerator extends FabricAdvancementProvider {
	static final AdvancementEntry __ROOT = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, Cobwebbed.INSTANCE.id())
			.display(COBWEBBY_SOIL, Identifier.of("textures/block/calcite.png"), AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createTick())
			.build();
	static final AdvancementEntry KILL_A_NEW_SPIDER = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "kill_a_new_spider")
			.parent(__ROOT)
			.display(SPIDER_LEG, null, AdvancementFrame.TASK, true, true, false)
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPIDER_LARVA)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.GUARD_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.WEAVING_WEB_SPIDER)))
			.requirements(AdvancementRequirements.anyOf(List.of(
					Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(),
					Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString())))
			.build();
	static final AdvancementEntry SHOT_SPRAY_POISON_SPIDER = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "shot_spray_poison_spider")
			.parent(KILL_A_NEW_SPIDER)
			.display(PotionContentsComponent.createStack(TIPPED_ARROW, Potions.POISON), null, AdvancementFrame.TASK, true, true, false)
			.criterion("shot_spray_poison_spider", PlayerHurtEntityCriterion.Conditions.create(
					Optional.of(DamagePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(EntityType.ARROW).build()).build()),
					Optional.of(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER).build())))
			.build();
	static final AdvancementEntry HAVE_A_DEPOISON_SPIDER_LEG = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "have_a_depoison_spider_leg")
			.parent(KILL_A_NEW_SPIDER)
			.display(DEPOISON_SPIDER_LEG, null, AdvancementFrame.TASK, true, true, false)
			.criterion(RecipeProvider.hasItem(DEPOISON_SPIDER_LEG), InventoryChangedCriterion.Conditions.items(DEPOISON_SPIDER_LEG))
			.build();
	static final AdvancementEntry HAVE_EFFECT_SPIDER_CAMOUFLAGE = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "have_effect_spider_camouflage")
			.parent(HAVE_A_DEPOISON_SPIDER_LEG)
			.display(SPIDER_EGG_COCOON, null, AdvancementFrame.TASK, true, true, false)
			.criterion(Registries.STATUS_EFFECT.getId(StatusEffects.SPIDER_CAMOUFLAGE.value()) + "",
					EffectsChangedCriterion.Conditions.create(EntityEffectPredicate.Builder.create().addEffect(StatusEffects.SPIDER_CAMOUFLAGE)))
			.build();
	static final AdvancementEntry KILL_ALL_SPIDERS = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "kill_all_spiders")
			.parent(KILL_A_NEW_SPIDER)
			.display(GUARD_SPIDER_SPAWN_EGG, null, AdvancementFrame.TASK, true, true, false)
			.criterion(Registries.ENTITY_TYPE.getId(EntityType.SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityType.CAVE_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.CAVE_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPIDER_LARVA).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPIDER_LARVA)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.GUARD_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.GUARD_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.SPRAY_POISON_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.SPRAY_POISON_SPIDER)))
			.criterion(Registries.ENTITY_TYPE.getId(EntityTypes.WEAVING_WEB_SPIDER).toString(), OnKilledCriterion.Conditions
					.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityTypes.WEAVING_WEB_SPIDER)))
			.build();
	
	AdvancementGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(output, registryLookup);
	}
	
	@Override
	public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
		RegistryEntryLookup<Biome> registryEntryLookup = wrapperLookup.getWrapperOrThrow(RegistryKeys.BIOME);
		// 真正的 ROOT
		consumer.accept(AdvancementGenUtil
				.builder(SpontaneousReplace.INSTANCE, Cobwebbed.INSTANCE.id())
				.display(COBWEBBY_SOIL, Identifier.of("textures/block/calcite.png"), AdvancementFrame.TASK, true, true, false)
				.criterion("biome", TickCriterion.Conditions.createLocation(LocationPredicate.Builder.createBiome(registryEntryLookup.getOrThrow(BiomeKeys.CREEPY_SPIDER_FOREST))))
				.build());
		consumer.accept(KILL_A_NEW_SPIDER);
		consumer.accept(SHOT_SPRAY_POISON_SPIDER);
		consumer.accept(HAVE_A_DEPOISON_SPIDER_LEG);
		consumer.accept(HAVE_EFFECT_SPIDER_CAMOUFLAGE);
		consumer.accept(KILL_ALL_SPIDERS);
	}
}
