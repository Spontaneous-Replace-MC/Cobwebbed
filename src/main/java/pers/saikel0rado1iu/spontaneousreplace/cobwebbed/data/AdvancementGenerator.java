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

import com.google.common.base.Suppliers;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtString;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.generate.data.AdvancementGenUtil;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.Cobwebbed;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome.BiomeKeys;
import pers.saikel0rado1iu.spontaneousreplace.item.Items;

import java.util.List;
import java.util.Optional;
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
	static final AdvancementEntry ROOT = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, Cobwebbed.INSTANCE.id())
			.display(COBWEBBY_SOIL, new Identifier("textures/block/calcite.png"), AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	public static final AdvancementEntry HAVE_A_NEW_RANGED = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "have_a_new_ranged")
			.parent(ROOT)
			.display(Items.RECURVE_BOW, null, AdvancementFrame.TASK, true, true, false)
			.criterion(RecipeProvider.hasItem(Items.SLINGSHOT), InventoryChangedCriterion.Conditions.items(Items.SLINGSHOT))
			.criterion(RecipeProvider.hasItem(Items.RECURVE_BOW), InventoryChangedCriterion.Conditions.items(Items.RECURVE_BOW))
			.criterion(RecipeProvider.hasItem(Items.ARBALEST), InventoryChangedCriterion.Conditions.items(Items.ARBALEST))
			.criterion(RecipeProvider.hasItem(Items.COMPOUND_BOW), InventoryChangedCriterion.Conditions.items(Items.COMPOUND_BOW))
			.criterion(RecipeProvider.hasItem(Items.JUGER_REPEATING_CROSSBOW), InventoryChangedCriterion.Conditions.items(Items.JUGER_REPEATING_CROSSBOW))
			.criterion(RecipeProvider.hasItem(Items.MARKS_CROSSBOW), InventoryChangedCriterion.Conditions.items(Items.MARKS_CROSSBOW))
			.criterion(RecipeProvider.hasItem(Items.ARROWPROOF_VEST), InventoryChangedCriterion.Conditions.items(Items.ARROWPROOF_VEST))
			.requirements(AdvancementRequirements.anyOf(List.of(
					RecipeProvider.hasItem(Items.SLINGSHOT),
					RecipeProvider.hasItem(Items.RECURVE_BOW),
					RecipeProvider.hasItem(Items.ARBALEST),
					RecipeProvider.hasItem(Items.COMPOUND_BOW),
					RecipeProvider.hasItem(Items.JUGER_REPEATING_CROSSBOW),
					RecipeProvider.hasItem(Items.MARKS_CROSSBOW),
					RecipeProvider.hasItem(Items.ARROWPROOF_VEST))))
			.build();
	static final AdvancementEntry KILL_A_NEW_SPIDER = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "kill_a_new_spider")
			.parent(ROOT)
			.display(SPIDER_LEG, null, AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	static final AdvancementEntry SHOT_SPRAY_POISON_SPIDER = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "shot_spray_poison_spider")
			.parent(KILL_A_NEW_SPIDER)
			.display(Suppliers.memoize(() -> {
				ItemStack stack = new ItemStack(TIPPED_ARROW);
				stack.setSubNbt("Potion", NbtString.of("poison"));
				return stack;
			}).get(), null, AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	static final AdvancementEntry HAVE_A_DEPOISON_SPIDER_LEG = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "have_a_depoison_spider_leg")
			.parent(KILL_A_NEW_SPIDER)
			.display(DEPOISON_SPIDER_LEG, null, AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	static final AdvancementEntry HAVE_EFFECT_SPIDER_CAMOUFLAGE = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "have_effect_spider_camouflage")
			.parent(HAVE_A_DEPOISON_SPIDER_LEG)
			.display(SPIDER_EGG_COCOON, null, AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	static final AdvancementEntry KILL_ALL_SPIDERS = AdvancementGenUtil.builder(SpontaneousReplace.INSTANCE, "kill_all_spiders")
			.parent(KILL_A_NEW_SPIDER)
			.display(GUARD_SPIDER_SPAWN_EGG, null, AdvancementFrame.TASK, true, true, false)
			.criterion("biome", TickCriterion.Conditions.createLocation(Optional.of(EntityPredicate.Builder.create().location(LocationPredicate.Builder.createBiome(BiomeKeys.CREEPY_SPIDER_FOREST)).build())))
			.build();
	
	AdvancementGenerator(FabricDataOutput output) {
		super(output);
	}
	
	@Override
	public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
		consumer.accept(ROOT);
		consumer.accept(KILL_A_NEW_SPIDER);
		consumer.accept(SHOT_SPRAY_POISON_SPIDER);
		consumer.accept(HAVE_A_DEPOISON_SPIDER_LEG);
		consumer.accept(HAVE_EFFECT_SPIDER_CAMOUFLAGE);
		consumer.accept(KILL_ALL_SPIDERS);
	}
}
