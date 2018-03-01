package ua.nure.chub.Elective.db.entity;

/**
 * Author Lera
 * created 05.09.2017.
 */
public class Topic {
    private int topicId;
    private String topicName;

    public Topic(int topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
