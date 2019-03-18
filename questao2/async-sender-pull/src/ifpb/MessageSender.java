package ifpb;

import com.google.common.util.concurrent.ListenableFuture;
import ifpb.sd.share.Message;
import ifpb.sd.share.MessageResult;
import ifpb.sd.share.ReceiverServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MessageSender extends ifpb.sd.share.SenderServiceGrpc.SenderServiceImplBase {

    private final Executor executor = Executors.newFixedThreadPool(10);

    @Override
    public void sendMessage(Message request, StreamObserver<MessageResult> responseObserver) {

        // Cria canal de comunicaçao com MessageReceiver em ServerApp
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("serverapp", 10991)
                .usePlaintext()
                .build();

        ifpb.sd.share.ReceiverServiceGrpc.ReceiverServiceFutureStub stub = ReceiverServiceGrpc.newFutureStub(channel);

        ListenableFuture<MessageResult> delivery = stub.delivery(request);

        delivery.addListener(() -> {

            try {
                MessageResult messageResult = delivery.get();

                responseObserver.onNext(messageResult);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            responseObserver.onCompleted();
        }, executor);

    }

}
