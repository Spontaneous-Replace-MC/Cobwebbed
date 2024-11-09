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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;

import java.util.concurrent.CompletableFuture;

/**
 * <h2 style="color:FFC800">配方生成器</h2>
 * 蛛丝网迹的配方生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
final class RecipeGenerator extends FabricRecipeProvider {
	RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}
	
	@Override
	public void generate(RecipeExporter exporter) {
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.SPIDER_LEATHER_CAP).group(getItemPath(Items.SPIDER_LEATHER_CAP)).input('#', Items.SPIDER_LEATHER).input('X', Items.DEPOISON_SPIDER_LEG)
				.pattern("X#X")
				.pattern("# #")
				.criterion(hasItem(Items.SPIDER_LEATHER), conditionsFromItem(Items.SPIDER_LEATHER))
				.criterion(hasItem(Items.DEPOISON_SPIDER_LEG), conditionsFromItem(Items.DEPOISON_SPIDER_LEG))
				.offerTo(exporter, getItemPath(Items.SPIDER_LEATHER_CAP));
		ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, Items.SPIDER_LEATHER_TUNIC).group(getItemPath(Items.SPIDER_LEATHER_TUNIC)).input('#', Items.SPIDER_LEATHER).input('X', Items.DEPOISON_SPIDER_LEG)
				.pattern("# #")
				.pattern("X#X")
				.pattern("X#X")
				.criterion(hasItem(Items.SPIDER_LEATHER), conditionsFromItem(Items.SPIDER_LEATHER))
				.criterion(hasItem(Items.DEPOISON_SPIDER_LEG), conditionsFromItem(Items.DEPOISON_SPIDER_LEG))
				.offerTo(exporter, getItemPath(Items.SPIDER_LEATHER_TUNIC));
	}
}
