package agents.nastyagent;

import java.util.List;

import negotiator.actions.Action;

public class ThrowInChoose extends NastyAgent {

	@Override
	public Action chooseAction(List<Class<? extends Action>> possibleActions) {
		throw new RuntimeException("bla");
	}
}
