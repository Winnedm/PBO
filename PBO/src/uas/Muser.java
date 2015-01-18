package uas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class Muser extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPassword;
	private JTextField txtUsername;
	private JTextField txtNama;
	private JTextField txtAlamat;
	private JTextField txtTelp;
	private JComboBox cmbLevel;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblFormMenuUser;
	private JLabel lblpassword;
	private JLabel lblusername;
	private JLabel lblNama;
	private JLabel lblAlamat;
	private JLabel lblTelp;
	private JLabel lblLevel;
	private JComboBox cmbCari;
	private JTextField txtCari;
	private JComboBox cmbSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Muser frame = new Muser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public void refresh()
	{
		try {
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			String query="select * from users";
			PreparedStatement pst=konek.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//cmb atas
	/*public void Combobox()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select * from users";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 cmbCari.addItem(rs.getString("Username"));
			 }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * Create the frame.
	 */
	public Muser() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 431);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadUser = new JButton("Refresh");
		btnLoadUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from users";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnLoadUser.setBounds(360, 187, 100, 20);
		contentPane.add(btnLoadUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10,210, 645, 180);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					int row=table.getSelectedRow();
					String usernameS_=(table.getModel().getValueAt(row, 2).toString());
					String query="select * from users where Username='"+usernameS_+"' ";
					PreparedStatement pst=konek.prepareStatement(query);
					//pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtNama.setText(rs.getString("Nama"));
						txtUsername.setText(rs.getString("Username"));
						txtPassword.setText(rs.getString("Password"));
						cmbLevel.setSelectedItem(rs.getString("Level"));;
						txtAlamat.setText(rs.getString("Alamat"));
						txtTelp.setText(rs.getString("Telepon"));
						
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		scrollPane.setViewportView(table);
		
		
		txtNama = new JTextField();
		txtNama.setBounds(104, 67, 206, 23);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(104, 97, 206, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(104, 127, 206, 21);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtAlamat = new JTextField();
		txtAlamat.setBounds(104, 157, 206, 23);
		contentPane.add(txtAlamat);
		txtAlamat.setColumns(10);
		
		txtTelp = new JTextField();
		txtTelp.setBounds(450, 67, 206, 23);
		contentPane.add(txtTelp);
		txtTelp.setColumns(10);
		
		cmbLevel = new JComboBox();
		
		try
		{
			Class.forName(koneksi.DATABASE_DRIVER);
			konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			String query="select Level from users";
			PreparedStatement pst=konek.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				cmbLevel.addItem(rs.getString(1));
				
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();

		}
		cmbLevel.setBounds(450, 97, 83, 20);
		contentPane.add(cmbLevel);
		
		
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try 
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
				 	String query="insert into users values(UNIQUEKEY('users'),'"+txtNama.getText()+"','"+txtUsername.getText()+"','"+txtPassword.getText()+"','"+cmbLevel.getSelectedItem()+"','"+txtAlamat.getText()+"','"+txtTelp.getText()+"',0) ";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtUsername.getText());
					pst.setString(2,txtPassword.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "data saved");
					pst.close();

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
	
				refresh();
			}
		});
		btnInsert.setBounds(520, 187, 100, 20);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="Update users set Username='"+txtUsername.getText()+"',Password='"+txtPassword.getText()+"',Nama='"+txtNama.getText()+"',Alamat='"+txtAlamat.getText()+"',Telepon='"+txtTelp.getText()+"' where Username='"+txtUsername.getText()+"' ";    
					PreparedStatement pst=konek.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "data Updated");
					pst.close();
					
				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				refresh();
			}
		});
		btnUpdate.setBounds(360, 157, 100, 23);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action=JOptionPane.showConfirmDialog(null,"Apakah anda yakin mau menghapus data tersebut","delete",JOptionPane.YES_NO_OPTION);
				if(action==0)
				{
					try
					{
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
						String query="delete from users where Username='"+txtUsername.getText()+"' ";
						PreparedStatement pst=konek.prepareStatement(query);
						pst.execute();
						JOptionPane.showMessageDialog(null, "data deleted");
						pst.close();
						
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
	
					}
				}
				refresh();
			}
		});
		btnDelete.setBounds(520, 157, 101, 23);
		contentPane.add(btnDelete);
		
		lblFormMenuUser = new JLabel("Maintenance User");
		lblFormMenuUser.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblFormMenuUser.setBounds(270, 11, 208, 32);
		contentPane.add(lblFormMenuUser);
		
		lblNama = new JLabel("Nama");
		lblNama.setBounds(10, 67, 83, 14);
		contentPane.add(lblNama);
		
		lblusername = new JLabel("User Name");
		lblusername.setBounds(10, 97, 83, 14);
		contentPane.add(lblusername);
		
		lblpassword = new JLabel("Password");
		lblpassword.setBounds(10, 127, 89, 14);
		contentPane.add(lblpassword);
		
		lblAlamat = new JLabel("Alamat");
		lblAlamat.setBounds(10, 157, 83, 14);
		contentPane.add(lblAlamat);
		
		lblTelp = new JLabel("Telepon");
		lblTelp.setBounds(360, 67, 83, 14);
		contentPane.add(lblTelp);
		
		lblLevel = new JLabel("Level");
		lblLevel.setBounds(360, 97, 83, 14);
		contentPane.add(lblLevel);
		
		//Combo atas
		/*
		cmbCari = new JComboBox();
		cmbCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from users where Username=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtUsername.setText(rs.getString("Username"));
						txtPassword.setText(rs.getString("Password"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
				
			}
		});
		cmbCari.setBounds(104, 52, 207, 20);
		contentPane.add(cmbCari);*/
		
		txtCari = new JTextField();
		txtCari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					String selection=(String)cmbSelection.getSelectedItem();
					String query="select * from users where "+selection+"=?";
					System.out.println(query);
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtCari.getText() );
					
					ResultSet rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}
				
				
			}
		});
		txtCari.setBounds(450, 127, 206, 21);
		contentPane.add(txtCari);
		txtCari.setColumns(10);
		
		cmbSelection = new JComboBox();
		cmbSelection.setModel(new DefaultComboBoxModel(new String[] {"Username", "Nama"}));
		cmbSelection.setBounds(360, 127, 83, 20);
		contentPane.add(cmbSelection);
		
		refresh();
		//Combobox();
		
		JButton btnKeluar = new JButton("Keluar");
		Image img3=new ImageIcon(this.getClass().getResource("img/lo.jpg")).getImage();
		btnKeluar.setIcon(new ImageIcon(img3));
		btnKeluar.setBounds(560, 5, 75, 20);
		contentPane.add(btnKeluar);
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="Update users set status=0 where status=1";    
					PreparedStatement pst=konek.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Logout Sukses");
					pst.close();
					new Login().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		JButton btnHome = new JButton("Home");
		Image img=new ImageIcon(this.getClass().getResource("img/h2.jpg")).getImage();
		btnHome.setIcon(new ImageIcon(img));
		btnHome.setBounds(20, 5, 75, 20);
		contentPane.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					new MenuUtama().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

}
