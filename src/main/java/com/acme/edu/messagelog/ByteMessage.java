package com.acme.edu.messagelog;

import com.acme.edu.loggerexceptions.OverflowAccumulationException;

public class ByteMessage extends Message<Byte> {
    private static final String TYPE_NAME = "primitive";

    public ByteMessage(byte message) {
        this.value = message;
        type = TYPE_NAME;
    }

    @Override
    public Message accumulate(Message message) throws OverflowAccumulationException {
        if (isOverflow(((ByteMessage) message).value)) throw new OverflowAccumulationException("Byte is overflowed!");
        ByteMessage newMessage = (ByteMessage) message;
        byte newValue = (byte) (value + newMessage.value);
        return new ByteMessage(newValue);
    }

    @Override
    public String getFormattedMessage(LoggerDecorator decorator) {
        return decorator.decorate(this);
    }

    @Override
    public boolean canBeAccumulated(Message message) {
        return super.canBeAccumulated(message);
    }

    private boolean isOverflow(byte term){
        return (term > 0 && value > Byte.MAX_VALUE - term) ||
                (term < 0 && value < Byte.MIN_VALUE - term);
    }
}
