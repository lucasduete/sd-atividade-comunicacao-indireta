package ifpb;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import ifpb.repositories.MessageRepository;
import ifpb.repositories.MessageResultRepository;
import ifpb.repositories.SendedMessageRepository;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		//log
		System.out.println("Inicializado o serviço de Sender");
		//inicializar o repositorio
		MessageRepository repository = new MessageRepository();
		SendedMessageRepository sendedMessageRepository = new SendedMessageRepository();
		MessageResultRepository resultRepository = new MessageResultRepository();
		//inicializar o gerenciador de tarefas
		TaskManager.runTask(repository, sendedMessageRepository, resultRepository);
		//inicializar o serviço para client app
		Registry registry = LocateRegistry.createRegistry(10990);
		registry.bind("Sender", new SenderImpl(repository, resultRepository));
	}
}
