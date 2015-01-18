package uas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.*;

import net.proteanit.sql.DbUtils;
public class Transaksi extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtJumlah;
	private JTextField txtJumlahBeli;
	private JTextField txtTanggal;
	private JTextField txtHargaSatuan;
	private JTextField txtSubTotal;
	private JComboBox cmbIdPrd;
	private JFormattedTextField amountField;
	private JButton btnOk;
	private Connection konek = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaksi frame = new Transaksi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh()
	{
		try 
		{
			 Class.forName(koneksi.DATABASE_DRIVER);
			 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
			 String query="select p.IdPrd,p.NamaPrd,p.IdSupp,p.Harga,s.JumlahStok from products p join stok s on p.IdPrd=s.IdPrd order by p.IdPrd asc";
			 PreparedStatement pst=konek.prepareStatement(query);
			 ResultSet rs=pst.executeQuery();
			 table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Transaksi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 428);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTgl = new JLabel("Tanggal");
		lblTgl.setBounds(13, 94, 93, 14);
		contentPane.add(lblTgl);
		
		
		txtTanggal= new JTextField();
		 txtTanggal.setBounds(116, 91, 138, 20);
		 contentPane.add( txtTanggal);
		 txtTanggal.setColumns(10);
		
		JLabel lblId = new JLabel("Id Prd");
		lblId.setBounds(13, 128, 46, 14);
		contentPane.add(lblId);
		
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

			

		cmbIdPrd.setBounds(116, 122, 138, 20);
		cmbIdPrd.setEnabled(false);
		contentPane.add(cmbIdPrd);
				
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(13, 159, 46, 14);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setEnabled(false);
		//txtJumlah.setText("");
		txtJumlah.setBounds(226, 153, 28, 20);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);
		
		txtJumlahBeli = new JTextField();
		txtJumlahBeli.setEnabled(true);
		//txtJumlah.setText("");
		txtJumlahBeli.setBounds(116, 153, 100, 20);
		contentPane.add(txtJumlahBeli);
		txtJumlahBeli.setColumns(10);
		
		
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(13, 189, 46, 14);
		contentPane.add(lblHarga);
		
		txtHargaSatuan = new JTextField();
		txtHargaSatuan.setEnabled(false);
		//txtJumlah.setText("");
		txtHargaSatuan.setBounds(116, 189, 138, 20);
		contentPane.add(txtHargaSatuan);
		txtHargaSatuan.setColumns(10);
		
		JButton btnSubTotal = new JButton("SubTotal");
		btnSubTotal.setBounds(13, 219, 100, 18);
		contentPane.add(btnSubTotal);
		btnSubTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);

					int row=table.getSelectedRow();
					String idP_=(table.getModel().getValueAt(row,1).toString());
					String query="select products.IdPrd,NamaPrd,IdSupp,Harga,stok.JumlahStok from products join stok on products.IdPrd=stok.IdPrd  where NamaPrd='"+idP_+"'";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						String tamp3=txtJumlahBeli.getText();
						String tamp4=rs.getString("Harga");
						int cek3=0;
						cek3=Integer.parseInt(tamp3);
						int cek4=0;
						cek4=Integer.parseInt(tamp4);
						int kali=0;
						kali=cek3*cek4;
						txtSubTotal.setText(String.valueOf(kali));	
					}
					
					pst.close();

				}

				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		
		txtSubTotal = new JTextField();
		txtSubTotal.setEnabled(false);
		//txtJumlah.setText("");
		txtSubTotal.setBounds(116, 219, 138, 20);
		contentPane.add(txtSubTotal);
		txtSubTotal.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(273, 59, 309, 285);
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
					String idP_=(table.getModel().getValueAt(row,1).toString());
					String query="select products.IdPrd,NamaPrd,IdSupp,Harga,stok.JumlahStok from products join stok on products.IdPrd=stok.IdPrd  where NamaPrd='"+idP_+"'";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						
						cmbIdPrd.setSelectedItem(rs.getString("products.IdPrd"));
						txtJumlah.setText(rs.getString("JumlahStok"));
						txtHargaSatuan.setText(rs.getString("Harga"));
						
						
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
		
		
		
		JButton btnNewButton = new JButton("Refresh");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					 Class.forName(koneksi.DATABASE_DRIVER);
					 konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					 String query="select p.IdPrd,p.NamaPrd,p.IdSupp,p.Harga,s.JumlahStok from products p join stok s on p.IdPrd=s.IdPrd order by p.IdPrd asc";
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
		btnNewButton.setBounds(13, 300, 100, 23);
		contentPane.add(btnNewButton);
		
		/*JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(116, 61, 138, 20);
		contentPane.add(comboBox_1);*/
		
		JButton btnOk = new JButton("Confirm");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Class.forName(koneksi.DATABASE_DRIVER);
					konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
					String query="select * from users where status=1";
					PreparedStatement pst=konek.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					int count=0;
					String tampung="unknown";
					while (rs.next())
					{
						count=count+1;
						tampung=rs.getString("IdUser");
						
						
					}
					if(count==1)
					{
						String tamp2="";
						int tamp3=0;
						String tamp4="";
						int tamp5=0;
						String tamp6="";
						int tamp7=0;
						String tamp8=txtJumlah.getText();
						int tamp9=Integer.parseInt(tamp8);
						int upd=0;
						
						tamp2=txtJumlahBeli.getText();
						tamp3=Integer.parseInt(tamp2);
						
						tamp4=txtHargaSatuan.getText();
						tamp5=Integer.parseInt(tamp4);
						
						tamp6=txtSubTotal.getText();
						tamp7=Integer.parseInt(tamp6);
						
						JOptionPane.showMessageDialog(null,tamp9);
						upd=(tamp9-tamp3);
						JOptionPane.showMessageDialog(null, upd);
						Class.forName(koneksi.DATABASE_DRIVER);
						konek=DriverManager.getConnection(koneksi.URL, koneksi.USERNAME, koneksi.PASSWORD);
						String query1="insert into transaksi values (UNIQUEKEY('transaksi'),'"+txtTanggal.getText()+"','"+cmbIdPrd.getSelectedItem().toString()+"',"+tamp3+","+tampung+","+tamp5+","+tamp7+")";   
						PreparedStatement pst1=konek.prepareStatement(query1);
						pst1.execute();
						
						String query2="update stok set JumlahStok="+upd+" where IdPrd='"+cmbIdPrd.getSelectedItem().toString()+"'";
						PreparedStatement pst2=konek.prepareStatement(query2);
						pst2.execute();
						
						JOptionPane.showMessageDialog(null, "Transaksi Berhasil");
						pst1.close();
						pst2.close();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Anda Harus Login Terlebih Dahulu");
					}
					
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		btnOk.setBounds(142, 300, 89, 23);
		contentPane.add(btnOk);
		refresh();
		
		JLabel lblTrans = new JLabel("Form Transaksi ");
		lblTrans.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblTrans.setBounds(266, 11, 246, 23);
		contentPane.add(lblTrans);
		
		JButton btnKeluar = new JButton("Keluar");
		Image img3=new ImageIcon(this.getClass().getResource("img/lo.jpg")).getImage();
		btnKeluar.setIcon(new ImageIcon(img3));
		btnKeluar.setBounds(530, 5, 75, 20);
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
		
		/*
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(20, 5, 100, 20);
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
		*/
	}

}
