package de.stamme.basicquests.model.quests;

import de.stamme.basicquests.Main;
import java.text.MessageFormat;

public class GainLevelQuest extends Quest {


	// ---------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------

	public GainLevelQuest(int goal, Reward reward) {
		super(goal, reward);
	}


	// ---------------------------------------------------------------------------------------
	// Functionality
	// ---------------------------------------------------------------------------------------

	@Override
	public QuestData toData() {
		QuestData data = super.toData();
		data.setQuestType(QuestType.GAIN_LEVEL.name());
		return data;
	}


	// ---------------------------------------------------------------------------------------
	// Getter & Setter
	// ---------------------------------------------------------------------------------------

	/**
	 * @return String in the format: "Level up <goal> times"
	 */
	@Override
	public String getName() {
		int goal = this.getGoal();
		return goal > 1 ? MessageFormat.format(Main.l10n("quest.gainLevel.plural"), goal) : Main.l10n("quest.gainLevel.singular");
	}

	@Override
	public String[] getDecisionObjectNames() {
		return new String[]{QuestType.GAIN_LEVEL.name()};
	}

	@Override
	public final QuestType getQuestType() {
		return QuestType.GAIN_LEVEL;
	}

	@Override
	public String getOptionName() {
		return "" + getGoal();
	}
}
