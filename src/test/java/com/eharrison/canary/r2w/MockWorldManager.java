package com.eharrison.canary.r2w;

import java.util.Collection;
import java.util.List;

import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.WorldType;
import net.canarymod.config.WorldConfiguration;

public class MockWorldManager implements WorldManager {
	@Override
	public World getWorld(final String name, final boolean autoload) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public World getWorld(final String name, final DimensionType type, final boolean autoload) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean createWorld(final String name, final DimensionType dimensionType) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean createWorld(final String name, final long seed, final DimensionType dimensionType) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean createWorld(final String name, final long seed, final DimensionType dimensionType,
			final WorldType worldType) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean createWorld(final WorldConfiguration configuration) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean destroyWorld(final String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public World loadWorld(final String name, final DimensionType type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void unloadWorld(final String name, final DimensionType type, final boolean force) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Collection<World> getAllWorlds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean worldIsLoaded(final String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean worldIsLoaded(final String name, final DimensionType type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean worldExists(final String name) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<String> getExistingWorlds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] getExistingWorldsArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] getLoadedWorldsNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] getLoadedWorldsNamesOfDimension(final DimensionType dimensionType) {
		// TODO Auto-generated method stub
		return null;
	}
}
