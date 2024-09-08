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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed;

import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.silk.api.modpass.pack.DataPack;
import pers.saikel0rado1iu.silk.api.modpass.pack.ResourcePack;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

import java.util.Optional;


/**
 * <h2 style="color:FFC800">蛛丝网迹模组通</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface Cobwebbed extends SpontaneousReplace {
	/**
	 * 实例
	 */
	Cobwebbed INSTANCE = new Cobwebbed() {
	};
	
	@Override
	default String id() {
		return "spontaneous-replace-cobwebbed";
	}
	
	@Override
	default Optional<Identifier> icon() {
		Optional<String> path = mod().getMetadata().getIconPath(4);
		return path.map(id -> new Identifier(SpontaneousReplace.INSTANCE.id(), "cobwebbed-icon.png"));
	}
	
	@Override
	default Optional<DataPack> dataPack() {
		return SpontaneousReplace.INSTANCE.dataPack();
	}
	
	@Override
	default Optional<ResourcePack> resourcePack() {
		return SpontaneousReplace.INSTANCE.resourcePack();
	}
}
