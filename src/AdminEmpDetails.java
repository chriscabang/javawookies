import java.text.SimpleDateFormat;
import com.mysql.cj.jdbc.exceptions.*;
import java.sql.*;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class AdminEmpDetails extends JFrame implements ActionListener
{
	JLabel lblHeader, lblPhoto, lblLoginDetails, lblFacebook, lblInstagram, lblTwitter, lblPinterest, lblLinkedin, lblTimeLogsHeader, lblBack;
	JButton btnDelete;
    JPanel panelMAIN, panelTOP, panelBOTTOM, panelPhoto, panelPhotoID, panelUP, panelNameAgeGend, panelFullEmpInfo, panelAddress, panelSeparator, panelLoginDetails;
	JPanel panelLEFT, panelLogoLoginDet, panelSocialMedia, panelSeparator2, panelSM1, panelSM2, panelSM3, panelRIGHT, panelTimeLogsHeader, panelButtonDelete, panelBD;
	
	DefaultTableModel tableModelComputer = new DefaultTableModel(), tableModelTimeLogs = new DefaultTableModel();
	JTable tableComputer = new JTable(tableModelComputer), tableTimeLogs = new JTable(tableModelTimeLogs);
	String[] columnComputerDetails = {"Computer Name", "Login Date", "Login Time"}, rcrd = new String[3], columnTimeLogs = {"Date In", "Time In", "Date Out", "Time Out"}, recordTimeLogs = new String[4];
	String sqlName;
	int count = 0; // -> use to determine if RS is empty or not
			
	ImageIcon logoHeader = new ImageIcon("img/Employee Details header.png"), logoPhoto = new ImageIcon("img/photo frame.png"), logoLoginDetails = new ImageIcon("img/Login Details.png");
	ImageIcon logoFacebook = new ImageIcon("img/social facebook.png"), logoInstagram = new ImageIcon("img/social instagram.png"), logoTwitter = new ImageIcon("img/social twitter.png");
	ImageIcon logoPinterest = new ImageIcon("img/social pinterest.png"), logoLinkedin = new ImageIcon("img/social linkedin.png"), logoTimeLogsHeader = new ImageIcon("img/time logs header.png");
	ImageIcon logoDelete = new ImageIcon("img/delete logo.png"), logoDeleteClicked = new ImageIcon("img/delete clicked logo.png"), logoBack = new ImageIcon("img/back logo.png"), logoBackClicked = new ImageIcon("img/back clicked logo.png");
	Image image, newimg;
	
	Connection dbConn;
	Statement sqlStmnt;
	PreparedStatement ps;
	String sqlQuery;
	ResultSet sqlRS;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM. dd, yyyy"), sqldateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a"), sqltimeFormat = new SimpleDateFormat("HH:mm:ss");

    AdminEmpDetails(String nmbr)
	{
		/*--- EMPLOYEE DETAILS HEADER ---*/
		lblHeader = new JLabel("");
		image = logoHeader.getImage();
		newimg = image.getScaledInstance(200, 35, java.awt.Image.SCALE_SMOOTH);
		logoHeader = new ImageIcon(newimg);lblHeader.setIcon(logoHeader);
		/*--- End . EMPLOYEE DETAILS HEADER ---*/
		
		/*--- PHOTO ALBUM LOGO FOR EMPLOYEE ---*/
		lblPhoto = new JLabel("");
		image = logoPhoto.getImage();
		newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
		logoPhoto = new ImageIcon(newimg);lblPhoto.setIcon(logoPhoto);
		/*--- End . PHOTO ALBUM LOGO FOR EMPLOYEE ---*/
		
		/*--- LOGIN DETAILS ---*/
		lblLoginDetails = new JLabel("");
		image = logoLoginDetails.getImage();
		newimg = image.getScaledInstance(110, 27, java.awt.Image.SCALE_SMOOTH);
		logoLoginDetails = new ImageIcon(newimg);lblLoginDetails.setIcon(logoLoginDetails);
		/*--- End . LOGIN DETAILS ---*/
		
		/*--- TIME LOGS HEADER ---*/
		lblTimeLogsHeader = new JLabel("                                     ");
		image = logoTimeLogsHeader.getImage();
		newimg = image.getScaledInstance(110, 27, java.awt.Image.SCALE_SMOOTH);
		logoTimeLogsHeader = new ImageIcon(newimg);lblTimeLogsHeader.setIcon(logoTimeLogsHeader);
		/*--- End . TIME LOGS HEADER ---*/
		
		/*--- FACEBOOK LOGO ---*/
		lblFacebook = new JLabel("  ");
		image = logoFacebook.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoFacebook = new ImageIcon(newimg);lblFacebook.setIcon(logoFacebook);
		/*--- End . FACEBOOK LOGO ---*/
		
		/*--- INSTAGRAM LOGO ---*/
		lblInstagram = new JLabel("  ");
		image = logoInstagram.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoInstagram = new ImageIcon(newimg);lblInstagram.setIcon(logoInstagram);
		/*--- End . INSTAGRAM LOGO ---*/
		
		/*--- TWITTER LOGO ---*/
		lblTwitter = new JLabel("  ");
		image = logoTwitter.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoTwitter = new ImageIcon(newimg);lblTwitter.setIcon(logoTwitter);
		/*--- End . TWITTER LOGO ---*/
		
		/*--- PINTEREST LOGO ---*/
		lblPinterest = new JLabel("  ");
		image = logoPinterest.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoPinterest = new ImageIcon(newimg);lblPinterest.setIcon(logoPinterest);
		/*--- End . PINTEREST LOGO ---*/
		
		/*--- LINKEDIN LOGO ---*/
		lblLinkedin = new JLabel("");
		image = logoLinkedin.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoLinkedin = new ImageIcon(newimg);lblLinkedin.setIcon(logoLinkedin);
		/*--- End . LINKEDIN LOGO ---*/
		
		/*--- BACK ICON ---*/
		lblBack = new JLabel(" ");
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
		lblBack.setToolTipText("Back to Admin page");
		lblBack.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	tableComputer.clearSelection(); tableTimeLogs.clearSelection(); panelMAIN.requestFocusInWindow();
				close();
				Admin adm = new Admin();
				adm.pack();
				adm.setLocationRelativeTo(null);
				adm.setResizable(false);
				adm.setVisible(true);
				adm.setTitle("JavaWookies Time Tracking System");
				dispose();
			}
		});
		/*--- End . BACK ICON ---*/
		
		panelMAIN = new JPanel(); panelMAIN.setLayout(new BorderLayout(1,1)); panelMAIN.setBackground(Color.lightGray);
		panelMAIN.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	tableComputer.clearSelection();
				tableTimeLogs.clearSelection();
				panelMAIN.requestFocusInWindow();
			}
		});	
		panelTOP = new JPanel(); panelTOP.setLayout(new BorderLayout(1,1)); panelTOP.setBackground(Color.lightGray);
		panelBOTTOM = new JPanel(); panelBOTTOM.setLayout(new BorderLayout(1,1)); panelBOTTOM.setBackground(Color.lightGray);
		panelPhoto = new JPanel(); panelPhoto.setLayout(new BorderLayout(1,1)); panelPhoto.setBackground(Color.lightGray);
		panelPhotoID = new JPanel(); panelPhotoID.setLayout(new BorderLayout(1,1)); panelPhotoID.setBackground(Color.lightGray);
		panelUP = new JPanel(); panelUP.setLayout(new BorderLayout(1,1)); panelUP.setBackground(Color.lightGray);
		panelNameAgeGend = new JPanel(); panelNameAgeGend.setLayout(new BorderLayout(1,1)); panelNameAgeGend.setBackground(Color.lightGray);
		panelFullEmpInfo = new JPanel(); panelFullEmpInfo.setLayout(new BorderLayout(1,1)); panelFullEmpInfo.setBackground(Color.lightGray);
		panelAddress = new JPanel(); panelAddress.setLayout(new BorderLayout(1,1)); panelAddress.setBackground(Color.lightGray);
		panelSeparator = new JPanel(); panelSeparator.setLayout(new BorderLayout(1,1)); panelSeparator.setBackground(Color.lightGray);
		panelLoginDetails = new JPanel(); panelLoginDetails.setLayout(new BorderLayout(1,1)); panelLoginDetails.setBackground(Color.lightGray);
		panelLEFT = new JPanel(); panelLEFT.setLayout(new BorderLayout(1,1)); panelLEFT.setBackground(Color.lightGray);
		panelLogoLoginDet = new JPanel(); panelLogoLoginDet.setLayout(new BorderLayout(1,1)); panelLogoLoginDet.setBackground(Color.lightGray);
		panelSocialMedia = new JPanel(); panelSocialMedia.setLayout(new BorderLayout(1,1)); panelSocialMedia.setBackground(Color.lightGray);
		panelSeparator2 = new JPanel(); panelSeparator2.setLayout(new BorderLayout(1,1)); panelSeparator2.setBackground(Color.lightGray);
		panelSM1 = new JPanel(); panelSM1.setLayout(new BorderLayout(1,1)); panelSM1.setBackground(Color.lightGray);
		panelSM2 = new JPanel(); panelSM2.setLayout(new BorderLayout(1,1)); panelSM2.setBackground(Color.lightGray);
		panelSM3 = new JPanel(); panelSM3.setLayout(new BorderLayout(1,1)); panelSM3.setBackground(Color.lightGray);
		panelRIGHT = new JPanel(); panelRIGHT.setLayout(new BorderLayout(1,1)); panelRIGHT.setBackground(Color.lightGray);
		panelTimeLogsHeader = new JPanel(); panelTimeLogsHeader.setLayout(new BorderLayout(1,1)); panelTimeLogsHeader.setBackground(Color.lightGray);
		panelButtonDelete = new JPanel(); panelButtonDelete.setLayout(new BorderLayout(1,1)); panelButtonDelete.setBackground(Color.lightGray);
		panelBD = new JPanel(); panelBD.setLayout(new BorderLayout(1,1)); panelBD.setBackground(Color.lightGray);
		
		panelUP.add(BorderLayout.WEST, new JLabel("                                                                                  "));
		panelUP.add(BorderLayout.CENTER, lblHeader);
		panelUP.add(BorderLayout.EAST, lblBack);
				
		panelTOP.add(BorderLayout.NORTH, panelUP);
        panelTOP.add(BorderLayout.CENTER, new JSeparator());
        
		panelPhoto.add(BorderLayout.NORTH, new JLabel(" "));
		panelPhoto.add(BorderLayout.WEST, new JLabel("                                             "));
        panelPhoto.add(BorderLayout.CENTER, lblPhoto);
		
		/*--- TABLE: LOGIN DETAILS ---*/
		tableComputer.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	tableComputer.requestFocus();
				tableTimeLogs.clearSelection();
			}
		});
		tableModelComputer.setColumnIdentifiers(columnComputerDetails);
		tableComputer.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableComputer.getTableHeader().setReorderingAllowed(false);
		tableComputer.setDefaultEditor(Object.class, null); // making the table uneditable
		tableComputer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*--- End . TABLE: LOGIN DETAILS ---*/
		
		/*--- TABLE: TIME LOGS ---*/
		tableTimeLogs.addMouseListener( new MouseAdapter()
		{	public void mouseClicked(MouseEvent e)
			{	tableTimeLogs.requestFocus();
				tableComputer.clearSelection();
			}
		});
		tableModelTimeLogs.setColumnIdentifiers(columnTimeLogs);
		tableTimeLogs.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableTimeLogs.getTableHeader().setReorderingAllowed(false);
		tableTimeLogs.setDefaultEditor(Object.class, null); // making the table uneditable
		tableTimeLogs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/*--- End . TABLE: TIME LOGS ---*/
		
		try
		{	dbConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/timetracker", "admin", "adminuser");
			sqlStmnt = dbConn.createStatement();
			sqlQuery = "SELECT * FROM timelogs WHERE emp_id = '" + nmbr + "' ORDER BY date_in DESC, time_in DESC";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
			
			while(sqlRS.next())
			{	count = 1;
				/*--- TABLE: LOGIN DETAILS ---*/
				rcrd[0] = sqlRS.getString("computer_name");
				rcrd[1] = dateFormat.format(sqlRS.getDate("date_in"));
				rcrd[2] = timeFormat.format(sqlRS.getTime("time_in"));
				
				tableModelComputer.addRow(rcrd);
				/*--- End . TABLE: LOGIN DETAILS ---*/
				
				/*--- TABLE: TIME LOGS ---*/
				recordTimeLogs[0] = dateFormat.format(sqlRS.getDate("date_in"));
				recordTimeLogs[1] = timeFormat.format(sqlRS.getTime("time_in"));
				
				if(sqlRS.getString("date_out").equals("0000-00-00"))
				{	recordTimeLogs[2] = recordTimeLogs[3] = "";
					//recordTimeLogs[3] = "";
				}
				else
				{	recordTimeLogs[2] = dateFormat.format(sqlRS.getDate("date_out"));
					recordTimeLogs[3] = timeFormat.format(sqlRS.getTime("time_out"));
				}
		
				tableModelTimeLogs.addRow(recordTimeLogs);
				/*--- End . TABLE: TIME LOGS ---*/
			}
		}
		catch(Exception error){ error.printStackTrace(); return; }
		
		tableComputer.setPreferredScrollableViewportSize(new Dimension(320, 62));
		tableComputer.setFillsViewportHeight(true);
		
		tableTimeLogs.setPreferredScrollableViewportSize(new Dimension(330, 60));
		tableTimeLogs.setFillsViewportHeight(true);
		
		panelPhotoID.add(BorderLayout.NORTH, panelPhoto);
        panelPhotoID.add(BorderLayout.CENTER, new JLabel("                                                ID #:  " + nmbr));
		panelPhotoID.add(BorderLayout.SOUTH, new JLabel(" "));
		
		try
		{	sqlQuery = "SELECT * FROM employees WHERE emp_id = '" + nmbr + "'";
			sqlRS = sqlStmnt.executeQuery(sqlQuery);
			sqlRS.first();
			
			if(sqlRS.getString("emp_mname").length()!=0)
			{	if(sqlRS.getString("emp_fname").length()!=0)
				{	if(sqlRS.getString("emp_lname").length()!=0)
						sqlName = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_fname") + " " + sqlRS.getString("emp_mname");
					else
						sqlName = sqlRS.getString("emp_fname") + " " + sqlRS.getString("emp_mname");
				}
				else
				{	if(sqlRS.getString("emp_lname").length()!=0)
						sqlName = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_mname");
					else
						sqlName = sqlRS.getString("emp_mname");
				}
			}
			else
			{	if(sqlRS.getString("emp_fname").length()!=0)
				{	if(sqlRS.getString("emp_lname").length()!=0)
						sqlName = sqlRS.getString("emp_lname") + ", " + sqlRS.getString("emp_fname");
					else
						sqlName = sqlRS.getString("emp_fname");
				}
				else
				{	if(sqlRS.getString("emp_lname").length()!=0)
						sqlName = sqlRS.getString("emp_lname");
					else
						sqlName = "";
				}
			}
			
			panelNameAgeGend.add(BorderLayout.NORTH, new JLabel("  Name:  " + sqlName));
			panelNameAgeGend.add(BorderLayout.CENTER, new JLabel("  Age:  " + sqlRS.getString("emp_age")));
			panelNameAgeGend.add(BorderLayout.SOUTH, new JLabel("  Gender:  " + sqlRS.getString("emp_gender")));
		
			panelSeparator.add(BorderLayout.WEST, new JLabel("    "));
			panelSeparator.add(BorderLayout.CENTER, new JSeparator());
			panelSeparator.add(BorderLayout.EAST, new JLabel("    "));
		
			panelAddress.add(BorderLayout.NORTH, new JLabel("  Address:  " + sqlRS.getString("emp_address")));
			panelAddress.add(BorderLayout.CENTER, new JLabel(" "));
			panelAddress.add(BorderLayout.SOUTH, panelSeparator);
			
			sqlName = nmbr; // -> sqlName value change to ID number value passed from Admin.java so that this will be use under delete button at ActionPerformed
		}
		catch(Exception error) { error.printStackTrace(); return; }
		
		panelFullEmpInfo.add(BorderLayout.NORTH, panelPhotoID);
        panelFullEmpInfo.add(BorderLayout.CENTER, panelNameAgeGend);
        panelFullEmpInfo.add(BorderLayout.SOUTH, panelAddress);
		
		panelLogoLoginDet.add(BorderLayout.WEST, new JLabel("                                       "));
        panelLogoLoginDet.add(BorderLayout.CENTER, lblLoginDetails);
        
		panelLoginDetails.add(BorderLayout.NORTH, panelLogoLoginDet);
        panelLoginDetails.add(BorderLayout.CENTER, new JScrollPane(tableComputer));
		panelLoginDetails.add(BorderLayout.SOUTH, new JLabel(" "));
		panelLoginDetails.add(BorderLayout.WEST, new JLabel(" "));
		panelLoginDetails.add(BorderLayout.EAST, new JLabel(" "));
		
		panelSeparator2.add(BorderLayout.WEST, new JLabel("    "));
        panelSeparator2.add(BorderLayout.CENTER, new JSeparator());
        panelSeparator2.add(BorderLayout.EAST, new JLabel("    "));
		
		panelSM1.add(BorderLayout.WEST, lblFacebook);
        panelSM1.add(BorderLayout.CENTER, lblInstagram);
        panelSM1.add(BorderLayout.EAST, lblTwitter);
		
		panelSM2.add(BorderLayout.WEST, panelSM1);
        panelSM2.add(BorderLayout.CENTER, lblPinterest);
        panelSM2.add(BorderLayout.EAST, lblLinkedin);
		
		panelSM3.add(BorderLayout.WEST, new JLabel("                            "));
        panelSM3.add(BorderLayout.CENTER, panelSM2);
        panelSM3.add(BorderLayout.EAST, new JLabel("                             "));
		
		panelSocialMedia.add(BorderLayout.NORTH, panelSeparator2);
        panelSocialMedia.add(BorderLayout.CENTER, panelSM3);
		panelSocialMedia.add(BorderLayout.SOUTH, new JLabel(" "));
		
		panelLEFT.add(BorderLayout.NORTH, panelFullEmpInfo);
        panelLEFT.add(BorderLayout.CENTER, panelLoginDetails);
        panelLEFT.add(BorderLayout.SOUTH, panelSocialMedia);
		
		panelTimeLogsHeader.add(BorderLayout.EAST, lblTimeLogsHeader);
		
		/*--- BUTTON DELETE ---*/
		btnDelete = new JButton("Delete");btnDelete.addActionListener(this);
		image = logoDelete.getImage();
		newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		logoDelete = new ImageIcon(newimg);btnDelete.setIcon(logoDelete);
		btnDelete.addMouseListener( new MouseAdapter()
		{	public void mouseEntered(MouseEvent e)
			{	if(btnDelete.isEnabled())
				{	image = logoDeleteClicked.getImage();
					newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
					logoDeleteClicked = new ImageIcon(newimg);btnDelete.setIcon(logoDeleteClicked);
					btnDelete.setForeground(new Color(0, 255, 234));
				}
			}
		});
		btnDelete.addMouseListener( new MouseAdapter()
		{	public void mouseExited(MouseEvent e)
			{	image = logoDelete.getImage();
				newimg = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				logoDelete = new ImageIcon(newimg);btnDelete.setIcon(logoDelete);
				btnDelete.setForeground(Color.BLACK);
			}
		});
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.setToolTipText("Delete the selected date/time shift");
		
		if(count == 0)
			btnDelete.setEnabled(false);
		/*--- End . BUTTON DELETE ---*/
		
		panelButtonDelete.add(BorderLayout.EAST, btnDelete);
		panelBD.add(BorderLayout.CENTER, panelButtonDelete);
		panelBD.add(BorderLayout.EAST, new JLabel("    "));
		
		panelRIGHT.add(BorderLayout.NORTH, panelTimeLogsHeader);
        panelRIGHT.add(BorderLayout.CENTER, new JScrollPane(tableTimeLogs));
        panelRIGHT.add(BorderLayout.SOUTH, panelBD);
		panelRIGHT.add(BorderLayout.EAST, new JLabel("    "));
		
		panelBOTTOM.add(BorderLayout.WEST, panelLEFT);
        panelBOTTOM.add(BorderLayout.CENTER, new JSeparator(JSeparator.VERTICAL));
        panelBOTTOM.add(BorderLayout.EAST, panelRIGHT);
		
		panelMAIN.add(BorderLayout.NORTH, panelTOP);
        panelMAIN.add(BorderLayout.SOUTH, panelBOTTOM);
		panelMAIN.setBorder(BorderFactory.createEtchedBorder(1));
		
		getContentPane().add(panelMAIN);	
		
		this.addWindowListener
		(	new WindowAdapter() 
			{	public void windowClosing(WindowEvent e)
				{	close();
					System.exit(0);	}
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

	public void actionPerformed(ActionEvent event)
	{
        Object source = event.getSource();
		
		if(source == btnDelete)
		{
			if(tableTimeLogs.getSelectedRow()!=-1)
			{	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this shift?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{	sqlQuery = "DELETE FROM timelogs WHERE emp_id = ? AND date_in = ? AND time_in = ?";
					try
					{	ps = dbConn.prepareStatement(sqlQuery);
						ps.setString(1, sqlName);
						ps.setDate(2, java.sql.Date.valueOf(sqldateFormat.format(dateFormat.parse(tableTimeLogs.getValueAt(tableTimeLogs.getSelectedRow(), 0).toString()))));
						ps.setTime(3, new Time(sqltimeFormat.parse(sqltimeFormat.format(timeFormat.parse(tableTimeLogs.getValueAt(tableTimeLogs.getSelectedRow(), 1).toString()))).getTime()));
						ps.executeUpdate();
					}
					catch(Exception error){ error.printStackTrace(); return; }
					
					tableModelComputer.removeRow(tableTimeLogs.getSelectedRow());
					tableModelTimeLogs.removeRow(tableTimeLogs.getSelectedRow());
					panelMAIN.requestFocusInWindow();
					if(tableTimeLogs.getRowCount()==0)
						btnDelete.setEnabled(false);
				
					JOptionPane.showMessageDialog(null, "Selected shift successfully deleted!");
				}
			}
		}
    }
}
