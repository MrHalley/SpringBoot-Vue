package org.sang;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@Slf4j
@WebListener
public class MyListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.warn("MyListener >>> requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.warn("MyListener >>> requestInitialized");
    }
}
