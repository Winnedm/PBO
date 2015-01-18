package uas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Event;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenuUtama extends JFrame {

	private JPanel contentPane;
	private JLabel lblcek;
	private Connection konek = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuUtama frame = new MenuUtama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuUtama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("FormattedTextField.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenuUtama = new JLabel("Menu Utama Maintenance");
		lblMenuUtama.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblMenuUtama.setBounds(248, 11, 300, 48);
		contentPane.add(lblMenuUtama);
		
		JButton btnFormProduct = new JButton("Product");
		btnFormProduct.setBounds(20, 70, 300, 150);
		btnFormProduct.setBackground(Color.cyan.darker());
		contentPane.add(btnFormProduct);
		
		btnFormProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					new Mproduct().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		JButton btnFormSupplier = new JButton("Supplier");
		btnFormSupplier.setBounds(400, 70, 300, 150);
		btnFormSupplier.setBackground(Color.cyan);
		contentPane.add(btnFormSupplier);
		
		btnFormSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					new Msuppliers().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		JButton btnFormStockBarang = new JButton("Stok Barang");
		btnFormStockBarang.setBounds(20, 260, 300, 150);
		btnFormStockBarang.setBackground(Color.gray);
		contentPane.add(btnFormStockBarang);
		
		btnFormStockBarang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					new MStok().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
	
		
		
		JButton btnFormMenuUser = new JButton("User");
		btnFormMenuUser.setBounds(400, 260, 300, 150);
		btnFormMenuUser.setBackground(Color.gray.brighter());
		contentPane.add(btnFormMenuUser);
		
		btnFormMenuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					new Muser().setVisible(true);
					dispose();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		/*JButton btnLaporan = new JButton("Laporan");
		btnLaporan.setBounds(386, 309, 258, 48);
		contentPane.add(btnLaporan);*/
		
		JButton btnKeluar = new JButton("Keluar");
		Image img3=new ImageIcon(this.getClass().getResource("img/lo.jpg")).getImage();
		btnKeluar.setIcon(new ImageIcon(img3));
		btnKeluar.setBounds(620, 5, 75, 20);
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
		
		
		
	
		
		
	
		
		
		
	}
}
