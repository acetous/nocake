package de.acetous.nocake.controller;

import de.acetous.nocake.service.AuthorizationService;
import de.acetous.nocake.service.LockService;
import de.acetous.nocake.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Controller
public class CakeController {

    private LockService lockService;
    private AuthorizationService authorizationService;
    private QrCodeService qrCodeService;

    @Autowired
    public CakeController(LockService lockService, AuthorizationService authorizationService, QrCodeService qrCodeService) {
        this.lockService = lockService;
        this.authorizationService = authorizationService;
        this.qrCodeService = qrCodeService;
    }

    @RequestMapping("/")
    public ModelAndView home(Map<String, Object> model, @RequestParam String token) {
        checkToken(token);
        model.put("locked", lockService.isLocked());
        model.put("token", token);
        return new ModelAndView("home", model);
    }

    @RequestMapping("/lock")
    public String lock(@RequestParam String token) {
        checkToken(token);
        try {
            lockService.lock();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/?token=" + token;
    }

    private void checkToken(@RequestParam String token) {
        if (!authorizationService.checkToken(token)) {
            throw new ForbiddenException();
        }
    }

    @RequestMapping("/connect")
    public ModelAndView connect(Map<String, Object> model, @Value("${server.port}") String port, @RequestParam(required = false) String token) {
        if (authorizationService.hasToken()) {
            checkToken(token);
        }
        String generatedToken = authorizationService.generateToken();
        String hostname = getHostname();
        String connectUrl = "http://" + hostname + ":" + port + "/?token=" + generatedToken;
        String qrCode = qrCodeService.generate(connectUrl);

        model.put("url", connectUrl);
        model.put("qrCode", "data:image/gif;base64," + qrCode);

        return new ModelAndView("connect", model);
    }

    @RequestMapping("/shutdown")
    public ModelAndView shutdown(@RequestParam String token) {
        checkToken(token);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return new ModelAndView("down");
    }

    private String getHostname() {
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            hostname = "localhost";
        }
        return hostname.toLowerCase();
    }
}
