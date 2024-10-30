package personal.zx.myocean.websocket;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 小z
 * @date 2024年10月29日 上午11:23
 */


@Service
public class WsServiceAsync {
    @Resource
    public WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message) {
        webSocketServer.sendInfo(message);
    }
}
