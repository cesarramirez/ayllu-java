package com.cesarandres.ayllu.discovery;

import com.cesarandres.ayllu.discovery.event.ClientFoundEventListener;
import com.cesarandres.ayllu.discovery.event.ClientFoundEventSource;
import java.io.IOException;
import java.util.ArrayList;

public class BeaconClient implements ClientFoundEventSource {

    private static BeaconClientThread listeningThread;
    protected ArrayList<ClientFoundEventListener> listeners;

    public BeaconClient() {
        this.listeners = new ArrayList<>();
    }

    public void startListening() throws IOException {
        BeaconClient.listeningThread = new BeaconClientThread();
        BeaconClient.listeningThread.setListener(listeners);
        BeaconClient.listeningThread.start();
    }

    public void stopListening() throws InterruptedException {
        BeaconClient.listeningThread.stopSignal();
        BeaconClient.listeningThread.join();
    }

    @Override
    public void addClientFoundEventListener(ClientFoundEventListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeClientFoundEventListener(ClientFoundEventListener listener) {
        this.listeners.remove(listener);
    }
}