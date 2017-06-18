package com.Timmy;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Receiver {

	public static void main(String[] args) {
		
		System.out.println("-----欢迎使用DTU服务端程序 V1.1-----");
		System.out.println("请输入监听端口号，按回车键结束，建议8080~8090");
		Scanner scanner = new Scanner(System.in);
		int prot = scanner.nextInt();
		try {
			Receiver receiver = new Receiver(prot);
			receiver.receive();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("端口被占用");
		}

	}

	private final ServerSocket serverSocket;

	public Receiver(int port) throws IOException {

		serverSocket = new ServerSocket(port);
		System.out.println("打开端口成功，等待数据...");
	}

	private void receive() throws IOException {

		Socket socket = serverSocket.accept();

		try (DataInputStream dis = new DataInputStream(socket.getInputStream())) {
			while (true) {
				byte[] bytes = new byte[30]; // 假设发送的字节数不超过 1024 个
				int size = dis.read(bytes); // size 是读取到的字节数

				if (size > 0) {
					Date now = new Date();
					SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println(sFormat.format(now) + "收到数据...");

				

					try {
						String str = new String(bytes, "GBK");
						String str2 = new String(bytes, "GB18030");

						System.out.println("使用GBK解析    :" + str);

						System.out.println("使用GB18030解析:" + str2);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("解析失败...");
					}
					System.out.println("-----------------");
				}


			}
		}

	}

}

