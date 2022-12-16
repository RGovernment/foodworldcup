package FoodWorldCup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorldCupPrint {
	BufferedImage img1 = null;
	BufferedImage img2 = null;
	BufferedImage imgwin = null;
	int choice;
	JFrame j = new JFrame();
	JButton button1 = new JButton();
	JButton button2 = new JButton();

	public void worldcup(String a, String b, int num) {
		j.getContentPane().removeAll();
		choice = 0;
		String round = String.valueOf(num) + "강";
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		j.setTitle("음식 월드컵");
		j.setSize(1100, 620);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			img1 = ImageIO.read(new File("Food/" + a + ".jpg"));
			img2 = ImageIO.read(new File("Food/" + b + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image updateImg1 = img1.getScaledInstance(480, 480, Image.SCALE_SMOOTH);
		Image updateImg2 = img2.getScaledInstance(480, 480, Image.SCALE_SMOOTH);
		ImageIcon updateIcon1 = new ImageIcon(updateImg1);
		ImageIcon updateIcon2 = new ImageIcon(updateImg2);
		JLabel round_label = new JLabel(round);
		JLabel versus = new JLabel("vs");
		JLabel label1 = new JLabel(updateIcon1);
		JLabel label2 = new JLabel(updateIcon2);
		button1.setText(a);
		button2.setText(b);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 1;
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 2;
			}
		});
		round_label.setFont(new Font("맑은고딕", Font.BOLD, 30));
		versus.setFont(new Font("맑은고딕", Font.BOLD, 15));
		p0.add(round_label);
		p1.add(label1);
		p1.add(versus);
		p1.add(label2);
		p2.add(button1);
		p2.add(button2);
		j.add(p0, BorderLayout.NORTH);
		j.add(p1, BorderLayout.CENTER);
		j.add(p2, BorderLayout.SOUTH);
		j.revalidate();
		j.setVisible(true);
	}

	public void win(String winfood) {
		j.dispose();
		JFrame jwin = new JFrame();
		JPanel pwin = new JPanel();
		JPanel pwinlabel = new JPanel();
		JPanel pwinend = new JPanel();
		JButton buttonend = new JButton("종료");
		buttonend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jwin.dispose();
			}
		});
		jwin.setTitle("음식 월드컵");
		jwin.setSize(600, 600);
		try {
			imgwin = ImageIO.read(new File("Food/" + winfood + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image winimg = imgwin.getScaledInstance(480, 480, Image.SCALE_SMOOTH);
		ImageIcon winicon = new ImageIcon(winimg);
		JLabel winlabel = new JLabel(winfood + "우승");
		JLabel win = new JLabel(winicon);
		winlabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
		jwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pwinlabel.add(winlabel);
		pwin.add(win);
		pwinend.add(buttonend);
		jwin.add(pwinlabel, BorderLayout.NORTH);
		jwin.add(pwin, BorderLayout.CENTER);
		jwin.add(pwinend, BorderLayout.SOUTH);
		jwin.setVisible(true);

	}

	public int getchoice() {
		return choice;
	}
}
