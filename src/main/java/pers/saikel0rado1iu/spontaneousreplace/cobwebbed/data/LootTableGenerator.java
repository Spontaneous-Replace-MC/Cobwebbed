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
import net.minecraft.loot.function.*;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.chrysalis.ChrysalisStyle;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.state.property.Properties;

import java.util.concurrent.CompletableFuture;
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
		Block(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, registryLookup);
		}
		
		private static RegistryKey<LootTable> getTempMobKey(EntityType<?> entityType) {
			return RegistryKey.of(RegistryKeys.LOOT_TABLE, Registries.ENTITY_TYPE.getId(entityType).withPrefixedPath("blocks/template_"));
		}
		
		@Override
		public void generate() {
			addDropWithSilkTouch(Blocks.COBWEBBY_SOIL, DIRT);
			addDrop(Blocks.GOSSAMER_CARPET, STRING);
			addDrop(Blocks.GOSSAMERY_LEAVES, leavesDrops(Blocks.GOSSAMERY_LEAVES, OAK_LEAVES, 0.05F, 0.0625F, 0.0833F, 0.1F));
			addDrop(Blocks.SPIDER_CHRYSALIS, spiderChrysalisDrops());
			addDrop(Blocks.STICKY_COMPACT_COBWEB, dropsWithShears(STICKY_COMPACT_GOSSAMER));
		}
		
		private void templateMobDrops() {
			lootTables.put(getTempMobKey(EntityType.PIG), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(BEEF)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.COW), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(LEATHER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(BEEF)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.SHEEP), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(WHITE_WOOL)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(MUTTON)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.ZOMBIE), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 有 5% 的概率掉落僵尸头颅
							.with(ItemEntry.builder(ZOMBIE_HEAD).weight(5))
							.with(EmptyEntry.builder().weight(95))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(ROTTEN_FLESH)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.SKELETON), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 有 5% 的概率掉落骷髅头颅
							.with(ItemEntry.builder(SKELETON_SKULL).weight(5))
							.with(EmptyEntry.builder().weight(95))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(BONE)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.VILLAGER), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(PAPER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(EMERALD)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
			lootTables.put(getTempMobKey(EntityType.WANDERING_TRADER), LootTable.builder()
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(LEAD)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2))))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(LAPIS_LAZULI)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder())));
		}
		
		private LootTable.Builder spiderChrysalisDrops() {
			final net.minecraft.block.Block block = Blocks.SPIDER_CHRYSALIS;
			// 生成模板生物掉落物
			templateMobDrops();
			// 默认风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.DEFAULT)), LootTable.builder()
					// 中量线掉落: 随机掉落 2 ~ 4 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 1 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 默认风格掉落物
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 50% 概率掉落动物掉落物
							.with(GroupEntry.create(
											// 猪掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.PIG)).weight(100)
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 牛掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.COW)).weight(100)
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 羊掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.SHEEP)).weight(100)
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
									.conditionally(SurvivesExplosionLootCondition.builder()))
							// 50% 概率掉落少量材料
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
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.LARGE)), LootTable.builder()
					// 大量线掉落: 随机掉落 3 ~ 6 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 6)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 2 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER)
									.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 大型风格掉落物
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 50% 概率掉落双倍动物掉落物
							.with(GroupEntry.create(
											// 猪掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.PIG)).weight(100)
													.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 牛掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.COW)).weight(100)
													.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 羊掉落物: 33.33% 概率；受“幸运”与“时运”影响
											LootTableEntry.builder(getTempMobKey(EntityType.SHEEP)).weight(100)
													.apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
									.conditionally(SurvivesExplosionLootCondition.builder()))
							// 50% 概率掉落中量材料
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
			// 小型风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.SMALL)), LootTable.builder()
					// 少量线掉落: 随机掉落 1 ~ 3 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 小型风格掉落物：概率掉落微量材料
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(GroupEntry.create(
											// 粗铜掉落: 49.67% 概率掉落 1 ~ 2 个；受“幸运”与“时运”影响
											ItemEntry.builder(RAW_COPPER).weight(149)
													.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 炼锭铜掉落: 49.67% 概率掉落 1 个；受“幸运”与“时运”影响
											ItemEntry.builder(COPPER_FOR_SMELTING_INGOT).weight(149)
													.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)),
											// 钻石掉落: 0.67% 概率掉落 1 颗；不受“幸运”与“时运”影响
											ItemEntry.builder(DIAMOND).weight(2))
									.conditionally(SurvivesExplosionLootCondition.builder())))
			);
			// 类人风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.HUMANOID)), LootTable.builder()
					// 中量线掉落: 随机掉落 2 ~ 4 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 1 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 类人风格掉落物
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 33.33% 概率掉落僵尸掉落物
							.with(LootTableEntry.builder(getTempMobKey(EntityType.ZOMBIE)).weight(25)
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							// 33.33% 概率掉落骷髅掉落物
							.with(LootTableEntry.builder(getTempMobKey(EntityType.SKELETON)).weight(25)
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							// 33.33% 概率掉落装备
							.with(GroupEntry.create(
									// 钻石掉落: 4% 概率掉落 1 ~ 2颗
									ItemEntry.builder(DIAMOND).weight(1).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))),
									// 精铜盔甲掉落: 12% 概率掉落
									ItemEntry.builder(REFINED_COPPER_CHESTPLATE).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 铁护腿掉落: 12% 概率掉落
									ItemEntry.builder(IRON_LEGGINGS).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 铜铁头盔掉落: 12% 概率掉落
									ItemEntry.builder(CUFE_ALLOY_HELMET).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 金铜靴子掉落: 12% 概率掉落
									ItemEntry.builder(AUCU_ALLOY_BOOTS).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 钢斧掉落: 12% 概率掉落
									ItemEntry.builder(STEEL_AXE).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 铜铁剑掉落: 12% 概率掉落
									ItemEntry.builder(CUFE_ALLOY_SWORD).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 弩掉落: 12% 概率掉落
									ItemEntry.builder(CROSSBOW).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F))),
									// 反曲弓掉落: 12% 概率掉落
									ItemEntry.builder(RECURVE_BOW).weight(3).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.3F, 0.7F)))))
							.conditionally(SurvivesExplosionLootCondition.builder()))
			);
			// 村民风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.VILLAGER)), LootTable.builder()
					// 中量线掉落: 随机掉落 2 ~ 4 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 4)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 1 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 默认风格掉落物
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							// 50% 概率掉落村民掉落物
							.with(LootTableEntry.builder(getTempMobKey(EntityType.VILLAGER))
									.conditionally(SurvivesExplosionLootCondition.builder()))
							// 50% 概率掉落流浪商人掉落物
							.with(LootTableEntry.builder(getTempMobKey(EntityType.WANDERING_TRADER))
									.conditionally(SurvivesExplosionLootCondition.builder())))
			);
			// 小鸡风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.CHICKEN)), LootTable.builder()
					// 少量线掉落: 随机掉落 1 ~ 3 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 鸡肉掉落：随机掉落 1 ~ 3 个；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(CHICKEN)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 羽毛掉落：随机掉落 0 ~ 2 个；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(FEATHER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 2)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
			);
			// 苦力怕风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.CREEPER)), LootTable.builder()
					// 少量线掉落: 随机掉落 1 ~ 3 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 33.33% 掉落 1 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.with(EmptyEntry.builder().weight(2))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 苦力怕头颅：有 25% 的概率掉落苦力怕头颅；不受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(CREEPER_HEAD).weight(1))
							.with(EmptyEntry.builder().weight(3))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 火药掉落：随机掉落 1 ~ 2 个；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(GUNPOWDER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
			);
			// 铁傀儡风格
			lootTables.put(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + ChrysalisStyle.IRON_GOLEM)), LootTable.builder()
					// 巨量线掉落: 随机掉落 5 ~ 10 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(STRING)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(5, 10)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 致密蛛丝掉落: 随机掉落 2 ~ 5 根线；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 南瓜头掉落：33.33% 概率掉落 1 个；不受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(EmptyEntry.builder().weight(2))
							.with(ItemEntry.builder(CARVED_PUMPKIN).weight(1)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 罂粟掉落：33.33% 概率掉落 1 个；不受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(EmptyEntry.builder().weight(2))
							.with(ItemEntry.builder(POPPY).weight(1)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 铁块掉落：随机掉落 0 ~ 1 块；不受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(IRON_BLOCK)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
							.conditionally(SurvivesExplosionLootCondition.builder()))
					// 铁锭掉落：随机掉落 3 ~ 5 个；受“幸运”与“时运”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).bonusRolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(IRON_INGOT)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3, 5)))
									.apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)))
							.conditionally(SurvivesExplosionLootCondition.builder()))
			);
			LootTable.Builder builder = LootTable.builder();
			for (ChrysalisStyle style : Properties.CHRYSALIS_STYLE.getValues()) {
				if (ChrysalisStyle.PLACEHOLDER == style || ChrysalisStyle.PLACEHOLDER_SHORT == style) continue;
				builder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
						.with(LootTableEntry.builder(RegistryKey.of(RegistryKeys.LOOT_TABLE, block.getLootTableKey().getValue().withSuffixedPath("_" + style.asString()))))
						.conditionally(SurvivesExplosionLootCondition.builder())
						.conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(Properties.CHRYSALIS_STYLE, style))));
			}
			return builder;
		}
	}
	
	final class Entity extends SimpleFabricLootTableProvider {
		public Entity(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, registryLookup, LootContextTypes.ENTITY);
		}
		
		@Override
		public void accept(RegistryWrapper.WrapperLookup registryLookup, BiConsumer<RegistryKey<LootTable>, LootTable.Builder> consumer) {
			consumer.accept(EntityTypes.GUARD_SPIDER.getLootTableId(), LootTable.builder()
					// 蜘蛛腿掉落：随机掉落 1 ~ 2 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					// 蜘蛛护皮掉落：50% 概率掉落 1 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEATHER))
							.with(EmptyEntry.builder())
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			consumer.accept(EntityTypes.SPRAY_POISON_SPIDER.getLootTableId(), LootTable.builder()
					// 蜘蛛腿掉落：随机掉落 1 ~ 2 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					// 蜘蛛毒牙掉落：50% 概率掉落 1 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_FANG))
							.with(EmptyEntry.builder())
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
			consumer.accept(EntityTypes.WEAVING_WEB_SPIDER.getLootTableId(), LootTable.builder()
					// 蜘蛛腿掉落：随机掉落 1 ~ 2 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(Items.SPIDER_LEG)
									.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2)))
									.apply(FurnaceSmeltLootFunction.builder().conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, EntityLootTableGenerator.NEEDS_ENTITY_ON_FIRE))))
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1))))
					// 致密蛛丝掉落：50% 概率掉落 1 个；受“掠夺”影响
					.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
							.with(ItemEntry.builder(COMPACT_GOSSAMER))
							.with(EmptyEntry.builder())
							.apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0, 1)))));
		}
	}
}
