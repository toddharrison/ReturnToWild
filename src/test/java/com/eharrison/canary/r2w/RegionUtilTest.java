package com.eharrison.canary.r2w;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegionUtilTest {
	@Test
	public void getChunkForBlockCoordinate() {
		assertEquals(-2, RegionUtil.getChunkForBlockCoordinate(-17));
		assertEquals(-1, RegionUtil.getChunkForBlockCoordinate(-16));
		assertEquals(-1, RegionUtil.getChunkForBlockCoordinate(-1));
		assertEquals(0, RegionUtil.getChunkForBlockCoordinate(0));
		assertEquals(0, RegionUtil.getChunkForBlockCoordinate(15));
		assertEquals(1, RegionUtil.getChunkForBlockCoordinate(16));
		assertEquals(1, RegionUtil.getChunkForBlockCoordinate(31));
		assertEquals(2, RegionUtil.getChunkForBlockCoordinate(32));
	}
	
	@Test
	public void getRegionForChunkCoordinate() {
		assertEquals(-2, RegionUtil.getRegionForChunkCoordinate(-33));
		assertEquals(-1, RegionUtil.getRegionForChunkCoordinate(-32));
		assertEquals(-1, RegionUtil.getRegionForChunkCoordinate(-1));
		assertEquals(0, RegionUtil.getRegionForChunkCoordinate(0));
		assertEquals(0, RegionUtil.getRegionForChunkCoordinate(31));
		assertEquals(1, RegionUtil.getRegionForChunkCoordinate(32));
		assertEquals(1, RegionUtil.getRegionForChunkCoordinate(63));
		assertEquals(2, RegionUtil.getRegionForChunkCoordinate(64));
	}
	
	@Test
	public void getChunkRelativeBlockCoordinate() {
		assertEquals(15, RegionUtil.getChunkRelativeBlockCoordinate(-17));
		assertEquals(0, RegionUtil.getChunkRelativeBlockCoordinate(-16));
		assertEquals(14, RegionUtil.getChunkRelativeBlockCoordinate(-2));
		assertEquals(15, RegionUtil.getChunkRelativeBlockCoordinate(-1));
		assertEquals(0, RegionUtil.getChunkRelativeBlockCoordinate(0));
		assertEquals(1, RegionUtil.getChunkRelativeBlockCoordinate(1));
		assertEquals(2, RegionUtil.getChunkRelativeBlockCoordinate(2));
		assertEquals(15, RegionUtil.getChunkRelativeBlockCoordinate(15));
		assertEquals(0, RegionUtil.getChunkRelativeBlockCoordinate(16));
		assertEquals(1, RegionUtil.getChunkRelativeBlockCoordinate(17));
	}
	
	@Test
	public void getRegionRelativeChunkCoordinate() {
		assertEquals(31, RegionUtil.getRegionRelativeChunkCoordinate(-33));
		assertEquals(0, RegionUtil.getRegionRelativeChunkCoordinate(-32));
		assertEquals(30, RegionUtil.getRegionRelativeChunkCoordinate(-2));
		assertEquals(31, RegionUtil.getRegionRelativeChunkCoordinate(-1));
		assertEquals(0, RegionUtil.getRegionRelativeChunkCoordinate(0));
		assertEquals(1, RegionUtil.getRegionRelativeChunkCoordinate(1));
		assertEquals(2, RegionUtil.getRegionRelativeChunkCoordinate(2));
		assertEquals(31, RegionUtil.getRegionRelativeChunkCoordinate(31));
		assertEquals(0, RegionUtil.getRegionRelativeChunkCoordinate(32));
		assertEquals(1, RegionUtil.getRegionRelativeChunkCoordinate(33));
	}
	
	@Test
	public void getRegionBlockIntersection() {
		assertEquals(-512, RegionUtil.getRegionBlockIntersection(-1, -513));
		assertEquals(-512, RegionUtil.getRegionBlockIntersection(-1, -512));
		assertEquals(-1, RegionUtil.getRegionBlockIntersection(-1, -1));
		assertEquals(-1, RegionUtil.getRegionBlockIntersection(-1, 0));
		
		assertEquals(0, RegionUtil.getRegionBlockIntersection(0, -1));
		assertEquals(0, RegionUtil.getRegionBlockIntersection(0, 0));
		assertEquals(511, RegionUtil.getRegionBlockIntersection(0, 511));
		assertEquals(511, RegionUtil.getRegionBlockIntersection(0, 512));
		
		assertEquals(512, RegionUtil.getRegionBlockIntersection(1, 511));
		assertEquals(512, RegionUtil.getRegionBlockIntersection(1, 512));
		assertEquals(1023, RegionUtil.getRegionBlockIntersection(1, 1023));
		assertEquals(1023, RegionUtil.getRegionBlockIntersection(1, 1024));
	}
	
	@Test
	public void getChunkBlockIntersection() {
		assertEquals(-16, RegionUtil.getChunkBlockIntersection(-1, -17));
		assertEquals(-16, RegionUtil.getChunkBlockIntersection(-1, -16));
		assertEquals(-1, RegionUtil.getChunkBlockIntersection(-1, -1));
		assertEquals(-1, RegionUtil.getChunkBlockIntersection(-1, 0));
		
		assertEquals(0, RegionUtil.getChunkBlockIntersection(0, -1));
		assertEquals(0, RegionUtil.getChunkBlockIntersection(0, 0));
		assertEquals(15, RegionUtil.getChunkBlockIntersection(0, 15));
		assertEquals(15, RegionUtil.getChunkBlockIntersection(0, 16));
		
		assertEquals(16, RegionUtil.getChunkBlockIntersection(1, 15));
		assertEquals(16, RegionUtil.getChunkBlockIntersection(1, 16));
		assertEquals(31, RegionUtil.getChunkBlockIntersection(1, 31));
		assertEquals(31, RegionUtil.getChunkBlockIntersection(1, 32));
	}
}
