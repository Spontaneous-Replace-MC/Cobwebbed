package pres.saikel_orado.spontaneous_replace.mod.variant.general.tree.treacherous_tree;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * <b style="color:FFC800"><font size="+2">TreacherousTrunkPlacer：诡谲木树干放置器</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">添加诡谲木树干放置器</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 1.0
 * | 创建于 2023/7/30 12:27
 */
public class TreacherousTrunkPlacer extends TrunkPlacer {
    public static final Codec<TreacherousTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillTrunkPlacerFields(instance).apply(instance, TreacherousTrunkPlacer::new));
    public static TrunkPlacerType<TreacherousTrunkPlacer> TREACHEROUS_TRUNK_PLACER;

    public TreacherousTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return TREACHEROUS_TRUNK_PLACER;
    }

    /**
     * 生成主干块并返回树节点列表以放置树叶
     */
    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        BlockPos downPos = startPos.down();
        setToDirt(world, replacer, random, downPos, config);
        setToDirt(world, replacer, random, downPos.east(), config);
        setToDirt(world, replacer, random, downPos.south(), config);
        setToDirt(world, replacer, random, downPos.south().east(), config);
        setToDirt(world, replacer, random, downPos.north(), config);
        setToDirt(world, replacer, random, downPos.north().east(), config);
        setToDirt(world, replacer, random, downPos.south(2), config);
        setToDirt(world, replacer, random, downPos.south(2).east(), config);
        setToDirt(world, replacer, random, downPos.east(2), config);
        setToDirt(world, replacer, random, downPos.east(2).south(), config);
        setToDirt(world, replacer, random, downPos.west(), config);
        setToDirt(world, replacer, random, downPos.west().south(), config);
        BlockPos pos = startPos;
        for (int count = 0; count < height; count++) {
            if (count != height - 1) {
                getAndSetState(world, replacer, random, pos, config);
                getAndSetState(world, replacer, random, pos.east(), config);
                getAndSetState(world, replacer, random, pos.south(), config);
                getAndSetState(world, replacer, random, pos.south().east(), config);
            }
            if (count < (height > 5 ? 3 : 2) || count == height - 1) {
                getAndSetState(world, replacer, random, pos.north(), config);
                getAndSetState(world, replacer, random, pos.north().east(), config);
                getAndSetState(world, replacer, random, pos.south(2), config);
                getAndSetState(world, replacer, random, pos.south(2).east(), config);
                getAndSetState(world, replacer, random, pos.east(2), config);
                getAndSetState(world, replacer, random, pos.east(2).south(), config);
                getAndSetState(world, replacer, random, pos.west(), config);
                getAndSetState(world, replacer, random, pos.west().south(), config);
            }
            if (count == 1) {
                getAndSetState(world, replacer, random, pos.north().west(), config);
                getAndSetState(world, replacer, random, pos.north().east(2), config);
                getAndSetState(world, replacer, random, pos.south(2).west(), config);
                getAndSetState(world, replacer, random, pos.south(2).east(2), config);
                if (height > 6) {
                    getAndSetState(world, replacer, random, pos.north(2), config);
                    getAndSetState(world, replacer, random, pos.north(2).east(), config);
                    getAndSetState(world, replacer, random, pos.south(3), config);
                    getAndSetState(world, replacer, random, pos.south(3).east(), config);
                    getAndSetState(world, replacer, random, pos.east(3), config);
                    getAndSetState(world, replacer, random, pos.east(3).south(), config);
                    getAndSetState(world, replacer, random, pos.west(2), config);
                    getAndSetState(world, replacer, random, pos.west(2).south(), config);
                }
            }

            pos = pos.up();
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false),
                new FoliagePlacer.TreeNode(startPos.up(height), 0, false));
    }
}
