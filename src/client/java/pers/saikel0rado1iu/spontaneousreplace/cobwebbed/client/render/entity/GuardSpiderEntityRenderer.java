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
import net.minecraft.client.util.math.MatrixStack;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.EntityModelLayers;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.GuardSpiderEntityModel;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.GuardSpiderEntity;

/**
 * <h2 style="color:FFC800">蜘蛛卫兵实体渲染器</h2>
 * 蜘蛛卫兵的实体渲染器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class GuardSpiderEntityRenderer<T extends GuardSpiderEntity> extends VariantsSpiderEntityRenderer<T> {
	public static final float MODEL_SCALE = 1.2F;
	
	/**
	 * 构建渲染类
	 */
	public GuardSpiderEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new GuardSpiderEntityModel<>(context.getPart(EntityModelLayers.GUARD_SPIDER_LAYER)), 0.875F);
	}
	
	/**
	 * 调整模型缩放
	 */
	@Override
	protected void scale(T entity, MatrixStack matrices, float amount) {
		matrices.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);
	}
	
	@Override
	public String getTextureId() {
		return GuardSpiderEntity.ID;
	}
}
