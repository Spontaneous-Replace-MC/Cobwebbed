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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.effect.StatusEffects;

import java.util.Objects;
import java.util.Random;

/**
 * <h2 style="color:FFC800">蜘蛛卵茧方块实体</h2>
 * 蜘蛛卵茧方块的方块实体，用于判断玩家是否在触发范围内并生成三只幼株
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class SpiderEggCocoonBlockEntity extends BlockEntity {
	/**
	 * 检测范围
	 */
	protected static final float RANGE = 7;
	/**
	 * 粒子个数
	 */
	protected static final int PARTICLE_COUNT = 20;
	
	/**
	 * 构建蜘蛛卵茧实体
	 */
	public SpiderEggCocoonBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityTypes.SPIDER_EGG_COCOON, pos, state);
	}
	
	/**
	 * <p>服务端每 Tick 运行函数</p>
	 * <p>生成蜘蛛</p>
	 */
	public static void serverTick(World world, BlockPos pos, BlockState ignoredState, SpiderEggCocoonBlockEntity ignoredBlockEntity) {
		if (canTrigger(world, pos))
			triggered(world, pos);
	}
	
	/**
	 * <p>客户端每 Tick 运行函数</p>
	 * <p>生成音效，粒子</p>
	 */
	public static void clientTick(World world, BlockPos pos, BlockState ignoredState, SpiderEggCocoonBlockEntity ignoredBlockEntity) {
		if (canTrigger(world, pos))
			triggered(world, pos);
	}
	
	/**
	 * 是否可以触发
	 */
	protected static boolean canTrigger(World world, BlockPos pos) {
		PlayerEntity player = world.getClosestPlayer(pos.getX() + EntityUtil.POS_SHIFTING, pos.getY() + EntityUtil.POS_SHIFTING, pos.getZ() + EntityUtil.POS_SHIFTING, RANGE, false);
		if (player != null) {
			// 判断是否有“蜘蛛伪装”效果
			boolean haventSpiderCamouflage = true;
			for (StatusEffectInstance statusEffectInstance : player.getStatusEffects()) {
				if (statusEffectInstance.getEffectType() == StatusEffects.SPIDER_CAMOUFLAGE)
					haventSpiderCamouflage = false;
			}
			// 如果没有“蜘蛛伪装”效果
			if (haventSpiderCamouflage) {
				// 如果玩家在触发范围内
				return world.isPlayerInRange((double) pos.getX() + EntityUtil.POS_SHIFTING, (double) pos.getY() + EntityUtil.POS_SHIFTING, (double) pos.getZ() + EntityUtil.POS_SHIFTING, RANGE)
						&& !player.isCreative();
			}
		}
		return false;
	}
	
	/**
	 * 触发蜘蛛卵茧并破坏蜘蛛卵茧
	 */
	public static void triggered(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.ENTITY_SPIDER_AMBIENT, SoundCategory.BLOCKS, 1, 1);
		if (world.isClient) {
			Random random = new Random();
			for (int i = 0; i < PARTICLE_COUNT; i++) {
				world.addParticle(ParticleTypes.CLOUD,
						pos.getX() + EntityUtil.POS_SHIFTING + random.nextDouble(-1, 1),
						pos.getY() + EntityUtil.POS_SHIFTING + random.nextDouble(-1, 1),
						pos.getZ() + EntityUtil.POS_SHIFTING + random.nextDouble(-1, 1),
						0, 0, 0);
			}
		} else {
			spawnSpiderEntity(EntityTypes.SPIDER_LARVA.create(world), world, pos);
			spawnSpiderEntity(EntityTypes.SPIDER_LARVA.create(world), world, pos);
			spawnSpiderEntity(EntityTypes.SPIDER_LARVA.create(world), world, pos);
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}
	
	/**
	 * 在蜘蛛卵茧位置生成蜘蛛实体
	 *
	 * @param spider 生成的蜘蛛实体
	 */
	public static void spawnSpiderEntity(HostileEntity spider, World world, BlockPos pos) {
		Objects.requireNonNull(spider).initialize((ServerWorld) world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, null);
		spider.refreshPositionAndAngles(pos, 0, 0);
		
		((ServerWorld) world).spawnEntityAndPassengers(spider);
	}
}