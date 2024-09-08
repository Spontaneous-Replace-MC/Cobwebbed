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
import pers.saikel0rado1iu.silk.api.codex.OptionTexts;
import pers.saikel0rado1iu.silk.api.generate.data.LinkedLanguageProvider;
import pers.saikel0rado1iu.silk.api.pattern.widget.WidgetTexts;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.Cobwebbed;
import pers.saikel0rado1iu.spontaneousreplace.cobwebbed.Settings;

/**
 * <h2 style="color:FFC800">语言生成器</h2>
 * 毛骨森然的全球化语言生成器
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img alt="author" src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"></a>
 * @since 1.0.0
 */
interface LanguageGenerator {
	final class EnUs extends LinkedLanguageProvider {
		EnUs(FabricDataOutput dataOutput) {
			super(dataOutput, "en_us");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "Origin Language is Simplified Chinese(zh_cn)");
			translationBuilder.add(i18nName(Cobwebbed.INSTANCE), "'Cobwebbed' DLC");
			translationBuilder.add(i18nSummary(Cobwebbed.INSTANCE), "A SR expansion that adds various spider variants and exclusive biomes.");
			translationBuilder.add(i18nDesc(Cobwebbed.INSTANCE), """
					§r  Have you ever wondered how much more fun Minecraft would be if spiders had better animations and more variants? That's exactly what this expansion pack does!
					§r  This expansion pack adds multiple spider variants and exclusive biomes, with each spider having its own unique drops and special behaviors. Go explore and discover the secrets hidden in the cobwebs!""");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "homepage"), "Homepage");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "support"), "Support");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "community"), "Discord");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "changelog"), "Changelog");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "target"), "1.0.0 > Target");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "target"), "    Continue planning and implementing the creatures, biomes, items, and tools.");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis"), "Synopsis");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), "§f§lMod introduction:");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), """
					§r  Have you ever wondered how much more fun Minecraft would be if spiders had better animations and more variants? That's exactly what this expansion pack does!
					§r  This expansion pack adds multiple spider variants and exclusive biomes, with each spider having its own unique drops and special behaviors. Go explore and discover the secrets hidden in the cobwebs!""");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), "§f§lMod Vision:");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), """
					§r  I hope to make a gameplay mod that is based on the core of the vanilla game and does not destroy the vanilla gameplay. It is very difficult to develop on this basis. Whether an item is added, how to design data so as not to destroy the balance of the game, These are all points that developers need to consider.
					§r  If you think the mod is doing a good job, you are welcome to sponsor my project, or translate this mod, thank your very much!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "Setting 'Cobwebbed' DLC...");
		}
	}
	
	final class ZhCn extends LinkedLanguageProvider {
		ZhCn(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_cn");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生语言");
			translationBuilder.add(i18nName(Cobwebbed.INSTANCE), "「蛛丝网迹」拓展包");
			translationBuilder.add(i18nSummary(Cobwebbed.INSTANCE), "添加了多种蜘蛛变种与专属生物群系的「自然更替」拓展");
			translationBuilder.add(i18nDesc(Cobwebbed.INSTANCE), """
					§r　　你有没有想过·Minecraft·中的蜘蛛有着更好的动画和更多的变种会有多好玩？这就是本拓展包所做的事情！
					§r　　本拓展包添加了多个蜘蛛变种与专属生物群系，每个蜘蛛都有专属的掉落物与特殊习性。去探索吧，找到隐藏在蛛网中的秘密吧！""");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "homepage"), "模组官网");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "support"), "支持我们");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "community"), "官方社群");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "changelog"), "更新日志");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "target"), "1.0.0 > 目标");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "target"), "　　继续计划实现但未实现的生物、生物群系、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis"), "简介");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), "§f§l模组简介：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), """
					§r　　你有没有想过·Minecraft·中的蜘蛛有着更好的动画和更多的变种会有多好玩？这就是本拓展包所做的事情！
					§r　　本拓展包添加了多个蜘蛛变种与专属生物群系，每个蜘蛛都有专属的掉落物与特殊习性。去探索吧，找到隐藏在蛛网中的秘密吧！""");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), "§f§l模组愿景：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一个基于原版游戏内核，不破坏原版游戏玩法的玩法类模组。在这基础上进行开发十分困难，一件物品是否加入，怎样设计数据才不会破坏游戏平衡性，这些都是开发者需要考虑的点。
					§r　　如果你觉得模组做的不错，欢迎对我的项目进行赞助，或者对此模组进行翻译，十分感谢你们!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「蛛丝网迹」拓展包设置…");
		}
	}
	
	final class ZhHk extends LinkedLanguageProvider {
		ZhHk(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_hk");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(i18nName(Cobwebbed.INSTANCE), "「蛛絲網跡」擴展包");
			translationBuilder.add(i18nSummary(Cobwebbed.INSTANCE), "添加咗多種蜘蛛變種與專屬生物羣落嘅「自然更替」擴展");
			translationBuilder.add(i18nDesc(Cobwebbed.INSTANCE), """
					§r　　你有冇諗過·Minecraft·中嘅蜘蛛如果有更好嘅動畫同更多變種會有幾好玩？呢個就係本擴展包所做嘅事情！
					§r　　本擴展包添加咗多個蜘蛛變種與專屬生物羣落，每個蜘蛛都有專屬嘅掉落物同特殊習性。去探索吧，搵到隱藏喺蛛網中嘅秘密吧！""");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "homepage"), "模組官網");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "support"), "支持我們");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "community"), "官方社羣");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "changelog"), "更新日誌");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "target"), "1.0.0 > 目標");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "target"), "　　繼續計劃實現但未實現嘅生物、生物羣落、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis"), "簡介");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), """
					§r　　你有冇諗過·Minecraft·中嘅蜘蛛如果有更好嘅動畫同更多變種會有幾好玩？呢個就係本擴展包所做嘅事情！
					§r　　本擴展包添加咗多個蜘蛛變種與專屬生物羣落，每個蜘蛛都有專屬嘅掉落物同特殊習性。去探索吧，搵到隱藏喺蛛網中嘅秘密吧！""");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一個基於原版遊戲內核，不破壞原版遊戲玩法嘅玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計數據才不會破壞遊戲平衡性，這些都是開發者需要考慮嘅點。
					§r　　如果你覺得模組做嘅不錯，歡迎對我嘅項目進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「蛛絲網跡」拓展包設定⋯⋯");
		}
	}
	
	final class ZhTw extends LinkedLanguageProvider {
		ZhTw(FabricDataOutput dataOutput) {
			super(dataOutput, "zh_tw");
		}
		
		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(comment("note"), "原生語言為簡體中文(zh_cn)");
			translationBuilder.add(i18nName(Cobwebbed.INSTANCE), "「蛛絲網跡」擴充套件");
			translationBuilder.add(i18nSummary(Cobwebbed.INSTANCE), "添加了多種蜘蛛變種與專屬生態域的「自然更替」擴充套件");
			translationBuilder.add(i18nDesc(Cobwebbed.INSTANCE), """
					§r　　你有沒有想過·Minecraft·中的蜘蛛如果有更好的動畫和更多的變種會有多好玩？這就是本擴充套件所做的事情！
					§r　　本擴充套件添加了多個蜘蛛變種與專屬生態域，每個蜘蛛都有專屬的掉落物與特殊習性。去探索吧，找到隱藏在蛛網中的秘密吧！""");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "homepage"), "模組官網");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "support"), "支援我們");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "community"), "官方社群");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "changelog"), "更新日誌");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "target"), "1.0.0 > 目標");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "target"), "　　繼續計劃實現但未實現的生物、生態域、物品、道具。");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis"), "簡介");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), "§f§l模組簡介：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.intro"), """
					§r　　你有沒有想過·Minecraft·中的蜘蛛如果有更好的動畫和更多的變種會有多好玩？這就是本擴充套件所做的事情！
					§r　　本擴充套件添加了多個蜘蛛變種與專屬生態域，每個蜘蛛都有專屬的掉落物與特殊習性。去探索吧，找到隱藏在蛛網中的秘密吧！""");
			translationBuilder.add(WidgetTexts.titleKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), "§f§l模組願景：");
			translationBuilder.add(WidgetTexts.textKey(Cobwebbed.INSTANCE, "tab.synopsis.vision"), """
					§r　　我希望做一個基於原版遊戲核心，不破壞原版遊戲玩法的玩法類模組。在這基礎上進行開發十分困難，一件物品是否加入，怎樣設計資料才不會破壞遊戲平衡性，這些都是開發者需要考慮的點。
					§r　　如果你覺得模組做的不錯，歡迎對我的專案進行贊助，或者對此模組進行翻譯，十分感謝你們!""");
			translationBuilder.add(OptionTexts.rootKey(Settings.SETTINGS), "「蛛絲網跡」擴充套件設定...");
		}
	}
}

