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

import net.minecraft.client.render.entity.model.EntityModelLayer;
import pers.saikel0rado1iu.silk.api.client.spinningjenny.EntityModelLayerRegistry;
import pers.saikel0rado1iu.spontaneousreplace.SpontaneousReplace;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.GuardSpiderEntity;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.SpiderLarvaEntity;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.SprayPoisonSpiderEntity;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.mob.WeavingWebSpiderEntity;

import static net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer;

/**
 * <h2 style="color:FFC800">实体模型图层集</h2>
 * 蛛丝网迹的所有实体模型图层
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public abstract class EntityModelLayers implements EntityModelLayerRegistry {
	public static final EntityModelLayer TOXIN_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId("toxin_projectile"), "main");
	public static final EntityModelLayer SPIDER_LARVA_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId(SpiderLarvaEntity.ID), "main");
	public static final EntityModelLayer GUARD_SPIDER_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId(GuardSpiderEntity.ID), "main");
	public static final EntityModelLayer SPRAY_POISON_SPIDER_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId(SprayPoisonSpiderEntity.ID), "main");
	public static final EntityModelLayer WEAVING_WEB_SPIDER_LAYER = new EntityModelLayer(SpontaneousReplace.INSTANCE.ofId(WeavingWebSpiderEntity.ID), "main");
	
	static {
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(TOXIN_LAYER, ToxinEntityModel::getTexturedModelData)).register(TOXIN_LAYER);
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(SPIDER_LARVA_LAYER, SpiderLarvaEntityModel::getTexturedModelData)).register(SPIDER_LARVA_LAYER);
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(GUARD_SPIDER_LAYER, GuardSpiderEntityModel::getTexturedModelData)).register(GUARD_SPIDER_LAYER);
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(SPRAY_POISON_SPIDER_LAYER, SprayPoisonSpiderEntityModel::getTexturedModelData)).register(SPRAY_POISON_SPIDER_LAYER);
		EntityModelLayerRegistry.registrar(() -> registerModelLayer(WEAVING_WEB_SPIDER_LAYER, WeavingWebSpiderEntityModel::getTexturedModelData)).register(WEAVING_WEB_SPIDER_LAYER);
	}
}
