package actors;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;


public class MessageActor extends UntypedActor {
    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    private final ActorRef out;

    public MessageActor(ActorRef out) {
        this.out = out;
    }

    private NewsAgentService newsAgentService = new NewsAgentService();
    private FeedService feedService = new FeedService();
    private FeedResponse feedResponse=new FeedResponse();

    @Override
    public void onReceive(Object message) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        if (message instanceof String) {
            Message messageObject = new Message();
            messageObject.text=(String)message;
            messageObject.sender=Message.Sender.USER;
            out.tell(mapper.writeValueAsString(messageObject),self());

            String query=newsAgentService.getNewsAgentResponse("Find " + message,UUID.randomUUID()).query;
            feedResponse=feedService.getFeedByQuery(query);
            messageObject.text = (feedResponse.title == null) ? "No results found" : "Showing results for: " + query;
            messageObject.feedResponse= feedResponse;
            messageObject.sender=Message.Sender.BOT;
            out.tell(mapper.writeValueAsString(messageObject), self());
        }

    }


}