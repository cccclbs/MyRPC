package czq.test;

import czq.rpc.api.HelloService;
import czq.rpc.registry.DefaultServiceRegistry;
import czq.rpc.registry.ServiceRegistry;
import czq.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry =new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
