package de.stamme.basicquests.listeners;

import de.stamme.basicquests.main.Main;
import de.stamme.basicquests.main.QuestPlayer;
import de.stamme.basicquests.quests.BlockBreakQuest;
import de.stamme.basicquests.quests.ChopWoodQuest;
import de.stamme.basicquests.quests.MineBlockQuest;
import de.stamme.basicquests.quests.Quest;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;


public class BreakBlockListener implements Listener {

	/**
	 * Checks if the player has an active BlockBreakQuest with the according block. If so, updates the quests progress.
	 */
	@EventHandler
	public void onBreakBlock(@NotNull BlockBreakEvent event) {
		Block block = event.getBlock();
		
		if (Main.getPlugin().getQuestPlayers().containsKey(event.getPlayer().getUniqueId())) {
			QuestPlayer questPlayer = Main.getPlugin().getQuestPlayers().get(event.getPlayer().getUniqueId());
			
			// Check whether the block has been placed by a player to prevent exploitation
			boolean placedByPlayer = block.hasMetadata("basicquests.placed");
			
			for (Quest q: questPlayer.getQuests()) {
				// BlockBreakQuest
				if (q instanceof BlockBreakQuest && !placedByPlayer) {
					BlockBreakQuest bbq = (BlockBreakQuest) q;

					if (bbq.getMaterial() == block.getType()) {
						bbq.progress(1, questPlayer);
					}

				} else if (q instanceof ChopWoodQuest && !placedByPlayer) {
					ChopWoodQuest cwq = (ChopWoodQuest) q;


					if (cwq.getMaterial() == block.getType() ||
						(cwq.getMaterialString() != null && cwq.getMaterialString().equals("LOG") &&
								(block.getType() == Material.ACACIA_LOG |
										block.getType() == Material.BIRCH_LOG |
										block.getType() == Material.DARK_OAK_LOG |
										block.getType() == Material.JUNGLE_LOG |
										block.getType() == Material.OAK_LOG |
										block.getType() == Material.SPRUCE_LOG))) {

						cwq.progress(1, questPlayer);
					}
				}
				
				// MineBlockQuest
				else if (q instanceof MineBlockQuest && !placedByPlayer) {
					MineBlockQuest mbq = (MineBlockQuest) q;
					if (mbq.getMaterial() == block.getType()) {
						mbq.progress(1, questPlayer);
					}
				}
			}
		}
	}
}
