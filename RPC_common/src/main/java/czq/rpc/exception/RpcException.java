package czq.rpc.exception;

import czq.rpc.enumeration.RpcError;

public class RpcException extends RuntimeException{

    public RpcException(RpcError error,String detail){
        super(error.getMessage()+": "+detail);
    }


    public RpcException(RpcError error){
        super(error.getMessage());
    }

    public RpcException(String error,Throwable cause){
        super(error,cause);
    }

}
