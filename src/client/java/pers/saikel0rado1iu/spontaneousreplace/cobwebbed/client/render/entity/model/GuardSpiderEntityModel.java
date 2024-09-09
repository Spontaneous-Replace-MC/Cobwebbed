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

import net.minecraft.client.model.ModelPart;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.animation.GuardSpiderAnimations;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.GuardSpiderEntity;

/**
 * <h2 style="color:FFC800">蜘蛛卫兵实体模型</h2>
 * 蜘蛛卫兵的实体模型和基础动作分配
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class GuardSpiderEntityModel<S extends GuardSpiderEntity> extends VariantsSpiderEntityModel<S> {
	/**
	 * 构建添加模组组件
	 *
	 * @param root 根组件
	 */
	public GuardSpiderEntityModel(ModelPart root) {
		super(root);
	}
	
	/**
	 * 设置动画
	 */
	@Override
	public void setAngles(S entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
		super.setAngles(entity, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch);
		updateAnimation(entity.walkingAnimationState, GuardSpiderAnimations.WALK, ageInTicks);
		updateAnimation(entity.swimmingAnimationState, GuardSpiderAnimations.SWIM, ageInTicks);
		updateAnimation(entity.climbingAnimationState, GuardSpiderAnimations.CLIMB, ageInTicks);
		updateAnimation(entity.jumpingAnimationState, GuardSpiderAnimations.JUMP, ageInTicks);
		updateAnimation(entity.attackingAnimationState, GuardSpiderAnimations.ATTACK, ageInTicks);
		updateAnimation(entity.dyingAnimationState, GuardSpiderAnimations.DEATH, ageInTicks);
	}
}
