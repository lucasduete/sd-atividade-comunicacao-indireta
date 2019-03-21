package ifpb;

import ifpb.sd.share.Message;
import ifpb.sd.share.MessageResult;
import ifpb.sd.share.ServerServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServerAppService extends ServerServiceGrpc.ServerServiceImplBase {

    @Override
    public void print(Message request, StreamObserver<MessageResult> responseObserver) {

        MessageDigest msd;

        try {
            msd = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro de MD5", e);
        }

        byte[] bhash = msd.digest(request.getText().getBytes());
        BigInteger bi = new BigInteger(bhash);

        try {
            MessageResult result = MessageResult
                    .newBuilder()
                    .setId(request.getId())
                    .setHash(bi.toString(16))
                    .build();

            System.out.println(request.getId() + " " + request.getText());

            responseObserver.onNext(result);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        responseObserver.onCompleted();

    }

}
