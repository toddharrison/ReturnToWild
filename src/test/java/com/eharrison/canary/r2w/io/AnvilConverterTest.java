package com.eharrison.canary.r2w.io;

import static com.eharrison.canary.r2w.io.AnvilConverter.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class AnvilConverterTest {
	@Ignore
	@Test
	public void foo() {
		
		for (int i = 0; i < 16; i++) {
			System.out.println(i + " " + (Math.abs(i - 16) + 7) % 16);
		}
		
	}
	
	@Test
	public void test() {
		Map<String, Object> map;
		
		// minecraft:air
		map = convert((byte) 0, 0);
		assertEquals(0, map.get("id"));
		assertEquals(0, map.get("data"));
		
		// minecraft:sapling oak
		map = convert((byte) 6, 0);
		assertEquals(6, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling spruce
		map = convert((byte) 6, 1);
		assertEquals(6, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling birch
		map = convert((byte) 6, 2);
		assertEquals(6, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling jungle
		map = convert((byte) 6, 3);
		assertEquals(6, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling acacia
		map = convert((byte) 6, 4);
		assertEquals(6, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling dark oak
		map = convert((byte) 6, 5);
		assertEquals(6, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals(0, map.get("stage"));
		
		// minecraft:sapling oak ready to grow
		map = convert((byte) 6, 8);
		assertEquals(6, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:sapling spruce ready to grow
		map = convert((byte) 6, 9);
		assertEquals(6, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:sapling birch ready to grow
		map = convert((byte) 6, 10);
		assertEquals(6, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:sapling jungle ready to grow
		map = convert((byte) 6, 11);
		assertEquals(6, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:sapling acacia ready to grow
		map = convert((byte) 6, 12);
		assertEquals(6, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:sapling dark oad ready to grow
		map = convert((byte) 6, 13);
		assertEquals(6, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals(1, map.get("stage"));
		
		// minecraft:flowing_water(source)
		map = convert((byte) 8, 0);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 1);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 2);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 3);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 4);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 5);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 6);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 7);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 8);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 9);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 10);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 11);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 12);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 13);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 14);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("level"));
		
		// minecraft:flowing_water
		map = convert((byte) 8, 15);
		assertEquals(8, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("level"));
		
		// minecraft:water(source)
		map = convert((byte) 9, 0);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 1);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 2);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 3);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 4);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 5);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 6);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 7);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 8);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 9);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 10);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 11);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 12);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 13);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 14);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("level"));
		
		// minecraft:water
		map = convert((byte) 9, 15);
		assertEquals(9, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("level"));
		
		// minecraft:flowing_lava(source)
		map = convert((byte) 10, 0);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 1);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 2);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 3);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 4);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 5);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 6);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 7);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 8);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 9);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 10);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 11);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 12);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 13);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 14);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("level"));
		
		// minecraft:flowing_lava
		map = convert((byte) 10, 15);
		assertEquals(10, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("level"));
		
		// minecraft:lava(source)
		map = convert((byte) 11, 0);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 1);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 2);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 3);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 4);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 5);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 6);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 7);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 8);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 9);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 10);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 11);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 12);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 13);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 14);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("level"));
		
		// minecraft:lava
		map = convert((byte) 11, 15);
		assertEquals(11, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("level"));
		
		// minecraft:log Oak Log U/D
		map = convert((byte) 17, 0);
		assertEquals(17, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log Spruce Log U/D
		map = convert((byte) 17, 1);
		assertEquals(17, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log Birch Log U/D
		map = convert((byte) 17, 2);
		assertEquals(17, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log Jungle Log U/D
		map = convert((byte) 17, 3);
		assertEquals(17, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log Oak Log E/W
		map = convert((byte) 17, 4);
		assertEquals(17, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log Spruce Log E/W
		map = convert((byte) 17, 5);
		assertEquals(17, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log Birch Log E/W
		map = convert((byte) 17, 6);
		assertEquals(17, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log Jungle Log E/W
		map = convert((byte) 17, 7);
		assertEquals(17, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log Oak Log N/S
		map = convert((byte) 17, 8);
		assertEquals(17, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log Spruce Log N/S
		map = convert((byte) 17, 9);
		assertEquals(17, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log Birch Log N/S
		map = convert((byte) 17, 10);
		assertEquals(17, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log Jungle Log N/S
		map = convert((byte) 17, 11);
		assertEquals(17, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log Oak Bark
		map = convert((byte) 17, 12);
		assertEquals(17, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("none", map.get("axis"));
		
		// minecraft:log Spruce Bark
		map = convert((byte) 17, 13);
		assertEquals(17, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("none", map.get("axis"));
		
		// minecraft:log Birch Bark
		map = convert((byte) 17, 14);
		assertEquals(17, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("none", map.get("axis"));
		
		// minecraft:log Jungle Bark
		map = convert((byte) 17, 15);
		assertEquals(17, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("none", map.get("axis"));
		
		// minecraft:leaves Oak
		map = convert((byte) 18, 0);
		assertEquals(18, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("check_decay"));
		assertEquals(true, map.get("decayable"));
		
		// minecraft:leaves Oak no decay
		map = convert((byte) 18, 4);
		assertEquals(18, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("check_decay"));
		assertEquals(false, map.get("decayable"));
		
		// minecraft:leaves Oak check decay
		map = convert((byte) 18, 8);
		assertEquals(18, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("check_decay"));
		assertEquals(true, map.get("decayable"));
		
		// minecraft:leaves Oak check decay no decay
		map = convert((byte) 18, 12);
		assertEquals(18, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("check_decay"));
		assertEquals(false, map.get("decayable"));
		
		// minecraft:dispencer down off
		map = convert((byte) 23, 0);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer up off
		map = convert((byte) 23, 1);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer north off
		map = convert((byte) 23, 2);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer south off
		map = convert((byte) 23, 3);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer west off
		map = convert((byte) 23, 4);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer east off
		map = convert((byte) 23, 5);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(false, map.get("triggered"));
		
		// minecraft:dispencer down on
		map = convert((byte) 23, 8);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:dispencer up on
		map = convert((byte) 23, 9);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:dispencer north on
		map = convert((byte) 23, 10);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:dispencer south on
		map = convert((byte) 23, 11);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:dispencer west on
		map = convert((byte) 23, 12);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:dispencer east on
		map = convert((byte) 23, 13);
		assertEquals(23, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(true, map.get("triggered"));
		
		// minecraft:bed foot, head facing south, not occupied
		map = convert((byte) 26, 0);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(0, map.get("part"));
		
		// minecraft:bed foot, head facing west, not occupied
		map = convert((byte) 26, 1);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(0, map.get("part"));
		
		// minecraft:bed foot, head facing north, not occupied
		map = convert((byte) 26, 2);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(0, map.get("part"));
		
		// minecraft:bed foot, head facing east, not occupied
		map = convert((byte) 26, 3);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(0, map.get("part"));
		
		// minecraft:bed head, head facing south, not occupied
		map = convert((byte) 26, 8);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(1, map.get("part"));
		
		// minecraft:bed head, head facing west, not occupied
		map = convert((byte) 26, 9);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(1, map.get("part"));
		
		// minecraft:bed head, head facing north, not occupied
		map = convert((byte) 26, 10);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(1, map.get("part"));
		
		// minecraft:bed head, head facing east, not occupied
		map = convert((byte) 26, 11);
		assertEquals(26, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(0, map.get("occupied"));
		assertEquals(1, map.get("part"));
		
		// minecraft:golden_rail N/S not active
		map = convert((byte) 27, 0);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("north_south", map.get("shape"));
		
		// minecraft:golden_rail E/W not active
		map = convert((byte) 27, 1);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("east_west", map.get("shape"));
		
		// minecraft:golden_rail AscendE not active
		map = convert((byte) 27, 2);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_east", map.get("shape"));
		
		// minecraft:golden_rail AscendW not active
		map = convert((byte) 27, 3);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_west", map.get("shape"));
		
		// minecraft:golden_rail AscendN not active
		map = convert((byte) 27, 4);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_north", map.get("shape"));
		
		// minecraft:golden_rail AscendS not active
		map = convert((byte) 27, 5);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_south", map.get("shape"));
		
		// minecraft:golden_rail N/S active
		map = convert((byte) 27, 8);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("north_south", map.get("shape"));
		
		// minecraft:golden_rail E/W active
		map = convert((byte) 27, 9);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("east_west", map.get("shape"));
		
		// minecraft:golden_rail AscendE active
		map = convert((byte) 27, 10);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_east", map.get("shape"));
		
		// minecraft:golden_rail AscendW active
		map = convert((byte) 27, 11);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_west", map.get("shape"));
		
		// minecraft:golden_rail AscendN active
		map = convert((byte) 27, 12);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_north", map.get("shape"));
		
		// minecraft:golden_rail AscendS active
		map = convert((byte) 27, 13);
		assertEquals(27, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_south", map.get("shape"));
		
		// minecraft:detector_rail N/S not active
		map = convert((byte) 28, 0);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("north_south", map.get("shape"));
		
		// minecraft:detector_rail E/W not active
		map = convert((byte) 28, 1);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("east_west", map.get("shape"));
		
		// minecraft:detector_rail AscendE not active
		map = convert((byte) 28, 2);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_east", map.get("shape"));
		
		// minecraft:detector_rail AscendW not active
		map = convert((byte) 28, 3);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_west", map.get("shape"));
		
		// minecraft:detector_rail AscendN not active
		map = convert((byte) 28, 4);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_north", map.get("shape"));
		
		// minecraft:detector_rail AscendS not active
		map = convert((byte) 28, 5);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("powered"));
		assertEquals("ascending_south", map.get("shape"));
		
		// minecraft:detector_rail N/S active
		map = convert((byte) 28, 8);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("north_south", map.get("shape"));
		
		// minecraft:detector_rail E/W active
		map = convert((byte) 28, 9);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("east_west", map.get("shape"));
		
		// minecraft:detector_rail AscendE active
		map = convert((byte) 28, 10);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_east", map.get("shape"));
		
		// minecraft:detector_rail AscendW active
		map = convert((byte) 28, 11);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_west", map.get("shape"));
		
		// minecraft:detector_rail AscendN active
		map = convert((byte) 28, 12);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_north", map.get("shape"));
		
		// minecraft:detector_rail AscendS active
		map = convert((byte) 28, 13);
		assertEquals(28, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("powered"));
		assertEquals("ascending_south", map.get("shape"));
		
		// minecraft:sticky_piston down
		map = convert((byte) 29, 0);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston up
		map = convert((byte) 29, 1);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston north
		map = convert((byte) 29, 2);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston south
		map = convert((byte) 29, 3);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston west
		map = convert((byte) 29, 4);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston east
		map = convert((byte) 29, 5);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:sticky_piston down extended
		map = convert((byte) 29, 8);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:sticky_piston up extended
		map = convert((byte) 29, 9);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:sticky_piston north extended
		map = convert((byte) 29, 10);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:sticky_piston south extended
		map = convert((byte) 29, 11);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:sticky_piston west extended
		map = convert((byte) 29, 12);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:sticky_piston east extended
		map = convert((byte) 29, 13);
		assertEquals(29, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston down
		map = convert((byte) 33, 0);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston up
		map = convert((byte) 33, 1);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston north
		map = convert((byte) 33, 2);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston south
		map = convert((byte) 33, 3);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston west
		map = convert((byte) 33, 4);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston east
		map = convert((byte) 33, 5);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(false, map.get("extended"));
		
		// minecraft:piston down
		map = convert((byte) 33, 8);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston up extended
		map = convert((byte) 33, 9);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston north extended
		map = convert((byte) 33, 10);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston south extended
		map = convert((byte) 33, 11);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston west extended
		map = convert((byte) 33, 12);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston east extended
		map = convert((byte) 33, 13);
		assertEquals(33, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals(true, map.get("extended"));
		
		// minecraft:piston_head normal down
		map = convert((byte) 34, 0);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head normal up
		map = convert((byte) 34, 1);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head normal north
		map = convert((byte) 34, 2);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head normal south
		map = convert((byte) 34, 3);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head normal west
		map = convert((byte) 34, 4);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head normal east
		map = convert((byte) 34, 5);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals("normal", map.get("type"));
		
		// minecraft:piston_head sticky down
		map = convert((byte) 34, 8);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("down", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:piston_head sticky up
		map = convert((byte) 34, 9);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:piston_head sticky north
		map = convert((byte) 34, 10);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:piston_head sticky south
		map = convert((byte) 34, 11);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:piston_head sticky west
		map = convert((byte) 34, 12);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:piston_head sticky east
		map = convert((byte) 34, 13);
		assertEquals(34, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals("sticky", map.get("type"));
		
		// minecraft:double_stone_slab stone seamed
		map = convert((byte) 43, 0);
		assertEquals(43, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab sandstone seamed
		map = convert((byte) 43, 1);
		assertEquals(43, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab stonewood seamed
		map = convert((byte) 43, 2);
		assertEquals(43, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab cobblestone seamed
		map = convert((byte) 43, 3);
		assertEquals(43, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab brick seamed
		map = convert((byte) 43, 4);
		assertEquals(43, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab stonebrick seamed
		map = convert((byte) 43, 5);
		assertEquals(43, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab netherbrick seamed
		map = convert((byte) 43, 6);
		assertEquals(43, map.get("id"));
		assertEquals(6, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab quartz seamed
		map = convert((byte) 43, 7);
		assertEquals(43, map.get("id"));
		assertEquals(7, map.get("data"));
		assertEquals(false, map.get("seamless"));
		
		// minecraft:double_stone_slab stone seamless
		map = convert((byte) 43, 8);
		assertEquals(43, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab sandstone seamless
		map = convert((byte) 43, 9);
		assertEquals(43, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab wooden stone seamless
		map = convert((byte) 43, 10);
		assertEquals(43, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab cobblestone seamless
		map = convert((byte) 43, 11);
		assertEquals(43, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab brick seamless
		map = convert((byte) 43, 12);
		assertEquals(43, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab stonebrick seamless
		map = convert((byte) 43, 13);
		assertEquals(43, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab netherbrick seamless
		map = convert((byte) 43, 14);
		assertEquals(43, map.get("id"));
		assertEquals(6, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:double_stone_slab quartz seamless
		map = convert((byte) 43, 15);
		assertEquals(43, map.get("id"));
		assertEquals(7, map.get("data"));
		assertEquals(true, map.get("seamless"));
		
		// minecraft:stone_slab stone bottom
		map = convert((byte) 44, 0);
		assertEquals(44, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab sandstone bottom
		map = convert((byte) 44, 1);
		assertEquals(44, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab stonewood bottom
		map = convert((byte) 44, 2);
		assertEquals(44, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab cobblestone bottom
		map = convert((byte) 44, 3);
		assertEquals(44, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab brick bottom
		map = convert((byte) 44, 4);
		assertEquals(44, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab stonebrick bottom
		map = convert((byte) 44, 5);
		assertEquals(44, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab netherbrick bottom
		map = convert((byte) 44, 6);
		assertEquals(44, map.get("id"));
		assertEquals(6, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab quartz bottom
		map = convert((byte) 44, 7);
		assertEquals(44, map.get("id"));
		assertEquals(7, map.get("data"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:stone_slab stone top
		map = convert((byte) 44, 8);
		assertEquals(44, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab sandstone top
		map = convert((byte) 44, 9);
		assertEquals(44, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab wooden stone top
		map = convert((byte) 44, 10);
		assertEquals(44, map.get("id"));
		assertEquals(2, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab cobblestone top
		map = convert((byte) 44, 11);
		assertEquals(44, map.get("id"));
		assertEquals(3, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab brick top
		map = convert((byte) 44, 12);
		assertEquals(44, map.get("id"));
		assertEquals(4, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab stonebrick top
		map = convert((byte) 44, 13);
		assertEquals(44, map.get("id"));
		assertEquals(5, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab netherbrick top
		map = convert((byte) 44, 14);
		assertEquals(44, map.get("id"));
		assertEquals(6, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:stone_slab quartz top
		map = convert((byte) 44, 15);
		assertEquals(44, map.get("id"));
		assertEquals(7, map.get("data"));
		assertEquals("top", map.get("half"));
		
		// minecraft:tnt breakable
		map = convert((byte) 46, 0);
		assertEquals(46, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("explode"));
		
		// minecraft:tnt explodes when broken
		map = convert((byte) 46, 1);
		assertEquals(46, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("explode"));
		
		// minecraft:torch top to the east
		map = convert((byte) 50, 1);
		assertEquals(50, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		
		// minecraft:torch top to the west
		map = convert((byte) 50, 2);
		assertEquals(50, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		
		// minecraft:torch top to the south
		map = convert((byte) 50, 3);
		assertEquals(50, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		
		// minecraft:torch top to the north
		map = convert((byte) 50, 4);
		assertEquals(50, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		
		// minecraft:torch top to the up
		map = convert((byte) 50, 5);
		assertEquals(50, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("up", map.get("facing"));
		
		// minecraft:fire
		map = convert((byte) 51, 0);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 1);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 2);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 3);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 4);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 5);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 6);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 7);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 8);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 9);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 10);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 11);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 12);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 13);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 14);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("age"));
		
		// minecraft:fire
		map = convert((byte) 51, 15);
		assertEquals(51, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("age"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 0);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 1);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 2);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 3);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals("bottom", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 4);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		assertEquals("top", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 5);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		assertEquals("top", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 6);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		assertEquals("top", map.get("half"));
		
		// minecraft:oak_stairs
		map = convert((byte) 53, 7);
		assertEquals(53, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		assertEquals("top", map.get("half"));
		
		// minecraft:chest
		map = convert((byte) 54, 2);
		assertEquals(54, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		
		// minecraft:chest
		map = convert((byte) 54, 3);
		assertEquals(54, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		
		// minecraft:chest
		map = convert((byte) 54, 4);
		assertEquals(54, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		
		// minecraft:chest
		map = convert((byte) 54, 5);
		assertEquals(54, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 0);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 1);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 2);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 3);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 4);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 5);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 6);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 7);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 8);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(8, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 9);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(9, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 10);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(10, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 11);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(11, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 12);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(12, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 13);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(13, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 14);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(14, map.get("power"));
		
		// minecraft:redstone_wire
		map = convert((byte) 55, 15);
		assertEquals(55, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(15, map.get("power"));
		
		// minecraft:wheat
		map = convert((byte) 59, 0);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 1);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 2);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(2, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 3);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(3, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 4);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(4, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 5);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(5, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 6);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(6, map.get("age"));
		
		// minecraft:wheat
		map = convert((byte) 59, 7);
		assertEquals(59, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("age"));
		
		// minecraft:farmland dry
		map = convert((byte) 60, 0);
		assertEquals(60, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("moisture"));
		
		// minecraft:farmland wet
		map = convert((byte) 60, 7);
		assertEquals(60, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(7, map.get("moisture"));
		
		// minecraft:furnace
		map = convert((byte) 61, 2);
		assertEquals(61, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		
		// minecraft:furnace
		map = convert((byte) 61, 3);
		assertEquals(61, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		
		// minecraft:furnace
		map = convert((byte) 61, 4);
		assertEquals(61, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		
		// minecraft:furnace
		map = convert((byte) 61, 5);
		assertEquals(61, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		
		// minecraft:lit_furnace
		map = convert((byte) 62, 2);
		assertEquals(62, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("facing"));
		
		// minecraft:lit_furnace
		map = convert((byte) 62, 3);
		assertEquals(62, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("facing"));
		
		// minecraft:lit_furnace
		map = convert((byte) 62, 4);
		assertEquals(62, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("facing"));
		
		// minecraft:lit_furnace
		map = convert((byte) 62, 5);
		assertEquals(62, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("facing"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 0);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 1);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south-southwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 2);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("southwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 3);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west-southwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 4);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 5);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("west-northwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 6);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("northwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 7);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north-northwest", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 8);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 9);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("north-northeast", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 10);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("northeast", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 11);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east-northeast", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 12);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 13);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("east-southeast", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 14);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("southeast", map.get("rotation"));
		
		// minecraft:standing_sign
		map = convert((byte) 63, 15);
		assertEquals(63, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("south-southeast", map.get("rotation"));
		
		// minecraft:wooden_door
		map = convert((byte) 64, 15);
		assertEquals(64, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("lower", map.get("half"));
		assertEquals("right", map.get("hinge"));
		assertEquals(true, map.get("powered"));
		
		// minecraft:snow_layer
		map = convert((byte) 78, 0);
		assertEquals(78, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(1, map.get("layer"));
		
		// minecraft:cactus
		map = convert((byte) 81, 0);
		assertEquals(81, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(0, map.get("age"));
		
		// minecraft:leaves2 acacia
		map = convert((byte) 161, 0);
		assertEquals(161, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("check_decay"));
		assertEquals(true, map.get("decayable"));
		
		// minecraft:leaves2 acacia no decay
		map = convert((byte) 161, 4);
		assertEquals(161, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(false, map.get("check_decay"));
		assertEquals(false, map.get("decayable"));
		
		// minecraft:leaves2 acacia check decay
		map = convert((byte) 161, 8);
		assertEquals(161, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("check_decay"));
		assertEquals(true, map.get("decayable"));
		
		// minecraft:leaves2 acacia check decay no decay
		map = convert((byte) 161, 12);
		assertEquals(161, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals(true, map.get("check_decay"));
		assertEquals(false, map.get("decayable"));
		
		// minecraft:log2 Acacia Log U/D
		map = convert((byte) 162, 0);
		assertEquals(162, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log2 Dark Oak Log U/D
		map = convert((byte) 162, 1);
		assertEquals(162, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("y", map.get("axis"));
		
		// minecraft:log2 Acacia Log E/W
		map = convert((byte) 162, 4);
		assertEquals(162, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log2 Dark Oak Log E/W
		map = convert((byte) 162, 5);
		assertEquals(162, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("z", map.get("axis"));
		
		// minecraft:log2 Acacia Log N/S
		map = convert((byte) 162, 8);
		assertEquals(162, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log2 Dark Oak Log N/S
		map = convert((byte) 162, 9);
		assertEquals(162, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("x", map.get("axis"));
		
		// minecraft:log2 Acacia Bark Only
		map = convert((byte) 162, 12);
		assertEquals(162, map.get("id"));
		assertEquals(0, map.get("data"));
		assertEquals("none", map.get("axis"));
		
		// minecraft:log2 Dark Oak Bark Only
		map = convert((byte) 162, 13);
		assertEquals(162, map.get("id"));
		assertEquals(1, map.get("data"));
		assertEquals("none", map.get("axis"));
	}
}
