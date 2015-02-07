package com.eharrison.canary.r2w;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.Future;

import net.canarymod.api.world.DimensionType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class ReturnTest {
	@Test
	public void getTemplateBlock() throws Exception {
		final File worldsDir = new File("src/test/resources/worlds");
		final File templatesDir = new File("src/test/resources/templates");
		
		final Logger logger = LogManager.getLogger();
		final TemplateManager templateManager = new TemplateManager(logger, new MockWorldManager(),
				worldsDir, templatesDir);
		
		final Future<Boolean> future = templateManager.createTemplate("default", DimensionType.NORMAL);
		if (future.get()) {
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -261, 69, 229).type);
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 229).type);
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -259, 69, 229).type);
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -258, 69, 229).type);
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 230).type);
			assertEquals(57,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 228).type);
			assertEquals(2,
					templateManager.getTemplateBlock("default", DimensionType.NORMAL, -260, 69, 227).type);
		} else {
			fail("Failed to create the template");
		}
	}
	
	@Test
	public void restore() throws Exception {
		final File worldsDir = new File("src/test/resources/worlds");
		final File templatesDir = new File("src/test/resources/templates");
		
		final Logger logger = LogManager.getLogger();
		final TemplateManager templateManager = new TemplateManager(logger, new MockWorldManager(),
				worldsDir, templatesDir);
		
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
		final File worldsDir = new File("src/test/resources/worlds");
		final File templatesDir = new File("src/test/resources/templates");
		
		final Logger logger = LogManager.getLogger();
		final TemplateManager templateManager = new TemplateManager(logger, new MockWorldManager(),
				worldsDir, templatesDir);
		
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
}
