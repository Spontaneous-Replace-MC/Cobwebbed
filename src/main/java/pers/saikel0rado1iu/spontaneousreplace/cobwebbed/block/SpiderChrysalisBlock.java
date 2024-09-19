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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Blocks;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.chrysalis.ChrysalisStyle;

import java.util.Objects;
import java.util.Random;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;
import static net.minecraft.state.property.Properties.VERTICAL_DIRECTION;
import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.state.property.Properties.CHRYSALIS_STYLE;

/**
 * <h2 style="color:FFC800">蜘蛛茧蛹方块</h2>
 * 蜘蛛茧蛹方块，一种功能性为战利品补给的装饰类方块，有多种风格以提供不同的战利品
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class SpiderChrysalisBlock extends HorizontalFacingBlock {
	public static final float STRENGTH = 4;
	public static final Settings SETTINGS = Settings.create().mapColor(MapColor.WHITE_GRAY).pistonBehavior(PistonBehavior.BLOCK);
	public static final MapCodec<SpiderChrysalisBlock> CODEC = createCodec(SpiderChrysalisBlock::new);
	protected ChrysalisStyle style = ChrysalisStyle.PLACEHOLDER;
	
	/**
	 * 构建蜘蛛茧蛹<br>
	 * 茧蛹风格：占位<br>
	 * 水平朝向：北<br>
	 * 垂直方向：上<br>
	 *
	 * @param settings 设置
	 */
	public SpiderChrysalisBlock(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState()
				.with(CHRYSALIS_STYLE, ChrysalisStyle.PLACEHOLDER)
				.with(HORIZONTAL_FACING, Direction.NORTH)
				.with(VERTICAL_DIRECTION, Direction.UP));
	}
	
	/**
	 * 获取随机茧蛹样式
	 *
	 * @return 随机茧蛹风格
	 */
	public static ChrysalisStyle getRandomStyle() {
		int random = new Random().nextInt(100);
		// 22%
		if (random > 77) return ChrysalisStyle.DEFAULT;
			// 22%
		else if (random > 55) return ChrysalisStyle.LARGE;
			// 22%
		else if (random > 33) return ChrysalisStyle.SMALL;
			// 12%
		else if (random > 21) return ChrysalisStyle.HUMANOID;
			// 10%
		else if (random > 11) return ChrysalisStyle.VILLAGER;
			// 10%
		else if (random > 1) return ChrysalisStyle.CHICKEN;
			// 1%
		else if (random > 0) return ChrysalisStyle.CREEPER;
			// 1%
		else return ChrysalisStyle.IRON_GOLEM;
	}
	
	/**
	 * 获取放置方向
	 */
	@Nullable
	private static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
		Direction direction2;
		if (canPlaceAtWithDirection(world, pos, direction)) {
			direction2 = direction;
		} else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) {
			direction2 = direction.getOpposite();
		} else {
			return null;
		}
		return direction2;
	}
	
	/**
	 * 是双高块
	 */
	public static boolean isDoubleBlock(ChrysalisStyle style) {
		return style != ChrysalisStyle.SMALL && style != ChrysalisStyle.CHICKEN && style != ChrysalisStyle.PLACEHOLDER;
	}
	
	/**
	 * 能放置方向
	 */
	protected static boolean canPlaceAtWithDirection(WorldView world, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSideSolidFullSquare(world, blockPos, direction) || blockState.isIn(BlockTags.LEAVES);
	}
	
	@Override
	protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
		return CODEC;
	}
	
	/**
	 * 方块追加属性
	 *
	 * @param builder 构建器
	 */
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(CHRYSALIS_STYLE, HORIZONTAL_FACING, VERTICAL_DIRECTION);
	}
	
	/**
	 * 获取放置状态，随机放置方块不同样式
	 */
	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos blockPos = ctx.getBlockPos();
		World world = ctx.getWorld();
		if (world.isClient) style = getRandomStyle();
		// 获取方向
		Direction direction = getDirectionToPlaceAt(world, blockPos, ctx.getVerticalPlayerLookDirection().getOpposite());
		if (direction == null)
			return null;
		else if (direction == Direction.UP && world.getBlockState(blockPos.down()).isOf(this))
			return null;
		else if (world.getBlockState(blockPos.up()).isOf(this))
			return null;
		// 保证如果方块被方块阻挡则只能放下小型样式方块
		if (isDoubleBlock(style)) {
			if (direction == Direction.UP) {
				if (!(blockPos.getY() < world.getTopY() - 1 && world.getBlockState(blockPos.up()).canReplace(ctx))) {
					return Objects.requireNonNull(super.getPlacementState(ctx))
							.with(CHRYSALIS_STYLE, ChrysalisStyle.SMALL)
							.with(HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
							.with(VERTICAL_DIRECTION, direction);
				}
			} else if (!(blockPos.getY() > world.getBottomY() + 1 && world.getBlockState(blockPos.down()).canReplace(ctx))) {
				return Objects.requireNonNull(super.getPlacementState(ctx))
						.with(CHRYSALIS_STYLE, ChrysalisStyle.SMALL)
						.with(HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
						.with(VERTICAL_DIRECTION, direction);
			}
		}
		return Objects.requireNonNull(super.getPlacementState(ctx))
				.with(CHRYSALIS_STYLE, style)
				.with(HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
				.with(VERTICAL_DIRECTION, direction);
	}
	
	/**
	 * 获取方块的轮廓形状
	 */
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		VoxelShape placeholderShortVoxelShape = null;
		if (state.get(CHRYSALIS_STYLE) == ChrysalisStyle.PLACEHOLDER_SHORT) {
			if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
				placeholderShortVoxelShape = VoxelShapes.cuboid(0.0625, -0.0625, 0.0625, 0.9375, 0.5, 0.9375);
			} else {
				placeholderShortVoxelShape = VoxelShapes.cuboid(0.0625, 0.5, 0.0625, 0.9375, 1.0625, 0.9375);
			}
		}
		
		if (state.get(VERTICAL_DIRECTION) == Direction.UP) {
			return switch (state.get(CHRYSALIS_STYLE)) {
				case PLACEHOLDER, IRON_GOLEM, HUMANOID, VILLAGER, CREEPER -> VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
				case DEFAULT -> VoxelShapes.cuboid(0, 0, 0, 1, 0.9375, 1);
				case LARGE -> VoxelShapes.cuboid(-0.125, 0, -0.125, 1.125, 1.5, 1.125);
				case SMALL, CHICKEN -> VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 1, 0.9375);
				case PLACEHOLDER_SHORT -> placeholderShortVoxelShape;
			};
		} else {
			return switch (state.get(CHRYSALIS_STYLE)) {
				case PLACEHOLDER, IRON_GOLEM, HUMANOID, VILLAGER, CREEPER -> VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
				case DEFAULT -> VoxelShapes.cuboid(0, 0.0625, 0, 1, 1, 1);
				case LARGE -> VoxelShapes.cuboid(-0.125, -0.5, -0.125, 1.125, 1, 1.125);
				case SMALL, CHICKEN -> VoxelShapes.cuboid(0.0625, 0, 0.0625, 0.9375, 1, 0.9375);
				case PLACEHOLDER_SHORT -> placeholderShortVoxelShape;
			};
		}
	}
	
	/**
	 * 方块能放在？
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return canPlaceAtWithDirection(world, pos, state.get(VERTICAL_DIRECTION));
	}
	
	/**
	 * 当双高方块被放置时放置碰撞箱占位方块
	 */
	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		if (isDoubleBlock(state.get(CHRYSALIS_STYLE))) {
			BlockPos blockPos = pos.up();
			Direction blockState = Direction.UP;
			if (state.get(VERTICAL_DIRECTION) == Direction.DOWN) {
				blockPos = pos.down();
				blockState = Direction.DOWN;
			}
			if (state.get(CHRYSALIS_STYLE) == ChrysalisStyle.DEFAULT)
				world.setBlockState(blockPos, getDefaultState().with(CHRYSALIS_STYLE, ChrysalisStyle.PLACEHOLDER_SHORT).with(VERTICAL_DIRECTION, blockState), Block.NOTIFY_ALL);
			else
				world.setBlockState(blockPos, getDefaultState().with(CHRYSALIS_STYLE, ChrysalisStyle.PLACEHOLDER).with(VERTICAL_DIRECTION, blockState), Block.NOTIFY_ALL);
		} else super.onPlaced(world, pos, state, placer, itemStack);
	}
	
	/**
	 * 在双高块其中一块被破坏时移除另一块
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (isDoubleBlock(state.get(CHRYSALIS_STYLE))) {
			if (direction.getAxis() == Direction.Axis.Y && neighborState.isOf(Blocks.AIR))
				return Blocks.AIR.getDefaultState();
		} else {
			if (direction.getAxis() == Direction.Axis.Y) {
				if (state.get(VERTICAL_DIRECTION) == Direction.UP && pos.down().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
					return Blocks.AIR.getDefaultState();
				else if (state.get(VERTICAL_DIRECTION) == Direction.DOWN && pos.up().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR))
					return Blocks.AIR.getDefaultState();
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	/**
	 * 在方块被破坏后双高块取消一个方块掉落
	 */
	@Override
	public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		BlockPos blockPos = pos.down();
		BlockState blockState;
		if (!world.isClient && player.isCreative()) {
			if (state.get(VERTICAL_DIRECTION) == Direction.UP && (blockState = world.getBlockState(blockPos)).isOf(state.getBlock())) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
				world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
			} else if (state.get(VERTICAL_DIRECTION) == Direction.DOWN && (blockState = world.getBlockState(blockPos = pos.up())).isOf(state.getBlock())) {
				world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
				world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
			}
		}
		return super.onBreak(world, pos, state, player);
	}
}
