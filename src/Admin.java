import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

import java.awt.Font;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.awt.Dimension;

import javax.swing.table.TableColumnModel;
import java.util.Arrays;
import java.awt.Image;
import java.awt.Cursor;
import com.mysql.cj.jdbc.exceptions.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Admin extends JFrame implements ActionListener
{
	JLabel lblAdmin, lblSettings, lblSearch, lblAddEmployee, lblBack;
	JTextField tfSearch;
    JButton btnTimeLogs, btnEdit, btnDeleteEmployee, btnDeleteAll;
    JPanel panelMain, panelTOP, panelSearch, panelUpperRightCorner, panelMID, panelEmpTableButtons, panelButtonLEFT, panelButtonRIGHT, panelBottom;
	
	ImageIcon logoAdmin = new ImageIcon("img/Admin logo.jpg"), logoSettings = new ImageIcon("img/settings logo.png"), logoSettingsClicked = new ImageIcon("img/settings clicked logo.png");
	ImageIcon logoSearch = new ImageIcon("img/search logo.png"), logoSearchClicked = new ImageIcon("img/search clicked logo.png"), logoAddEmployee = new ImageIcon("img/add employee logo.png"), logoAddEmployeeClicked  = new ImageIcon("img/add employee clicked logo.png");
	ImageIcon logoBack = new ImageIcon("img/back logo.png"), logoBackClicked = new ImageIcon("img/back clicked logo.png"), logoTimeLogs = new ImageIcon("img/time logs logo.png"), logoTimeLogsClicked = new ImageIcon("img/time logs clicked logo.png");
	ImageIcon logoEdit = new ImageIcon("img/edit logo.png"), logoEditClicked = new ImageIcon("img/edit clicked logo.png"), logoDeleteEmployee = new ImageIcon("img/delete user logo.png"), logoDeleteEmployeeClicked = new ImageIcon("img/delete user clicked logo.png");
	ImageIcon logoDeleteAll = new ImageIcon("img/delete all user logo.png"), logoDeleteAllClicked = new ImageIcon("img/delete all user clicked logo.png");
	Image image, newimg;
	
	DefaultTableModel tableModel = new DefaultTableModel();
	JTable table = new JTable(tableModel);
	String[] columnEmployee = {"Complete Name", "ID #", "Age", "Gender", "Address"}, record = new String[5];
	TableColumnModel colModel;
	
    JLabel lblVerify = new JLabel("");
	JTextField tfFname = new JTextField(20), tfMname = new JTextField(20), tfLname = new JTextField(20), tfAge, tfAddress = new JTextField(20);
	JPasswordField pfPasscode = new JPasswordField(15), pfVerifyPasscode = new JPasswordField(15);
	JButton btnClearAll;
	String genderList[]={"-Select-","Male","Female"}, noFname; 
	JComboBox<String> cbGender = new JComboBox<>(genderList);
	
	/*--- mostly for table Login PC user ---*/
	JLabel lblLogPC, lblSystemPC, lblUsersLogHeader;
	JPanel panelLIPC, panelLogPC, panelUsersLogHeader, panelTableLoggedPC, panelGapTable, panelwithGAP, panelwithSeparator, panelTOPTOP, panelBottomBorder;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
	
	DefaultTableModel tablemodelLoggedPC = new DefaultTableModel();
	JTable tableLoggedPC = new JTable(tablemodelLoggedPC);
	String[] columnUsersLoggedPC = {"Name", "ID #", "Date In", "Time In", "Date Out", "Time Out"}, recordLoggedPC = new String[6];
	TableColumnModel colmodelLoginPC;
	
	Connection dbConn;
	Statement sqlStmnt, fullnameStmnt;
	String sqlQuery, fullnameQuery;
	PreparedStatement ps;
	ResultSet sqlRS, fullnameRS;
	/*--- End . mostly for table Login PC user ---*/
	
	Admin()
	{	
		tfAge = new JTextField(3)
		{	public void processKeyEvent(KeyEvent ev)
			{	char c = ev.getKeyChar();
				try
				{	if (c > 31 && c < 127)// Ignore all non-printable characters. Just check the printable ones.
					{	Integer.parseInt(c + "");	}
					super.processKeyEvent(ev);
				}
				catch (NumberFormatException nfe) {}
			}
		};
		tfAge.addKeyListener(new KeyAdapter()
		{	@Override
			public void keyTyped(KeyEvent e)
			{	if (tfAge.getText().length() >= 3) // limit to 3 characters
					e.consume();
			}
		});
		tfAge.addFocusListener( new FocusAdapter()
		{	public void focusLost(FocusEvent e)
			{	if(tfAge.getText().length()!=0)
				{	if(Integer.parseInt(tfAge.getText()) != 0)
						tfAge.setText(String.valueOf(Integer.parseInt(tfAge.getText())));
					else
						tfAge.setText("0");
				}
			}
		});
		
		lblAdmin = new JLabel("");
		image = logoAdmin.getImage();
		newimg = image.getScaledInstance(125, 50, java.awt.Image.SCALE_SMOOTH);
		logoAdmin = new ImageIcon(newimg);lblAdmin.setIcon(logoAdmin);

/*--- SETTINGS ICON UPPER RIGHT HAND CORNER ---*/		
		lblSettings = new JLabel("  ");
		image = logoSettings.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoSettings = new ImageIcon(newimg);lblSettings.setIcon(logoSettings);
		lblSettings.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	image = logoSettingsClicked.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoSettingsClicked = new ImageIcon(newimg);lblSettings.setIcon(logoSettingsClicked);
			}
		});
		lblSettings.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoSettings.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoSettings = new ImageIcon(newimg);lblSettings.setIcon(logoSettings);
			}
		});
		lblSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSettings.setToolTipText("Account Settings: Reset Password");
