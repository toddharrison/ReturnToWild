package com.eharrison.canary.r2w.io;

import static org.easymock.EasyMock.*;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;

import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NewAnvilConverterTest {
	@TestSubject
	private AnvilConverter converter;
	
	@Mock
	private Block mockBlock;
	
	@Before
	public void init() {
		mockBlock = createMock(Block.class);
		converter = new AnvilConverter();
	}
	
	@Test
	public void testAir() {
		mockBlock.setType(BlockType.Air);
		mockBlock.update();
		replay(mockBlock);
		
		final byte id = 0;
		final int data = 0;
		AnvilConverter.convert(mockBlock, id, data);
		verify(mockBlock);
	}
	
	@Test
	public void testStone() {
		mockBlock.setType(BlockType.Stone);
		mockBlock.update();
		replay(mockBlock);
		
		final byte id = 1;
		final int data = 0;
		AnvilConverter.convert(mockBlock, id, data);
		verify(mockBlock);
	}
	
	@Test
	public void testGranite() {
		mockBlock.setType(BlockType.Granite);
		mockBlock.update();
		replay(mockBlock);
		
		final byte id = 1;
		final int data = 1;
		AnvilConverter.convert(mockBlock, id, data);
		verify(mockBlock);
	}
	
	@Ignore
	@Test
	public void testWoodenStairs() {
		mockBlock.setType(BlockType.WoodenStair);
		mockBlock.update();
		replay(mockBlock);
		
		final byte id = 53;
		final int data = 0;
		AnvilConverter.convert(mockBlock, id, data);
		verify(mockBlock);
	}
}
