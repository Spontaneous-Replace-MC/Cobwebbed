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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.block;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.FoliageColors;
import pers.saikel0rado1iu.silk.api.spinningjenny.BlockRegistry;

import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks.*;

/**
 * <h2 style="color:FFC800">方块集</h2>
 * 蛛丝网迹的方块的客户端注册
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public abstract class Blocks implements BlockRegistry {
	static {
		BlockRegistry.registrar(() -> BlockRenderLayerMap.INSTANCE.putBlock(GOSSAMER_CARPET, RenderLayer.getCutout())).register(GOSSAMER_CARPET);
		BlockRegistry.registrar(() -> {
			BlockRenderLayerMap.INSTANCE.putBlock(GOSSAMERY_LEAVES, RenderLayer.getCutoutMipped());
			ColorProviderRegistry.BLOCK.register(((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor()), GOSSAMERY_LEAVES);
			ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> FoliageColors.getDefaultColor()), GOSSAMERY_LEAVES);
		}).register(GOSSAMERY_LEAVES);
		BlockRegistry.registrar(() -> BlockRenderLayerMap.INSTANCE.putBlock(STICKY_COMPACT_COBWEB, RenderLayer.getCutout())).register(STICKY_COMPACT_COBWEB);
	}
}
