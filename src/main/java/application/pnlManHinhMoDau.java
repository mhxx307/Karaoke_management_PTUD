package application;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class pnlManHinhMoDau extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public pnlManHinhMoDau() {
		
		Font tahoma16 = new Font("Tahoma", Font.PLAIN, 16);
		
		JLabel lblNewLabel = new JLabel("Nhấn vào ");
		lblNewLabel.setIcon(null);
		lblNewLabel.setFont(tahoma16);
		
		JLabel lblXemThng = new JLabel("để xem thông tin tài khoản hoặc ctrl + p");
		lblXemThng.setIcon(new ImageIcon(getClass().getResource("/images/eye.png")));
		lblXemThng.setFont(tahoma16);
		
		JLabel lblNewLabel_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1.setFont(tahoma16);
		
		JLabel lbliMt = new JLabel("để đổi mật khẩu hoặc ctrl + a");
		lbliMt.setIcon(new ImageIcon(getClass().getResource("/images/changePassword.png")));
		lbliMt.setFont(tahoma16);
		
		JLabel lblNewLabel_2 = new JLabel("Phần mềm quản lý karaoke");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1_1.setFont(tahoma16);
		
		JLabel lblXemThng_1 = new JLabel("để xem thông tin về các phím tắt hoặc alt + shift + p");
		lblXemThng_1.setIcon(new ImageIcon(getClass().getResource("/images/key.png")));
		lblXemThng_1.setFont(tahoma16);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhấn vào ");
		lblNewLabel_1_1_1.setFont(tahoma16);
		
		JLabel lblXemThng_1_1 = new JLabel("để xem thông tin chi tiết của ứng dụng và chúng tôi hoặc ctrl + i");
		lblXemThng_1_1.setIcon(new ImageIcon(getClass().getResource("/images/info.png")));
		lblXemThng_1_1.setFont(tahoma16);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(239)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbliMt, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng_1, GroupLayout.PREFERRED_SIZE, 427, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblXemThng_1_1, GroupLayout.PREFERRED_SIZE, 495, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2)
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblXemThng, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbliMt, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXemThng_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblXemThng_1_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(201, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
}
