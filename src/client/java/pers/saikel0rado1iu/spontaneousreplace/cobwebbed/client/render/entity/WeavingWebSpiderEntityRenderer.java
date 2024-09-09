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
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.feature.WeavingWebSpiderEyes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.WeavingWebSpiderEntityModel;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.WeavingWebSpiderData;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.WeavingWebSpiderEntity;
import pers.saikel0rado1iu.sr.variant.spider.mob.general.SpiderRenderer;

import static pers.saikel0rado1iu.sr.data.client.ModelLayers.WEAVING_WEB_SPIDER_LAYER;

/**
 * <h2 style="color:FFC800">织网蜘蛛渲染类</h2>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public class WeavingWebSpiderEntityRenderer<T extends WeavingWebSpiderEntity> extends VariantsSpiderEntityRenderer<T> {
	/**
	 * 构建渲染
	 */
	public WeavingWebSpiderEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new WeavingWebSpiderEntityModel<>(context.getPart(WEAVING_WEB_SPIDER_LAYER)), WeavingWebSpiderData.MODEL_SHADOW);
		this.addFeature(new WeavingWebSpiderEyes<>(this));
	}
	
	@Override
	public String getTextureId() {
		return WeavingWebSpiderData.ID;
	}
}
