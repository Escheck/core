package negotiator.timeline;

/**
 * Implementation of the timeline in which time is divided in rounds. Time does
 * not pass within a round. Note that requesting the total time is in this case
 * undefined.
 * 
 * NOTICE: DiscreteTimeline assumes each action of a player a 'round'. In
 * PocketNegotiator this would be called a 'turn'. Check also
 * {@link #getOwnRoundsLeft()}
 */
public class DiscreteTimeline extends Timeline {
	/** With 3 rounds, this is set to 4. */
	private int totalRounds;
	/**
	 * E.g. with 3 rounds, it takes the values 1, 2, 3, and on 4 is the
	 * deadline.
	 */
	protected int cRound;

	/**
	 * Creates a timeline with a deadline of {@link #totalRounds} number of
	 * rounds.
	 */
	public DiscreteTimeline(int totalRounds) {
		// makes more sense to add one, as in this case "totalrounds" is still
		// before the deadline as the
		// deadline is on time >= 1.0
		this.totalRounds = totalRounds + 1;
		hasDeadline = true;
		cRound = 1;
	}

	/**
	 * Prints time in seconds
	 */
	public void printRoundElapsed() {
		System.out.println("Elapsed Rounds: " + cRound);
	}

	/**
	 * Prints time, running from t = 0 (start) to t = 1 (deadline).
	 */
	@Override
	public void printTime() {
		System.out.println("t = " + getTime());
	}

	/**
	 * @return the time, running from t = 0 (start) to t = 1 (deadline). The
	 *         time is normalized, so agents need not be concerned with the
	 *         actual internal clock.
	 */
	@Override
	public double getTime() {
		double t = (double) cRound / (double) totalRounds;
		if (t > 1)
			t = 1;
		return t;
	}

	public void increment() {
		cRound++;
	}

	public void setcRound(int cRound) {
		this.cRound = cRound;
	}

	/**
	 * The DiscreteTimeline does not have to be paused/resumed, as time does not
	 * pass within a round.
	 */
	public void pause() {
	}

	/**
	 * The DiscreteTimeline does not have to be paused/resumed, as time does not
	 * pass within a round.
	 */
	public void resume() {
	}

	/**
	 * This a hack because in Agent.java a method sleep requires this
	 */
	@Override
	public double getTotalTime() {
		return totalRounds;
	}

	/**
	 * Starting to count from 1, until the total amount of rounds.
	 */
	public int getRound() {
		return cRound;
	}

	public int getRoundsLeft() {
		return totalRounds - cRound - 1;
	}

	/**
	 * Be careful, this is not equal to the initializing value!
	 */
	public int getTotalRounds() {
		return totalRounds;
	}

	/**
	 * The total number of rounds for ourself. Be careful, this is not equal to
	 * the initializing value!
	 */
	public int getOwnTotalRounds() {
		return (int) Math.floor(getTotalRounds() / 2);
	}

	/**
	 * The number of rounds left for ourself.
	 */
	public int getOwnRoundsLeft() {
		return (int) Math.floor(getRoundsLeft() / 2);
	}

	public Type getType() {
		return Type.Rounds;
	}

	@Override
	public double getCurrentTime() {
		return cRound;
	}
}