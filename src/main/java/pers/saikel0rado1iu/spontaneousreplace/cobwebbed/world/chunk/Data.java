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

package pers.saikel0rado1iu.spontaneousreplace.cobwebbed.world.chunk;

import net.minecraft.util.math.BlockPos;
import pers.saikel0rado1iu.silk.api.base.common.noise.PerlinNoise;
import pers.saikel0rado1iu.spontaneousreplace.terriforest.world.gen.chunk.ClassicChunkGenerator;

/**
 * <p><b style="color:FFC800"><font size="+1"></font></b></p>
 * <style="color:FFC800">
 *
 * @author <a href="https://github.com/Saikel-Orado-Liu"><img src="https://avatars.githubusercontent.com/u/88531138?s=64&v=4"><p>
 * @since
 */
public
class Data {
	public static final int POS_BASE_RADIUS = 2000;
	public static final int POS_SHIFTING_RADIUS = 200;
	public static final int SIZE_BASE_RADIUS = 200;
	public static final int SIZE_SHIFTING_RADIUS = 30;
	public static final int NOISE_OCTAVES = (int) (Math.log((SIZE_BASE_RADIUS + SIZE_SHIFTING_RADIUS) * 2) / Math.log(2)) + 1;
	public static final int NOISE_SIZE = (int) Math.pow(2, NOISE_OCTAVES);
	public static final float NOISE_BIAS = 0.01f;
	public static final int CENTER_X = 0;
	public static final int CENTER_Z = 0;
	public static long seed = 0;
	private static double[][] noise = null;
	static int posX = 0;
	static int posZ = 0;
	static long sizeRadius = 0;
	
	static double[][] getNoise(long seed) {
		if (null == noise) noise = new PerlinNoise(NOISE_SIZE / 10, NOISE_SIZE / 10, 0, 2, 1, 1, seed).scale(NOISE_SIZE, NOISE_SIZE).noiseMap();
		return noise;
	}
	
	static double getBiomeRatio(int x, int z, long seed, double origin) {
		float ratio = 0;
		float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
		if (distance > 0.3) {
			float r = (1 - distance) / 0.7F;
			ratio = r * 0.3F + (1 - r) * 0.5F;
		}
		if (origin > ratio) return 1;
		return (origin / ratio) * 0.9F + (1 - (origin / ratio)) * 0.1F;
	}
	
	private static boolean canSetNoise(int x, int z, long seed) {
		int noiseX = (int) Math.abs(sizeRadius + (x - posX));
		int noiseZ = (int) Math.abs(sizeRadius + (z - posZ));
		float ratio = 0;
		float distance = distanceFromBiomeCenterRatio(x, 0, z, seed);
		if (distance > 0.3) {
			float r = (1 - distance) / 0.7F;
			ratio = r * 0.3F + (1 - r) * 0.5F;
		}
		return !(noiseX < 0 || noiseX >= NOISE_SIZE)
				&& !(noiseZ < 0 || noiseZ >= NOISE_SIZE)
				&& getNoise(seed)[noiseX][noiseZ] > ratio;
	}
	
	private static float distanceFromBiomeCenterRatio(int x, int y, int z, long seed) {
		if (0 == posX && 0 == posZ && 0 == sizeRadius) {
			long posRadius = seed > 0 ? POS_BASE_RADIUS + seed % POS_SHIFTING_RADIUS
					: POS_BASE_RADIUS - seed % POS_SHIFTING_RADIUS;
			double radian = Math.toRadians(seed % 360);
			posX = (int) (CENTER_X + posRadius * Math.cos(radian));
			posZ = (int) (CENTER_Z + posRadius * Math.sin(radian));
			sizeRadius = seed > 0 ? SIZE_BASE_RADIUS + seed % SIZE_SHIFTING_RADIUS
					: SIZE_BASE_RADIUS - seed % SIZE_SHIFTING_RADIUS;
		}
		return (float) ((Math.pow(x - posX, 2) + Math.pow(z - posZ, 2)) / Math.pow(sizeRadius, 2));
	}
	
	static boolean isInBiome(int x, int y, int z, long seed) {
		return distanceFromBiomeCenterRatio(x, y, z, seed) <= 1;
	}
	
	static boolean canSetBiome(ClassicChunkGenerator generator, BlockPos pos) {
		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		final long seed = generator.seed();
		return isInBiome(x, y, z, seed) && canSetNoise(x, z, seed);
	}
}
