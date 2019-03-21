package ifpb;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Servidor inicializado");


        Server server = ServerBuilder
                .forPort(10992)
                .addService(new ServerAppService())
                .build();

        server.start();
        server.awaitTermination();
    }

}
