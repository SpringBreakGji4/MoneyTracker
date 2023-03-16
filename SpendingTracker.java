import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class SpendingTracker extends JFrame implements ActionListener{
	private JTextField itemField, costField, totalField;
    	private JTextArea itemList;
    	private double total = 0;
	
	public static void main(String[] args){
		new SpendingTracker();
	}
	public SpendingTracker(){
		super("Spending Tracker");
		

		itemField = new JTextField(10);
        	costField = new JTextField(10);
        	totalField = new JTextField(10);
        	totalField.setEditable(false);
        	itemList = new JTextArea(5, 20);
        	JScrollPane scrollPane = new JScrollPane(itemList);
        	JButton addButton = new JButton("Add");
        	addButton.addActionListener(this);
		
		setLayout(new GridBagLayout());
        	GridBagConstraints c = new GridBagConstraints();
        	c.insets = new Insets(5, 5, 5, 5);  // set the external padding
        	c.gridx = 0;
        	c.gridy = 0;
        	add(new JLabel("Item:"), c);
        	c.gridx = 1;
        	add(itemField, c);
        	c.gridx = 0;
        	c.gridy = 1;
        	add(new JLabel("Cost:"), c);
        	c.gridx = 1;
        	add(costField, c);
        	c.gridx = 2;
        	add(addButton, c);
        	c.gridx = 0;
        	c.gridy = 2;
        	add(new JLabel("Total:"), c);
        	c.gridx = 1;
        	add(totalField, c);
        	c.gridx = 0;
        	c.gridy = 3;
        	c.gridwidth = 3;
        	add(scrollPane, c);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	setSize(400, 300);
        	setLocationRelativeTo(null);
        	
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		add(exitButton, BorderLayout.SOUTH);
		pack();
			
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width - getWidth();
		int y = screenSize.height - getHeight();
		setLocation(x, y);
		
		setVisible(true);

	}
	public void actionPerformed(ActionEvent e){
		String item = itemField.getText();
        	double cost = Double.parseDouble(costField.getText());
       		total += cost;
        	itemList.append(item + "\t$" + cost + "\n");
       	 	totalField.setText("$" + total);
        	itemField.setText("");
        	costField.setText("");
		
		try (FileWriter fw = new FileWriter("spending.txt", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            		out.println(item + "," + cost);
        	}	 
		catch (IOException ex){
            		System.err.println("Error writing to file: " + ex.getMessage());
        	}
	}
}
