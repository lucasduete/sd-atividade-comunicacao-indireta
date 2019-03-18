package ifpb;

import ifpb.sd.share.Message;
import ifpb.sd.share.MessageResult;
import ifpb.sd.share.SenderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

	private static Executor executor = Executors.newFixedThreadPool(10);

	private static void sendAndResultMessage(String id, String text, SenderServiceGrpc.SenderServiceFutureStub stub) {

		com.google.common.util.concurrent.ListenableFuture<ifpb.sd.share.MessageResult> futureResonse = stub.sendMessage(
				Message.newBuilder()
						.setId(id)
						.setText(text)
						.build()
		);

		futureResonse.addListener(() -> {
			try {
				MessageResult messageResult = futureResonse.get();

				System.out.println("Recebido resultado para mensagem " + id + ": " + messageResult.getHash());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}, executor);

	}

	public static void main(String[] args) {

		System.out.println("Acionado o clientapp");

		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 10990)
				.usePlaintext()
				.build();

		ifpb.sd.share.SenderServiceGrpc.SenderServiceFutureStub stub = ifpb.sd.share.SenderServiceGrpc.newFutureStub(channel);

		String id = "askjdlkasjd";
		String text = "Hello World!";

		for (int i = 0; i < 100; i++) {

			final String ix = id + "#" + i;
			final String mx = text + "#" + i;

			Thread t = new Thread(() -> sendAndResultMessage(ix, mx, stub));
			t.start();
		}
		
	}
	
}
