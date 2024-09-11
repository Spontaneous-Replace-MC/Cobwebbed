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

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import pers.saikel0rado1iu.silk.api.spinningjenny.BlockRegistry;

import static net.minecraft.block.Blocks.*;

/**
 * <h2 style="color:FFC800">方块集</h2>
 * 蛛丝网迹的所有方块
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface Blocks extends BlockRegistry {
	float COBWEB_STRENGTH = 4;
	float STRENGTH_RADIO = 10;
	float STICKY_COMPACT_COBWEB_STRENGTH = COBWEB_STRENGTH * STRENGTH_RADIO;
	int STICKY_COMPACT_COBWEB_BURN_CHANCE = 5;
	int STICKY_COMPACT_COBWEB_SPREAD_CHANCE = 20;
	AbstractBlock.Settings COBWEB = AbstractBlock.Settings.create().mapColor(MapColor.WHITE_GRAY).pistonBehavior(PistonBehavior.BLOCK);
	CobwebbySoilBlock COBWEBBY_SOIL = BlockRegistry.registrar(() -> new CobwebbySoilBlock(FabricBlockSettings.copyOf(MYCELIUM).mapColor(MapColor.WHITE))).register("cobwebby_soil");
	GossamerCarpetBlock GOSSAMER_CARPET = BlockRegistry.registrar(() -> new GossamerCarpetBlock(FabricBlockSettings.copyOf(MOSS_CARPET).noCollision().nonOpaque().mapColor(MapColor.WHITE_GRAY))).register("gossamer_carpet");
	GossameryLeavesBlock GOSSAMERY_LEAVES = BlockRegistry.registrar(() -> new GossameryLeavesBlock(FabricBlockSettings.copyOf(OAK_LEAVES).mapColor(MapColor.WHITE_GRAY))).register("gossamery_leaves");
	StickyCompactCobwebBlock STICKY_COMPACT_COBWEB = BlockRegistry.registrar(() -> new StickyCompactCobwebBlock(FabricBlockSettings.copyOf(
					AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).burnable().nonOpaque().pistonBehavior(PistonBehavior.BLOCK)).noCollision().requiresTool().strength(STICKY_COMPACT_COBWEB_STRENGTH)))
			.other(stickyCompactCobweb -> FlammableBlockRegistry.getDefaultInstance().add(stickyCompactCobweb, STICKY_COMPACT_COBWEB_BURN_CHANCE, STICKY_COMPACT_COBWEB_SPREAD_CHANCE)).register("sticky_compact_cobweb");
	SpiderChrysalisBlock SPIDER_CHRYSALIS = BlockRegistry.registrar(() -> new SpiderChrysalisBlock(FabricBlockSettings.copyOf(COBWEB).requiresTool().nonOpaque().strength(COBWEB_STRENGTH))).register("spider_chrysalis");
	SpiderEggCocoonBlock SPIDER_EGG_COCOON = BlockRegistry.registrar(() -> new SpiderEggCocoonBlock(FabricBlockSettings.copyOf(COBWEB).requiresTool().nonOpaque().strength(COBWEB_STRENGTH))).register("spider_egg_cocoon");
}
