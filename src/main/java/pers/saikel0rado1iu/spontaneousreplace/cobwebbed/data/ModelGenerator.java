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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import pers.saikel0rado1iu.silk.api.generate.data.ModelGenUtil;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedBlockStateModelGenerator;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedItemModelGenerator;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;

/**
 * <h2 style="color:FFC800">模型生成器</h2>
 * 蛛丝网迹的模型生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
final class ModelGenerator extends FabricModelProvider {
	ModelGenerator(FabricDataOutput output) {
		super(output);
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ExtendedBlockStateModelGenerator generator = new ExtendedBlockStateModelGenerator(blockStateModelGenerator);
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		ExtendedItemModelGenerator generator = new ExtendedItemModelGenerator(itemModelGenerator);
		generator.register(Items.SPIDER_LEG, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER, Models.GENERATED);
		generator.register(Items.SPIDER_FANG, Models.GENERATED);
		generator.register(Items.DEPOISON_SPIDER_LEG, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER_CAP, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER_TUNIC, Models.GENERATED);
		generator.register(Items.SPIDER_LARVA_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.GUARD_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.SPIDER_CHRYSALIS, Models.GENERATED);
		generator.register(Items.SPIDER_EGG_COCOON, Models.GENERATED);
	}
}
