package de.stamme.basicquests.listeners;

import de.stamme.basicquests.main.Main;
import de.stamme.basicquests.main.QuestPlayer;
import de.stamme.basicquests.quests.EntityKillQuest;
import de.stamme.basicquests.quests.Quest;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDeathListener implements Listener {

	@EventHandler
	public void onEntityDeath(@NotNull EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();

		if (entity.getKiller() == null) return;
		if (!Main.getPlugin().getQuestPlayers().containsKey(entity.getKiller().getUniqueId())) return;

		QuestPlayer questPlayer = Main.getPlugin().getQuestPlayers().get(entity.getKiller().getUniqueId());

		for (Quest q: questPlayer.getQuests()) {
			if (!(q instanceof EntityKillQuest)) continue;
			// is EntityKillQuest
			EntityKillQuest ekq = (EntityKillQuest) q;

			if (ekq.getEntity() != entity.getType()) continue;
			// is correct entity
			ekq.progress(1, questPlayer);
		}
	}
}
