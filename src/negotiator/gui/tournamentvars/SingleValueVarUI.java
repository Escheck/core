package negotiator.gui.tournamentvars;

import java.awt.Frame;
import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import negotiator.tournament.VariablesAndValues.TotalSessionNumberValue;
import panels.DefaultOKCancelDialog;

public class SingleValueVarUI extends DefaultOKCancelDialog {

	private static final long serialVersionUID = -2316094573538048269L;
	private JTextField textField;
	
	public SingleValueVarUI(Frame frame) {
		super(frame, "Number of sessions");
	}
	
	@Override
	public Panel getPanel() {
		textField = new JTextField();
		Panel panel = new Panel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(textField);
		return panel;

	}

	@Override
	public Object ok() {
		return new TotalSessionNumberValue(Integer.valueOf(textField.getText()));
	}

}
