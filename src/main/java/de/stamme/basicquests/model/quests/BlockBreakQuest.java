package de.stamme.basicquests.model.quests;

import de.stamme.basicquests.Main;
import de.stamme.basicquests.util.StringFormatter;
import java.text.MessageFormat;
import org.bukkit.Material;

public class BlockBreakQuest extends Quest {


	// ---------------------------------------------------------------------------------------
	// Quest State
	// ---------------------------------------------------------------------------------------

	private final Material material;


	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public BlockBreakQuest(Material mat, int goal, Reward reward) {
		super(goal, reward);
		this.material = mat;
	}


	// ---------------------------------------------------------------------------------------
	// Functionality
	// ---------------------------------------------------------------------------------------

	@Override
	public QuestData toData() {
		QuestData data = super.toData();
		data.setQuestType(QuestType.BREAK_BLOCK.name());
		data.setMaterial(material.name());
		return data;
	}


	// ---------------------------------------------------------------------------------------
	// Getter & Setter
	// ---------------------------------------------------------------------------------------

	/**
	 * @return String in the format: "Break <amount> <material>"
 	 */
	@Override
	public String getName() {
		int goal = this.getGoal();
		String materialName = StringFormatter.getLocalizedName(this.material.name(), "block.minecraft.");
		return goal > 1
			? MessageFormat.format(Main.l10n("quest.blockBreak.plural"), goal, materialName)
			: MessageFormat.format(Main.l10n("quest.blockBreak.singular"), materialName);
	}

	@Override
	public String[] getDecisionObjectNames() {
		return new String[]{QuestType.BREAK_BLOCK.name(), material.name()};
	}

	@Override
	public String getOptionName() {
		return StringFormatter.format(material.toString());
	}

	public Material getMaterial() {
		return material;
	}

	@Override
	public final QuestType getQuestType() {
		return QuestType.BREAK_BLOCK;
	}
}
