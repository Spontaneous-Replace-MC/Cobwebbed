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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.entity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import pers.saikel0rado1iu.silk.api.spinningjenny.EntityTypeRegistry;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.client.render.entity.*;

import static pers.saikel0rado1iu.spontaneousreplace.cobwebbed.entity.EntityTypes.*;


/**
 * <h2 style="color:FFC800">实体类型集</h2>
 * 蛛丝网迹的实体类型的客户端注册
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
public abstract class EntityTypes implements EntityTypeRegistry {
	static {
		EntityTypeRegistry.registrar(() -> EntityRendererRegistry.register(TOXIN, ToxinEntityRenderer::new)).register(TOXIN);
		EntityTypeRegistry.registrar(() -> EntityRendererRegistry.register(SPIDER_LARVA, SpiderLarvaEntityRenderer::new)).register(SPIDER_LARVA);
		EntityTypeRegistry.registrar(() -> EntityRendererRegistry.register(GUARD_SPIDER, GuardSpiderEntityRenderer::new)).register(GUARD_SPIDER);
		EntityTypeRegistry.registrar(() -> EntityRendererRegistry.register(SPRAY_POISON_SPIDER, SprayPoisonSpiderEntityRenderer::new)).register(SPRAY_POISON_SPIDER);
		EntityTypeRegistry.registrar(() -> EntityRendererRegistry.register(WEAVING_WEB_SPIDER, WeavingWebSpiderEntityRenderer::new)).register(WEAVING_WEB_SPIDER);
	}
}
