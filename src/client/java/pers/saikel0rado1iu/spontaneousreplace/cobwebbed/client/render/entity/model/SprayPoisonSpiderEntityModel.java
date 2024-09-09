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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.model;

import net.minecraft.client.model.*;
import pers.saikel0rado1iu.silk.api.base.common.util.MathUtil;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.animation.SprayPoisonSpiderAnimations;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.SprayPoisonSpiderEntity;

/**
 * <h2 style="color:FFC800">喷吐毒蛛实体模型</h2>
 * 喷吐毒蛛的实体模型和基础动作分配
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class SprayPoisonSpiderEntityModel<S extends SprayPoisonSpiderEntity> extends VariantsSpiderEntityModel<S> {
	public SprayPoisonSpiderEntityModel(ModelPart root) {
		super(root);
	}
	
	/**
	 * 设置鳌肢对称
	 */
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		
		getBodyTexturedModelData(modelData);
		getLegsTexturedModelData(modelData);
		
		ModelPartData head = modelData.getRoot().getChild("base").getChild("body").getChild("head");
		head.addChild("rightJaw", ModelPartBuilder.create().uv(56, 1).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.0F, -8.0F));
		head.addChild("leftJaw", ModelPartBuilder.create().uv(56, 6).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, -8.0F));
		head.addChild("rightChelicera", ModelPartBuilder.create().uv(36, 20).cuboid(-9.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -2.0F, -1.0F));
		ModelPartData leftChelicera = head.addChild("leftChelicera", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, -2.0F, -1.0F));
		leftChelicera.addChild("leftChelicera_rotate", ModelPartBuilder.create().uv(36, 20).cuboid(-5.0F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, MathUtil.toRadians(180)));
		
		return TexturedModelData.of(modelData, 64, 32);
	}
	
	/**
	 * 注册喷涂动作
	 */
	@Override
	public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
		updateAnimation(entity.walkingAnimationState, SprayPoisonSpiderAnimations.WALK, ageInTicks);
		updateAnimation(entity.swimmingAnimationState, SprayPoisonSpiderAnimations.SWIM, ageInTicks);
		updateAnimation(entity.climbingAnimationState, SprayPoisonSpiderAnimations.CLIMB, ageInTicks);
		updateAnimation(entity.jumpingAnimationState, SprayPoisonSpiderAnimations.JUMP, ageInTicks);
		updateAnimation(entity.attackingAnimationState, SprayPoisonSpiderAnimations.ATTACK, ageInTicks);
		updateAnimation(entity.dyingAnimationState, SprayPoisonSpiderAnimations.DEATH, ageInTicks);
		updateAnimation(entity.sprayingAnimationState, SprayPoisonSpiderAnimations.SPRAY, ageInTicks);
	}
}
