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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.chrysalis;

import net.minecraft.util.StringIdentifiable;

/**
 * <h2 style="color:FFC800">茧蛹样式</h2>
 * 蜘蛛茧蛹的不同样式枚举
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public enum ChrysalisStyle implements StringIdentifiable {
	/**
	 * 默认样式
	 */
	DEFAULT("default"),
	/**
	 * 大型样式
	 */
	LARGE("large"),
	/**
	 * 小型样式
	 */
	SMALL("small"),
	/**
	 * 类人样式
	 */
	HUMANOID("humanoid"),
	/**
	 * 村民样式
	 */
	VILLAGER("villager"),
	/**
	 * 小鸡样式
	 */
	CHICKEN("chicken"),
	/**
	 * 苦力怕样式
	 */
	CREEPER("creeper"),
	/**
	 * 铁傀儡样式
	 */
	IRON_GOLEM("iron_golem"),
	/**
	 * 占位模型的样式
	 */
	PLACEHOLDER("placeholder"),
	/**
	 * 短占位模型的样式
	 */
	PLACEHOLDER_SHORT("placeholder_short");
	
	private final String style;
	
	ChrysalisStyle(String name) {
		style = name;
	}
	
	@Override
	public String toString() {
		return style;
	}
	
	@Override
	public String asString() {
		return style;
	}
}
