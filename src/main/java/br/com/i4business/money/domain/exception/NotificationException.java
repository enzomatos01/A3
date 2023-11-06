package br.com.i4business.money.domain.exception;

import br.com.i4business.money.domain.validation.handler.Notification;

public class NotificationException extends DomainException{
    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
