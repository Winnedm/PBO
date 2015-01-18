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

public class MStok extends JFrame {

	private static final long serialVersionUID = 1L;
	private Connection konek = null;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblIdStok;
	private JTextField txtIdStok;
	private JLabel lblIdPrd;
	private JTextField txtIdPrd;
	private JLabel lblJumlah;
	private JTextField txtJumlah;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblMaintenanceBarang;
	private JComboBox cmbCari;
	private JTextField txtCari;
	private JComboBox cmbSelect;
	private JComboBox cmbIdPrd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MStok frame = new MStok();
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
			 String query="select * from stok order by IdStok asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Combobox()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select * from stok order by IdStok asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 
			 while(rs.next())
			 {
				 cmbCari.addItem(rs.getString("IdStok"));
			 }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the frame.
	 */
	public MStok() {
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
					 String query="select * from stok";
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
		btnLoadDataProduct.setBounds(320, 80, 340, 20);
		contentPane.add(btnLoadDataProduct);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 220, 650, 170);
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
					String idP_=(table.getModel().getValueAt(row, 1).toString());
					String query="select * from stok where IdPrd='"+idP_+"' order by IdStok asc ";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIdStok.setText(rs.getString("IdStok"));
						cmbIdPrd.setSelectedItem(rs.getString("IdPrd"));
						txtJumlah.setText(rs.getString("JumlahStok"));
						
						
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
		
		lblIdStok = new JLabel("ID Stok");
		lblIdStok.setBounds(10, 84, 73, 14);
		contentPane.add(lblIdStok);
		
		txtIdStok = new JTextField();
		txtIdStok.setEnabled(false);
		txtIdStok.setBounds(103, 81, 207, 21);
		contentPane.add(txtIdStok);
		txtIdStok.setColumns(10);
		
		/*txtIdPrd = new JTextField();
		txtIdPrd.setBounds(103, 113, 207, 23);
		contentPane.add(txtIdPrd);
		txtIdPrd.setColumns(10);*/
		
		lblIdPrd = new JLabel("ID Produk");
		lblIdPrd.setBounds(10, 113, 73, 14);
		contentPane.add(lblIdPrd);
		
		
		 cmbIdPrd = new JComboBox();
			
			try
			{
				Class.forName(koneksi.DATABASE_DRIVER);
				konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
				String query="select IdPrd from products order by IdPrd asc";
				PreparedStatement pst=konek.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				
				while(rs.next())
				{
					cmbIdPrd.addItem(rs.getString(1));
					
				}
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();

			}

			

		cmbIdPrd.setBounds(103, 113, 207, 23);
		contentPane.add(cmbIdPrd);
		
		/*
		txtIdPrd = new JTextField();
		txtIdPrd.setBounds(103, 113, 207, 23);
		contentPane.add(txtIdPrd);
		txtIdPrd.setColumns(10);*/
		
		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(10, 147, 73, 14);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setBounds(103, 147, 207, 23);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);
		
		txtCari = new JTextField();
		txtCari.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					
					String selection=(String)cmbSelect.getSelectedItem();
					String query="select * from stok where "+selection+"=?";
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
		txtCari.setBounds(103, 180, 207, 23);
		contentPane.add(txtCari);
		txtCari.setColumns(10);
		
		cmbSelect = new JComboBox();
		cmbSelect.setModel(new DefaultComboBoxModel(new String[] {"IdStok", "IdPrd"}));
		cmbSelect.setBounds(10, 180, 83, 20);
		contentPane.add(cmbSelect);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="insert into stok values (UNIQUEKEY('stok'),?,?)";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1,cmbIdPrd.getSelectedItem().toString());
					pst.setString(2,txtJumlah.getText());
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
	
		btnInsert.setBounds(320, 113, 107, 23);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="Update stok set IdPrd='"+cmbIdPrd.getSelectedItem().toString()+"',JumlahStok='"+txtJumlah.getText()+"'"+"  where IdPrd='"+cmbIdPrd.getSelectedItem().toString()+"' ";    
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
		btnUpdate.setBounds(437, 113, 111, 23);
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
						String query="delete from stok where IdPrd='"+cmbIdPrd.getSelectedItem().toString()+"' ";
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
		btnDelete.setBounds(558, 113, 107, 23);
		contentPane.add(btnDelete);
		
		/*lblNamaProduct = new JLabel("Nama Product");
		lblNamaProduct.setBounds(10, 117, 83, 14);
		contentPane.add(lblNamaProduct);*/
		
		lblMaintenanceBarang = new JLabel("Maintenance Stok Barang");
		lblMaintenanceBarang.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblMaintenanceBarang.setBounds(199, 9, 269, 32);
		contentPane.add(lblMaintenanceBarang);
		
		cmbCari = new JComboBox();
		cmbCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from stok where IdPrd=?";
					PreparedStatement pst=konek.prepareStatement(query);
					pst.setString(1, (String)cmbCari.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						txtIdStok.setText(rs.getString("IdStok"));
						txtIdPrd.setText(rs.getString("IdPrd"));
						txtJumlah.setText(rs.getString("JumlahStok"));
						
					}
					
					pst.close();

				}
				catch (Exception ex)
				{
					ex.printStackTrace();

				}

				
			}
		});
		// combo box cari bagian atas
		/*cmbCari.setBounds(103, 50, 207, 20);
		contentPane.add(cmbCari);*/
		
		
	
		refresh();
		Combobox();
		
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
		
		
		
		
		JButton btnHome = new JButton("");
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
