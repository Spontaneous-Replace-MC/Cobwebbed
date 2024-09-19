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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import pers.saikel0rado1iu.silk.api.generate.data.ModelGenUtil;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedBlockStateModelGenerator;
import pers.saikel0rado1iu.silk.api.generate.data.client.ExtendedItemModelGenerator;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.Blocks;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.SpiderChrysalisBlock;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.SpiderEggCocoonBlock;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.block.chrysalis.ChrysalisStyle;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.item.Items;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;
import static net.minecraft.state.property.Properties.VERTICAL_DIRECTION;
import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.state.property.Properties.CHRYSALIS_STYLE;

/**
 * <h2 style="color:FFC800">模型生成器</h2>
 * 蛛丝网迹的模型生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
final class ModelGenerator extends FabricModelProvider {
	ModelGenerator(FabricDataOutput output) {
		super(output);
	}
	
	private static void registerSpiderChrysalisBlockState(ExtendedBlockStateModelGenerator generator) {
		final SpiderChrysalisBlock block = Blocks.SPIDER_CHRYSALIS;
		BlockStateVariantMap.TripleProperty<ChrysalisStyle, Direction, Direction> property = BlockStateVariantMap.create(CHRYSALIS_STYLE, HORIZONTAL_FACING, VERTICAL_DIRECTION);
		for (ChrysalisStyle style : CHRYSALIS_STYLE.getValues()) {
			for (Direction vertical : VERTICAL_DIRECTION.getValues()) {
				for (Direction horizontal : HORIZONTAL_FACING.getValues()) {
					BlockStateVariant variant = BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockSubModelId(block, "_" + style.asString()));
					variant.put(VariantSettings.Y, switch (horizontal) {
						case EAST -> VariantSettings.Rotation.R90;
						case SOUTH -> VariantSettings.Rotation.R180;
						case WEST -> VariantSettings.Rotation.R270;
						default -> VariantSettings.Rotation.R0;
					});
					switch (vertical) {
						case UP -> variant.put(VariantSettings.X, VariantSettings.Rotation.R0);
						case DOWN -> variant.put(VariantSettings.X, VariantSettings.Rotation.R180);
						default -> {
						}
					}
					property.register(style, horizontal, vertical, variant);
				}
			}
		}
		generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(block))).coordinate(property));
		generator.registerItemModel(block.asItem());
	}
	
	private static void registerSpiderEggCocoonBlockState(ExtendedBlockStateModelGenerator generator) {
		final SpiderEggCocoonBlock block = Blocks.SPIDER_EGG_COCOON;
		BlockStateVariantMap.SingleProperty<Direction> property = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION);
		property.register(Direction.UP, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(block)));
		property.register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(block)).put(VariantSettings.X, VariantSettings.Rotation.R180));
		generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(block))).coordinate(property));
		generator.registerItemModel(block.asItem());
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		ExtendedBlockStateModelGenerator generator = new ExtendedBlockStateModelGenerator(blockStateModelGenerator);
		generator.registerTopSoil(Blocks.COBWEBBY_SOIL);
		generator.registerCarpet(Blocks.GOSSAMER_CARPET, false);
		generator.registerCustomModel(Blocks.GOSSAMERY_LEAVES, false);
		registerSpiderChrysalisBlockState(generator);
		registerSpiderEggCocoonBlockState(generator);
		generator.registerTintableCross(Blocks.STICKY_COMPACT_COBWEB, BlockStateModelGenerator.TintType.NOT_TINTED);
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		ExtendedItemModelGenerator generator = new ExtendedItemModelGenerator(itemModelGenerator);
		generator.register(Items.SPIDER_LEG, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER, Models.GENERATED);
		generator.register(Items.SPIDER_FANG, Models.GENERATED);
		generator.register(Items.DEPOISON_SPIDER_LEG, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER_CAP, Models.GENERATED);
		generator.register(Items.SPIDER_LEATHER_TUNIC, Models.GENERATED);
		generator.register(Items.SPIDER_LARVA_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.GUARD_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.SPRAY_POISON_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
		generator.register(Items.WEAVING_WEB_SPIDER_SPAWN_EGG, ModelGenUtil.TEMPLATE_SPAWN_EGG);
	}
}
