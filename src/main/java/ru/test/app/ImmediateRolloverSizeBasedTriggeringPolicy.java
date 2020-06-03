package ru.test.app;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class ImmediateRolloverSizeBasedTriggeringPolicy<E> extends SizeBasedTriggeringPolicy<E> {
    private AtomicBoolean startup = new AtomicBoolean(true);

    @Override
    public boolean isTriggeringEvent(File activeFile, E event) {
        if (startup.getAndSet(false))
            return true;
        return super.isTriggeringEvent(activeFile, event);
    }
}
