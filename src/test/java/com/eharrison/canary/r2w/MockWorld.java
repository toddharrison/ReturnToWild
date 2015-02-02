package com.eharrison.canary.r2w;

import java.util.List;

import net.canarymod.api.EntityTracker;
import net.canarymod.api.GameMode;
import net.canarymod.api.PlayerManager;
import net.canarymod.api.chat.ChatComponent;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityItem;
import net.canarymod.api.entity.living.EntityLiving;
import net.canarymod.api.entity.living.animal.EntityAnimal;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.entity.living.monster.EntityMob;
import net.canarymod.api.entity.vehicle.Boat;
import net.canarymod.api.entity.vehicle.Minecart;
import net.canarymod.api.entity.vehicle.Vehicle;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.Biome;
import net.canarymod.api.world.BiomeType;
import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.ChunkProvider;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.TreeType;
import net.canarymod.api.world.Village;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldType;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.TileEntity;
import net.canarymod.api.world.effects.AuxiliarySoundEffect;
import net.canarymod.api.world.effects.Particle;
import net.canarymod.api.world.effects.SoundEffect;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;

public class MockWorld implements World {
	
	@Override
	public void setNanoTick(final int tickIndex, final long tick) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getNanoTick(final int tickIndex) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public EntityTracker getEntityTracker() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DimensionType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EntityItem dropItem(final int x, final int y, final int z, final int itemId,
			final int amount, final int damage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EntityItem dropItem(final int x, final int y, final int z, final Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public EntityItem dropItem(final Position position, final Item item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EntityAnimal> getAnimalList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EntityMob> getMobList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EntityLiving> getEntityLivingList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Entity> getTrackedEntities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Player> getPlayerList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Boat> getBoatList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Minecart> getMinecartList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Vehicle> getVehicleList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<EntityItem> getItemList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Block getBlockAt(final int x, final int y, final int z) {
		return new MockBlock();
	}
	
	@Override
	public Block getBlockAt(final Position position) {
		return new MockBlock();
	}
	
	@Override
	public short getDataAt(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public short getDataAt(final Position position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Location getSpawnLocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setSpawnLocation(final Location position) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getLightLevelAt(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setLightLevelOnBlockMap(final int x, final int y, final int z, final int newLevel) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setLightLevelOnSkyMap(final int x, final int y, final int z, final int newLevel) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlock(final Block block) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final int x, final int y, final int z, final short type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final Position vector, final Block block) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final Position position, final short type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final Position position, final short type, final short data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final Position position, final BlockType type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final int x, final int y, final int z, final short type, final short data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setBlockAt(final int x, final int y, final int z, final BlockType type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setDataAt(final int x, final int y, final int z, final short data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void markBlockNeedsUpdate(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Player getClosestPlayer(final double x, final double y, final double z,
			final double distance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Player getClosestPlayer(final Entity entity, final int distance) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ChunkProvider getChunkProvider() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isChunkLoaded(final Block block) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isChunkLoaded(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isChunkLoaded(final int x, final int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Chunk loadChunk(final int x, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Chunk loadChunk(final Location location) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Chunk loadChunk(final Position vec3d) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Chunk getChunk(final int x, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Chunk> getLoadedChunks() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BiomeType getBiomeType(final int x, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Biome getBiome(final int x, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setBiome(final int x, final int z, final BiomeType biome) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getHighestBlockAt(final int x, final int z) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void playNoteAt(final int x, final int y, final int z, final int instrument,
			final byte notePitch) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setTime(final long time) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public long getRelativeTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getRawTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getTotalTime() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Difficulty getDifficulty() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setDifficulty(final Difficulty difficulty) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public WorldType getWorldType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void spawnParticle(final Particle particle) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playSound(final SoundEffect effect) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playAUXEffect(final AuxiliarySoundEffect effect) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void playAUXEffectAt(final Player player, final AuxiliarySoundEffect effect) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getFqName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PlayerManager getPlayerManager() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getBlockPower(final Block block) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getBlockPower(final Position position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getBlockPower(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean isBlockPowered(final Block block) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isBlockPowered(final Position position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isBlockPowered(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isBlockIndirectlyPowered(final Block block) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isBlockIndirectlyPowered(final Position position) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isBlockIndirectlyPowered(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setThundering(final boolean thundering) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setThunderStrength(final float strength) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public float getThunderStrength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setThunderTime(final int ticks) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setRaining(final boolean downfall) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setRainStrength(final float strength) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public float getRainStrength() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setRainTime(final int ticks) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isRaining() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isThundering() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void makeLightningBolt(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void makeLightningBolt(final Position position) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void makeExplosion(final Entity exploder, final double x, final double y, final double z,
			final float power, final boolean smoke) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void makeExplosion(final Entity exploder, final Position position, final float power,
			final boolean smoke) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getRainTicks() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getThunderTicks() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public long getWorldSeed() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void removePlayerFromWorld(final Player player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addPlayerToWorld(final Player player) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TileEntity getTileEntity(final Block block) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TileEntity getTileEntityAt(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TileEntity getOnlyTileEntity(final Block block) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TileEntity getOnlyTileEntityAt(final int x, final int y, final int z) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public GameMode getGameMode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setGameMode(final GameMode mode) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void broadcastMessage(final String msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Village> getVillages() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Village getNearestVillage(final Position position, final int radius) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Village getNearestVillage(final Location location, final int radius) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Village getNearestVillage(final int x, final int y, final int z, final int radius) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean generateTree(final Position pos, final TreeType type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void showTitle(final ChatComponent title) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void showTitle(final ChatComponent title, final ChatComponent subtitle) {
		// TODO Auto-generated method stub
		
	}
	
}
