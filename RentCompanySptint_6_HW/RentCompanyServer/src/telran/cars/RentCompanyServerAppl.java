package telran.cars;

import telran.cars.model.RentCompanyEmbedded;
import telran.cars.model.RentCompanyProtocol;
import telran.cars.utils.Persistable;
import telran.net.server.ProtocolJava;
import telran.net.server.ServerJava;
import telran.cars.model.IRentCompany;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RentCompanyServerAppl {
    private static final String FILE_NAME = "company.data";
    private static final int PORT = 3000;
    private static final int TIMEOUT = 10;
    public static void main(String[] args) throws Exception {
        IRentCompany service = RentCompanyEmbedded.restoreFromFile(FILE_NAME);
        ProtocolJava protocol = new RentCompanyProtocol(service);
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        ServerJava server = new ServerJava(protocol, PORT, executor, TIMEOUT);
        executor.execute(server);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("exit")){
                break;
            }
        }
        ((Persistable)service).save(FILE_NAME);
        executor.shutdown();
        server.shutdown();
        executor.awaitTermination(TIMEOUT, TimeUnit.SECONDS);
        scanner.close();
    }
}