/*--- End . SETTINGS ICON UPPER RIGHT HAND CORNER ---*/
		
/*--- SEARCH ICON ---*/
		lblSearch = new JLabel("");
		image = logoSearch.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoSearch = new ImageIcon(newimg);lblSearch.setIcon(logoSearch);
		lblSearch.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(lblSearch.isEnabled())
				{	image = logoSearchClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoSearchClicked = new ImageIcon(newimg);lblSearch.setIcon(logoSearchClicked);
				}
			}
		});
		lblSearch.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoSearch.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoSearch = new ImageIcon(newimg);lblSearch.setIcon(logoSearch);
			}
		});
		lblSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSearch.setToolTipText("Search");
/*--- End . SEARCH ICON ---*/

        tfSearch = new JTextField("Search employee", 20);
		tfSearch.setEnabled(false);
		tfSearch.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	if(lblSearch.isEnabled())
				{	table.clearSelection();
					tfSearch.setEnabled(true);
					tfSearch.requestFocus();
					if(tfSearch.getText().equals("Search employee"))
						tfSearch.setText("");
				}
			}
		});
		
/*--- ADD EMPLOYEE ICON ---*/
		lblAddEmployee = new JLabel("    ");
		image = logoAddEmployee.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoAddEmployee = new ImageIcon(newimg);lblAddEmployee.setIcon(logoAddEmployee);
		lblAddEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	image = logoAddEmployeeClicked.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoAddEmployeeClicked = new ImageIcon(newimg);lblAddEmployee.setIcon(logoAddEmployeeClicked);
			}
		});
		lblAddEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoAddEmployee.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoAddEmployee = new ImageIcon(newimg);lblAddEmployee.setIcon(logoAddEmployee);
			}
		});
		lblAddEmployee.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAddEmployee.setToolTipText("Add New Employee");
		lblAddEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	Addemp();	}
		});
/*--- End . ADD EMPLOYEE ICON ---*/

/*--- BACK ICON ---*/
		lblBack = new JLabel("");
		image = logoBack.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoBack = new ImageIcon(newimg);lblBack.setIcon(logoBack);
		lblBack.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	image = logoBackClicked.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoBackClicked = new ImageIcon(newimg);lblBack.setIcon(logoBackClicked);
			}
		});
		lblBack.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoBack.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoBack = new ImageIcon(newimg);lblBack.setIcon(logoBack);
			}
		});
		lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBack.setToolTipText("Logout");
		lblBack.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	focusMainWindow();
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout?", "Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{	close();
					
					Login login = new Login();
					login.pack();
					login.setLocationRelativeTo(null);
					login.setResizable(false);
					login.setVisible(true);
					login.setTitle("JavaWookies Time Tracking System");
					dispose();
				}
			}
		});
/*--- End . BACK ICON ---*/

/*--- TABLE TABLE TABLE ---*/
		try
		{	dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetracker", "admin", "adminuser");
			sqlStmnt = dbConn.createStatement();
			sqlQuery = "SELECT * FROM employees ORDER BY emp_lname ASC, emp_fname ASC, emp_mname ASC, emp_id ASC";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
						
			tableModel.setColumnIdentifiers(columnEmployee);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			colModel = table.getColumnModel();
			colModel.getColumn(0).setPreferredWidth(100);    
			colModel.getColumn(1).setPreferredWidth(5);
			colModel.getColumn(2).setPreferredWidth(3);
			colModel.getColumn(3).setPreferredWidth(6);
			colModel.getColumn(4).setPreferredWidth(140);
			
			table.getTableHeader().setReorderingAllowed(false);
			table.setDefaultEditor(Object.class, null); // making the table uneditable
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			while(sqlRS.next())
			{	if(!(sqlRS.getString("emp_id").equals("0")))
				{	if(sqlRS.getString("emp_mname").length()!=0)
					{	if(sqlRS.getString("emp_fname").length()!=0)
						{	if(sqlRS.getString("emp_lname").length()!=0)
								record[0] = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_fname") + " " + sqlRS.getString("emp_mname").substring(0, 1) + ".";
							else
								record[0] = sqlRS.getString("emp_fname") + " " + sqlRS.getString("emp_mname").substring(0, 1) + ".";
						}
						else
						{	if(sqlRS.getString("emp_lname").length()!=0)
								record[0] = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_mname").substring(0, 1) + ".";
							else
								record[0] = sqlRS.getString("emp_mname").substring(0, 1) + ".";
						}
					}
					else
					{	if(sqlRS.getString("emp_fname").length()!=0)
						{	if(sqlRS.getString("emp_lname").length()!=0)
								record[0] = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_fname");
							else
								record[0] = sqlRS.getString("emp_fname");
						}
						else
						{	if(sqlRS.getString("emp_lname").length()!=0)
								record[0] = sqlRS.getString("emp_lname");
							else
								record[0] = "";
						}
					}
				
					record[1] = sqlRS.getString("emp_id");
					record[2] = sqlRS.getString("emp_age");
					record[3] = sqlRS.getString("emp_gender");
					record[4] = sqlRS.getString("emp_address");
				
					tableModel.addRow(record);
				}	
			}
			
			table.setPreferredScrollableViewportSize(new Dimension(100, 160));
			table.setFillsViewportHeight(true);
			
			table.addMouseListener( new MouseAdapter()
			{	public void mouseClicked(MouseEvent e)
				{	if(tfSearch.getText().trim().isEmpty())
					{	tfSearch.setText("Search employee");
						tfSearch.setEnabled(false);
					}
				}
			});
		}
		catch(Exception error){ error.printStackTrace(); return; }
/*--- End . TABLE TABLE TABLE ---*/

