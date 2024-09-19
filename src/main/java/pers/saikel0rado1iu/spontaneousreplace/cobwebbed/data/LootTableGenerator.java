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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.GroupEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.chrysalis.ChrysalisStyle;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.state.property.Properties;

import java.util.function.BiConsumer;

import static net.minecraft.block.Blocks.DIRT;
import static net.minecraft.block.Blocks.OAK_LEAVES;
import static net.minecraft.item.Items.*;
import static pers.saikel0rado1iu.spontaneousreplace.item.Items.*;

/**
 * <h2 style="color:FFC800">战利品表生成器</h2>
 * 蛛丝网迹的战利品表生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface LootTableGenerator {
	final class Block extends FabricBlockLootTableProvider {
		Block(FabricDataOutput dataOutput) {
			super(dataOutput);
		}
		
		@Override
		public void generate() {
			addDropWithSilkTouch(Blocks.COBWEBBY_SOIL, DIRT);
			addDrop(Blocks.GOSSAMER_CARPET, STRING);
			addDrop(Blocks.GOSSAMERY_LEAVES, leavesDrops(Blocks.GOSSAMERY_LEAVES, OAK_LEAVES, 0.05F, 0.0625F, 0.0833F, 0.1F));
			addDrop(Blocks.SPIDER_CHRYSALIS, spiderChrysalisDrops());
			addDrop(Blocks.STICKY_COMPACT_COBWEB, dropsWithShears(STICKY_COMPACT_GOSSAMER));
		}
		
		private LootTable.Builder spiderChrysalisDrops() {
			final net.minecraft.block.Block block = Blocks.SPIDER_CHRYSALIS;
			// 默认风格
			lootTables.put(block.getLootTableId().withSuffixedPath("_" + ChrysalisStyle.DEFAULT), LootTable.builder()
					// 中量线掉落: 随机掉落 2 ~ 4 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0.5F))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 1 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0.5F))
							.with(ItemEntry.builder(COMPACT_GOSSAMER).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 默认风格掉落物：50% 概率掉落动物掉落物；50% 概率掉落材料
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(GroupEntry.create(
											ItemEntry.builder(BEEF)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											ItemEntry.builder(PORKCHOP)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
									.conditionally(SurvivesExplosionLootCondition.builder()))
							.with(GroupEntry.create(
											// 粗铜掉落: 33.33% 概率掉落 1 ~ 3 个；受“幸运”与“时运”影响
											ItemEntry.builder(RAW_COPPER).weight(100)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 炼锭铜掉落: 33.33% 概率掉落 1 ~ 2 个；受“幸运”与“时运”影响
											ItemEntry.builder(COPPER_FOR_SMELTING_INGOT).weight(100)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 粗铁掉落: 31.67% 概率掉落 1 个；受“幸运”与“时运”影响
											ItemEntry.builder(RAW_IRON).weight(95)
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 钻石掉落: 1.67% 概率掉落 1 颗；不受“幸运”与“时运”影响
											ItemEntry.builder(DIAMOND).weight(5))
									.conditionally(SurvivesExplosionLootCondition.builder())))
			);
			// 大型风格
			lootTables.put(block.getLootTableId().withSuffixedPath("_" + ChrysalisStyle.LARGE), LootTable.builder()
					// 大量线掉落: 随机掉落 3 ~ 6 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0.5F))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 6)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 2 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(0.5F))
							.with(ItemEntry.builder(COMPACT_GOSSAMER)
									.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 默认风格掉落物：50% 概率掉落动物掉落物；50% 概率掉落材料
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(GroupEntry.create(
											// 牛掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(EntityType.COW.getLootTableId())
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 羊掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(EntityType.SHEEP.getLootTableId())
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 猪掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(EntityType.PIG.getLootTableId())
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
									.conditionally(SurvivesExplosionLootCondition.builder()))
							.with(GroupEntry.create(
											// 炼锭铜掉落: 33.33% 概率掉落 2 ~ 3 个；受“幸运”与“时运”影响
											ItemEntry.builder(COPPER_FOR_SMELTING_INGOT).weight(100)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 3)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 粗铁掉落: 33.33% 概率掉落 1 ~ 3 个；受“幸运”与“时运”影响
											ItemEntry.builder(RAW_IRON).weight(100)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 粗金掉落: 31.67% 概率掉落 1 ~ 2 个；受“幸运”与“时运”影响
											ItemEntry.builder(RAW_IRON).weight(95)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 钻石掉落: 1.67% 概率掉落 1 ~ 2 颗；不受“幸运”与“时运”影响
											ItemEntry.builder(DIAMOND).weight(5)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))
									.conditionally(SurvivesExplosionLootCondition.builder())))
			);
			LootTable.Builder builder = LootTable.builder();
			for (ChrysalisStyle style : Properties.CHRYSALIS_STYLE.getValues()) {
				if (ChrysalisStyle.PLACEHOLDER == style || ChrysalisStyle.PLACEHOLDER_SHORT == style) continue;
				builder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
						.with(LootTableEntry.builder(block.getLootTableId().withSuffixedPath("_" + style.asString())))
						.conditionally(SurvivesExplosionLootCondition.builder())
						.conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(Properties.CHRYSALIS_STYLE, style))));
			}
			return builder;
		}
	}
	
	final class Entity extends SimpleFabricLootTableProvider {
		public Entity(FabricDataOutput dataOutput) {
			super(dataOutput, LootContextTypes.ENTITY);
		}
		
		@Override
		public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
			exporter.accept(EntityTypes.GUARD_SPIDER.getLootTableId(), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEATHER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			exporter.accept(EntityTypes.SPRAY_POISON_SPIDER.getLootTableId(), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_FANG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			exporter.accept(EntityTypes.WEAVING_WEB_SPIDER.getLootTableId(), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-1, 1))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
		}
	}
}
