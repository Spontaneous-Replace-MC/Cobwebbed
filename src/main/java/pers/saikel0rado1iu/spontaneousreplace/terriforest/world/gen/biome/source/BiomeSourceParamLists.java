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

package pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.biome.source;

import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import pers.saikel0rado1iu.silk.api.generate.world.MultiNoiseBiomeSourceParameterListEntry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;

/**
 * <h2 style="color:FFC800">生物群系源参数列表集</h2>
 * 毛骨森然的所有生物群系源参数列表
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface BiomeSourceParamLists extends MultiNoiseBiomeSourceParameterListEntry {
	BiomeSourceParamLists INSTANCE = new BiomeSourceParamLists() {
	};
	
	RegistryKey<MultiNoiseBiomeSourceParameterList> CLASSIC = MultiNoiseBiomeSourceParameterListEntry.of(SpontaneousReplace.INSTANCE, "classic");
	RegistryKey<MultiNoiseBiomeSourceParameterList> SNAPSHOT = MultiNoiseBiomeSourceParameterListEntry.of(SpontaneousReplace.INSTANCE, "snapshot");
	
	@Override
	default RegistryBuilder.BootstrapFunction<MultiNoiseBiomeSourceParameterList> bootstrap() {
		return registerable -> {
			RegistryEntryLookup<Biome> registryEntryLookup = registerable.getRegistryLookup(RegistryKeys.BIOME);
			registerable.register(CLASSIC, new MultiNoiseBiomeSourceParameterList(BiomeSourceParamListPresets.CLASSIC, registryEntryLookup));
			registerable.register(SNAPSHOT, new MultiNoiseBiomeSourceParameterList(BiomeSourceParamListPresets.SNAPSHOT, registryEntryLookup));
		};
	}
}
