package apps.inventory.screens;

import org.openlegacy.annotations.screen.Action;
import org.openlegacy.annotations.screen.Identifier;
import org.openlegacy.annotations.screen.ScreenActions;
import org.openlegacy.annotations.screen.ScreenBooleanField;
import org.openlegacy.annotations.screen.ScreenEntity;
import org.openlegacy.annotations.screen.ScreenField;
import org.openlegacy.annotations.screen.ScreenIdentifiers;
import org.openlegacy.annotations.screen.ScreenNavigation;
import org.openlegacy.definitions.EnumGetValue;
import org.openlegacy.terminal.actions.TerminalActions;

@ScreenEntity
@ScreenIdentifiers(identifiers = { @Identifier(row = 6, column = 2, value = "Item Number . ."),
		@Identifier(row = 7, column = 2, value = "Item Description") })
@ScreenActions(actions = { @Action(action = TerminalActions.F2.class, displayName = "Save", alias = "save") })
@ScreenNavigation(accessedFrom = ItemsList.class, exitAction = TerminalActions.F12.class)
public class ItemDetails1 {

	@ScreenField(row = 6, column = 33, key = true)
	private Integer itemNumber;

	@ScreenField(row = 7, column = 33, endColumn = 40, editable = true)
	private String itemDescription;

	@ScreenBooleanField(trueValue = "Y", falseValue = "N")
	@ScreenField(row = 20, column = 33, editable = true)
	private Boolean palletLabelRequired;

	@ScreenField(row = 18, column = 33, editable = true)
	private OuterUnitOfMeasure outerUnitOfMeasure;

	private ItemDetails2 itemDetails2;

	public enum OuterUnitOfMeasure implements EnumGetValue {
		Kilogram("kg", "kilogram"),
		Ton("tn", "ton");

		private String value;
		private String display;

		OuterUnitOfMeasure(String value, String display) {
			this.value = value;
			this.display = display;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return display;
		}
	}
}
