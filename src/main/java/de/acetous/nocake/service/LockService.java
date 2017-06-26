package de.acetous.nocake.service;

import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

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
        String path = Paths.get(System.getenv("windir"), "System32", "rundll32.exe").toString();
        Runtime.getRuntime().exec(path + " user32.dll,LockWorkStation");
        isLocked = true;
    }
}
