package htgd.com.radiocontrol.visualaudio.model;

import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;

public class EventBusEvent {
    private AppEvent message;

    public EventBusEvent(AppEvent str){
        this.message = str;
    }
    public AppEvent getMessage() {
        return message;
    }
    public void setMessage(AppEvent message) {
        this.message = message;
    }
}
