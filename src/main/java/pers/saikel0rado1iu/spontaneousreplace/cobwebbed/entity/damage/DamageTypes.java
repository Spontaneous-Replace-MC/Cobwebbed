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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.damage;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKey;
import pers.saikel0rado1iu.silk.api.generate.entity.damage.DamageTypeEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

/**
 * <h2 style="color:FFC800">伤害类型集</h2>
 * 毛骨森然的所有伤害类型
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface DamageTypes extends DamageTypeEntry {
	DamageTypes INSTANCE = new DamageTypes() {
	};
	
	RegistryKey<DamageType> SPIDER_TOXIN = DamageTypeEntry.of(SpontaneousReplace.INSTANCE, "spider_toxin");
	
	@Override
	default RegistryBuilder.BootstrapFunction<DamageType> bootstrap() {
		return registerable -> registerable.register(SPIDER_TOXIN, new DamageType("spider_toxin", 0));
	}
}
