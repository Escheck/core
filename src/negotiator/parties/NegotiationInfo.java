package negotiator.parties;

import negotiator.AgentID;
import negotiator.Deadline;
import negotiator.persistent.PersistentDataContainer;
import negotiator.timeline.TimeLineInfo;
import negotiator.utility.AbstractUtilitySpace;

/**
 * An object that collects all the init parameters for
 * {@link NegotiationParty#init()}. This makes the
 * {@link NegotiationParty#init(NegotiationInfo)} call cleaner, and it makes it
 * easier to add new functionality without having to fix all agents.
 * 
 *
 */
public class NegotiationInfo {
	private AbstractUtilitySpace utilSpace;
	private Deadline deadline;
	private TimeLineInfo timeline;
	private long randomSeed;
	private AgentID agentID;
	private PersistentDataContainer storage;

	/**
	 * @param utilSpace
	 *            (a copy of/readonly version of) the
	 *            {@link AbstractUtilitySpace} to be used for this session.
	 * @param deadline
	 *            The deadline used for this negotiation.
	 * @param timeline
	 *            The {@link TimeLineInfo} about current session.
	 * @param randomSeed
	 *            A random seed that can be used for creating "consistent
	 *            random" behaviour.
	 * @param agentID
	 *            The agent's ID.
	 * @param storage
	 *            storage space where the agent can store data that is
	 *            persistent over sessions. Depending on the run settings, each
	 *            [agentclass, profiles] tuple can have its own unique storage
	 *            that persists only during the run of a tournament. Between
	 *            sessions, this data is saved to disk to avoid memory issues
	 *            when other agents are running. If the storage is not empty,
	 *            this data is retrieved at the start of each session and saved
	 *            at the end of each session. The load is timeboxed by the
	 *            negotiation settings. The save time is limited to 1 second.
	 *            The programmer should ensure that storage is actually
	 *            serializable. This call is timeboxed by the negotiation
	 *            deadline settings.
	 */
	public NegotiationInfo(AbstractUtilitySpace utilSpace, Deadline deadline, TimeLineInfo timeline, long randomSeed,
			AgentID agentID, PersistentDataContainer storage) {
		if (utilSpace == null) {
			throw new NullPointerException("utilSpace");
		}
		if (deadline == null) {
			throw new NullPointerException("deadline");
		}
		if (timeline == null) {
			throw new NullPointerException("timeline");
		}
		if (agentID == null) {
			throw new NullPointerException("agentID");
		}
		if (storage == null) {
			throw new NullPointerException("storage");
		}
		this.utilSpace = utilSpace;
		this.deadline = deadline;
		this.timeline = timeline;
		this.randomSeed = randomSeed;
		this.agentID = agentID;
		this.storage = storage;
	}

	public AbstractUtilitySpace getUtilitySpace() {
		return utilSpace;
	}

	public long getRandomSeed() {
		return randomSeed;
	}

	public TimeLineInfo getTimeline() {
		return timeline;
	}

	public AgentID getAgentID() {
		return agentID;
	}

	public Deadline getDeadline() {
		return deadline;
	}

	public PersistentDataContainer getPersistentData() {
		return storage;
	}
}
