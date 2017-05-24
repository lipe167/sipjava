/*
    This file is part of Peers, a java SIP softphone.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Copyright 2010 Yohann Martineau 
 */

package net.sourceforge.peers.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import net.sourceforge.peers.Logger;
import net.sourceforge.peers.sip.Utils;
import net.sourceforge.peers.sip.syntaxencoding.SipUriSyntaxException;
import net.sourceforge.peers.sip.transport.SipRequest;
import net.sourceforge.peers.sip.transport.SipResponse;

public class MainFrame implements WindowListener, ActionListener {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(args);
            }
        });
    }

    private static void createAndShowGUI(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new MainFrame(args);
    }

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel dialerPanel;
    private JTextField uri;
    private JButton actionButton;
    private JLabel statusLabel;

    private EventManager eventManager;
    private Registration registration;
    private Logger logger;
    
    private JLabel lbNumber;
    
    private JButton terminateButton;
    private JButton btnNum1;
    private JButton btnNum2;
    private JButton btnNum3;
    private JButton btnNum4;
    private JButton btnNum5;
    private JButton btnNum6;
    private JButton btnNum7;
    private JButton btnNum8;
    private JButton btnNum9;
    private JButton btnNum0;
    private JButton btnNumE;
    private JButton btnNumT;
    
    private JButton[] numberButtons;
    private String[] buttonString = { "1", "2", "3",
			  "4", "5", "6",
			  "7", "8", "9",
			  "*", "0", "#" };

    public MainFrame(final String[] args) {
        String peersHome = Utils.DEFAULT_PEERS_HOME;
        if (args.length > 0) {
            peersHome = args[0];
        }
        logger = new Logger(peersHome);
        String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
        } catch (Exception e) {
            logger.error("cannot change look and feel", e);
        }
        String title = "";
        if (!Utils.DEFAULT_PEERS_HOME.equals(peersHome)) {
            title = peersHome;
        }
        title += "SoftPhone With Cams";
        mainFrame = new JFrame(title);        
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.addWindowListener(this);
        

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //mainPanel.setBackground(Color.GREEN);
        //mainPanel.setLayout(new GridLayout(3,1));
        
        dialerPanel = new JPanel();
        
        //dialerPanel.setLayout(new GridLayout(1, 3));
        //dialerPanel.setBackground(Color.PINK);
        dialerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        Dimension dimension2 = new Dimension(280, 30);
        dialerPanel.setMinimumSize(dimension2);
        dialerPanel.setMaximumSize(dimension2);

        
        lbNumber = new JLabel("Number:");
        //uri = new JTextField("sip:", 15);
        uri = new JTextField(15);
        uri.addActionListener(this);

        actionButton = new JButton("Call");
        actionButton.addActionListener(this);
        
        terminateButton = new JButton("Terminate");
        terminateButton.addActionListener(this);
        
        dialerPanel.add(lbNumber);
        //dialerPanel.add(terminateButton);
        dialerPanel.add(uri);
        dialerPanel.add(actionButton);
        
        dialerPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);        
                
        JPanel teclasPanel = new JPanel();
        //teclasPanel.setBackground(Color.black);
        teclasPanel.setLayout(new GridLayout(5, 3, 5, 5));
        
        teclasPanel.setBorder(BorderFactory.createEmptyBorder(15, 50, 0, 50));
        Dimension dimension = new Dimension(280, 115);
        teclasPanel.setMinimumSize(dimension);
        teclasPanel.setMaximumSize(dimension);
        teclasPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        /*numberButtons = new JButton[buttonString.length];
        
        for(int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton(buttonString[i]);
			numberButtons[i].setFont(new Font("Tahoma", Font.BOLD, 16));		
			numberButtons[i].setBackground(new Color(255,248,220));
			
			numberButtons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					uri.setText();
				}
			});
		}*/
        
        btnNum1 = new JButton("1");
        btnNum2 = new JButton("2");
        btnNum3 = new JButton("3");
        btnNum4 = new JButton("4");
        btnNum5 = new JButton("5");
        btnNum6 = new JButton("6");
        btnNum7 = new JButton("7");
        btnNum8 = new JButton("8");
        btnNum9 = new JButton("9");
        btnNum0 = new JButton("0");
        btnNumE = new JButton("*");
        btnNumT = new JButton("#");
        
        btnNum1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum1.getText());
				
			}
		});
        
        btnNum2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum2.getText());
				
			}
		});
        
        btnNum3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum3.getText());
				
			}
		});        
           

        btnNum4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum4.getText());
				
			}
		});        
        
        btnNum5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum5.getText());
				
			}
		});
        
        btnNum6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum6.getText());
				
			}
		});
        
        btnNum7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum7.getText());
				
			}
		});        
           

        btnNum8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum8.getText());
				
			}
		});

        btnNum9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum9.getText());
				
			}
		});
        
        btnNum0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNum0.getText());
				
			}
		});        
           

        btnNumE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNumE.getText());
				
			}
		});
        
        
        btnNumT.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uri.setText(uri.getText()+btnNumT.getText());
				
			}
		});
        
        
        teclasPanel.add(btnNum1);
        teclasPanel.add(btnNum2);
        teclasPanel.add(btnNum3);
        teclasPanel.add(btnNum4);
        teclasPanel.add(btnNum5);
        teclasPanel.add(btnNum6);
        teclasPanel.add(btnNum7);
        teclasPanel.add(btnNum8);
        teclasPanel.add(btnNum9);
        teclasPanel.add(btnNumE);
        teclasPanel.add(btnNum0);
        teclasPanel.add(btnNumT);
        
        teclasPanel.add(terminateButton);
        
        statusLabel = new JLabel(title);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Border border = BorderFactory.createEmptyBorder(0, 2, 2, 2);
        statusLabel.setBorder(border);

        mainPanel.add(dialerPanel);
        mainPanel.add(teclasPanel);
        mainPanel.add(statusLabel);

        Container contentPane = mainFrame.getContentPane();
        contentPane.add(mainPanel);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic('x');
        menuItem.setActionCommand(EventManager.ACTION_EXIT);

        registration = new Registration(statusLabel, logger);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                String peersHome = Utils.DEFAULT_PEERS_HOME;
                if (args.length > 0) {
                    peersHome = args[0];
                }
                eventManager = new EventManager(MainFrame.this,
                        peersHome, logger);
                try {
                    eventManager.register();
                } catch (SipUriSyntaxException e) {
                    statusLabel.setText(e.getMessage());
                }
            }
        });
        thread.start();

        try {
            while (eventManager == null) {
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            return;
        }
        menuItem.addActionListener(eventManager);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Edit");
        menu.setMnemonic('E');
        menuItem = new JMenuItem("Account");
        menuItem.setMnemonic('A');
        menuItem.setActionCommand(EventManager.ACTION_ACCOUNT);
        menuItem.addActionListener(eventManager);
        menu.add(menuItem);
        menuItem = new JMenuItem("Preferences");
        menuItem.setMnemonic('P');
        menuItem.setActionCommand(EventManager.ACTION_PREFERENCES);
        menuItem.addActionListener(eventManager);
        menu.add(menuItem);
        menuBar.add(menu);

        menu = new JMenu("Help");
        menu.setMnemonic('H');
        menuItem = new JMenuItem("User manual");
        menuItem.setMnemonic('D');
        menuItem.setActionCommand(EventManager.ACTION_DOCUMENTATION);
        menuItem.addActionListener(eventManager);
        menu.add(menuItem);
        menuItem = new JMenuItem("About");
        menuItem.setMnemonic('A');
        menuItem.setActionCommand(EventManager.ACTION_ABOUT);
        menuItem.addActionListener(eventManager);
        menu.add(menuItem);
        menuBar.add(menu);

        mainFrame.setJMenuBar(menuBar);

        mainFrame.pack();
        mainFrame.setSize(400, 600);
        mainFrame.setVisible(true);
        
    }

    // window events

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        eventManager.windowClosed();
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    // action event

    @Override
    public void actionPerformed(ActionEvent e) {
        eventManager.callClicked(uri.getText());
    }

    // misc.
    public void setLabelText(String text) {
        statusLabel.setText(text);
        mainFrame.pack();
    }

    public void registerFailed(SipResponse sipResponse) {
        registration.registerFailed();
    }

    public void registerSuccessful(SipResponse sipResponse) {
        registration.registerSuccessful();
    }

    public void registering(SipRequest sipRequest) {
        registration.registerSent();
    }

    public void socketExceptionOnStartup() {
        JOptionPane.showMessageDialog(mainFrame, "peers SIP port " +
        		"unavailable, exiting");
        System.exit(1);
    }

}
