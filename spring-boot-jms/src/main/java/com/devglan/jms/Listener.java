package com.devglan.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Listener {

	@JmsListener(destination = "inbound.queue")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = "";
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
            //Map<String, String> map = new Gson().fromJson(messageData, Map.class);
            //response  = "Hello " + map.get("name");
			response = "Hello " +messageData;
			System.out.println(messageData);
		}
		return response;
	}

}
