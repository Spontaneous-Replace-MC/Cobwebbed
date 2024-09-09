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
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.VariantsSpiderEntityModel;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.VariantsSpiderEntity;

/**
 * <h2 style="color:FFC800">自然更替的蜘蛛渲染类</h2>
 * <p style="color:FFC800">自然更替的蜘蛛的基础渲染设置</p>
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 */
public abstract class VariantsSpiderEntityRenderer<S extends VariantsSpiderEntity> extends MobEntityRenderer<S, VariantsSpiderEntityModel<S>> {
	/**
	 * 注册渲染器
	 *
	 * @param context 实体渲染器工厂
	 * @param shadow  阴影半径
	 */
	public VariantsSpiderEntityRenderer(EntityRendererFactory.Context context, VariantsSpiderEntityModel<S> model, float shadow) {
		super(context, model, shadow);
	}
	
	@Override
	public Identifier getTexture(S spiderEntity) {
		return SpontaneousReplace.INSTANCE.ofId("textures/entity/" + getTextureId() + ".png");
	}
	
	public abstract String getTextureId();
}
