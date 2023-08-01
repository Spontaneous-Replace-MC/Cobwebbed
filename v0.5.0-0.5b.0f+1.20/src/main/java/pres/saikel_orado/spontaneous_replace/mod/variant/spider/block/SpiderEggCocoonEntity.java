package pres.saikel_orado.spontaneous_replace.mod.variant.spider.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import pres.saikel_orado.spontaneous_replace.mod.variant.spider.equipment.SpiderLeatherArmor;

import java.util.Objects;
import java.util.Random;

import static pres.saikel_orado.spontaneous_replace.mod.data.SRData.POS_SHIFTING;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Effects.SPIDER_CAMOUFLAGE;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.SPIDER_EGG_COCOON_ENTITY;
import static pres.saikel_orado.spontaneous_replace.mod.data.SRElements.Variant.Spider.SPIDER_LARVA;

/**
 * <b style="color:FFC800"><font size="+2">SpiderEggCocoonEntity：蜘蛛卵茧方块实体类</font></b>
 * <p><i><b style="color:FFC800"><font size="+1">确定蜘蛛卵茧方块的特殊方块特性</font></b></i></p>
 * <style="color:FFC800">
 *
 * @author 刘 Saikel Orado 又称 “游戏极客-Saikel”
 * <p>Saikel Orado Liu aka ”GameGeek-Saikel“</p>
 * @version 7.0
 * | 创建于 2023/2/3 18:17
 */
public class SpiderEggCocoonEntity extends BlockEntity {
    /**
     * 构建蜘蛛卵茧实体
     */
    public SpiderEggCocoonEntity(BlockPos pos, BlockState state) {
        super(SPIDER_EGG_COCOON_ENTITY, pos, state);
    }

    /**
     * 检测范围
     */
    protected static final float RANGE = 7;
    /**
     * 装备蜘蛛皮甲范围值
     */
    protected static final float SPIDER_LEATHER_TUNIC_RANGE = 4.5F;

    /**
     * 粒子个数
     */
    protected static final int PARTICLE_COUNT = 20;

    /**
     * <p>服务端每 Tick 运行函数</p>
     * <p>生成蜘蛛</p>
     */
    public static void serverTick(World world, BlockPos pos, BlockState ignoredState, SpiderEggCocoonEntity ignoredBlockEntity) {
        if (canTrigger(world, pos))
            triggered(world, pos);
    }

    /**
     * <p>客户端每 Tick 运行函数</p>
     * <p>生成音效，粒子</p>
     */
    public static void clientTick(World world, BlockPos pos, BlockState ignoredState, SpiderEggCocoonEntity ignoredBlockEntity) {
        if (canTrigger(world, pos))
            triggered(world, pos);
    }

    /**
     * 是否可以触发
     */
    protected static boolean canTrigger(World world, BlockPos pos) {
        PlayerEntity player = world.getClosestPlayer(pos.getX() + POS_SHIFTING, pos.getY() + POS_SHIFTING, pos.getZ() + POS_SHIFTING, RANGE, false);
        if (player != null) {
            // 判断是否有“蜘蛛伪装”效果
            boolean haventSpiderCamouflage = true;
            for (StatusEffectInstance statusEffectInstance : player.getStatusEffects()) {
                if (statusEffectInstance.getEffectType() == SPIDER_CAMOUFLAGE)
                    haventSpiderCamouflage = false;
            }
            // 如果没有“蜘蛛伪装”效果
            if (haventSpiderCamouflage) {
                // 如果玩家在触发范围内
                if (world.isPlayerInRange((double) pos.getX() + POS_SHIFTING, (double) pos.getY() + POS_SHIFTING, (double) pos.getZ() + POS_SHIFTING, RANGE)
                        && !player.isCreative()) {
                    Iterable<ItemStack> iterable = player.getArmorItems();
                    for (ItemStack armor : iterable) {
                        // 如果玩家装备蜘蛛皮甲则范围减小
                        Item item = armor.getItem();
                        if (!(item instanceof ArmorItem) || ((ArmorItem) item).getMaterial() != SpiderLeatherArmor.SPIDER_LEATHER_ARMOR)
                            continue;
                        return world.isPlayerInRange((double) pos.getX() + POS_SHIFTING, (double) pos.getY() + POS_SHIFTING, (double) pos.getZ() + POS_SHIFTING, SPIDER_LEATHER_TUNIC_RANGE);
                    }
                    return true;
                }
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
                        pos.getX() + POS_SHIFTING + random.nextDouble(-1, 1),
                        pos.getY() + POS_SHIFTING + random.nextDouble(-1, 1),
                        pos.getZ() + POS_SHIFTING + random.nextDouble(-1, 1),
                        0, 0, 0);
            }
        } else {
            spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
            spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
            spawnSpiderEntity(SPIDER_LARVA.create(world), world, pos);
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    /**
     * 在蜘蛛卵茧位置生成蜘蛛实体
     *
     * @param spider 生成的蜘蛛实体
     */
    public static void spawnSpiderEntity(HostileEntity spider, World world, BlockPos pos) {
        Objects.requireNonNull(spider).initialize((ServerWorld) world, world.getLocalDifficulty(pos), SpawnReason.NATURAL, null, null);
        spider.refreshPositionAndAngles(pos, 0, 0);

        ((ServerWorld) world).spawnEntityAndPassengers(spider);
    }
}
