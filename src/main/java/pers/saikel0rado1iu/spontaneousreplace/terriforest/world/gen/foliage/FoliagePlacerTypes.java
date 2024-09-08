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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.foliage;

import net.minecraft.world.gen.foliage.FoliagePlacerType;
import pers.saikel0rado1iu.silk.api.spinningjenny.world.gen.FoliagePlacerTypeRegistry;

/**
 * <h2 style="color:FFC800">树叶放置器类型集</h2>
 * 毛骨森然的所有树叶放置器类型
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface FoliagePlacerTypes extends FoliagePlacerTypeRegistry {
	FoliagePlacerType<EerieFoliagePlacer> EERIE_TREE_FOLIAGE_PLACER = FoliagePlacerTypeRegistry.registrar(() -> new FoliagePlacerType<>(EerieFoliagePlacer.CODEC)).register("eerie_tree_foliage_placer");
	FoliagePlacerType<TreacherousFoliagePlacer> TREACHEROUS_TREE_FOLIAGE_PLACER = FoliagePlacerTypeRegistry.registrar(() -> new FoliagePlacerType<>(TreacherousFoliagePlacer.CODEC)).register("treacherous_tree_foliage_placer");
}
