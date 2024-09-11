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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.ropestick.armor.Armor;
import pers.saikel0rado1iu.silk.api.ropestick.property.EffectiveItemSlot;
import pers.saikel0rado1iu.silk.api.ropestick.property.InherentStatusEffect;
import pers.saikel0rado1iu.silk.api.ropestick.property.ItemProperty;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.effect.StatusEffects;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.sound.SoundEvents;

import java.util.function.Supplier;

import static net.minecraft.item.ArmorMaterials.LEATHER;

/**
 * <h2 style="color:FFC800">盔甲材料类</h2>
 * 蛛丝网迹的盔甲工具材料
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public enum ArmorMaterials implements Armor {
	/**
	 * 蜘蛛护皮装备<br>
	 * 一种可以藏匿自己于蜘蛛中的蜘蛛装备
	 */
	SPIDER_LEATHER("spider_leather", 6, new int[]{2, 4, 3, 1}, LEATHER.getEnchantability(), SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR, 0, 0, () -> Ingredient.ofItems(Items.SPIDER_LEATHER));
	
	private final String name;
	private final int durability;
	private final int[] protections;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> ingredient;
	
	ArmorMaterials(String name, int durability, int[] protections, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient) {
		this.name = name;
		this.durability = durability;
		this.protections = protections;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.ingredient = Suppliers.memoize(ingredient::get);
	}
	
	/**
	 * 蜘蛛护皮装备属性
	 *
	 * @return 物品属性数组
	 */
	public static ItemProperty[] spiderLeatherProperty() {
		return new ItemProperty[]{
				new InherentStatusEffect(
						new InherentStatusEffect.Property(StatusEffects.SPIDER_CAMOUFLAGE, 1, 1, 0,
								() -> ImmutableSet.of(Items.SPIDER_LEATHER_CAP, Items.SPIDER_LEATHER_TUNIC), 2, new EffectiveItemSlot(EquipmentSlot.HEAD, EquipmentSlot.CHEST)))};
	}
	
	/**
	 * 获取盔甲 ID
	 *
	 * @return 盔甲 ID
	 */
	@Override
	public Identifier getId() {
		return SpontaneousReplace.INSTANCE.ofId(name);
	}
	
	/**
	 * 获取耐久度
	 *
	 * @return 耐久度
	 */
	@Override
	public int getDurability() {
		return durability;
	}
	
	/**
	 * 获取护甲值
	 *
	 * @return 护甲值
	 */
	@Override
	public int[] getProtections() {
		return protections;
	}
	
	/**
	 * 获取击退抗性
	 *
	 * @return 击退抗性
	 */
	@Override
	public float getKnockBackResistance() {
		return knockbackResistance;
	}
	
	@Override
	public int getEnchantability() {
		return enchantability;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return equipSound;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return ingredient.get();
	}
	
	@Override
	public float getToughness() {
		return toughness;
	}
}
