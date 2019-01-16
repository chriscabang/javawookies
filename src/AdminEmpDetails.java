import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable; 
import java.awt.Image;
import javax.swing.BorderFactory;

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
	
	ImageIcon logoHeader = new ImageIcon("img/Employee Details header.png"), logoPhoto = new ImageIcon("img/photo frame.png"), logoLoginDetails = new ImageIcon("img/Login Details.png");
	ImageIcon logoFacebook = new ImageIcon("img/social facebook.png"), logoInstagram = new ImageIcon("img/social instagram.png"), logoTwitter = new ImageIcon("img/social twitter.png");
	ImageIcon logoPinterest = new ImageIcon("img/social pinterest.png"), logoLinkedin = new ImageIcon("img/social linkedin.png"), logoTimeLogsHeader = new ImageIcon("img/time logs header.png");
	ImageIcon logoDelete = new ImageIcon("img/delete logo.png"), logoDeleteClicked = new ImageIcon("img/delete clicked logo.png"), logoBack = new ImageIcon("img/back logo.png"), logoBackClicked = new ImageIcon("img/back clicked logo.png");
	Image image, newimg;

    AdminEmpDetails()
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
				
				//if(JOptionPane.showConfirmDialog(null, "Are you sure you want to Logout?", "Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				//{	close();
					
					Admin adm = new Admin();
					adm.pack();
					adm.setLocationRelativeTo(null);
					adm.setResizable(false);
					adm.setVisible(true);
					adm.setTitle("JavaWookies Time Tracking System");
					dispose();
					
				//}
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
		
		panelUP.add(BorderLayout.WEST, new JLabel("                                                                         "));
		panelUP.add(BorderLayout.CENTER, lblHeader);
		panelUP.add(BorderLayout.EAST, lblBack);
				
		panelTOP.add(BorderLayout.NORTH, panelUP);
        panelTOP.add(BorderLayout.CENTER, new JSeparator());
        
		panelPhoto.add(BorderLayout.NORTH, new JLabel(" "));
		panelPhoto.add(BorderLayout.WEST, new JLabel("                                        "));
        panelPhoto.add(BorderLayout.CENTER, lblPhoto);
		
		panelPhotoID.add(BorderLayout.NORTH, panelPhoto);
        panelPhotoID.add(BorderLayout.CENTER, new JLabel("                                           ID #:  99999"));
        panelPhotoID.add(BorderLayout.SOUTH, new JLabel(" "));
		
		panelNameAgeGend.add(BorderLayout.NORTH, new JLabel("  Name:  Benjie Pagador Fuentes"));
        panelNameAgeGend.add(BorderLayout.CENTER, new JLabel("  Age:  35"));
        panelNameAgeGend.add(BorderLayout.SOUTH, new JLabel("  Gender:  Male"));
		
		panelSeparator.add(BorderLayout.WEST, new JLabel("    "));
        panelSeparator.add(BorderLayout.CENTER, new JSeparator());
        panelSeparator.add(BorderLayout.EAST, new JLabel("    "));
		
		panelAddress.add(BorderLayout.NORTH, new JLabel("  Address:  4-A Archbishop Reyes Avenue, Cebu City"));
        panelAddress.add(BorderLayout.CENTER, new JLabel(" "));
        panelAddress.add(BorderLayout.SOUTH, panelSeparator);
		
		panelFullEmpInfo.add(BorderLayout.NORTH, panelPhotoID);
        panelFullEmpInfo.add(BorderLayout.CENTER, panelNameAgeGend);
        panelFullEmpInfo.add(BorderLayout.SOUTH, panelAddress);
		
		panelLogoLoginDet.add(BorderLayout.WEST, new JLabel("                                  "));
        panelLogoLoginDet.add(BorderLayout.CENTER, lblLoginDetails);
        
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
		
		rcrd[0] = "Alpha Computer";
		rcrd[1] = "Jan. 12, 2019";
		rcrd[2] = "03:55 AM";
				
		tableModelComputer.addRow(rcrd);
		tableComputer.setPreferredScrollableViewportSize(new Dimension(310, 50));
		tableComputer.setFillsViewportHeight(true);
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
		
		recordTimeLogs[0] = "Jan. 12, 2019";
		recordTimeLogs[1] = "23:55 PM";
		recordTimeLogs[2] = "Jan. 13, 2019";
		recordTimeLogs[3] = "03:55 AM";
		
		tableModelTimeLogs.addRow(recordTimeLogs);
		tableTimeLogs.setPreferredScrollableViewportSize(new Dimension(330, 60));
		tableTimeLogs.setFillsViewportHeight(true);
		/*--- End . TABLE: TIME LOGS ---*/
		
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
		
		panelSM3.add(BorderLayout.WEST, new JLabel("                        "));
        panelSM3.add(BorderLayout.CENTER, panelSM2);
        panelSM3.add(BorderLayout.EAST, new JLabel("                         "));
		
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
		/*--- End . BUTTON DELETE ---*/
		
		
		
		
		panelButtonDelete.add(BorderLayout.EAST, btnDelete);
		panelBD.add(BorderLayout.CENTER, panelButtonDelete);
		panelBD.add(BorderLayout.EAST, new JLabel(" "));
		
				
		panelRIGHT.add(BorderLayout.NORTH, panelTimeLogsHeader);
        panelRIGHT.add(BorderLayout.CENTER, new JScrollPane(tableTimeLogs));
        panelRIGHT.add(BorderLayout.SOUTH, panelBD);
		panelRIGHT.add(BorderLayout.WEST, new JLabel(" "));
		panelRIGHT.add(BorderLayout.EAST, new JLabel(" "));
		
		panelBOTTOM.add(BorderLayout.WEST, panelLEFT);
        panelBOTTOM.add(BorderLayout.CENTER, new JSeparator(JSeparator.VERTICAL));
        panelBOTTOM.add(BorderLayout.EAST, panelRIGHT);
		
		panelMAIN.add(BorderLayout.NORTH, panelTOP);
        //panelMAIN.add(BorderLayout.CENTER, ); //-> extra slot 
        panelMAIN.add(BorderLayout.SOUTH, panelBOTTOM);
		panelMAIN.setBorder(BorderFactory.createEtchedBorder(1));
		
		getContentPane().add(panelMAIN);	
		
		
		this.addWindowListener
		(	new WindowAdapter() 
			{	public void windowClosing(WindowEvent e)
				{	//close();
					System.exit(0);	}
			}
		);
    }

/*	
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
*/	
	
	public void actionPerformed(ActionEvent event)
	{
        Object source = event.getSource();

    }

    public static void main(String[] args)
	{
        AdminEmpDetails frame = new AdminEmpDetails();
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("JavaWookies Time Tracking System");
    }
}
