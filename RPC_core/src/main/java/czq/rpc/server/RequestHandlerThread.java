package czq.rpc.server;

import czq.rpc.entity.RpcRequest;
import czq.rpc.entity.RpcResponse;
import czq.rpc.registry.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);
    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegistry serviceRegistry;


    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegistry serviceRegistry) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegistry = serviceRegistry;
    }

    @Override
    public void run() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ){
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName  = rpcRequest.getInterfaceName();
            Object service = serviceRegistry.getService(interfaceName);
            Object result = requestHandler.handler(rpcRequest,service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();

        }catch (IOException | ClassNotFoundException e){
            logger.error("调用或发送时有错误发生：", e);
        }

    }
}
