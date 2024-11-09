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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import pers.saikel0rado1iu.silk.api.spinningjenny.tag.BlockTags;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.registry.tag.BiomeTags;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.registry.tag.BlockTags.LEAVES;

/**
 * <h2 style="color:FFC800">标签生成器</h2>
 * 蛛丝网迹的标签生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
interface TagGenerator {
	final class Item extends FabricTagProvider.ItemTagProvider {
		Item(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(ItemTags.MEAT).add(Items.SPIDER_LEG, Items.DEPOISON_SPIDER_LEG);
		}
	}
	
	final class Block extends FabricTagProvider.BlockTagProvider {
		Block(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(LEAVES).add(Blocks.GOSSAMERY_LEAVES);
			getOrCreateTagBuilder(BlockTags.COBWEB).add(Blocks.SPIDER_CHRYSALIS, Blocks.SPIDER_EGG_COCOON, Blocks.GOSSAMER_CARPET, Blocks.STICKY_COMPACT_COBWEB);
		}
	}
	
	final class Biome extends FabricTagProvider<net.minecraft.world.biome.Biome> {
		Biome(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, RegistryKeys.BIOME, registriesFuture);
		}
		
		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(BiomeTags.IS_SPIDER_BIOME).add(BiomeKeys.CREEPY_SPIDER_FOREST);
		}
	}
}
