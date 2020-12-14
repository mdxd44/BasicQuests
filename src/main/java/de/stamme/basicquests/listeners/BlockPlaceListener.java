package de.stamme.basicquests.listeners;

import de.stamme.basicquests.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class BlockPlaceListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		event.getBlock().setMetadata("basicquests.placed", new FixedMetadataValue(Main.plugin, true));
	}
	
}
