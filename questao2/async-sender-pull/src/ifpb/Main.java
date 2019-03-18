package ifpb;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Inicializado o serviço de Sender");

        // Inicializar o serviço de MessageSender para o ClientApp
        Server server = ServerBuilder
                .forPort(10990)
                .addService(new MessageSender())
                .build();

        server.start();
        server.awaitTermination();
    }

}
