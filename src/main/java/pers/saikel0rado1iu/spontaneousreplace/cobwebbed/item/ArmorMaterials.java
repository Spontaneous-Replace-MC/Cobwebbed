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
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import pers.saikel0rado1iu.silk.api.ropestick.armor.ArmorHelper;
import pers.saikel0rado1iu.silk.api.ropestick.component.DataComponentTypes;
import pers.saikel0rado1iu.silk.api.ropestick.component.type.EffectiveItemSlotData;
import pers.saikel0rado1iu.silk.api.ropestick.component.type.InherentStatusEffectData;
import pers.saikel0rado1iu.silk.api.ropestick.component.type.InherentStatusEffectsComponent;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.sound.SoundEvents;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.item.ArmorMaterials.LEATHER;
import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.effect.StatusEffects.SPIDER_CAMOUFLAGE;

/**
 * <h2 style="color:FFC800">盔甲材料类</h2>
 * 蛛丝网迹的盔甲工具材料
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public enum ArmorMaterials implements ArmorHelper {
	/**
	 * 蜘蛛护皮装备<br>
	 * 一种可以藏匿自己于蜘蛛中的蜘蛛装备
	 */
	SPIDER_LEATHER("spider_leather", 6, Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
		map.put(ArmorItem.Type.HELMET, 2);
		map.put(ArmorItem.Type.CHESTPLATE, 4);
		map.put(ArmorItem.Type.LEGGINGS, 3);
		map.put(ArmorItem.Type.BOOTS, 1);
		map.put(ArmorItem.Type.BODY, 4);
	}), LEATHER.value().enchantability(), SoundEvents.EQUIP_SPIDER_LEATHER_ARMOR, 0, 0, () -> Ingredient.ofItems(Items.SPIDER_LEATHER));
	
	private final String name;
	private final int durability;
	private final Map<ArmorItem.Type, Integer> defense;
	private final int enchantability;
	private final RegistryEntry<SoundEvent> equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> ingredient;
	private final Function<Identifier, List<ArmorMaterial.Layer>> layers;
	private final Supplier<RegistryEntry<ArmorMaterial>> material;
	
	ArmorMaterials(String name, int durability, Map<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient) {
		this(name, durability, defense, enchantability, equipSound, toughness, knockbackResistance, ingredient, id -> List.of(new ArmorMaterial.Layer(id)));
	}
	
	ArmorMaterials(String name, int durability, Map<ArmorItem.Type, Integer> defense, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> ingredient, Function<Identifier, List<ArmorMaterial.Layer>> layers) {
		this.name = name;
		this.durability = durability;
		this.defense = defense;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.ingredient = Suppliers.memoize(ingredient::get);
		this.layers = layers;
		this.material = Suppliers.memoize(() -> ArmorHelper.registerMaterial(this));
	}
	
	/**
	 * 获取蜘蛛护皮装备物品设置
	 *
	 * @return 物品设置
	 */
	public static Item.Settings getSpiderLeatherSetting() {
		return new Item.Settings().component(DataComponentTypes.INHERENT_STATUS_EFFECTS, InherentStatusEffectsComponent.of(
				InherentStatusEffectData.create(SPIDER_CAMOUFLAGE,
						1,
						1,
						0,
						() -> List.of(Items.SPIDER_LEATHER_CAP, Items.SPIDER_LEATHER_TUNIC),
						2,
						EffectiveItemSlotData.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST))));
	}
	@Override
	public Identifier id() {
		return SpontaneousReplace.INSTANCE.ofId(name);
	}
	
	@Override
	public int durability() {
		return durability;
	}
	
	@Override
	public Map<ArmorItem.Type, Integer> defense() {
		return defense;
	}
	
	@Override
	public int enchantability() {
		return enchantability;
	}
	
	@Override
	public RegistryEntry<SoundEvent> equipSound() {
		return equipSound;
	}
	
	@Override
	public Supplier<Ingredient> repairIngredient() {
		return ingredient;
	}
	
	@Override
	public float toughness() {
		return toughness;
	}
	
	@Override
	public float knockbackResistance() {
		return knockbackResistance;
	}
	
	@Override
	public List<ArmorMaterial.Layer> layers() {
		return layers.apply(id());
	}
	
	@Override
	public Supplier<RegistryEntry<ArmorMaterial>> material() {
		return material;
	}
}
