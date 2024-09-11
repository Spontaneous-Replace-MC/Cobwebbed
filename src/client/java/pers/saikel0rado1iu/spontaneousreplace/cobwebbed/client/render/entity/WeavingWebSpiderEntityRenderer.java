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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.EntityModelLayers;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.WeavingWebSpiderEntityModel;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.WeavingWebSpiderEntity;

/**
 * <h2 style="color:FFC800">织网蜘蛛实体渲染器</h2>
 * 织网蜘蛛的实体渲染器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class WeavingWebSpiderEntityRenderer<T extends WeavingWebSpiderEntity> extends VariantsSpiderEntityRenderer<T> {
	/**
	 * 构建渲染
	 */
	public WeavingWebSpiderEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new WeavingWebSpiderEntityModel<>(context.getPart(EntityModelLayers.WEAVING_WEB_SPIDER_LAYER)), 0.775F);
	}
	
	@Override
	public String getTextureId() {
		return WeavingWebSpiderEntity.ID;
	}
}
