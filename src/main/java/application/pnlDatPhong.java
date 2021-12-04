/**
 * Nguyễn Hồng Quân
 * Ngày tạo: 16/11/2021
 * Giao diện dùng để đặt phòng 
 */
package application;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.KhachHangDAO;
import dao.PhongDAO;
import dao.DonDatPhongDAO;
import dao.HoaDonDao;
import entity.KhachHang;
import entity.Phong;
import entity.DonDatPhong;
import entity.HoaDon;
import helpers.MessageDialogHelpers;
import helpers.ShareData;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class pnlDatPhong extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_MaKH;
	private JTextField txt_TenKH;
	private JTextField txt_LoaiKH;
	private JTextField txt_TimSDT;
	private JTable tbl_DonDatPhong;
	private JTable tbl_DSPhong;
	private JSpinner spn_ThoiGianVao;
	private Date dateThoiGianVao;
	private DefaultTableModel dfDanhSachPhong;
	private JButton btn_Tim;
	private JButton btn_DatPhong;
	private JButton btn_NhanPhong;
	private JButton btn_Huy;
	private JButton btn_LamMoi;
	private Component mainFrame;
	private DefaultTableModel dfDanhSachDonDatPhong;
	private DialogChuaCoKhachHang tb;
	private JComboBox<String> cmbTinhTrangPhong;
	private JComboBox<String> cmb_LocPhongTheoTrangThai;
	private JTextField txtTimDon;
	private JComboBox<String> cmbTimDon;

	/**
	 * Create the panel.
	 */
	public pnlDatPhong() {

		/** set color **/
		Color whiteColor = new Color(255, 255, 255);
		Color mainColor = new Color(88, 159, 177);
		Color hoverColor = new Color(121, 178, 192);

		Font tahoma13 = new Font("Tahoma", Font.PLAIN, 13);
		Font tahomaBold13 = new Font("Tahoma", Font.BOLD, 13);
		Font tahoma14 = new Font("Tahoma", Font.PLAIN, 14);
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		Font tahoma18 = new Font("Tahoma", Font.PLAIN, 18);

		JPanel pnl_DatPhong = new JPanel();
		pnl_DatPhong.setBackground(whiteColor);

		JPanel pnl_DSDonDat = new JPanel();
		pnl_DSDonDat.setBackground(whiteColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_DSDonDat, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
				.addComponent(pnl_DatPhong, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnl_DatPhong, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnl_DSDonDat, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)));

		JScrollPane scrollPane_DSDonDatPhong = new JScrollPane();

		JLabel lblNewLabel_3 = new JLabel("Danh Sách Đơn Đặt Phòng");
		lblNewLabel_3.setFont(tahoma18);

		cmb_LocPhongTheoTrangThai = new JComboBox<String>();
		cmb_LocPhongTheoTrangThai.addItem("Đã Thanh Toán");
		cmb_LocPhongTheoTrangThai.addItem("Chờ Nhận Phòng");
		cmb_LocPhongTheoTrangThai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DonDatPhongDAO donDatPhongDao = new DonDatPhongDAO();

				String trangThaiDon = cmb_LocPhongTheoTrangThai.getSelectedItem().toString();

				List<DonDatPhong> dsDonDatPhong = donDatPhongDao.getDanhSachDonDatPhong(trangThaiDon);

				dfDanhSachDonDatPhong.setRowCount(0);

				for (DonDatPhong ddp : dsDonDatPhong) {
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
					String tgv = formatter.format(ddp.getThoiGianVao());
					dfDanhSachDonDatPhong.addRow(new Object[] { ddp.getMaDatPhong(), ddp.getKhachHang().getHoTenKH(),
							ddp.getKhachHang().getSoDienThoai(), ddp.getPhong().getTenPhong(),
							ddp.getPhong().getLoaiPhong().getTenLoaiPhong(), tgv, ddp.getTrangThaiDon() });
				}
			}
		});
		
		txtTimDon = new JTextField();
		txtTimDon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String searchStr = txtTimDon.getText();
				search(searchStr);
			}
		});
		txtTimDon.setFont(tahoma14);
		txtTimDon.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		
		cmbTimDon = new JComboBox<String>();
		cmbTimDon.addItem("Tìm theo tên khách hàng");
		cmbTimDon.addItem("Tìm theo ngày đặt");
		cmbTimDon.addItem("Tìm theo tên phòng");
		cmbTimDon.addItem("Tìm theo số điện thoại");
		GroupLayout gl_pnl_DSDonDat = new GroupLayout(pnl_DSDonDat);
		gl_pnl_DSDonDat.setHorizontalGroup(
			gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_DSDonDatPhong, GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
						.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
							.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
									.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cmbTimDon, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
									.addGap(18)))
							.addGap(18)
							.addComponent(txtTimDon, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
							.addGap(101)
							.addComponent(cmb_LocPhongTheoTrangThai, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pnl_DSDonDat.setVerticalGroup(
			gl_pnl_DSDonDat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DSDonDat.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_DSDonDat.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(cmb_LocPhongTheoTrangThai, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(cmbTimDon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtTimDon, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_DSDonDatPhong, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
					.addContainerGap())
		);

		tbl_DonDatPhong = new JTable();
		tbl_DonDatPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DonDatPhongDAO donDatPhongDao = new DonDatPhongDAO();
				int row = tbl_DonDatPhong.getSelectedRow();
				if (row >= 0) {
					String trangThai = tbl_DonDatPhong.getValueAt(row, 6).toString();
					String maDon = tbl_DonDatPhong.getValueAt(row, 0).toString();
					DonDatPhong donDatPhong = donDatPhongDao.getDonDatPhongTheoMa(maDon);
					int ngayVao = donDatPhong.getThoiGianVao().getDate();
					int ngayHienTai = new Date().getDate();
					if (ngayVao == ngayHienTai && trangThai.equals("Chờ Nhận Phòng")) {
						btn_NhanPhong.setEnabled(true);
						btn_DatPhong.setEnabled(false);
						btn_Huy.setEnabled(true);
					} else if (ngayVao > ngayHienTai && trangThai.equals("Chờ Nhận Phòng")) {
						btn_DatPhong.setEnabled(false);
						btn_Huy.setEnabled(true);
						btn_NhanPhong.setEnabled(false);
					}

					if (trangThai.equals("Đã Thanh Toán") || trangThai.equals("Đã Nhận Phòng")
							|| trangThai.equals("Hủy Đơn")) {
						btn_DatPhong.setEnabled(false);
						btn_Huy.setEnabled(false);
						btn_NhanPhong.setEnabled(false);
					}

				}
			}
		});
		scrollPane_DSDonDatPhong.setViewportView(tbl_DonDatPhong);
		tbl_DonDatPhong.setFont(tahoma16);
		tbl_DonDatPhong.setRowHeight(28);
		tbl_DonDatPhong.setAutoCreateRowSorter(true);
		tbl_DonDatPhong.getTableHeader().setFont(tahoma16);
		pnl_DSDonDat.setLayout(gl_pnl_DSDonDat);

		JLabel lblNewLabel_1 = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1.setFont(tahoma14);

		btn_Tim = new JButton("");
		btn_Tim.setBorder(null);
		btn_Tim.setFocusPainted(false);
		btn_Tim.setFocusTraversalKeysEnabled(false);
		btn_Tim.setFocusable(false);
		btn_Tim.setIcon(new ImageIcon(getClass().getResource("/images/icons8-search-16.png")));

		txt_MaKH = new JTextField();
		txt_MaKH.setEditable(false);
		txt_MaKH.setFont(tahoma13);
		txt_MaKH.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1_1.setFont(tahoma14);

		txt_TenKH = new JTextField();
		txt_TenKH.setEditable(false);
		txt_TenKH.setFont(tahoma13);
		txt_TenKH.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Lo\u1EA1i Kh\u00E1ch H\u00E0ng:");
		lblNewLabel_1_1_1.setFont(tahoma14);

		txt_LoaiKH = new JTextField();
		txt_LoaiKH.setEditable(false);
		txt_LoaiKH.setFont(tahoma13);
		txt_LoaiKH.setColumns(10);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i:");
		lblNewLabel_1_1_1_1.setFont(tahoma14);

		txt_TimSDT = new JTextField();
		txt_TimSDT.setFont(tahoma13);
		txt_TimSDT.setColumns(10);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Thời gian đặt trước:");
		lblNewLabel_1_1_1_2.setFont(tahoma14);

		dateThoiGianVao = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(dateThoiGianVao, null, null, Calendar.HOUR_OF_DAY);
		spn_ThoiGianVao = new JSpinner(sm);
		spn_ThoiGianVao.setBounds(152, 16, 212, 34);
		spn_ThoiGianVao.setFont(tahoma16);
		JSpinner.DateEditor de_spn_ThoiGianVao = new JSpinner.DateEditor(spn_ThoiGianVao, "HH:mm:ss - dd/MM/yyyy");
		spn_ThoiGianVao.setEditor(de_spn_ThoiGianVao);

		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("Ch\u1ECDn Ph\u00F2ng:");
		lblNewLabel_1_1_1_2_1_1.setFont(tahoma14);

		JScrollPane scrollPane_DSPhong = new JScrollPane();

		btn_DatPhong = new JButton("\u0110\u1EB7t Ph\u00F2ng");
		btn_DatPhong.setForeground(Color.WHITE);
		btn_DatPhong.setBorder(null);
		btn_DatPhong.setFocusPainted(false);
		btn_DatPhong.setFocusTraversalKeysEnabled(false);
		btn_DatPhong.setFocusable(false);
		btn_DatPhong.setEnabled(false);
		btn_DatPhong.setFont(tahomaBold13);
		btn_DatPhong.setIcon(new ImageIcon(getClass().getResource("/images/icons8-add-20.png")));

		btn_NhanPhong = new JButton("Nh\u1EADn Ph\u00F2ng");
		btn_NhanPhong.setForeground(Color.WHITE);
		btn_NhanPhong.setBorder(null);
		btn_NhanPhong.setFocusPainted(false);
		btn_NhanPhong.setFocusTraversalKeysEnabled(false);
		btn_NhanPhong.setFocusable(false);
		btn_NhanPhong.setEnabled(false);
		btn_NhanPhong.setFont(tahomaBold13);

		btn_Huy = new JButton("H\u1EE7y \u0110\u01A1n \u0110\u1EB7t");
		btn_Huy.setForeground(Color.WHITE);
		btn_Huy.setBorder(null);
		btn_Huy.setFocusPainted(false);
		btn_Huy.setFocusTraversalKeysEnabled(false);
		btn_Huy.setFocusable(false);
		btn_Huy.setEnabled(false);
		btn_Huy.setFont(tahomaBold13);
		btn_Huy.setIcon(new ImageIcon(getClass().getResource("/images/x-mark-16.png")));

		btn_LamMoi = new JButton("L\u00E0m M\u1EDBi");
		btn_LamMoi.setForeground(Color.WHITE);
		btn_LamMoi.setBorder(null);
		btn_LamMoi.setFocusPainted(false);
		btn_LamMoi.setFocusTraversalKeysEnabled(false);
		btn_LamMoi.setFocusable(false);
		btn_LamMoi.setFont(tahomaBold13);
		btn_LamMoi.setIcon(new ImageIcon(getClass().getResource("/images/icons8-refresh-16.png")));

		JCheckBox ckb_DatTruoc = new JCheckBox("");

		cmbTinhTrangPhong = new JComboBox<String>();
		cmbTinhTrangPhong.addItem("Trống");
		cmbTinhTrangPhong.addItem("Đang Sử Dụng");
		cmbTinhTrangPhong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhongDAO phongDao = new PhongDAO();

				String tinhTrangPhong = cmbTinhTrangPhong.getSelectedItem().toString();

				List<Phong> dsPhong = phongDao.getDanhSachPhongTheoTinhTrang(tinhTrangPhong);

				dfDanhSachPhong.setRowCount(0);

				for (Phong phong : dsPhong) {
					dfDanhSachPhong.addRow(new Object[] { phong.getMaPhong(), phong.getTenPhong(),
							phong.getLoaiPhong().getTenLoaiPhong(), phong.getLoaiPhong().getDonGia(),
							phong.getTrangThai() });
				}
			}
		});
		GroupLayout gl_pnl_DatPhong = new GroupLayout(pnl_DatPhong);
		gl_pnl_DatPhong.setHorizontalGroup(
			gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DatPhong.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1_1_2_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 114, Short.MAX_VALUE))
					.addGap(35)
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnl_DatPhong.createSequentialGroup()
									.addComponent(txt_MaKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnl_DatPhong.createSequentialGroup()
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
										.addComponent(cmbTinhTrangPhong, Alignment.LEADING, 0, 28, Short.MAX_VALUE)
										.addComponent(txt_TenKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_pnl_DatPhong.createSequentialGroup()
									.addComponent(txt_TimSDT, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btn_Tim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(txt_LoaiKH, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane_DSPhong, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
					.addGap(64)
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_DatPhong, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(btn_Huy, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
							.addGap(54)
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
								.addComponent(btn_LamMoi, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
								.addComponent(btn_NhanPhong, GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)))
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spn_ThoiGianVao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ckb_DatTruoc)))
					.addContainerGap())
		);
		gl_pnl_DatPhong.setVerticalGroup(
			gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_DatPhong.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addComponent(ckb_DatTruoc, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
							.addComponent(txt_MaKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(txt_LoaiKH, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1_1_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(spn_ThoiGianVao, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1)))
					.addGap(30)
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Tim, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(txt_TenKH, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(txt_TimSDT, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addComponent(cmbTinhTrangPhong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_2_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(175, Short.MAX_VALUE))
						.addGroup(gl_pnl_DatPhong.createSequentialGroup()
							.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_DSPhong, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addGroup(gl_pnl_DatPhong.createSequentialGroup()
									.addGap(24)
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.LEADING)
										.addComponent(btn_NhanPhong, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(btn_DatPhong, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
									.addGroup(gl_pnl_DatPhong.createParallelGroup(Alignment.BASELINE)
										.addComponent(btn_LamMoi, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(btn_Huy, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
							.addGap(30))))
		);

		tbl_DSPhong = new JTable();
		tbl_DSPhong.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl_DSPhong.getSelectedRow();
				if (row >= 0) {
					btn_DatPhong.setEnabled(true);
					btn_Huy.setEnabled(false);
					btn_NhanPhong.setEnabled(false);

					String tinhTrangPhong = tbl_DSPhong.getValueAt(row, 4).toString();

					if (tinhTrangPhong.equals("Trống")) {
						btn_DatPhong.setText("Đặt phòng");
					} else if (tinhTrangPhong.equals("Đang Sử Dụng")) {
						btn_DatPhong.setText("Đặt trước");
					}
				}
			}
		});

		scrollPane_DSPhong.setViewportView(tbl_DSPhong);
		tbl_DSPhong.setFont(tahoma16);
		tbl_DSPhong.setRowHeight(28);
		tbl_DSPhong.setAutoCreateRowSorter(true);
		tbl_DSPhong.getTableHeader().setFont(tahoma16);
		pnl_DatPhong.setLayout(gl_pnl_DatPhong);
		setLayout(groupLayout);
		
		btn_DatPhong.setBackground(mainColor);
		btn_Huy.setBackground(mainColor);
		btn_LamMoi.setBackground(mainColor);
		btn_NhanPhong.setBackground(mainColor);
		btn_Tim.setBackground(mainColor);
		
		btn_DatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_DatPhong.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_DatPhong.setBackground(mainColor);
			}
		});
		
		btn_Huy.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_Huy.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_Huy.setBackground(mainColor);
			}
		});
		
		btn_LamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_LamMoi.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_LamMoi.setBackground(mainColor);
			}
		});
		
		btn_NhanPhong.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_NhanPhong.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_NhanPhong.setBackground(mainColor);
			}
		});
		
		btn_Tim.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btn_Tim.setBackground(hoverColor);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btn_Tim.setBackground(mainColor);
			}
		});

		initTablePhong();
		initTableDonDatPhong();
		loadDataToTablePhong();
		loadDataToTableDonDatPhong();

		btn_Tim.addActionListener(this);
		btn_DatPhong.addActionListener(this);
		btn_Huy.addActionListener(this);
		btn_NhanPhong.addActionListener(this);
		btn_LamMoi.addActionListener(this);

	}

	private void initTablePhong() {
		dfDanhSachPhong = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfDanhSachPhong.setColumnIdentifiers(
				new String[] { "Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá", "Tình Trạng Phòng" });
		tbl_DSPhong.setModel(dfDanhSachPhong);
	}

	private void initTableDonDatPhong() {
		dfDanhSachDonDatPhong = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dfDanhSachDonDatPhong.setColumnIdentifiers(new String[] { "Mã Đặt Phòng", "Khách Hàng", "Số Điện Thoại",
				"Tên Phòng", "Loại Phòng", "Thời Gian Vào", "Trạng Thái Đơn" });
		tbl_DonDatPhong.setModel(dfDanhSachDonDatPhong);
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTablePhong() {
		try {
			PhongDAO phongDAO = new PhongDAO();
			List<Phong> listPhong = phongDAO.getDanhSachPhong();
			for (Phong phong : listPhong) {
				dfDanhSachPhong.addRow(
						new Object[] { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong().getTenLoaiPhong(),
								phong.getLoaiPhong().getDonGia(), phong.getTrangThai() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * tải dữ liệu từ cơ sở dữ liệu vào table
	 */
	private void loadDataToTableDonDatPhong() {
		try {
			DonDatPhongDAO ddpDAO = new DonDatPhongDAO();
			List<DonDatPhong> listTTDatPhong = ddpDAO.getDanhSachDonDatPhong();
			for (DonDatPhong ddp : listTTDatPhong) {
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
				String tgv = formatter.format(ddp.getThoiGianVao());
				dfDanhSachDonDatPhong.addRow(new Object[] { ddp.getMaDatPhong(), ddp.getKhachHang().getHoTenKH(),
						ddp.getKhachHang().getSoDienThoai(), ddp.getPhong().getTenPhong(),
						ddp.getPhong().getLoaiPhong().getTenLoaiPhong(), tgv, ddp.getTrangThaiDon() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btn_Tim)) {
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			KhachHang kh = khachHangDAO.getKhachHangTheoSĐT(txt_TimSDT.getText());
			if (kh != null) {
				txt_MaKH.setText(kh.getMaKhachHang());
				txt_TenKH.setText(kh.getHoTenKH());
				txt_LoaiKH.setText(kh.getLoaiKhachHang());
				txt_TimSDT.setText(kh.getSoDienThoai());
			} else {
				tb = new DialogChuaCoKhachHang();
				tb.setVisible(true);
			}
		}

		if (o.equals(btn_DatPhong)) {
			DonDatPhongDAO ddpDAO = new DonDatPhongDAO();
			PhongDAO phongDAO = new PhongDAO();
			DonDatPhong ddp = new DonDatPhong();
			HoaDonDao hoaDonDao = new HoaDonDao();

			ddp = createThongTinDatPhong();
			int row = tbl_DSPhong.getSelectedRow();
			String tinhTrangPhong = tbl_DSPhong.getValueAt(row, 4).toString();
			int thangvao = ddp.getThoiGianVao().getMonth();
			int ngayvao = ddp.getThoiGianVao().getDate();
			int giovao = ddp.getThoiGianVao().getHours();
			Date tght = new Date();
			int thanght = tght.getMonth();
			int ngayht = tght.getDate();
			int gioht = tght.getHours();

			if (tinhTrangPhong.equals("Trống")) {
				if (ngayvao == ngayht && thangvao == thanght && giovao == gioht) {
					ddp.setTrangThaiDon("Đã Nhận Phòng");
					ddp.getPhong().setTrangThai("Đang Sử Dụng");
					phongDAO.updatePhong(ddp.getPhong());

					HoaDon hoaDon = new HoaDon();
					hoaDon.setNhanVien(ShareData.taiKhoanDangNhap.getNhanVien());
					hoaDon.setTenKhachHang(ddp.getKhachHang().getHoTenKH());
					hoaDonDao.addHoaDon(hoaDon);
				} else if (ngayvao == ngayht && thangvao == thanght && giovao > gioht) {
					ddp.setTrangThaiDon("Chờ Nhận Phòng");
					ddp.getPhong().setTrangThai("Đã Đặt");
					phongDAO.updatePhong(ddp.getPhong());
				} else if (ngayvao > ngayht) {
					ddp.setTrangThaiDon("Chờ Nhận Phòng");
				}

			} else if (tinhTrangPhong.equals("Đang Sử Dụng") && ngayvao > ngayht
					|| tinhTrangPhong.equals("Đã Đặt") && ngayvao > ngayht) {
				ddp.setTrangThaiDon("Chờ Nhận Phòng");
			} else {
				MessageDialogHelpers.showConfirmDialog(mainFrame, "Thông Báo",
						"Thời gian vào được chọn đã bận! Vui lòng thay đổi thời gian vào!");
			}

			if (ddpDAO.addThongTinDatPhong(ddp)) {
				MessageDialogHelpers.showMessageDialog(mainFrame, "Thông báo", "Đặt Phòng thành công");
				dfDanhSachDonDatPhong.setRowCount(0);
				loadDataToTableDonDatPhong();
				dfDanhSachPhong.setRowCount(0);
				loadDataToTablePhong();
			}
			spn_ThoiGianVao.setValue(new Date());
		}

		if (o.equals(btn_NhanPhong)) {
			DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO();
			PhongDAO phongDAO = new PhongDAO();
			int row = tbl_DonDatPhong.getSelectedRow();
			String maDDP = tbl_DonDatPhong.getValueAt(row, 0).toString();
			DonDatPhong ddp = donDatPhongDAO.getDonDatPhongTheoMa(maDDP);
			Phong p = ddp.getPhong();
			p.setTrangThai("Đang Sử Dụng");
			phongDAO.updatePhong(p);
			int ngayht = new Date().getDate();
			donDatPhongDAO.updateTrangThaiDonDat_DaNhanPhong(maDDP, ngayht);
			dfDanhSachPhong.setRowCount(0);
			loadDataToTablePhong();
			dfDanhSachDonDatPhong.setRowCount(0);
			loadDataToTableDonDatPhong();
		}

		if (o.equals(btn_Huy)) {
			DonDatPhongDAO donDatPhongDAO = new DonDatPhongDAO();
			int row = tbl_DonDatPhong.getSelectedRow();
			String maDDP = tbl_DonDatPhong.getValueAt(row, 0).toString();
			donDatPhongDAO.updateTrangThaiDonDat_Huy(maDDP);
			dfDanhSachDonDatPhong.setRowCount(0);
			loadDataToTableDonDatPhong();
		}

		if (o.equals(btn_LamMoi)) {
			spn_ThoiGianVao.setValue(new Date());
			txt_TimSDT.setText("");
			txt_MaKH.setText("");
			txt_LoaiKH.setText("");
			txt_TenKH.setText("");
			txt_TimSDT.setText("");

			dfDanhSachDonDatPhong.setRowCount(0);
			loadDataToTableDonDatPhong();
			dfDanhSachPhong.setRowCount(0);
			loadDataToTablePhong();
		}

	}

	private DonDatPhong createThongTinDatPhong() {
		DonDatPhong donDatPhong = new DonDatPhong();
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		PhongDAO phongDAO = new PhongDAO();

		Date thoiGianVao = (Date) spn_ThoiGianVao.getValue();
		KhachHang kh = khachHangDAO.getKhachHangTheoSĐT(txt_TimSDT.getText());
		int row = tbl_DSPhong.getSelectedRow();
		if (row >= 0) {
			Phong phong = phongDAO.getPhongTheoMa(tbl_DSPhong.getValueAt(row, 0).toString());
			donDatPhong.setPhong(phong);
			donDatPhong.setKhachHang(kh);
			donDatPhong.setThoiGianVao(thoiGianVao);
		} else {
			MessageDialogHelpers.showErrorDialog(btn_DatPhong, "Cảnh báo", "Chọn 1 phòng để đặt");
		}
		return donDatPhong;
	}
	
	public void search(String str) {
		dfDanhSachDonDatPhong = (DefaultTableModel) tbl_DonDatPhong.getModel();
		TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dfDanhSachDonDatPhong);
		tbl_DonDatPhong.setRowSorter(trs);

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo tên khách hàng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 1));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo tên phòng")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 3));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo số điện thoại")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 2));
		}

		if (cmbTimDon.getSelectedItem().toString().equals("Tìm theo ngày đặt")) {
			trs.setRowFilter(RowFilter.regexFilter(str, 5));
		}


	}
}
