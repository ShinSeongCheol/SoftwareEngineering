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
	
	//SwingUi 생성자 
	public SwingUi(Socket socket) {
		this.socket = socket;

		frame = new JFrame();
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new JPanel();
	}
	
	//Login Gui를 구성하는 함수
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
		passwordField.setToolTipText("비밀번호");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(94, 202, 116, 21);
		passwordField.setColumns(10);
		panel.add(passwordField);

		JButton btnNewButton = new JButton("로그인");
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

		JButton btnNewButton_1 = new JButton("회원 가입");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiToRegister();
			}
		});
		btnNewButton_1.setBounds(28, 267, 97, 23);
		panel.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 174, 57, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 205, 57, 15);
		panel.add(lblNewLabel_1);
		frame.setVisible(true);
		frame.setTitle("로그인");
	}
	
	//Userinterface를 구성하는 함수
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
		
		frame.setTitle("사용자 창");

		frame.setBounds(100, 100, 547, 560);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 60, 507, 391);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(10, 1, 0, 0));

		productLabel_1 = new JLabel("상품_1");
		productLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_1.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_1.getText());
			}
		});
		panel.add(productLabel_1);

		productLabel_2 = new JLabel("상품_2");
		productLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_2.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_2.getText());
			}
		});
		panel.add(productLabel_2);

		productLabel_3 = new JLabel("상품_3");
		productLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_3.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_3.getText());
			}
		});
		panel.add(productLabel_3);

		productLabel_4 = new JLabel("상품_4");
		productLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_4.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_4.getText());
			}
		});
		panel.add(productLabel_4);

		productLabel_5 = new JLabel("상품_5");
		productLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_5.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_5.getText());
			}
		});
		panel.add(productLabel_5);

		productLabel_6 = new JLabel("상품_6");
		productLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_6.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_6.getText());
			}
		});
		panel.add(productLabel_6);

		productLabel_7 = new JLabel("상품_7");
		productLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_7.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_7.getText());
			}
		});
		panel.add(productLabel_7);

		productLabel_8 = new JLabel("상품_8");
		productLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_8.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_8.getText());
			}
		});
		panel.add(productLabel_8);

		productLabel_9 = new JLabel("상품_9");
		productLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_9.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
		}
		productLabel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiTopostEnd(productLabel_9.getText());
			}
		});
		panel.add(productLabel_9);

		productLabel_10 = new JLabel("상품_10");
		productLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		if(st.hasMoreTokens()) {
			st1 = new StringTokenizer(st.nextToken(),",");
			productLabel_10.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());
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
		textField.setText("검색창");
		textField.setBounds(12, 6, 396, 34);
		panel_1.add(textField);
		textField.setColumns(10);

		Search search = new Search(socket);
		
		JButton btnNewButton_1 = new JButton("검색");
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
						productLabel_1.setText("물품명 : " + st1.nextToken() + ", 물품 개수 : " + st1.nextToken() + ", 날짜 : " + st1.nextToken());	
					}else {
						productLabel_1.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st2 = new StringTokenizer(st.nextToken(),",");
						productLabel_2.setText("물품명 : " + st2.nextToken() + ", 물품 개수 : " + st2.nextToken() + ", 날짜 : " + st2.nextToken());
					}
					else {
						productLabel_2.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st3 = new StringTokenizer(st.nextToken(),",");
						productLabel_3.setText("물품명 : " + st3.nextToken() + ", 물품 개수 : " + st3.nextToken() + ", 날짜 : " + st3.nextToken());
					}else {
						productLabel_3.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st4 = new StringTokenizer(st.nextToken(),",");
						productLabel_4.setText("물품명 : " + st4.nextToken() + ", 물품 개수 : " + st4.nextToken() + ", 날짜 : " + st4.nextToken());
					}else {
						productLabel_4.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st5 = new StringTokenizer(st.nextToken(),",");
						productLabel_5.setText("물품명 : " + st5.nextToken() + ", 물품 개수 : " + st5.nextToken() + ", 날짜 : " + st5.nextToken());
					}else {
						productLabel_5.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st6 = new StringTokenizer(st.nextToken(),",");
						productLabel_6.setText("물품명 : " + st6.nextToken() + ", 물품 개수 : " + st6.nextToken() + ", 날짜 : " + st6.nextToken());
					}else {
						productLabel_6.setText("물품이 없습니다.");
					}
					
					if(st.hasMoreTokens()) {
						StringTokenizer st7 = new StringTokenizer(st.nextToken(),",");
						productLabel_7.setText("물품명 : " + st7.nextToken() + ", 물품 개수 : " + st7.nextToken() + ", 날짜 : " + st7.nextToken());
					}else {
						productLabel_7.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st8 = new StringTokenizer(st.nextToken(),",");
						productLabel_8.setText("물품명 : " + st8.nextToken() + ", 물품 개수 : " + st8.nextToken() + ", 날짜 : " + st8.nextToken());
					}else {
						productLabel_8.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st9 = new StringTokenizer(st.nextToken(),",");
						productLabel_9.setText("물품명 : " + st9.nextToken() + ", 물품 개수 : " + st9.nextToken() + ", 날짜 : " + st9.nextToken());
					}else {
						productLabel_9.setText("물품이 없습니다.");
					}
					if(st.hasMoreTokens()) {
						StringTokenizer st10 = new StringTokenizer(st.nextToken(),",");
						productLabel_10.setText("물품명 : " + st10.nextToken() + ", 물품 개수 : " + st10.nextToken() + ", 날짜 : " + st10.nextToken());
					}else {
						productLabel_10.setText("물품이 없습니다.");
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

		JButton btnNewButton = new JButton("물품 등록");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeUiToPost();
			}
		});
		btnNewButton.setBounds(12, 10, 483, 33);
		panel_2.add(btnNewButton);
		
	}
	
	//게시물 GUI를 구성하는 함수
	public void changeUiToPost() {
		
		JFrame frame2= new JFrame("게시물");
		frame2.setVisible(true);
		
		
		frame2.setBounds(300, 100, 660, 580);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame2.setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setText("게시물 내용");
		textPane.setBounds(12, 84, 620, 381);
		contentPane.add(textPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(148, 10, 484, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("물품 이름 :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 21, 84, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("물품 개수 :");
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

		JLabel lblNewLabel_1 = new JLabel("판매자 정보");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 460, 36);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("뒤로 가기");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 10, 124, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("게시물 등록");
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
	
	//게시물 정보 GUI를 구성하는 함수
	public void changeUiTopostEnd(String str) {
		JFrame frame2= new JFrame("게시물");
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

		JLabel lblNewLabel_1 = new JLabel("판매자 정보");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 10, 460, 36);
		lblNewLabel_1.setText(sellerInfo);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new JButton("뒤로 가기");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame2.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 10, 124, 64);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("판매 완료");
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

	//회원가입 GUI를 구성하는 함수
	public void changeUiToRegister() {

		JFrame Register = new JFrame("회원 가입");
		Register.setLayout(null);
		Register.setBounds(100, 100, 400, 562);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Register.setContentPane(contentPane);

		contentPane.setLayout(null);

		JTextField textField = new JTextField();
		textField.setText("아이디");
		textField.setBounds(127, 111, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JTextField textField_1 = new JTextField();
		textField_1.setText("비밀번호");
		textField_1.setBounds(127, 142, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.setText("주소");
		textField_2.setBounds(127, 173, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JTextField textField_3 = new JTextField();
		textField_3.setText("주민번호");
		textField_3.setBounds(127, 204, 116, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JTextField textField_4 = new JTextField();
		textField_4.setText("이름");
		textField_4.setBounds(127, 235, 116, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JTextField textField_5 = new JTextField();
		textField_5.setText("연락처");
		textField_5.setBounds(127, 266, 116, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("회원 가입 하기");
		
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
