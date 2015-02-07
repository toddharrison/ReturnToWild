package com.eharrison.canary.r2w;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Future;

import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.blocks.BlockType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TemplateManagerTest {
	@Rule
	public TemporaryFolder templateTempDir = new TemporaryFolder();
	
	private TemplateManager templateManager;
	
	@Before
	public void init() throws URISyntaxException {
		final URL worldsUrl = this.getClass().getClassLoader().getResource("worlds");
		final File worldsDir = new File(worldsUrl.toURI());
		final File templatesDir = templateTempDir.getRoot();
		final Logger logger = LogManager.getLogger();
		templateManager = new TemplateManager(logger, new MockWorldManager(), worldsDir, templatesDir);
	}
	
	@Test
	public void getTemplateBlock() throws Exception {
		final Future<Boolean> future = templateManager.createTemplate("default", DimensionType.NORMAL);
		if (future.get()) {
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -261, 69, 229));
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 229));
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -259, 69, 229));
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -258, 69, 229));
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 230));
			assertEquals(BlockType.DiamondBlock,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 228));
			assertEquals(BlockType.Grass,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 227));
		} else {
			fail("Failed to create the template");
		}
	}
	
	@Test
	public void restore() throws Exception {
		final Future<Boolean> futureCreate = templateManager.createTemplate("default",
				DimensionType.NORMAL);
		if (futureCreate.get()) {
			final Future<Boolean> futureRestore = templateManager.restore("default",
					DimensionType.NORMAL, -1, 62, -1, 1, 63, 1);
			if (!futureRestore.get()) {
				fail("Failed to restore the template");
			}
		} else {
			fail("Failed to create the template");
		}
	}
	
	@Test
	public void restoreHigherThanSnapshot() throws Exception {
		final Future<Boolean> futureCreate = templateManager.createTemplate("default",
				DimensionType.NORMAL);
		if (futureCreate.get()) {
			final Future<Boolean> futureRestore = templateManager.restore("default",
					DimensionType.NORMAL, -1, 200, -1, 1, 201, 1);
			if (!futureRestore.get()) {
				fail("Failed to restore the template");
			}
		} else {
			fail("Failed to create the template");
		}
	}
	
	@Test
	public void update() throws Exception {
		final Future<Boolean> futureCreate = templateManager.createTemplate("default",
				DimensionType.NORMAL);
		if (futureCreate.get()) {
			assertNotEquals(BlockType.Air,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -1, 62, -1));
			final Future<Boolean> futureRestore = templateManager.update("default", DimensionType.NORMAL,
					-1, 62, -1, 1, 63, 1);
			if (futureRestore.get()) {
				assertEquals(BlockType.Air,
						templateManager.getTemplateBlock("default", DimensionType.NORMAL, -1, 62, -1));
			} else {
				fail("Failed to update the template");
			}
		} else {
			fail("Failed to update the template");
		}
	}
}