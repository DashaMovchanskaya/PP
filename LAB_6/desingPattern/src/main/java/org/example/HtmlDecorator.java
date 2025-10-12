package org.example;

class HtmlDecorator implements Message {
    private final Message message;

    public HtmlDecorator(Message message) {
        this.message = message;
    }

    public String getContent() {
        return "<html>" + message.getContent() + "</html>";
    }
}
