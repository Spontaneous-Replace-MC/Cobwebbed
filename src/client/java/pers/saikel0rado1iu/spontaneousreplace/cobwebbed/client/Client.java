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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client;

import com.google.common.collect.ImmutableSet;
import pers.saikel0rado1iu.silk.api.client.event.modplus.ModifyModTabEvents;
import pers.saikel0rado1iu.silk.api.client.event.modplus.ModifySynopsisTabEvents;
import pers.saikel0rado1iu.silk.api.modpass.ModClient;
import pers.saikel0rado1iu.silk.api.modpass.ModData;
import pers.saikel0rado1iu.silk.api.modpass.ModPass;
import pers.saikel0rado1iu.silk.api.modpass.registry.ClientRegistrationProvider;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.Cobwebbed;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.EntityModelLayers;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.Terriforest;

import java.util.Set;

/**
 * <h2 style="color:FFC800">客户端主类</h2>
 * 蛛丝网迹的客户端主类
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public final class Client implements ModClient {
	/**
	 * 模组主函数
	 *
	 * @param mod 提供的模组通
	 */
	@Override
	public void main(ModPass mod) {
		ModifyModTabEvents.MODIFY_ALL.register(modPass -> {
			if (!modPass.modData().id().equals(SpontaneousReplace.INSTANCE.modData().id())
					&& !modPass.modData().id().equals(Terriforest.INSTANCE.modData().id())) {
				return modPass;
			}
			return Cobwebbed.INSTANCE.modData();
		});
		ModifySynopsisTabEvents.MODIFY_ALL.register(modPass -> {
			if (!modPass.modData().id().equals(SpontaneousReplace.INSTANCE.modData().id())
					&& !modPass.modData().id().equals(Terriforest.INSTANCE.modData().id())) {
				return modPass;
			}
			return Cobwebbed.INSTANCE.modData();
		});
	}
	
	/**
	 * 注册表方法，提供注册表以供注册
	 *
	 * @return 注册表的类型集合
	 */
	@Override
	public Set<Class<? extends ClientRegistrationProvider<?>>> registry() {
		return ImmutableSet.of(
				EntityTypes.class,
				EntityModelLayers.class
		);
	}
	
	/**
	 * 用于提供模组数据以基于模组数据实现功能
	 *
	 * @return 模组数据
	 */
	@Override
	public ModData modData() {
		return Cobwebbed.INSTANCE;
	}
	
	@Override
	public ModPass registrationNamespace() {
		return SpontaneousReplace.INSTANCE;
	}
}

