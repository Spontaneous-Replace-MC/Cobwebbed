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
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.data.client.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedBlockStateModelGenerator;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.entity.BlockEntityTypes;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.entity.SpiderEggCocoonBlockEntity;

import java.util.Objects;

/**
 * <h2 style="color:FFC800">蜘蛛卵茧方块</h2>
 * 蜘蛛卵茧方块，一种特殊的陷阱类方块，在玩家靠近时会生成三只幼株
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public class SpiderEggCocoonBlock extends BlockWithEntity {
	public static final float STRENGTH = SpiderChrysalisBlock.STRENGTH;
	public static final AbstractBlock.Settings SETTINGS = SpiderChrysalisBlock.SETTINGS;
	public static final MapCodec<SpiderEggCocoonBlock> CODEC = createCodec(SpiderEggCocoonBlock::new);
	
	/**
	 * <p>构建蜘蛛卵茧</p>
	 * <p>垂直方向：上</p>
	 *
	 * @param settings 设置
	 */
	public SpiderEggCocoonBlock(Settings settings) {
		super(settings);
		setDefaultState(getDefaultState().with(Properties.VERTICAL_DIRECTION, Direction.UP));
	}
	
	public void registerBlockState(ExtendedBlockStateModelGenerator generator) {
		BlockStateVariantMap.SingleProperty<Direction> property = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION);
		property.register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(this)));
		property.register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(this)).put(VariantSettings.X, VariantSettings.Rotation.R180));
		generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(this, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(this))).coordinate(property));
		generator.registerItemModel(asItem());
	}
	
	/**
	 * 获取放置方向
	 */
	protected static Direction getDirectionToPlaceAt(WorldView world, BlockPos pos, Direction direction) {
		Direction direction2;
		if (canPlaceAtWithDirection(world, pos, direction)) direction2 = direction;
		else if (canPlaceAtWithDirection(world, pos, direction.getOpposite())) direction2 = direction.getOpposite();
		else return null;
		return direction2;
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
	protected MapCodec<? extends BlockWithEntity> getCodec() {
		return CODEC;
	}
	
	/**
	 * 创建蜘蛛卵茧实体
	 */
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new SpiderEggCocoonBlockEntity(pos, state);
	}
	
	/**
	 * 方块一旦被破坏立刻生成蜘蛛
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		SpiderEggCocoonBlockEntity.triggered(world, pos);
	}
	
	/**
	 * 方块追加属性
	 *
	 * @param builder 构建器
	 */
	@Override
	protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
		builder.add(Properties.VERTICAL_DIRECTION);
	}
	
	/**
	 * 获取渲染类型
	 */
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	/**
	 * 获取放置状态
	 */
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = getDirectionToPlaceAt(ctx.getWorld(), ctx.getBlockPos(), ctx.getVerticalPlayerLookDirection().getOpposite());
		return direction == null ? null : Objects.requireNonNull(super.getPlacementState(ctx)).with(Properties.VERTICAL_DIRECTION, direction);
	}
	
	/**
	 * 如果蜘蛛卵茧的附着方块被破坏则破坏蜘蛛卵茧
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction.getAxis() == Direction.Axis.Y) {
			if (state.get(Properties.VERTICAL_DIRECTION) == Direction.UP && pos.down().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR)) {
				SpiderEggCocoonBlockEntity.triggered((World) world, pos);
			} else if (state.get(Properties.VERTICAL_DIRECTION) == Direction.DOWN && pos.up().getY() == neighborPos.getY() && neighborState.isOf(Blocks.AIR)) {
				SpiderEggCocoonBlockEntity.triggered((World) world, pos);
			}
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	/**
	 * 设置每 Tick 运行函数
	 */
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		return SpiderEggCocoonBlock.validateTicker(type, BlockEntityTypes.SPIDER_EGG_COCOON, world.isClient ? SpiderEggCocoonBlockEntity::clientTick : SpiderEggCocoonBlockEntity::serverTick);
	}
}
