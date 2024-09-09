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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import pers.saikel0rado1iu.silk.api.base.common.util.SpawnUtil;
import pers.saikel0rado1iu.silk.api.spinningjenny.EntityTypeRegistry;
import pers.saikel0rado1iu.silk.api.spore.EntityUtil;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.*;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.registry.tag.BiomeTags;


/**
 * <h2 style="color:FFC800">实体类型集</h2>
 * 蛛丝网迹的所有实体类型
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public interface EntityTypes extends EntityTypeRegistry {
	EntityType<ToxinEntity> TOXIN = EntityTypeRegistry.registrar(() -> FabricEntityTypeBuilder.<ToxinEntity>create(SpawnGroup.MISC, ToxinEntity::new)
					.dimensions(EntityDimensions.fixed(EntityUtil.PROJECTILE_BOX, EntityUtil.PROJECTILE_BOX)).trackRangeBlocks(EntityUtil.PROJECTILE_RANGE).trackedUpdateRate(EntityUtil.PROJECTILE_UPDATE_RATE).build())
			.register("toxin_projectile");
	EntityType<SpiderLarvaEntity> SPIDER_LARVA = EntityTypeRegistry.registrar(() -> FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(SpiderLarvaEntity::new)
					.dimensions(EntityDimensions.fixed(SpiderLarvaData.BOX_WEIGHT, SpiderLarvaData.BOX_HEIGHT)).build())
			.other(spiderLarvaEntityEntityType -> FabricDefaultAttributeRegistry.register(spiderLarvaEntityEntityType, SpiderLarvaEntity.createSpiderAttributes()))
			.register(SpiderLarvaData.ID);
	EntityType<GuardSpiderEntity> GUARD_SPIDER = EntityTypeRegistry.registrar(() -> FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(GuardSpiderEntity::new)
					.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(GuardSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(BiomeTags.IS_SPIDER_BIOME)).build())
					.dimensions(EntityDimensions.fixed(GuardSpiderData.BOX_WEIGHT, GuardSpiderData.BOX_HEIGHT)).build())
			.other(guardSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(guardSpiderEntityEntityType, GuardSpiderEntity.createSpiderAttributes()))
			.register(GuardSpiderData.ID);
	EntityType<SprayPoisonSpiderEntity> SPRAY_POISON_SPIDER = EntityTypeRegistry.registrar(() -> FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(SprayPoisonSpiderEntity::new)
					.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(SprayPoisonSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(BiomeTags.IS_SPIDER_BIOME)).build())
					.dimensions(EntityDimensions.fixed(SprayPoisonSpiderData.BOX_WEIGHT, SprayPoisonSpiderData.BOX_HEIGHT)).build())
			.other(sprayPoisonSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(sprayPoisonSpiderEntityEntityType, SprayPoisonSpiderEntity.createSpiderAttributes()))
			.register(SprayPoisonSpiderData.ID);
	EntityType<WeavingWebSpiderEntity> WEAVING_WEB_SPIDER = EntityTypeRegistry.registrar(() -> FabricEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.MONSTER).entityFactory(WeavingWebSpiderEntity::new)
					.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnUtil.builder(WeavingWebSpiderEntity.class).monster().biome(biomeRegistryEntry -> biomeRegistryEntry.isIn(BiomeTags.IS_SPIDER_BIOME)).build())
					.dimensions(EntityDimensions.fixed(WeavingWebSpiderData.BOX_WEIGHT, WeavingWebSpiderData.BOX_HEIGHT)).build())
			.other(weavingWebSpiderEntityEntityType -> FabricDefaultAttributeRegistry.register(weavingWebSpiderEntityEntityType, WeavingWebSpiderEntity.createSpiderAttributes()))
			.register(WeavingWebSpiderData.ID);
}
