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

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import pers.saikel0rado1iu.silk.api.spinningjenny.ItemRegistry;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.*;
import pers.saikel0rado1iu.spontaneousreplace.item.ItemGroups;

/**
 * <h2 style="color:FFC800">物品集</h2>
 * 蛛丝网迹的所有物品
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface Items extends ItemRegistry {
	Item SPIDER_LEATHER = ItemRegistry.registrar(() -> new Item(new FabricItemSettings())).group(ItemGroups.INGREDIENTS).register("spider_leather");
	ArmorItem SPIDER_LEATHER_TUNIC = ItemRegistry.registrar(() -> ArmorMaterials.SPIDER_LEATHER.createChestplate(new FabricItemSettings(), ArmorMaterials.spiderLeatherProperty())).group(ItemGroups.COMBAT).register("spider_leather_tunic");
	ArmorItem SPIDER_LEATHER_CAP = ItemRegistry.registrar(() -> ArmorMaterials.SPIDER_LEATHER.createHelmet(new FabricItemSettings(), ArmorMaterials.spiderLeatherProperty())).group(ItemGroups.COMBAT).register("spider_leather_cap");
	SpawnEggItem SPIDER_LARVA_SPAWN_EGG = ItemRegistry.registrar(() -> new SpawnEggItem(EntityTypes.SPIDER_LARVA, 0xFFFDE6, VariantsSpiderEntity.SPIDER_EYES_COLOR, new FabricItemSettings())).group(ItemGroups.SPAWN_EGGS).register(SpiderLarvaEntity.ID);
	SpawnEggItem GUARD_SPIDER_SPAWN_EGG = ItemRegistry.registrar(() -> new SpawnEggItem(EntityTypes.GUARD_SPIDER, 0x4D4600, VariantsSpiderEntity.SPIDER_EYES_COLOR, new FabricItemSettings())).group(ItemGroups.SPAWN_EGGS).register(GuardSpiderEntity.ID);
	SpawnEggItem SPRAY_POISON_SPIDER_SPAWN_EGG = ItemRegistry.registrar(() -> new SpawnEggItem(EntityTypes.SPRAY_POISON_SPIDER, 0x0F5000, VariantsSpiderEntity.SPIDER_EYES_COLOR, new FabricItemSettings())).group(ItemGroups.SPAWN_EGGS).register(SprayPoisonSpiderEntity.ID);
	SpawnEggItem WEAVING_WEB_SPIDER_SPAWN_EGG = ItemRegistry.registrar(() -> new SpawnEggItem(EntityTypes.WEAVING_WEB_SPIDER, 0x404040, VariantsSpiderEntity.SPIDER_EYES_COLOR, new FabricItemSettings())).group(ItemGroups.SPAWN_EGGS).register(WeavingWebSpiderEntity.ID);
}