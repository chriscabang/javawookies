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
	JLabel lblHeader, lblPhoto, lblLoginDetails, lblFacebook, lblInstagram, lblTwitter;
    //JTextField ;
    //JButton ;
    JPanel panelMAIN, panelTOP, panelBOTTOM, panelPhoto, panelPhotoID, panelUP, panelNameAgeGend, panelFullEmpInfo, panelAddress, panelSeparator, panelLoginDetails;
	JPanel panelLEFT, panelLogoLoginDet, panelSocialMedia, panelSeparator2, panelSM1;
	
	DefaultTableModel tableModelComputer = new DefaultTableModel();
	JTable tableComputer = new JTable(tableModelComputer);
	String[] columnComputerDetails = {"Computer Name", "Login Date", "Login Time"}, rcrd = new String[3];
		
	ImageIcon logoHeader = new ImageIcon("img/Employee Details header.png"), logoPhoto = new ImageIcon("img/photo frame.png"), logoLoginDetails = new ImageIcon("img/Login Details.png");
	ImageIcon logoFacebook = new ImageIcon("img/social facebook.png"), logoInstagram = new ImageIcon("img/social instagram.jpg"), logoTwitter = new ImageIcon("img/social twitter.png");
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
		
		
		
		
        panelMAIN = new JPanel(); panelMAIN.setLayout(new BorderLayout(1,1)); panelMAIN.setBackground(Color.lightGray);
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
		
		
		panelUP.add(BorderLayout.WEST, new JLabel("   "));
        panelUP.add(BorderLayout.CENTER, lblHeader);
        panelUP.add(BorderLayout.EAST, new JLabel("   "));
		
		panelTOP.add(BorderLayout.NORTH, panelUP);
        panelTOP.add(BorderLayout.CENTER, new JSeparator());
        
		panelPhoto.add(BorderLayout.NORTH, new JLabel(" "));
		panelPhoto.add(BorderLayout.WEST, new JLabel("                                      "));
        panelPhoto.add(BorderLayout.CENTER, lblPhoto);
		
		panelPhotoID.add(BorderLayout.NORTH, panelPhoto);
        panelPhotoID.add(BorderLayout.CENTER, new JLabel("                                         ID #:  99999"));
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
		
		panelLogoLoginDet.add(BorderLayout.WEST, new JLabel("                                 "));
        panelLogoLoginDet.add(BorderLayout.CENTER, lblLoginDetails);
        //panelLogoLoginDet.add(BorderLayout.EAST, new JLabel("   "));
		
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

		panelLoginDetails.add(BorderLayout.NORTH, panelLogoLoginDet);
        panelLoginDetails.add(BorderLayout.CENTER, new JScrollPane(tableComputer));
		panelLoginDetails.add(BorderLayout.SOUTH, new JLabel(" "));
		
		panelSeparator2.add(BorderLayout.WEST, new JLabel("    "));
        panelSeparator2.add(BorderLayout.CENTER, new JSeparator());
        panelSeparator2.add(BorderLayout.EAST, new JLabel("    "));
		
		panelSM1.add(BorderLayout.WEST, lblFacebook);
        panelSM1.add(BorderLayout.CENTER, lblInstagram);
        panelSM1.add(BorderLayout.EAST, lblTwitter);
		
		
		
		
		
		panelSocialMedia.add(BorderLayout.NORTH, panelSeparator2);
        panelSocialMedia.add(BorderLayout.CENTER, panelSM1);
		panelSocialMedia.add(BorderLayout.SOUTH, new JLabel(" "));
		
		
		
				
		
		panelLEFT.add(BorderLayout.NORTH, panelFullEmpInfo);
        panelLEFT.add(BorderLayout.CENTER, panelLoginDetails);
        panelLEFT.add(BorderLayout.SOUTH, panelSocialMedia);
		
		panelBOTTOM.add(BorderLayout.WEST, panelLEFT);
        panelBOTTOM.add(BorderLayout.CENTER, new JSeparator(JSeparator.VERTICAL));
        panelBOTTOM.add(BorderLayout.EAST, new JLabel("TIme Logs"));
		
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
		//frame.setSize(800,200); // (x, y)
        frame.setVisible(true);
		frame.setTitle("JavaWookies Time Tracking System");
    }
}