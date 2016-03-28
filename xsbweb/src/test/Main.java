package test;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Main {

	public static void main(String[] args) {
		// 这是在极光网站上申请的密钥
		String masterSecret = "1c59df909c3790cc7e1fc601";
		// 应用的appKey,同样在网站上申请
		String appKey = "3c92c6395227cbe166d1a853";
		// 建立JpushClient类，用来发送消息的对象
		JPushClient client = new JPushClient(masterSecret, appKey);
		try {
			// client.sendNotificationAll("hello world");

			// client.sendMessageAll("this is a message");
			PushPayload payload = PushPayload
					.newBuilder()
					.setPlatform(Platform.android_ios())
					.setNotification(Notification.alert("sendPush is useful!!"))
					.setAudience(Audience.alias("dalancon1")).build();
			client.sendPush(payload);

		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}
