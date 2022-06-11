package czq.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class RpcRequest implements Serializable {

    //请求号
    private String requestId;

    //待调用接口名称
    private String interfaceName;

    //待调用方法名称
    private String methodName;

    //调用方法的参数
    private Object[] parameters;

    //调用方法的参数类型
    private Class<?>[] paramTypes;

    //是否是心跳包
    private Boolean heartBeat;





}
