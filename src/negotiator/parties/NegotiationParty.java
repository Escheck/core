package negotiator.parties;

import java.util.List;
import java.util.Map;

import negotiator.AgentID;
import negotiator.Bid;
import negotiator.Deadline;
import negotiator.actions.Action;
import negotiator.persistent.PersistentDataContainer;
import negotiator.protocol.MultilateralProtocol;
import negotiator.protocol.StackedAlternatingOffersProtocol;
import negotiator.timeline.TimeLineInfo;
import negotiator.utility.AbstractUtilitySpace;
import negotiator.xml.XmlWriteStream;

/**
 * Base interface for Negotiation parties. All parties must minimally implement
 * this interface. Parties can extend {@link AbstractNegotiationParty} to get
 * more support from the parent class. Parties can do multilateral negotiations.
 * Notice that 'multilateral' includes 'bilateral'.
 * 
 * <h1>Protocol</h1>
 * <p>
 * Your implementation must adhere to the protocol that it specifies in
 * {@link #getProtocol()}. If it doesn't, it may be kicked from the negotiation
 * at runtime.
 * 
 * <p>
 * Implementors of this class must have a public no-argument constructor. In
 * fact we recommend not to implement any constructor at all and do
 * initialization in the init() call.
 * <p>
 * Immediately after construction of the class, the functiondsf
 * {@link #init(AbstractUtilitySpace, Deadline, TimeLineInfo, long, AgentID,PersistentDataContainer)}
 * will be called.
 *
 * <h1>Sand-boxing</h1>
 * <p>
 * The functions in this interface are implemented by competitors in a
 * competition of agents. All calls to the functions are sand-boxed in an
 * attempt to ensure the competition will follow the protocols (instead of
 * crashing). Sand-boxing attempts to protect the rest of the system for
 * out-of-memory, time-out, throws, and various types of {@link SecurityManager}
 * related issues (eg calling {@link System#exit(int)}) that may occur inside
 * the implemented party.
 * </p>
 * <p>
 * The time limits are given below for each of the functions. Some functions are
 * limited to a fixed time limit. Other functions are limited to a deadline as
 * set in the actual settings for the negotiation. In that case, the deadline is
 * a global deadline for which the entire negotiation session must be completed.
 * If the deadline is round based, the session is also time-limited to 180
 * seconds.
 * </p>
 */
public interface NegotiationParty {
	/**
	 * Initializes the party. This will be called once, immediately after
	 * construction of any class implementing this. Tells which utility space
	 * and timeline the party is running in.
	 * 
	 * @param info
	 *            information about the negotiation that this party is part of.
	 */
	public void init(NegotiationInfo info);

	/**
	 * When this function is called, it is expected that the Party chooses one
	 * of the actions from the possible action list and returns an instance of
	 * the chosen action. This call is timeboxed by the negotiation deadline
	 * settings.
	 *
	 * 
	 * @param possibleActions
	 *            List of all actions possible.
	 * @return The chosen {@link Action}.
	 */
	public Action chooseAction(List<Class<? extends Action>> possibleActions);

	/**
	 * This method is called when another {@link NegotiationParty} chose an
	 * {@link Action}. This call is timeboxed by the negotiation deadline
	 * settings.
	 *
	 * @param sender
	 *            The initiator of the action.This is either the AgentID, or
	 *            null if the sender is not an agent (e.g., the protocol).
	 * @param action
	 *            The action performed
	 */
	void receiveMessage(AgentID sender, Action action);

	/**
	 * @return a human-readable description for this party. The call must finish
	 *         within 1 second.
	 */
	public String getDescription();

	/**
	 * Get the protocol that this party supports.
	 * 
	 * @return the actual supported {@link MultilateralProtocol}, usually
	 *         {@link StackedAlternatingOffersProtocol}. The call must finish
	 *         within 1 second.
	 */
	public Class<? extends MultilateralProtocol> getProtocol();

	/**
	 * This is called to inform the agent that the negotiation has been ended.
	 * This allows the agent to record some final conclusions about the run. The
	 * call must finish within 1 second.
	 * 
	 * @param acceptedBid
	 *            the final accepted bid, or null if no agreement was reached.
	 * @return {@link Map} containing data to log for this agent. null is equal
	 *         to returning an empty HashMap. Typically, this info will be
	 *         logged by {@link XmlWriteStream#write(String, java.util.Map)}
	 */
	public Map<String, String> negotiationEnded(Bid acceptedBid);
}
