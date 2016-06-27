package de.acetous.nocake.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class LockService {

    private boolean isLocked;

    public LockService() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new WorkstationLockListening() {
                    @Override
                    protected void onMachineLocked(int sessionId) {
                        isLocked = true;
                    }

                    @Override
                    protected void onMachineUnlocked(int sessionId) {
                        isLocked = false;
                    }
                };

            }
        }).start();
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() throws IOException {
        try {
            final String path = System.getenv("windir") + File.separator + "System32" + File.separator + "rundll32.exe";
            Runtime.getRuntime().exec(path + " user32.dll,LockWorkStation");
            isLocked = true;
        } catch (IOException e) {
            throw e;
        }
    }
}
