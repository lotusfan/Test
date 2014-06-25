package test.invoiceimage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Test_image_font {

	private Font font = new Font("����", Font.PLAIN, 80);// ����������������

	private Graphics2D g = null;

	private int fontsize = 0;

	private int x = 0;

	private int y = 0;

	/**
	 * ���뱾��ͼƬ��������
	 */
	public BufferedImage loadImageLocal(String imgName) {

		try {

			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * ��������ͼƬ��������
	 */
	public BufferedImage loadImageUrl(String imgName) {

		try {
			URL url = new URL(imgName);
			return ImageIO.read(url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * ������ͼƬ������
	 */
	public void writeImageLocal(String newImage, BufferedImage img) {

		if (newImage != null && img != null) {
			try {
				File outputfile = new File(newImage);
				ImageIO.write(img, "jpg", outputfile);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * �趨���ֵ������
	 */
	public void setFont(String fontStyle, int fontSize) {

		this.fontsize = fontSize;
		this.font = new Font(fontStyle, Font.PLAIN, fontSize);
	}

	/**
	 * �޸�ͼƬ,�����޸ĺ��ͼƬ��������ֻ���һ���ı���
	 */
	public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			System.out.println("��=" + w + "����=" + h);
			g = img.createGraphics();
			g.setStroke(new BasicStroke(10.00F));
			//g.setBackground(Color.WHITE);
			g.setColor(Color.white);//����������ɫ
			if (this.font != null) g.setFont(this.font);
			// ��֤���λ�õ�������ͺ�����
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (content != null) {
				g.drawString(content.toString(), this.x, this.y);
				g.drawString(content.toString(), 360, 360);
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	/**
	 * �޸�ͼƬ,�����޸ĺ��ͼƬ���������������ı��Σ� xory��true��ʾ��������һ���������false��ʾ�����ݶ������
	 */
	public BufferedImage modifyImage(BufferedImage img, Object[] contentArr, int x, int y, boolean xory) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setColor(Color.RED);
			if (this.font != null) g.setFont(this.font);
			// ��֤���λ�õ�������ͺ�����
			if (x >= h || y >= w) {
				this.x = h - this.fontsize + 2;
				this.y = w;
			} else {
				this.x = x;
				this.y = y;
			}
			if (contentArr != null) {
				int arrlen = contentArr.length;
				if (xory) {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.x += contentArr[i].toString().length() * this.fontsize / 2 + 5;// ���¼����ı����λ��
					}
				} else {
					for (int i = 0; i < arrlen; i++) {
						g.drawString(contentArr[i].toString(), this.x, this.y);
						this.y += this.fontsize + 2;// ���¼����ı����λ��
					}
				}
			}
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	/**
	 * �޸�ͼƬ,�����޸ĺ��ͼƬ��������ֻ���һ���ı���
	 * 
	 * ʱ��:2007-10-8
	 * 
	 * @param img
	 * @return
	 */
	public BufferedImage modifyImageYe(BufferedImage img) {

		try {
			int w = img.getWidth();
			int h = img.getHeight();
			g = img.createGraphics();
			g.setBackground(Color.WHITE);
			g.setStroke(new BasicStroke(10.00F));
			g.setColor(Color.blue);//����������ɫ
			if (this.font != null) g.setFont(this.font);
			g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return img;
	}

	public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

		try {
			int w = b.getWidth();
			int h = b.getHeight();

			g = d.createGraphics();
			g.drawImage(b, 100, 10, w, h, null);
			g.dispose();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return d;
	}

	public static void main(String[] args) {

		Test_image_font tt = new Test_image_font();

		BufferedImage d = tt.loadImageLocal("D:\\11.jpg");
		//			BufferedImage b = tt
		//					.loadImageLocal("E:\\�ļ�(word,excel,pdf,ppt.txt)\\zte-logo.png");
		tt.writeImageLocal("D:\\ccrrr.jpg", tt.modifyImage(d, "��ԭ", 190, 200)
		//��ͼƬ��д�ļ�
		);

		//tt.writeImageLocal("D:\\cc.jpg", tt.modifyImagetogeter(b, d));
		//������ͼƬ����һ��
		System.out.println("success");
	}

}
