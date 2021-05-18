package client;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SwingUi {

	private JFrame frame;
	private JPanel panel;
	private Socket socket;
	private String id;
	private JLabel productLabel_1,productLabel_2,productLabel_3,productLabel_4,productLabel_5,productLabel_6,productLabel_7,productLabel_8,productLabel_9,productLabel_10;
	
	//SwingUi ������ 
	public SwingUi(Socket socket) {
		this.socket = socket;

		frame = new JFrame();
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new JPanel();
	}
	
	//Login Gui�� �����ϴ� �Լ�
	public void changeUiToLogin() {

		panel.setBounds(12, 10, 360, 441);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		Login login = new Login();

		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(94, 171, 116, 21);
		panel.add(textField);
		textField.setColumns(10);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setToolTipText("��й�ȣ");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(94, 202, 116, 21);
		passwordField.setColumns(10);
		panel.add(passwordField);

		JButton btnNewButton = new JButton("�α���");
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					login.getID(textField.getText());
					login.getPASSWD(passwordField.getText());
					login.tryLogin(socket);
					if (login.acceptLogin(socket)) {
						id = login.getID(textField.getText());
						changeUiToUserif();
					}

				}
			});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNewButton.setBounds(222, 170, 97, 53);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("ȸ�� ����");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiToRegister();
			}
		});
		btnNewButton_1.setBounds(28, 267, 97, 23);
		panel.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("���̵�");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 174, 57, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("��й�ȣ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 205, 57, 15);
		panel.add(lblNewLabel_1);
		frame.setVisible(true);
		frame.setTitle("�α���");
	}
	
	//Userinterface�� �����ϴ� �Լ�
	public void changeUiToUserif() {
		
		String str = "";
		
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			BufferedReader s_br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			s_bw.write(("getProduct\n"));
			s_bw.flush();
			str = s_br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringTokenizer st = new StringTokenizer(str,"/");
		StringTokenizer st1;
		
		frame.setTitle("����� â");

		frame.setBounds(100, 100, 547, 560);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 60, 507, 391);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(10, 1, 0, 0));

		productLabel_1 = new JLabel("��ǰ_1");
		productLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_1.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_1.getText());
			}
		});
		panel.add(productLabel_1);

		productLabel_2 = new JLabel("��ǰ_2");
		productLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_2.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_2.getText());
			}
		});
		panel.add(productLabel_2);

		productLabel_3 = new JLabel("��ǰ_3");
		productLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_3.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_3.getText());
			}
		});
		panel.add(productLabel_3);

		productLabel_4 = new JLabel("��ǰ_4");
		productLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_4.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_4.getText());
			}
		});
		panel.add(productLabel_4);

		productLabel_5 = new JLabel("��ǰ_5");
		productLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_5.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_5.getText());
			}
		});
		panel.add(productLabel_5);

		productLabel_6 = new JLabel("��ǰ_6");
		productLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_6.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_6.getText());
			}
		});
		panel.add(productLabel_6);

		productLabel_7 = new JLabel("��ǰ_7");
		productLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_7.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_7.getText());
			}
		});
		panel.add(productLabel_7);

		productLabel_8 = new JLabel("��ǰ_8");
		productLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_8.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_8.getText());
			}
		});
		panel.add(productLabel_8);

		productLabel_9 = new JLabel("��ǰ_9");
		productLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_9.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_9.getText());
			}
		});
		panel.add(productLabel_9);

		productLabel_10 = new JLabel("��ǰ_10");
		productLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_10.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());
		}
		productLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_10.getText());
			}
		});
		panel.add(productLabel_10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 0, 507, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("�˻�â");
		textField.setBounds(12, 6, 396, 34);
		panel_1.add(textField);
		textField.setColumns(10);

		Search search = new Search(socket);
		
		JButton btnNewButton_1 = new JButton("�˻�");
		btnNewButton_1.setBounds(420, 5, 75, 35);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				search.getSEARCH(textField.getText());
				search.searchProduct();
				String str = search.getProduct();
				
				if(str != "false") {
					StringTokenizer st = new StringTokenizer(str,"/");
					if(st.hasMoreTokens()) {
						StringTokenizer st1 = new StringTokenizer(st.nextToken(),",");
						productLabel_1.setText("��ǰ�� : " + st1.nextToken() + ", ��ǰ ���� : " + st1.nextToken() + ", ��¥ : " + st1.nextToken());	
					}else {
						productLabel_1.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st2 = new StringTokenizer(st.nextToken(),",");
						productLabel_2.setText("��ǰ�� : " + st2.nextToken() + ", ��ǰ ���� : " + st2.nextToken() + ", ��¥ : " + st2.nextToken());
					}
					else {
						productLabel_2.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st.nextToken(),",");
						productLabel_3.setText("��ǰ�� : " + st3.nextToken() + ", ��ǰ ���� : " + st3.nextToken() + ", ��¥ : " + st3.nextToken());
					}else {
						productLabel_3.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st4 = new StringTokenizer(st.nextToken(),",");
						productLabel_4.setText("��ǰ�� : " + st4.nextToken() + ", ��ǰ ���� : " + st4.nextToken() + ", ��¥ : " + st4.nextToken());
					}else {
						productLabel_4.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st5 = new StringTokenizer(st.nextToken(),",");
						productLabel_5.setText("��ǰ�� : " + st5.nextToken() + ", ��ǰ ���� : " + st5.nextToken() + ", ��¥ : " + st5.nextToken());
					}else {
						productLabel_5.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st6 = new StringTokenizer(st.nextToken(),",");
						productLabel_6.setText("��ǰ�� : " + st6.nextToken() + ", ��ǰ ���� : " + st6.nextToken() + ", ��¥ : " + st6.nextToken());
					}else {
						productLabel_6.setText("��ǰ�� �����ϴ�.");
					}
					
					if(st.hasMoreTokens()) {
						StringTokenizer st7 = new StringTokenizer(st.nextToken(),",");
						productLabel_7.setText("��ǰ�� : " + st7.nextToken() + ", ��ǰ ���� : " + st7.nextToken() + ", ��¥ : " + st7.nextToken());
					}else {
						productLabel_7.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st8 = new StringTokenizer(st.nextToken(),",");
						productLabel_8.setText("��ǰ�� : " + st8.nextToken() + ", ��ǰ ���� : " + st8.nextToken() + ", ��¥ : " + st8.nextToken());
					}else {
						productLabel_8.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st9 = new StringTokenizer(st.nextToken(),",");
						productLabel_9.setText("��ǰ�� : " + st9.nextToken() + ", ��ǰ ���� : " + st9.nextToken() + ", ��¥ : " + st9.nextToken());
					}else {
						productLabel_9.setText("��ǰ�� �����ϴ�.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st10 = new StringTokenizer(st.nextToken(),",");
						productLabel_10.setText("��ǰ�� : " + st10.nextToken() + ", ��ǰ ���� : " + st10.nextToken() + ", ��¥ : " + st10.nextToken());
					}else {
						productLabel_10.setText("��ǰ�� �����ϴ�.");
					}
				}else {
					
				}
				frame.repaint();
			}
		});
		panel_1.add(btnNewButton_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 461, 507, 50);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("��ǰ ���");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiToPost();
			}
		});
		btnNewButton.setBounds(12, 10, 483, 33);
		panel_2.add(btnNewButton);
		
	}
	
	//�Խù� GUI�� �����ϴ� �Լ�
	public void changeUiToPost() {
		
		JFrame frame2= new JFrame("�Խù�");
		frame2.setVisible(true);
		
		
		frame2.setBounds(300, 100, 660, 580);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame2.setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setText("�Խù� ����");
		textPane.setBounds(12, 84, 620, 381);
		contentPane.add(textPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(148, 10, 484, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��ǰ �̸� :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 21, 84, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("��ǰ ���� :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(236, 21, 84, 21);
		panel.add(lblNewLabel_2);
		
		JTextField textField = new JTextField();
		textField.setBounds(108, 21, 116, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(332, 21, 116, 21);
		panel.add(textField_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 475, 484, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("�Ǹ��� ����");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 460, 36);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("�ڷ� ����");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 10, 124, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("�Խù� ���");
		btnNewButton.setBounds(508, 475, 124, 56);
		contentPane.add(btnNewButton);
		
		Product product = new Product();
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = "";
				StringTokenizer st = new StringTokenizer(textPane.getText(),"\n");
				while(st.hasMoreElements()) {
					str += st.nextToken() + "/";
				}
				product.getP_NAME(textField.getText());
				product.getP_Quantity(textField_1.getText());
				product.getPost(str);
				product.setDATE();
				product.addProduct(socket,id);
				frame2.dispose();
			}
		});
		
		
	}
	
	//�Խù� ���� GUI�� �����ϴ� �Լ�
	public void changeUiTopostEnd(String str) {
		JFrame frame2= new JFrame("�Խù�");
		frame2.setVisible(true);
		
		String tempPost = "";
		String post = "";
		String sellerInfo = "";
		String P_NAME,P_Quantity,Date;
		StringTokenizer st = new StringTokenizer(str,",");
		StringTokenizer st1 = new StringTokenizer(st.nextToken()," ");
		StringTokenizer st2 = new StringTokenizer(st.nextToken()," ");
		StringTokenizer st3 = new StringTokenizer(st.nextToken()," ");
		st1.nextToken();
		st1.nextToken();
		P_NAME = st1.nextToken();
		st2.nextToken();
		st2.nextToken();
		st2.nextToken();
		P_Quantity = st2.nextToken();
		st3.nextToken();
		st3.nextToken();
		Date = st3.nextToken();
		
		try {
			BufferedWriter s_bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader s_br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			s_bw.write("getPost," + P_NAME + "\n");
			s_bw.flush();
			tempPost = s_br.readLine();
			
			s_bw.write("getSellerInfo," + P_NAME + "," + P_Quantity + "," + Date + "\n");
			s_bw.flush();
			sellerInfo = s_br.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StringTokenizer Post = new StringTokenizer(tempPost,"/");
		while(Post.hasMoreTokens()) {
			post += Post.nextToken() + "\n";
		}
		
		frame2.setBounds(300, 100, 660, 580);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame2.setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setText(post);
		textPane.setBounds(12, 84, 620, 381);
		contentPane.add(textPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(148, 10, 484, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(str);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 21, 474, 21);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 475, 484, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("�Ǹ��� ����");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 460, 36);
		lblNewLabel_1.setText(sellerInfo);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("�ڷ� ����");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 10, 124, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("�Ǹ� �Ϸ�");
		btnNewButton.setBounds(508, 475, 124, 56);
		contentPane.add(btnNewButton);
		
		
		Finished finished = new Finished(socket);
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				finished.deleteProduct(P_NAME, id);
				if(finished.finished()) {
					frame2.dispose();
				}else {
					System.out.println("failure");
				}
			}
		});
		
	}

	//ȸ������ GUI�� �����ϴ� �Լ�
	public void changeUiToRegister() {

		JFrame Register = new JFrame("ȸ�� ����");
		Register.setLayout(null);
		Register.setBounds(100, 100, 400, 562);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Register.setContentPane(contentPane);

		contentPane.setLayout(null);

		JTextField textField = new JTextField();
		textField.setText("���̵�");
		textField.setBounds(127, 111, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setText("��й�ȣ");
		textField_1.setBounds(127, 142, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.setText("�ּ�");
		textField_2.setBounds(127, 173, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JTextField textField_3 = new JTextField();
		textField_3.setText("�ֹι�ȣ");
		textField_3.setBounds(127, 204, 116, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JTextField textField_4 = new JTextField();
		textField_4.setText("�̸�");
		textField_4.setBounds(127, 235, 116, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JTextField textField_5 = new JTextField();
		textField_5.setText("����ó");
		textField_5.setBounds(127, 266, 116, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("ȸ�� ���� �ϱ�");
		
		Join join = new Join();
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				join.getID(textField.getText());
				join.getPASSWD(textField_1.getText());
				join.getADRESS(textField_2.getText());
				join.getP_NUM(textField_3.getText());
				join.getJS_NAME(textField_4.getText());
				join.getJS_NUM(textField_5.getText());
				join.tryJoin(socket);
				join.acceptJoin(socket);
				Register.dispose();
			}
		});
		btnNewButton.setBounds(127, 340, 116, 30);
		contentPane.add(btnNewButton);

		Register.setVisible(true);
	}
	
}
