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

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.EntityModelLayers;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model.ToxinEntityModel;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.ToxinEntity;

/**
 * <h2 style="color:FFC800">毒素实体渲染器</h2>
 * 喷吐毒蛛的喷吐毒素的实体渲染器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class ToxinEntityRenderer extends EntityRenderer<ToxinEntity> {
	/**
	 * 纹理路径
	 */
	protected static final Identifier TEXTURE = SpontaneousReplace.INSTANCE.ofId("textures/entity/toxin_projectile.png");
	/**
	 * 毒素模型
	 */
	protected final ToxinEntityModel<ToxinEntity> model;
	
	/**
	 * 构建渲染
	 */
	public ToxinEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		model = new ToxinEntityModel<>(context.getPart(EntityModelLayers.TOXIN_LAYER));
	}
	
	/**
	 * 渲染
	 */
	@Override
	public void render(ToxinEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push();
		matrixStack.translate(0, 0.15F, 0);
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, mobEntity.prevYaw, mobEntity.getYaw()) - 90));
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, mobEntity.prevPitch, mobEntity.getPitch())));
		model.setAngles(mobEntity, g, 0, -0.1F, 0, 0);
		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE));
		model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
		matrixStack.pop();
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
	
	/**
	 * 获取纹理
	 */
	@Override
	public Identifier getTexture(ToxinEntity entity) {
		return TEXTURE;
	}
}

