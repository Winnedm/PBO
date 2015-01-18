package uas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

public class Mproduct extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblIdProduct;
	private JTextField txtProduct;
	private JTextField txtNamaProduct;
	private JLabel lblIdSupplier;
	private JTextField txtSupplier;
	private JLabel lblHarga;
	private JTextField txtHarga;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNamaProduct;
	private JLabel lblFormMenuProduct;
	private JComboBox cmbCari;
	private JTextField txtCari;
	private JComboBox cmbSelect;
	private JLabel lblCari;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mproduct frame = new Mproduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public int coba() 
	{
		int a=1;
		do
		{
			int b= a+1;
		}
		while(a==100);
		return a;
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select * from products order by IdPrd asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/*public void Combobox()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select * from products order by IdPrd asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 cmbCari.addItem(rs.getString("NamaPrd"));
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
	public Mproduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 431);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadDataProduct = new JButton("Refresh");
		btnLoadDataProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					 String query="select * from products order by IdPrd asc";
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
		btnLoadDataProduct.setBounds(320, 80, 350, 20);
		contentPane.add(btnLoadDataProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 650, 210);
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
					String idP_=(table.getModel().getValueAt(row, 0).toString());
					
					String query="select * from products where IdPrd='"+idP_+"' order by IdPrd asc ";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtProduct.setText(rs.getString("IdPrd"));
						txtNamaProduct.setText(rs.getString("NamaPrd"));
						txtSupplier.setText(rs.getString("IdSupp"));
						txtHarga.setText(rs.getString("Harga"));
						
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
		
		lblIdProduct = new JLabel("ID Product");
		lblIdProduct.setBounds(10, 50, 73, 14);
		contentPane.add(lblIdProduct);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(103, 50, 207, 21);
		contentPane.add(txtProduct);
		txtProduct.setColumns(10);
		
		lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setBounds(10, 80, 83, 14);
		contentPane.add(lblNamaProduct);
		
		txtNamaProduct = new JTextField();
		txtNamaProduct.setBounds(103, 80, 207, 23);
		contentPane.add(txtNamaProduct);
		txtNamaProduct.setColumns(10);
		
		lblIdSupplier = new JLabel("ID Supplier");
		lblIdSupplier.setBounds(10, 110, 73, 14);
		contentPane.add(lblIdSupplier);
		
		txtSupplier = new JTextField();
		txtSupplier.setBounds(103, 110, 207, 23);
		contentPane.add(txtSupplier);
		txtSupplier.setColumns(10);
		
		lblHarga = new JLabel("Harga");
		lblHarga.setBounds(10, 140, 73, 14);
		contentPane.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setBounds(103, 140, 207, 23);
		contentPane.add(txtHarga);
		txtHarga.setColumns(10);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="insert into products values (?,?,?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,txtProduct.getText());
					pst.setString(2,txtNamaProduct.getText());
					pst.setString(3,txtSupplier.getText());
					pst.setString(4,txtHarga.getText());
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
		btnInsert.setBounds(320, 120, 107, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="Update products set IdPrd='"+txtProduct.getText()+"',NamaPrd='"+txtNamaProduct.getText()+"',IdSupp='"+txtSupplier.getText()+"',Harga='"+txtHarga.getText()+"' where IdPrd='"+txtProduct.getText()+"' ";    
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
		btnUpdate.setBounds(437, 120, 111, 23);
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
						String query="delete from products where IdPrd='"+txtProduct.getText()+"' ";
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
		btnDelete.setBounds(558, 120, 107, 23);
		contentPane.add(btnDelete);
		
		
		
		lblFormMenuProduct = new JLabel("Maintenance Product");
		lblFormMenuProduct.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFormMenuProduct.setBounds(243, 11, 208, 32);
		contentPane.add(lblFormMenuProduct);
		
		//cmb atas
		/*
		cmbCari = new JComboBox();
		cmbCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from products where NamaPrd=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtProduct.setText(rs.getString("IdPrd"));
						txtNamaProduct.setText(rs.getString("NamaPrd"));
						txtSupplier.setText(rs.getString("IdSupp"));
						txtHarga.setText(rs.getString("Harga"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		cmbCari.setBounds(103, 50, 207, 20);
		contentPane.add(cmbCari);*/
		txtCari = new JTextField();
		txtCari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					String selection=(String)cmbSelect.getSelectedItem();
					String query="select * from products where "+selection+"=?";
					System.out.println(query);
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(coba(),txtCari.getText() );
					
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
		
		lblCari = new JLabel("Cari By");
		lblCari.setBounds(320, 50, 50, 14);
		contentPane.add(lblCari);
		
		txtCari.setBounds(460, 50, 200, 23);
		contentPane.add(txtCari);
		txtCari.setColumns(10);
		
		cmbSelect = new JComboBox();
		cmbSelect.setModel(new DefaultComboBoxModel(new String[] {"IdPrd", "NamaPrd", "IdSupp"}));
		cmbSelect.setBounds(370, 50, 83, 20);
		contentPane.add(cmbSelect);
	
		refresh();
		//Combobox();
		
		JButton btnKeluar = new JButton("Keluar");
		Image img3=new ImageIcon(this.getClass().getResource("img/lo.jpg")).getImage();
		btnKeluar.setIcon(new ImageIcon(img3));
		btnKeluar.setBounds(580, 5, 75, 20);
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
