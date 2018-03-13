/*
 * ProgressUI2.java
 *
 * Created on September 8, 2008, 3:24 PM
 */

package negotiator.gui.progress;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import negotiator.Global;
import negotiator.NegotiationEventListener;
import negotiator.actions.Accept;
import negotiator.analysis.BidPoint;
import negotiator.analysis.BidPointTime;
import negotiator.analysis.BidSpace;
import negotiator.analysis.BidSpaceCache;
import negotiator.events.BilateralAtomicNegotiationSessionEvent;
import negotiator.events.LogMessageEvent;
import negotiator.gui.chart.BidChart;
import negotiator.protocol.BilateralAtomicNegotiationSession;
import negotiator.protocol.Protocol;
import negotiator.tournament.TournamentConfiguration;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

/**
 * 
 * @author dmytro
 */
public class ProgressUI2 extends javax.swing.JPanel implements
		NegotiationEventListener {

	private static final long serialVersionUID = -9120579814262948566L;
	/** the table model at the bottom */
	private ProgressInfo progressinfo;
	protected int round = 0;
	private BidChart bidChart;
	protected BilateralAtomicNegotiationSession session;
	private TextArea logText;
	private JPanel chart;
	private boolean showAllBids = false;
	private boolean showLastBid = false;

	// used from NegoSessionUI2 in combination with close
	// not clear how this relates to BilateralAtomicNegotiationSession
	private Protocol protocol = null;

	/**
	 * Creates new form ProgressUI2
	 */
	public ProgressUI2() {
		initComponents();
		if (!TournamentConfiguration.getBooleanOption("disableGUI", false)) {
			bidChart = new BidChart();
		}
		progressinfo = new ProgressInfo();
		// DEFAULT: show all bids
		showAllBids = TournamentConfiguration.getBooleanOption("showAllBids",
				true);
		// DEFAULT: show last bid
		showLastBid = TournamentConfiguration.getBooleanOption("showLastBid",
				true);
		biddingTable.setModel(progressinfo);
		biddingTable.setGridColor(Color.lightGray);
		if (!TournamentConfiguration.getBooleanOption("disableGUI", false)) {
			ProgressUI1("initialized...", bidChart, biddingTable);
		}
	}

	public ProgressUI2(boolean showAllBids, boolean showLastBid) {
		initComponents();
		bidChart = new BidChart();
		progressinfo = new ProgressInfo();
		this.showAllBids = showAllBids;
		this.showLastBid = showLastBid;
		biddingTable.setModel(progressinfo);
		biddingTable.setGridColor(Color.lightGray);
		ProgressUI1("initialized...", bidChart, biddingTable);
	}

	public void fillGUI(BilateralAtomicNegotiationSession ng) {
		setNegotiationSession(ng);
		setLogText(session.getLog());
		addGraph();
		addTableData();
	}

	public void ProgressUI1(String logging, BidChart bidChart, JTable bidTable) {
		Container pane = pnlChart;
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Border loweredetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);

		// the chart panel
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		JFreeChart plot = bidChart.getChart();
		chart = new ChartPanel(plot);
		chart.setMinimumSize(new Dimension(350, 350));
		chart.setBorder(loweredetched);
		c.insets = new Insets(10, 0, 0, 10);
		c.ipadx = 10;
		c.ipady = 10;
		pane.add(chart, c);

		pnlChart.add(chart);
		logText = new TextArea();

		logText.setText("");

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jSplitPane2 = new javax.swing.JSplitPane();
		jSplitPane1 = new javax.swing.JSplitPane();
		jSplitPane3 = new javax.swing.JSplitPane();
		pnlChart = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		biddingTable = new javax.swing.JTable();
		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		textOutput = new javax.swing.JTextArea();

		jSplitPane2.setName("jSplitPane2"); // NOI18N

		setName("Form"); // NOI18N

		jSplitPane1.setDividerSize(3);
		jSplitPane1.setName("jSplitPane1"); // NOI18N

		jSplitPane3.setDividerSize(3);
		jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane3.setName("jSplitPane3"); // NOI18N

		pnlChart.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Negotiation dynamics chart"));
		pnlChart.setName("pnlChart"); // NOI18N

		org.jdesktop.layout.GroupLayout pnlChartLayout = new org.jdesktop.layout.GroupLayout(
				pnlChart);
		pnlChart.setLayout(pnlChartLayout);
		pnlChartLayout.setHorizontalGroup(pnlChartLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 375,
				Short.MAX_VALUE));
		pnlChartLayout.setVerticalGroup(pnlChartLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 74,
				Short.MAX_VALUE));

		jSplitPane3.setTopComponent(pnlChart);

		jPanel3.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Exchanged offers"));
		jPanel3.setName("jPanel3"); // NOI18N

		jScrollPane2.setName("jScrollPane2"); // NOI18N

		biddingTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		biddingTable.setName("biddingTable"); // NOI18N
		jScrollPane2.setViewportView(biddingTable);

		org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 375,
				Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane2,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 167,
				Short.MAX_VALUE));

		jSplitPane3.setRightComponent(jPanel3);

		jSplitPane1.setRightComponent(jSplitPane3);

		jPanel1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Negotiation log"));
		jPanel1.setName("jPanel1"); // NOI18N

		jScrollPane1.setName("jScrollPane1"); // NOI18N

		textOutput.setColumns(20);
		textOutput.setRows(5);
		textOutput.setName("textOutput"); // NOI18N
		jScrollPane1.setViewportView(textOutput);

		org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 88,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jScrollPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 272,
				Short.MAX_VALUE));

		jSplitPane1.setLeftComponent(jPanel1);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jSplitPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 494,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(jSplitPane1,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 300,
				Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTable biddingTable;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JSplitPane jSplitPane2;
	private javax.swing.JSplitPane jSplitPane3;
	private javax.swing.JPanel pnlChart;
	private javax.swing.JTextArea textOutput;

	// End of variables declaration//GEN-END:variables

	private double[][] getPareto() {
		double[][] pareto = null;
		List<BidPoint> paretoBids = null;

		BidSpace bs = BidSpaceCache.getBidSpace(
				session.getAgentAUtilitySpace(),
				session.getAgentBUtilitySpace());
		if (bs == null)
			System.out.println("bidspace == null");
		else {
			try {
				paretoBids = bs.getParetoFrontier();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (paretoBids != null) {
				pareto = new double[2][paretoBids.size()];
				for (int i = 0; i < paretoBids.size(); i++) {
					pareto[0][i] = paretoBids.get(i).getUtilityA();
					pareto[1][i] = paretoBids.get(i).getUtilityB();
				}
			}
		}
		return pareto;
	}

	private double[][] getAllBidsInBidSpace() {
		// save the possible bids in double [][] and display in graph
		double[][] possibleBids = null;
		BidSpace bs = BidSpaceCache.getBidSpace(
				session.getAgentAUtilitySpace(),
				session.getAgentBUtilitySpace());
		if (bs.bidPoints.size() > 300000)
			return possibleBids;
		else {
			ArrayList<BidPoint> allBids = bs.bidPoints;// always gives a
														// nullpointer
			if (allBids != null) {
				possibleBids = new double[2][allBids.size()];
				int i = 0;
				for (BidPoint p : bs.bidPoints) {
					possibleBids[0][i] = p.getUtilityA();
					possibleBids[1][i] = p.getUtilityB();
					i++;
				}
				// bidChart.setPossibleBids(possibleBids);
			} else {
				System.out.println("possibleBids is null");
			}
		}
		return possibleBids;
	}

	public void addLoggingText(String t) {
		textOutput.append(t + "\n");
		session.setLog(textOutput.getText());
	}

	public void setLogText(String str) {
		textOutput.setText(str);
	}

	public void setNegotiationSession(BilateralAtomicNegotiationSession nego) {
		nego.addNegotiationEventListener(this);
		session = nego;
		String agentAName = nego.getAgentAname();
		String agentBName = nego.getAgentBname();

		// If the names are useless, use getName(). If that is useless, use the
		// class name.
		if ("Agent A".equals(agentAName))
			agentAName = Global.getAgentDescription(nego.getAgentA());
		if ("Agent B".equals(agentBName))
			agentBName = Global.getAgentDescription(nego.getAgentB());

		bidChart.setAgentAName("Agent A:" + agentAName);
		bidChart.setAgentBName("Agent B:" + agentBName);
		BidSpace bs = BidSpaceCache.getBidSpace(
				session.getAgentAUtilitySpace(),
				session.getAgentBUtilitySpace());
		double[][] pb = null;
		if (showAllBids)
			pb = getAllBidsInBidSpace();

		double[][] agreement = new double[2][1];
		agreement[0][0] = -1;
		agreement[1][0] = -1;
		bidChart.setAgreementPoint(agreement);

		double[][] nash = new double[2][1];
		double[][] kalai = new double[2][1];
		try {
			if (pb == null) {
				pb = new double[2][1];
			}
			bidChart.setPossibleBids(pb);

			Double resA = session.getAgentAUtilitySpace().getReservationValue();
			Double resB = session.getAgentBUtilitySpace().getReservationValue();

			// Reservation values
			if (resA != null && resB != null && (resA > 0.0 || resB > 0.0)) {
				double[][] rvA = getReservationValueA(resA, resB);
				bidChart.setBidderAReservationValue(rvA);
				double[][] rvB = getReservationValueB(resA, resB);
				bidChart.setBidderBReservationValue(rvB);
			} else {
				bidChart.setBidderAReservationValue(new double[2][1]);
				bidChart.setBidderBReservationValue(new double[2][1]);
			}

			double[][] paretoB = getPareto();
			if (paretoB != null)
				bidChart.setPareto(paretoB);

			// nash
			BidPoint bp1 = bs.getNash();
			nash[0][0] = bp1.getUtilityA();
			nash[1][0] = bp1.getUtilityB();
			if (nash != null)
				bidChart.setNash(nash);
			// kalai
			BidPoint bp2 = bs.getKalaiSmorodinsky();
			kalai[0][0] = bp2.getUtilityA();
			kalai[1][0] = bp2.getUtilityB();
			if (kalai != null)
				bidChart.setKalai(kalai);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * only sets the nego session and connects to it.
	 * 
	 * @param proto
	 *            the protocol being used. Needed to terminate this session if
	 *            user closes the panel.
	 */
	public void setOldProtocol(Protocol proto) {
		proto.addNegotiationEventListener(this);
		protocol = proto;
	}

	private double[][] getReservationValueA(double resA, double resB) {
		double[][] rvA = new double[2][2];
		rvA[0][0] = resA;
		rvA[1][0] = resB;
		rvA[0][1] = resA;
		rvA[1][1] = 1.0;
		return rvA;
	}

	private double[][] getReservationValueB(double resA, double resB) {
		double[][] rvB = new double[2][2];
		rvB[0][0] = resA;
		rvB[1][0] = resB;
		rvB[0][1] = 1;
		rvB[1][1] = resB;
		return rvB;
	}

	/**
	 * Fills the columns of the bottom table.
	 * 
	 * TODO Wouter: this should be taken out of the agent's CPU time. Currently
	 * this does not follow my warning/prescribed way to do this (see the
	 * handleActionEvent docuemntation)
	 */
	public void handleActionEvent(negotiator.events.ActionEvent evt) {
		// System.out.println("Caught event "+evt+ "in ProgressUI");
		round += 1;
		if (round > biddingTable.getModel().getRowCount()) {
			progressinfo.addRow();
		}
		// round = evt.getRound();
		biddingTable.getModel().setValueAt(round, round - 1, 0);
		biddingTable.getModel()
				.setValueAt(evt.getAgentAsString(), round - 1, 1);
		biddingTable.getModel().setValueAt(evt.getNormalizedUtilityA(),
				round - 1, 2);
		biddingTable.getModel().setValueAt(evt.getNormalizedUtilityB(),
				round - 1, 3);
		biddingTable.getModel()
				.setValueAt(evt.getUtilADiscount(), round - 1, 4);
		biddingTable.getModel()
				.setValueAt(evt.getUtilBDsicount(), round - 1, 5);
		biddingTable.getModel().setValueAt(evt.getTime(), round - 1, 6);

		// adding graph data:
		double[][] curveA = session.getNegotiationPathA();
		double[][] curveB = session.getNegotiationPathB();

		if (curveA != null)
			bidChart.setBidSeriesA(curveA);
		if (curveB != null)
			bidChart.setBidSeriesB(curveB);

		if ((evt.getAct() instanceof Accept)) {
			double[][] ap = new double[2][1];
			ap[0][0] = evt.getNormalizedUtilityA();
			ap[1][0] = evt.getNormalizedUtilityB();
			bidChart.setAgreementPoint(ap);
		}

		if (showLastBid) {
			ArrayList<BidPointTime> agentABids = session.getAgentABids();
			ArrayList<BidPointTime> agentBBids = session.getAgentBBids();
			if (!agentABids.isEmpty()) {
				BidPoint bidPointA = agentABids.get(agentABids.size() - 1);
				double[][] pA = makeChartPoint(bidPointA);
				bidChart.setLastBidAData(pA);
			}
			if (!agentBBids.isEmpty()) {
				BidPoint bidPointB = agentBBids.get(agentBBids.size() - 1);
				double[][] pB = makeChartPoint(bidPointB);
				bidChart.setLastBidBData(pB);
			}
		}

	}

	private static double[][] makeChartPoint(BidPoint bidPoint) {
		if (bidPoint == null)
			return null;
		double[][] pA = new double[2][1];
		pA[0][0] = bidPoint.getUtilityA();
		pA[1][0] = bidPoint.getUtilityB();
		return pA;
	}

	public void addGraph() {
		// adding graph data:
		double[][] curveA = session.getNegotiationPathA();
		double[][] curveB = session.getNegotiationPathB();
		if (curveA != null)
			bidChart.setBidSeriesA(curveA);
		if (curveB != null)
			bidChart.setBidSeriesB(curveB);
		System.out.println("curveA length " + (curveA.length - 1));
		/*
		 * double [][]ap = new double [2][1]; ap[0][0]=
		 * curveA[0][curveA.length-1]; ap[1][0]= curveA[1][curveA.length-1];
		 * bidChart.setAgreementPoint(ap);
		 */
	}

	public void addTableData() {
		// System.out.println("updating the table...");
		double[][] curveA = session.getNegotiationPathA();
		double[][] curveB = session.getNegotiationPathB();
		// System.out.println(curveA.length);
		// System.out.println(curveA[0].length);
		// System.out.println(curveB.length);
		// System.out.println(curveB[0].length);

		// Wouter:due to a bug both paths contained all values twice.
		double[][] curve0 = curveA;
		double[][] curve1 = curveB;

		String starter = session.getStartingAgent();
		String second = "";
		if (starter.equals("Agent A")) {
			second = "Agent B";
		} else {
			second = "Agent A";
			curve0 = curveB; // Agent B started, then we need curveB at the even
								// points.
			curve1 = curveA;
		}
		System.out.println("nr of bids in session " + session.getNrOfBids());
		for (int i = 0; i < session.getNrOfBids(); i++) {
			if (i >= biddingTable.getModel().getRowCount()) {
				// System.out.println("i bigger than row count "+i);
				progressinfo.addRow();
			}
			// round = evt.getRound();
			biddingTable.getModel().setValueAt(i + 1, i, 0);
			if (i % 2 == 0) {
				biddingTable.getModel().setValueAt(starter, i, 1);
				// round 0 and 1 both need point 0 in a curve, round 2 and 3
				// point 1 in a curve, etc.
				biddingTable.getModel().setValueAt(curve0[0][i / 2], i, 2);
				biddingTable.getModel().setValueAt(curve0[1][i / 2], i, 3);
			} else {
				biddingTable.getModel().setValueAt(second, i, 1);
				biddingTable.getModel().setValueAt(curve1[0][i / 2], i, 2);
				biddingTable.getModel().setValueAt(curve1[1][i / 2], i, 3);

			}
			/*
			 * try{ biddingTable.getModel().setValueAt(curveA[0][i / 2],i,2);
			 * biddingTable.getModel().setValueAt(curveB[0][i/2],i,3);
			 * }catch(ArrayIndexOutOfBoundsException e){
			 * System.out.println("out of bounds "+i); }
			 */
		}
	}

	public void resetGUI() {
		// clear TextArea:
		// logText.setText("");
		textOutput.setText("");
		// clear table
		progressinfo.reset();

		round = 0;
	}

	public void handleLogMessageEvent(LogMessageEvent evt) {
		addLoggingText(evt.getMessage());
	}

	public void handleBlateralAtomicNegotiationSessionEvent(
			BilateralAtomicNegotiationSessionEvent evt) {
		setNegotiationSession(evt.getSession());
	}

	/**
	 * This is called when the parent closes this component. #869 we then
	 * terminate the running session.
	 */
	public void close() {
		if (protocol != null) {
			protocol.stopNegotiation();
		}
	}
}