/*--- BUTTONS BUTTONS BUTTONS BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS ---*/
	/*--- BUTTON TIME LOGS ---*/
		btnTimeLogs = new JButton("View Details");
        btnTimeLogs.addActionListener(this);
		
		image = logoTimeLogs.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoTimeLogs = new ImageIcon(newimg);btnTimeLogs.setIcon(logoTimeLogs);
		btnTimeLogs.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(btnTimeLogs.isEnabled())
				{	image = logoTimeLogsClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoTimeLogsClicked = new ImageIcon(newimg);btnTimeLogs.setIcon(logoTimeLogsClicked);
					btnTimeLogs.setForeground(new Color(0, 255, 234));
				}
			}
		});
		btnTimeLogs.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoTimeLogs.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoTimeLogs = new ImageIcon(newimg);btnTimeLogs.setIcon(logoTimeLogs);
				btnTimeLogs.setForeground(Color.BLACK);
			}
		});
		btnTimeLogs.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTimeLogs.setToolTipText("View selected employee's time logs and Account details");
	/*--- End . BUTTON TIME LOGS ---*/
	
	/*--- BUTTON EDIT ---*/
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(this);
	
		image = logoEdit.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoEdit = new ImageIcon(newimg);btnEdit.setIcon(logoEdit);
		btnEdit.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(btnEdit.isEnabled())
				{	image = logoEditClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoEditClicked = new ImageIcon(newimg);btnEdit.setIcon(logoEditClicked);
					btnEdit.setForeground(new Color(0, 255, 234));
				}
			}
		});
		btnEdit.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoEdit.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoEdit = new ImageIcon(newimg);btnEdit.setIcon(logoEdit);
				btnEdit.setForeground(Color.BLACK);
			}
		});
		btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEdit.setToolTipText("Edit employee's information");
	/*--- End . BUTTON EDIT ---*/
	
	/*--- BUTTON DELETE EMPLOYEE ---*/
		btnDeleteEmployee = new JButton("Delete");
		btnDeleteEmployee.addActionListener(this);
	
		image = logoDeleteEmployee.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoDeleteEmployee = new ImageIcon(newimg);btnDeleteEmployee.setIcon(logoDeleteEmployee);
		btnDeleteEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(btnDeleteEmployee.isEnabled())
				{	image = logoDeleteEmployeeClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoDeleteEmployeeClicked = new ImageIcon(newimg);btnDeleteEmployee.setIcon(logoDeleteEmployeeClicked);
					btnDeleteEmployee.setForeground(new Color(0, 255, 234));
				}
			}
		});
		btnDeleteEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoDeleteEmployee.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoDeleteEmployee = new ImageIcon(newimg);btnDeleteEmployee.setIcon(logoDeleteEmployee);
				btnDeleteEmployee.setForeground(Color.BLACK);
			}
		});
		btnDeleteEmployee.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDeleteEmployee.setToolTipText("Delete the selected employee / user");
	/*--- End . BUTTON DELETE EMPLOYEE ---*/
	
	/*--- BUTTON DELETE ALL EMPLOYEE ---*/
		btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.addActionListener(this);
	
		image = logoDeleteAll.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoDeleteAll = new ImageIcon(newimg);btnDeleteAll.setIcon(logoDeleteAll);
		btnDeleteAll.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(btnDeleteAll.isEnabled())
				{	image = logoDeleteAllClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoDeleteAllClicked = new ImageIcon(newimg);btnDeleteAll.setIcon(logoDeleteAllClicked);
					btnDeleteAll.setForeground(new Color(0, 255, 234));
				}
			}
		});
		btnDeleteAll.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoDeleteAll.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoDeleteAll = new ImageIcon(newimg);btnDeleteAll.setIcon(logoDeleteAll);
				btnDeleteAll.setForeground(Color.BLACK);
			}
		});
		btnDeleteAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDeleteAll.setToolTipText("Delete all employees / users");
	/*--- End . BUTTON DELETE ALL EMPLOYEE ---*/
