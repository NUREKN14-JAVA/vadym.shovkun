package com.shovkun.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.shovkun.usermanagement.db.DaoFactory;
import com.shovkun.usermanagement.db.UserDao;
import com.shovkun.usermanagement.util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private JPanel addPanel;
	private UserDao dao;
	private JPanel editPanel;
	private JPanel detailsPanel;

	public MainFrame(){
		super();
		dao = DaoFactory.getInstance().getUserDao();
		initialize();
	}



	public UserDao getDao() {
		return dao;
	}


	private void initialize() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
		this.setContentPane(getContentPanel());
	}


	private JPanel getContentPanel() {
		if(contentPanel == null)
		{
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
		}
		return contentPanel;
	}


	private JPanel getBrowsePanel() {
		if (browsePanel == null)
		{
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel) browsePanel).initTable();
		return browsePanel;
	}


	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}


	public void showAddPanel() {
		showPanel(getAddPanel());
		
	}


	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
		
	}


	private JPanel getAddPanel() {
		if(addPanel == null){
			addPanel = new AddPanel(this);
		}
		return addPanel;
	
	
	}
	
	private JPanel getEditPanel(Long id) {
	//	if(editPanel == null){
			editPanel = new EditPanel(this, id);
	//	}
		return editPanel;
	
	
	}
	
	
	private JPanel getDetailsPanel(Long id) {
	
			detailsPanel = new DetailsPanel(this, id);
	
		return detailsPanel;
	    
	}
	
	
	
	public void showBrowsePanel() {
		showPanel(getBrowsePanel());
	}
	
	public void showEditPanel(Long id) {
		showPanel(getEditPanel(id));
	}
	
	public void showDetailsPanel(Long id) {
		showPanel(getDetailsPanel(id));
	}
	
}
