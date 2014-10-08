import java.util.*;
import java.io.File;
import java.io.IOException;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.table.*;

public class spamfilter extends JFrame implements ActionListener{

	// Declaring labels,textboxes, and buttons
	static JPanel jp1=new JPanel();
	static JPanel jp2=new JPanel();
	static JPanel jp3=new JPanel();
	static JLabel jl1=new JLabel("SPAM CHECKER");
	static JLabel jl2=new JLabel("Attached File:");
	static JTextField jt1=new JTextField(20);
	static JLabel jl3=new JLabel("Type Your Query:");
	static JTextField jt2=new JTextField(20);
	static JLabel jl4=new JLabel("SPAM OR NOT: ");
	static JTextField jt3=new JTextField(20);
	static JTextArea textArea = new JTextArea(10, 50);
	JButton jb1=new JButton("Attach File");
	JButton jb2=new JButton("Check if spam");
	JButton jb3=new JButton("Run Query");
	static JScrollPane scrollpane;

	// Database connection parameters declaration
	static String url = "jdbc:mysql://localhost/";
	static String dbName = "test";
	static String driver = "com.mysql.jdbc.Driver";
	static String userName = "root"; 
	static String password = "hello";
	static int count2;

	// Constructor to add components
	public spamfilter() {
		setSize(700,700);
		setTitle("Login");
		jp1.add(jl1);
		jp2.add(jl4);
		jp2.add(jt3);
		scrollpane=new JScrollPane(textArea);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jp2.add(scrollpane);  
		jp3.add(jl3); 
		jp3.add(jt2); 
		jp3.add(jl2); 
		jp3.add(jt1); 
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jp3.add(jb1); 
		jp3.add(jb2);
		jp3.add(jb3);
		setLayout(new GridLayout(3,1));
		add(jp1);
		add(jp2);
		add(jp3);
		setVisible(true);
	}

	// Main Class
	public static void main(String[] args) {
		int count=0;
		spamfilter s=new spamfilter();
		Connection conn = null;
		Statement state;

 		try {
			//Connecting to Database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			state=conn.createStatement();
			String sql="Select * from spam_mail";
			ResultSet rs=state.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();

			// Displaying Database in TextArea
			int columnsNumber = rsmd.getColumnCount();
			String output1=" ";
			for ( int i=1;i<columnsNumber+1;i++) 
			{
				output1=output1+rsmd.getColumnName(i)+"	";
			}
 		        textArea.setText(textArea.getText()+"\n"+ output1);
			while (rs.next()) 
			{
  			    String output=" ";
			    for ( int i=1;i<columnsNumber+1;i++) 
  			    {
				output=output+rs.getString(i)+"	";

			    }
			    textArea.setText(textArea.getText()+"\n"+ output);
			}

			// Closing connection to Database
  		        rs.close();
		        state.close();
		        conn.close();
		}
 		catch(Exception e) {
 		}
	}
	public void actionPerformed(ActionEvent e) {
	    //Handle open button action.
	    if (e.getSource() == jb1) {
		System.out.println(e.getSource());
		JFileChooser openFile = new JFileChooser();	
 		openFile.showOpenDialog(null);
		jt1.setText(openFile.getSelectedFile().getName());
	    }
	    //Handle run query button action.
	    else if (e.getSource() == jb3) {
	        textArea.setText("");
		Connection conn = null;
		Statement state;

 		try {
			//Connecting to Database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			state=conn.createStatement();
			String sql=jt2.getText();
			ResultSet rs=state.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			// Displaying Results of query in TextArea
			int columnsNumber = rsmd.getColumnCount();
			String output1=" ";
			for ( int i=1;i<columnsNumber+1;i++) 
			{
				output1=output1+rsmd.getColumnName(i)+"	";
			}
 		        textArea.setText(textArea.getText()+"\n"+ output1);
			while (rs.next()) 
			{
  			    String output=" ";
			    for ( int i=1;i<columnsNumber+1;i++) 
  			    {
				output=output+rs.getString(i)+"	";

			    }
			    textArea.setText(textArea.getText()+"\n"+ output);
			}
			System.out.println(sql);

			// Closing connection to Database
		        state.close();
		        conn.close();
		}
		catch(Exception e2) {
		}
	    }
	    //Handle check spam button action.
	    else if (e.getSource() == jb2) {
		// Calling spam filtering algorithm
		Map<String, Integer> h=new HashMap<String, Integer>();
		File file = null;
		if(jt1.getText()!=null) {
			file = new File(jt1.getText());
		}
		SpamMail sm_obj = new SpamMail();
		sm_obj.readFile(file);
		sm_obj.printMap(h);
	        sm_obj.computeIsSpam();
	        System.out.println("FINAL RESULT "+sm_obj.isSpam); 
		if(sm_obj.isSpam==true) {
			jt3.setText("SPAM");
		}
		else if(sm_obj.isSpam==false) {
			jt3.setText("NOT SPAM");
		}
		
		for(Map.Entry entry: h.entrySet()) {
			break;	
		}

		//Connecting to Database to insert values of new mail
		Connection conn = null;
		Statement state;
 		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			state=conn.createStatement();
			String sql2=""+count2+",";
			for(int i=0;i<57;i++) {
				sql2=sql2+sm_obj.spamData[i];
				if(i!=56) {
					sql2=sql2+",";
				}
			}
			String sql="Insert into spam_mail values("+sql2+",0)";
			state.execute(sql);
			System.out.println(sql);
		        state.close();
		        conn.close();
		}
		catch(Exception e2) {
		}
	    }
	}
}
