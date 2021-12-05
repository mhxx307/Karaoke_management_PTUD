package application;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.CardLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class pnlThongKe extends JPanel {

	private static final long serialVersionUID = 1L;
	private HoaDonDao hoaDonDao;
	private ChiTietHoaDonDao cthdDao;
	private JDateChooser dateChooser;

	/**
	 * Create the panel.
	 */
	public pnlThongKe() {

		hoaDonDao = new HoaDonDao();
		cthdDao = new ChiTietHoaDonDao();

		JPanel pnl_Top = new JPanel();

		JPanel pnl_Bottom = new JPanel();
		
		JPanel pnl_Center = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnl_Top, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
				.addComponent(pnl_Bottom, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
				.addComponent(pnl_Center, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnl_Top, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnl_Center, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnl_Bottom, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		dateChooser = new JDateChooser();
		dateChooser.setPreferredSize(new Dimension(200, 30));
		GroupLayout gl_pnl_Center = new GroupLayout(pnl_Center);
		gl_pnl_Center.setHorizontalGroup(
			gl_pnl_Center.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnl_Center.createSequentialGroup()
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(688, Short.MAX_VALUE))
		);
		gl_pnl_Center.setVerticalGroup(
			gl_pnl_Center.createParallelGroup(Alignment.LEADING)
				.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
		);
		pnl_Center.setLayout(gl_pnl_Center);
		
		setDateToChart1(pnl_Top);
		setDateToChart2(pnl_Bottom);
	}

	private void setDateToChart1(JPanel panelItem) {
		List<HoaDon> listHoaDon = hoaDonDao.getDSHoaDon();
		if (listHoaDon != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (HoaDon hd : listHoaDon) {
				dataset.addValue(hd.getTongTien(), "Doanh thu", hd.getNgayTao());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ DOANH THU THEO NGÀY", "Thời gian", "Doanh thu",
					dataset);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 300));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}

	private void setDateToChart2(JPanel panelItem) {
		List<ChiTietHoaDon> listCTHD = cthdDao.getdsCTHD();
		if (listCTHD != null) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (ChiTietHoaDon cthd : listCTHD) {
				dataset.addValue(cthd.getSoLuong(), "Số lượng", cthd.getSanPham().getTenSanPham());
			}

			JFreeChart charts = ChartFactory.createBarChart("THỐNG KÊ SỐ SẢN PHẨM BÁN ĐƯỢC", "Sản phẩm", "Số lượng",
					dataset);
			ChartPanel chartPanel = new ChartPanel(charts);
			chartPanel.setPreferredSize(new Dimension(panelItem.getWidth(), 300));

			panelItem.removeAll();
			panelItem.setLayout(new CardLayout());
			panelItem.add(chartPanel);
			panelItem.validate();
			panelItem.repaint();
		}
	}
}
