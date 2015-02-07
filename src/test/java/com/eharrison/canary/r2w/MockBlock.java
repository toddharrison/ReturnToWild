package com.eharrison.canary.r2w;

import java.util.Collection;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.packet.BlockChangePacket;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockBase;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.api.world.blocks.BlockMaterial;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.TileEntity;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;
import net.canarymod.api.world.blocks.properties.BlockIntegerProperty;
import net.canarymod.api.world.blocks.properties.BlockProperty;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;

import com.google.common.collect.ImmutableMap;

public class MockBlock implements Block {
	
	@Override
	public short getTypeId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setTypeId(final short type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public short getData() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setData(final short data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BlockType getType() {
		return BlockType.Air;
	}
	
	@Override
	public void setType(final BlockType type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setWorld(final World world) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BlockFace getFaceClicked() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setFaceClicked(final BlockFace face) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Block getFacingBlock(final BlockFace face) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Block getRelative(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setX(final int x) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setY(final int y) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setZ(final int z) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setStatus(final byte status) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public byte getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isAir() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public BlockMaterial getBlockMaterial() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getIdDropped() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getDamageDropped() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getQuantityDropped() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void dropBlockAsItem(final boolean remove) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TileEntity getTileEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean rightClick(final Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void sendUpdateToPlayers(final Player... players) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BlockChangePacket getBlockPacket() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Collection<BlockProperty> getPropertyKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ImmutableMap<BlockProperty, Comparable> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BlockProperty getPropertyForName(final String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Comparable getValue(final BlockProperty property) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setPropertyValue(final BlockProperty property, final Comparable value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setIntegerPropertyValue(final BlockIntegerProperty property, final int value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBooleanPropertyValue(final BlockBooleanProperty property, final boolean value) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean canApply(final BlockProperty property) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public BlockBase getBlockBase() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
