package agents.nastyagent;

import java.util.List;

import negotiator.AgentID;
import negotiator.actions.Action;
import negotiator.actions.DefaultAction;

/**
 * returns a nonsense action
 * 
 * @author W.Pasman 20jul15
 *
 */
public class NonsenseActionInChoose extends NastyAgent {
	@Override
	public Action chooseAction(List<Class<? extends Action>> possibleActions) {
		return new NonsenseAction(id);
	}
}

class NonsenseAction extends DefaultAction {
	public NonsenseAction(AgentID agentID) {
		super(agentID);
	}

	@Override
	public String toString() {
		return null;
	}

}