/*--- BUTTONS BUTTONS BUTTONS BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS  BUTTONS BUTTONS BUTTONS ---*/
		
		try
		{	sqlQuery = "SELECT * FROM employees";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
			sqlRS.last();
			if(sqlRS.getString("emp_id").equals("0"))
				disableButtons();
		}
		catch(Exception error){ error.printStackTrace(); return; }

		panelMain = new JPanel(); panelMain.setLayout(new BorderLayout(1,1)); panelMain.setBackground(Color.lightGray);
		panelMain.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	focusMainWindow();	}
		});	
		
		panelTOP = new JPanel(); panelTOP.setLayout(new BorderLayout(1,1)); panelTOP.setBackground(Color.lightGray);
		panelTOP.setBorder(BorderFactory.createEtchedBorder(1));
		panelSearch = new JPanel(); panelSearch.setLayout(new BorderLayout(1,1)); panelSearch.setBackground(Color.lightGray);
		panelUpperRightCorner = new JPanel(); panelUpperRightCorner.setLayout(new BorderLayout(1,1)); panelUpperRightCorner.setBackground(Color.lightGray);
		panelMID = new JPanel(); panelMID.setLayout(new BorderLayout(1,1)); panelMID.setBackground(Color.lightGray);
		panelEmpTableButtons = new JPanel(); panelEmpTableButtons.setLayout(new BorderLayout(1,1)); panelEmpTableButtons.setBackground(Color.lightGray);
		panelButtonLEFT = new JPanel(); panelButtonLEFT.setLayout(new BorderLayout(1,1)); panelButtonLEFT.setBackground(Color.lightGray);
		panelButtonRIGHT = new JPanel(); panelButtonRIGHT.setLayout(new BorderLayout(1,1)); panelButtonRIGHT.setBackground(Color.lightGray);
		panelBottom = new JPanel(); panelBottom.setLayout(new BorderLayout(1,1)); panelBottom.setBackground(Color.lightGray);
		panelBottomBorder = new JPanel(); panelBottomBorder.setLayout(new BorderLayout(1,1)); panelBottomBorder.setBackground(Color.lightGray);
		
		panelUpperRightCorner.add(BorderLayout.CENTER, lblSettings);
		panelUpperRightCorner.add(BorderLayout.EAST, lblBack);	
		
		panelTOP.add(BorderLayout.WEST, lblAdmin);
		panelTOP.add(BorderLayout.EAST, panelUpperRightCorner);
		
		


		int rscount = 0;
		
		String hostname = "UNKNOWN";
		
		lblLogPC = new JLabel("  Current login PC: ");
		lblSystemPC = new JLabel(hostname); lblSystemPC.setForeground(new Color(189, 8, 27));
		
		try
		{	InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
			lblSystemPC.setText(hostname);
		}
		catch (UnknownHostException ex) {}
		
		lblUsersLogHeader = new JLabel("* Users / Employees who logged in to this PC *");
		
        panelLIPC = new JPanel(); panelLIPC.setLayout(new BorderLayout(1,1)); panelLIPC.setBackground(Color.lightGray);
		panelLogPC = new JPanel(); panelLogPC.setLayout(new BorderLayout(1,1)); panelLogPC.setBackground(Color.lightGray);
		panelUsersLogHeader = new JPanel(); panelUsersLogHeader.setLayout(new BorderLayout(1,1)); panelUsersLogHeader.setBackground(Color.lightGray);
		panelTableLoggedPC = new JPanel(); panelTableLoggedPC.setLayout(new BorderLayout(1,1)); panelTableLoggedPC.setBackground(Color.lightGray);
		panelGapTable = new JPanel(); panelGapTable.setLayout(new BorderLayout(1,1)); panelGapTable.setBackground(Color.lightGray);
		panelwithGAP = new JPanel(); panelwithGAP.setLayout(new BorderLayout(1,1)); panelwithGAP.setBackground(Color.lightGray);
		panelwithSeparator = new JPanel(); panelwithSeparator.setLayout(new BorderLayout(1,1)); panelwithSeparator.setBackground(Color.lightGray);
		panelTOPTOP = new JPanel(); panelTOPTOP.setLayout(new BorderLayout(1,1)); panelTOPTOP.setBackground(Color.lightGray);
				
		panelLogPC.add(BorderLayout.WEST, lblLogPC);
		panelLogPC.add(BorderLayout.CENTER, lblSystemPC);
		
		panelUsersLogHeader.add(BorderLayout.NORTH, new JLabel(" "));
		panelUsersLogHeader.add(BorderLayout.WEST, new JLabel("                                                "));
		panelUsersLogHeader.add(BorderLayout.CENTER, lblUsersLogHeader);
		
		/*--- TABLE FOR LOGGED IN PC ---*/
		tablemodelLoggedPC.setColumnIdentifiers(columnUsersLoggedPC);
		tableLoggedPC.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		colmodelLoginPC = tableLoggedPC.getColumnModel();
		colmodelLoginPC.getColumn(0).setPreferredWidth(50);    
		colmodelLoginPC.getColumn(1).setPreferredWidth(8);
		colmodelLoginPC.getColumn(2).setPreferredWidth(18);
		colmodelLoginPC.getColumn(3).setPreferredWidth(13);
		colmodelLoginPC.getColumn(4).setPreferredWidth(18);
		colmodelLoginPC.getColumn(5).setPreferredWidth(13);
		
		tableLoggedPC.getTableHeader().setReorderingAllowed(false);
		tableLoggedPC.setDefaultEditor(Object.class, null); // making the table uneditable
		tableLoggedPC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		try
		{	dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetracker", "admin", "adminuser");
			fullnameStmnt = dbConn.createStatement();
			sqlStmnt = dbConn.createStatement();
			sqlQuery = "SELECT * FROM timelogs WHERE computer_name = '" + hostname + "' ORDER BY date_in DESC, time_in DESC";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
			
			while(sqlRS.next())
			{	rscount++;
				recordLoggedPC[1] = sqlRS.getString("emp_id");
				
					/*--- query for fullname ---*/
					fullnameQuery  = "SELECT * FROM employees WHERE emp_id = '" + recordLoggedPC[1] + "'";
					fullnameRS = fullnameStmnt.executeQuery(fullnameQuery);
					fullnameRS.next();
					if(fullnameRS.getString("emp_mname").length()!=0)
					{	if(fullnameRS.getString("emp_fname").length()!=0)
						{	if(fullnameRS.getString("emp_lname").length()!=0)
								recordLoggedPC[0] = fullnameRS.getString("emp_lname") + ", " + fullnameRS.getString("emp_fname") + " " + fullnameRS.getString("emp_mname").substring(0, 1) + ".";
							else
								recordLoggedPC[0] = fullnameRS.getString("emp_fname") + " " + fullnameRS.getString("emp_mname").substring(0, 1) + ".";
						}
						else
						{	if(fullnameRS.getString("emp_lname").length()!=0)
								recordLoggedPC[0] = fullnameRS.getString("emp_lname") + ", " + fullnameRS.getString("emp_mname").substring(0, 1) + ".";
							else
								recordLoggedPC[0] = fullnameRS.getString("emp_mname").substring(0, 1) + ".";
						}
					}
					else
					{	if(fullnameRS.getString("emp_fname").length()!=0)
						{	if(fullnameRS.getString("emp_lname").length()!=0)
								recordLoggedPC[0] = fullnameRS.getString("emp_lname") + ", " + fullnameRS.getString("emp_fname");
							else
								recordLoggedPC[0] = fullnameRS.getString("emp_fname");
						}
						else
						{	if(fullnameRS.getString("emp_lname").length()!=0)
								recordLoggedPC[0] = fullnameRS.getString("emp_lname");
							else
								recordLoggedPC[0] = "";
						}
					}
					/*--- End . query for fullname ---*/
				
				recordLoggedPC[2] = dateFormat.format(sqlRS.getDate("date_in"));
				recordLoggedPC[3] = timeFormat.format(sqlRS.getTime("time_in"));
				
				if(sqlRS.getString("date_out").equals("0000-00-00"))
				{	recordLoggedPC[4] = "";
					recordLoggedPC[5] = "";
				}
				else
				{	recordLoggedPC[4] = dateFormat.format(sqlRS.getDate("date_out"));
					recordLoggedPC[5] = timeFormat.format(sqlRS.getTime("time_out"));
				}
				
				tablemodelLoggedPC.addRow(recordLoggedPC);
			}
		}
		catch(Exception error) { error.printStackTrace(); return; }
		
		tableLoggedPC.setPreferredScrollableViewportSize(new Dimension(520, 96));
		tableLoggedPC.setFillsViewportHeight(true);
		tableLoggedPC.setEnabled(false);
		/*--- End . TABLE FOR LOGGED IN PC ---*/
		
		panelTableLoggedPC.add(BorderLayout.WEST, new JLabel("  "));
		panelTableLoggedPC.add(BorderLayout.CENTER, new JScrollPane(tableLoggedPC));
		panelTableLoggedPC.add(BorderLayout.EAST, new JLabel("  "));
		panelTableLoggedPC.add(BorderLayout.SOUTH, new JLabel("  " + rscount +" results found."));
		
		panelGapTable.add(BorderLayout.CENTER, panelTableLoggedPC);
        //panelGapTable.add(BorderLayout.SOUTH, new JLabel(" "));
		
		/*--- MAIN PANEL FOR LOGGED IN PC GROUP ---*/
		panelLIPC.add(BorderLayout.NORTH, panelLogPC);
        panelLIPC.add(BorderLayout.CENTER, panelUsersLogHeader);
        panelLIPC.add(BorderLayout.SOUTH, panelGapTable);
		panelLIPC.setBorder(BorderFactory.createLoweredBevelBorder());
		/*--- End. MAIN PANEL FOR LOGGED IN PC GROUP ---*/

		panelwithGAP.add(BorderLayout.NORTH, new JLabel(" ")); 
        panelwithGAP.add(BorderLayout.CENTER, panelLIPC); 
        panelwithGAP.add(BorderLayout.SOUTH, new JLabel(" "));
		panelwithGAP.add(BorderLayout.WEST, new JLabel("     "));
		panelwithGAP.add(BorderLayout.EAST, new JLabel("     "));

		panelwithSeparator.add(BorderLayout.WEST, new JLabel("   "));
		panelwithSeparator.add(BorderLayout.CENTER, new JSeparator());
		panelwithSeparator.add(BorderLayout.EAST, new JLabel("   "));
		
		panelTOPTOP.add(BorderLayout.NORTH, panelTOP); 
        panelTOPTOP.add(BorderLayout.CENTER, panelwithGAP); 
        panelTOPTOP.add(BorderLayout.SOUTH, panelwithSeparator);

		panelSearch.add(BorderLayout.WEST, lblAddEmployee);
		panelSearch.add(BorderLayout.CENTER, tfSearch);
		panelSearch.add(BorderLayout.EAST, lblSearch);
		
		panelMID.add(BorderLayout.WEST, new JLabel("                              "));
		panelMID.add(BorderLayout.CENTER, panelSearch);
		panelMID.add(BorderLayout.EAST, new JLabel("                              "));
		
		panelButtonLEFT.add(BorderLayout.WEST, btnTimeLogs);
		panelButtonLEFT.add(BorderLayout.CENTER, btnEdit);
		panelButtonLEFT.add(BorderLayout.EAST, btnDeleteEmployee);
		
		panelButtonRIGHT.add(BorderLayout.WEST, new JLabel("                                                             "));
		panelButtonRIGHT.add(BorderLayout.EAST, btnDeleteAll);
		
		panelBottom.add(BorderLayout.WEST, panelButtonLEFT);
		panelBottom.add(BorderLayout.EAST, panelButtonRIGHT);
		
		/*--- TABLE SORTER ---*/
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
		/*--- End . TABLE SORTER ---*/
		
        panelEmpTableButtons.add(BorderLayout.NORTH, new JScrollPane(table)); 
        panelEmpTableButtons.add(BorderLayout.SOUTH, panelBottom);
		
		panelBottomBorder.add(BorderLayout.CENTER, panelMID); 
        panelBottomBorder.add(BorderLayout.SOUTH, panelEmpTableButtons);
		panelBottomBorder.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()," LIST OF EMPLOYEES ",2,0, new Font("Arial", Font.BOLD,16), new Color(47, 89, 106)));
				
		panelMain.add(BorderLayout.NORTH, panelTOPTOP);
		panelMain.add(BorderLayout.CENTER, panelBottomBorder);
        	
		getContentPane().add(panelMain);
		
		this.addWindowListener
		(	new WindowAdapter() 
			{	public void windowClosing(WindowEvent e)
				{	close(); System.exit(0);	}
			}
		);
    }
	
	public void close()
	{
		if (sqlRS != null)
		{	try
			{	sqlRS.close();	} 
			catch (SQLException e) {}
        }
        if (sqlStmnt != null)
		{	try
			{	sqlStmnt.close();	} 
			catch (SQLException e) {}
        }
		if (ps != null)
		{	try
			{	ps.close();	} 
			catch (SQLException e) {}
        }
		if (dbConn != null)
		{	try
			{	dbConn.close();	} 
			catch (SQLException e) {}
        }
	}
	
	public void focusMainWindow()
	{
		if(tfSearch.getText().trim().isEmpty())
		{	tfSearch.setText("Search employee");
			tfSearch.setEnabled(false);
		}
		table.clearSelection();
		panelMain.requestFocusInWindow();
	}
	
	public void disableButtons()
	{	
		btnTimeLogs.setEnabled(false);
		btnEdit.setEnabled(false);
		btnDeleteEmployee.setEnabled(false);
		btnDeleteAll.setEnabled(false);
		lblSearch.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent event)
	{	
		Object source = event.getSource();
        
		if(source == btnDeleteAll)
		{	if(btnDeleteAll.isEnabled())
			{	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all employees and their corresponding time logs?", "DELETE ALL EMPLOYEES", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{	DefaultTableModel model = (DefaultTableModel) table.getModel();
					while( model.getRowCount() > 0 )
						model.removeRow(0);
					disableButtons();
					
					sqlQuery = "DELETE FROM employees WHERE emp_id != 0";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						ps.executeUpdate();
					}
					catch(Exception error){ error.printStackTrace(); return; }
					
					sqlQuery = "DELETE FROM timelogs";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						if(ps != null)
							ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "All employees were successfully deleted!");
					}
					catch(Exception error){ error.printStackTrace(); return; }
				}
			}
		}
		else if(source == btnClearAll)
			eraseAll();
		else if(source == btnDeleteEmployee)
		{	if(table.getSelectedRow()!=-1)
			{	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee and his/her time logs?", "DELETE SELECTED EMPLOYEE", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{	sqlQuery = "DELETE FROM employees WHERE emp_id = ?";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						ps.setString(1, (table.getValueAt(table.getSelectedRow(), 1).toString()));
						ps.executeUpdate();
					}
					catch(Exception error){ error.printStackTrace(); return; }
					
					sqlQuery = "DELETE FROM timelogs WHERE emp_id = ?";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						if(ps != null)
						{	ps.setString(1, table.getValueAt(table.getSelectedRow(), 1).toString());
							ps.executeUpdate();
						}
					}
					catch(Exception error){ error.printStackTrace(); return; }
					
					tableModel.removeRow(table.getSelectedRow());
					panelMain.requestFocusInWindow();
					if(table.getRowCount()==0)
						disableButtons();
					JOptionPane.showMessageDialog(null, "Employee successfully deleted!");
				}
			}
		}
		else if(source == btnTimeLogs)
		{	
			if(table.getSelectedRow()!=-1)
			{
				AdminEmpDetails aed = new AdminEmpDetails(table.getValueAt(table.getSelectedRow(), 1).toString());
			
				aed.pack();
				aed.setLocationRelativeTo(null);
				aed.setResizable(false);
				aed.setVisible(true);
				aed.setTitle("JavaWookies Time Tracking System");
			
				dispose();
			}
		}
	}
	
	public void eraseAll()
	{
		tfFname.setText("");
		tfMname.setText("");
		tfLname.setText("");
		tfAge.setText("");
		tfAddress.setText("");
		pfPasscode.setText("");
		pfVerifyPasscode.setText("");
		cbGender.setSelectedIndex(0);
		lblVerify.setText("");
		pfPasscode.requestFocus();
	}
	
	public void Addemp()
	{
		JLabel lblEmpIDtext;
		JPanel panelAddEmployee, panelEmpPass, panelEmpID, panelPasscode, panelFname, panelMname, panelLname, panelFullName, panelAge, panelGender, panelAddress, panelAGA;
		JPanel panelFullnameAGA, panelVerifyPasscode, panelIDPasscodeGap, panelSeparatorLEFTRIGHT, panelSeparatorUPDOWN, panelFullnameSeparatorAGA, panelAgeGender;
		JPanel panelButtonClear;
		
		focusMainWindow();
		
		btnClearAll = new JButton("Clear All");btnClearAll.addActionListener(this);
		lblEmpIDtext = new JLabel("1");lblEmpIDtext.setForeground(new Color(142, 47, 53));
		try
		{	sqlQuery = "SELECT * FROM employees ORDER BY emp_id ASC";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
			
			if (sqlRS != null)
			{	int x = 0;
				while(sqlRS.next())
				{
					if(Integer.parseInt(sqlRS.getString("emp_id")) > x)
						x = Integer.parseInt(sqlRS.getString("emp_id"));
				}
				lblEmpIDtext.setText(String.valueOf(x + 1));
			}
		}
		catch(Exception error){ error.printStackTrace(); return; }
		
		panelAddEmployee = new JPanel(); panelAddEmployee.setLayout(new BorderLayout(1,1)); panelAddEmployee.setBackground(Color.lightGray);
		panelAddEmployee.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	if(tfAge.getText().length()!=0)
				{	if(Integer.parseInt(tfAge.getText()) != 0)
						tfAge.setText(String.valueOf(Integer.parseInt(tfAge.getText())));
					else
						tfAge.setText("0");
				}
			}
		});
		
		panelEmpPass = new JPanel(); panelEmpPass.setLayout(new BorderLayout(1,1)); panelEmpPass.setBackground(Color.lightGray);
		panelEmpID = new JPanel(); panelEmpID.setLayout(new BorderLayout(1,1)); panelEmpID.setBackground(Color.lightGray);
		panelPasscode = new JPanel(); panelPasscode.setLayout(new BorderLayout(1,1)); panelPasscode.setBackground(Color.lightGray);
		panelFname = new JPanel(); panelFname.setLayout(new BorderLayout(1,1)); panelFname.setBackground(Color.lightGray);
		panelMname = new JPanel(); panelMname.setLayout(new BorderLayout(1,1)); panelMname.setBackground(Color.lightGray);
		panelLname = new JPanel(); panelLname.setLayout(new BorderLayout(1,1)); panelLname.setBackground(Color.lightGray);
		panelFullName = new JPanel(); panelFullName.setLayout(new BorderLayout(1,1)); panelFullName.setBackground(Color.lightGray);
		panelAge = new JPanel(); panelAge.setLayout(new BorderLayout(1,1)); panelAge.setBackground(Color.lightGray);
		panelGender = new JPanel(); panelGender.setLayout(new BorderLayout(1,1)); panelGender.setBackground(Color.lightGray);
		panelAddress = new JPanel(); panelAddress.setLayout(new BorderLayout(1,1)); panelAddress.setBackground(Color.lightGray);
		panelAGA = new JPanel(); panelAGA.setLayout(new BorderLayout(1,1)); panelAGA.setBackground(Color.lightGray);
		panelFullnameAGA = new JPanel(); panelFullnameAGA.setLayout(new BorderLayout(1,1)); panelFullnameAGA.setBackground(Color.lightGray);
		panelVerifyPasscode = new JPanel(); panelVerifyPasscode.setLayout(new BorderLayout(1,1)); panelVerifyPasscode.setBackground(Color.lightGray);
		panelIDPasscodeGap = new JPanel(); panelIDPasscodeGap.setLayout(new BorderLayout(1,1)); panelIDPasscodeGap.setBackground(Color.lightGray);
		panelSeparatorLEFTRIGHT = new JPanel(); panelSeparatorLEFTRIGHT.setLayout(new BorderLayout(1,1)); panelSeparatorLEFTRIGHT.setBackground(Color.lightGray);
		panelSeparatorUPDOWN = new JPanel(); panelSeparatorUPDOWN.setLayout(new BorderLayout(1,1)); panelSeparatorUPDOWN.setBackground(Color.lightGray);
		panelFullnameSeparatorAGA = new JPanel(); panelFullnameSeparatorAGA.setLayout(new BorderLayout(1,1)); panelFullnameSeparatorAGA.setBackground(Color.lightGray);
		panelAgeGender = new JPanel(); panelAgeGender.setLayout(new BorderLayout(1,1)); panelAgeGender.setBackground(Color.lightGray);
		panelButtonClear = new JPanel(); panelButtonClear.setLayout(new BorderLayout(1,1)); panelButtonClear.setBackground(Color.lightGray);
		
		panelEmpID.add(BorderLayout.WEST, new JLabel("  Employee ID #:  "));
		panelEmpID.add(BorderLayout.CENTER, lblEmpIDtext);
        panelEmpID.add(BorderLayout.EAST, lblVerify);
		
		panelPasscode.add(BorderLayout.WEST, new JLabel("  Passcode:     "));
		panelPasscode.add(BorderLayout.CENTER, pfPasscode);         
        panelPasscode.add(BorderLayout.EAST, new JLabel("  "));
		
		panelVerifyPasscode.add(BorderLayout.WEST, new JLabel("  Verify Code:  "));
		panelVerifyPasscode.add(BorderLayout.CENTER, pfVerifyPasscode);         
        panelVerifyPasscode.add(BorderLayout.EAST, new JLabel("  "));
		
		panelEmpPass.add(BorderLayout.NORTH, panelEmpID);
		panelEmpPass.add(BorderLayout.CENTER, panelPasscode);
		panelEmpPass.add(BorderLayout.SOUTH, panelVerifyPasscode);
		
		panelIDPasscodeGap.add(BorderLayout.NORTH, new JLabel("  "));
		panelIDPasscodeGap.add(BorderLayout.CENTER, panelEmpPass);
		panelIDPasscodeGap.add(BorderLayout.SOUTH, new JLabel("  "));
		panelIDPasscodeGap.setBorder(BorderFactory.createLoweredBevelBorder());
		
		panelFname.add(BorderLayout.WEST, new JLabel("  Firstname:     "));
		panelFname.add(BorderLayout.CENTER, tfFname);         
        panelFname.add(BorderLayout.EAST, new JLabel("  "));
		
		panelMname.add(BorderLayout.WEST, new JLabel("  Middlename: "));
		panelMname.add(BorderLayout.CENTER, tfMname);         
        panelMname.add(BorderLayout.EAST, new JLabel("  "));
		
		panelLname.add(BorderLayout.WEST, new JLabel("  Lastname:     "));
		panelLname.add(BorderLayout.CENTER, tfLname);         
        panelLname.add(BorderLayout.EAST, new JLabel("  "));
		
		panelFullName.add(BorderLayout.NORTH, panelFname);
		panelFullName.add(BorderLayout.CENTER, panelMname);         
        panelFullName.add(BorderLayout.SOUTH, panelLname);
		
		panelSeparatorLEFTRIGHT.add(BorderLayout.WEST, new JLabel("     "));
		panelSeparatorLEFTRIGHT.add(BorderLayout.CENTER, new JSeparator());
        panelSeparatorLEFTRIGHT.add(BorderLayout.EAST, new JLabel("     "));
		
		panelSeparatorUPDOWN.add(BorderLayout.NORTH, new JLabel("  "));
		panelSeparatorUPDOWN.add(BorderLayout.CENTER, panelSeparatorLEFTRIGHT);         
        
		panelAge.add(BorderLayout.WEST, new JLabel("  Age:          "));
		panelAge.add(BorderLayout.CENTER, tfAge);         
        
		panelGender.add(BorderLayout.WEST, new JLabel("  Gender:   "));
		panelGender.add(BorderLayout.CENTER, cbGender);         
        
		panelAgeGender.add(BorderLayout.WEST, panelAge);
		panelAgeGender.add(BorderLayout.CENTER, panelGender); 
		panelAgeGender.add(BorderLayout.EAST, new JLabel("  "));
		
		panelAddress.add(BorderLayout.WEST, new JLabel("  Address: "));
		panelAddress.add(BorderLayout.CENTER, tfAddress);         
        panelAddress.add(BorderLayout.EAST, new JLabel("  "));
		
		panelAGA.add(BorderLayout.NORTH, panelAgeGender);
		panelAGA.add(BorderLayout.SOUTH, panelAddress);
		
		panelFullnameAGA.add(BorderLayout.NORTH, panelFullName);
		panelFullnameAGA.add(BorderLayout.CENTER, panelSeparatorUPDOWN);         
		panelFullnameAGA.add(BorderLayout.SOUTH, panelAGA);
		
		panelFullnameSeparatorAGA.add(BorderLayout.NORTH, new JLabel(" "));
		panelFullnameSeparatorAGA.add(BorderLayout.CENTER, panelFullnameAGA);         
		panelFullnameSeparatorAGA.add(BorderLayout.SOUTH, new JLabel(" "));
		
		panelButtonClear.add(BorderLayout.WEST, new JLabel("                         "));
		panelButtonClear.add(BorderLayout.CENTER, btnClearAll);         
        panelButtonClear.add(BorderLayout.EAST, new JLabel("                         "));
			
		panelAddEmployee.add(BorderLayout.NORTH, panelIDPasscodeGap);
		panelAddEmployee.add(BorderLayout.CENTER, panelFullnameSeparatorAGA);         
        panelAddEmployee.add(BorderLayout.SOUTH, panelButtonClear);
		panelAddEmployee.setBorder(BorderFactory.createEtchedBorder(1));
				
		Object[] selectOptions = {"ADD", "CANCEL"};
		
		if(JOptionPane.showOptionDialog(null, panelAddEmployee, "ENTER EMPLOYEE DETAILS", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, selectOptions, selectOptions[0]) == 0)
		{	if(JOptionPane.showConfirmDialog(null, "Are all entries correct?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{	if ((Arrays.equals(pfPasscode.getPassword(), pfVerifyPasscode.getPassword())) && (pfPasscode.getPassword().length != 0))
				{	if(tfMname.getText().length()!=0)
					{	if(tfFname.getText().length()!=0)
						{	if(tfLname.getText().length()!=0)
								record[0] = tfLname.getText() + ", " + tfFname.getText() + " " + tfMname.getText().substring(0, 1) + ".";
							else
								record[0] = tfFname.getText() + " " + tfMname.getText().substring(0, 1) + ".";
						}
						else
						{	if(tfLname.getText().length()!=0)
								record[0] = tfLname.getText() + ", " + tfMname.getText().substring(0, 1) + ".";
							else
								record[0] = tfMname.getText().substring(0, 1) + ".";
						}
					}
					else
					{	if(tfFname.getText().length()!=0)
						{	if(tfLname.getText().length()!=0)
								record[0] = tfLname.getText() + ", " + tfFname.getText();
							else
								record[0] = tfFname.getText();
						}
						else
						{	if(tfLname.getText().length()!=0)
								record[0] = tfLname.getText();
							else
								record[0] = "";
						}
					}
					
					record[1] = lblEmpIDtext.getText();
					record[2] = "0";
					if(tfAge.getText().length()!=0)
					{	record[2] = String.valueOf(Integer.parseInt(tfAge.getText()));
						if(Integer.parseInt(tfAge.getText()) == 0)
							record[2] = "0";
					}
					record[3] = (String) cbGender.getSelectedItem();
					if((String) cbGender.getSelectedItem() == "-Select-")
						record[3] = "";
					record[4] = tfAddress.getText();
				
					tableModel.insertRow(0, record);
					
					btnTimeLogs.setEnabled(true);
					btnEdit.setEnabled(true);
					btnDeleteEmployee.setEnabled(true);
					btnDeleteAll.setEnabled(true);
					lblSearch.setEnabled(true);
										
					/*--- ADD to database ---*/
					sqlQuery = "INSERT INTO employees(emp_id, passcode, emp_fname, emp_mname, emp_lname, emp_age, emp_gender, emp_address) VALUES (?,?,?,?,?,?,?,?)";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						ps.setString(1, record[1]);
						ps.setString(2, new String(pfVerifyPasscode.getPassword()));
						ps.setString(3, tfFname.getText());
						ps.setString(4, tfMname.getText());
						ps.setString(5, tfLname.getText());
						ps.setInt(6, Integer.parseInt(record[2]));
						ps.setString(7, record[3]);
						ps.setString(8, record[4]);
						ps.executeUpdate();
				
						JOptionPane.showMessageDialog(null, "New employee successfully added!");
					}
					catch(Exception error){ error.printStackTrace(); return; }
					/*--- End . ADD to database ---*/
				} 
				else
				{	lblVerify.setText("Passcode not verified  "); lblVerify.setForeground(Color.RED);
					if(tfAge.getText().length()!=0)
					{
						tfAge.setText(String.valueOf(Integer.parseInt(tfAge.getText())));
						if(Integer.parseInt(tfAge.getText()) == 0)
							tfAge.setText("0");
					}
					Addemp();
				}
			}
			else
			{	lblVerify.setText("");
				if(tfAge.getText().length()!=0)
				{	tfAge.setText(String.valueOf(Integer.parseInt(tfAge.getText())));
					if(Integer.parseInt(tfAge.getText()) == 0)
						tfAge.setText("0");
				}
				Addemp();
			}
		}
		eraseAll();
	}
	
	public static void main(String[] args)
	{	Admin frame = new Admin();
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("JavaWookies Time Tracking System");
		
	}
}
