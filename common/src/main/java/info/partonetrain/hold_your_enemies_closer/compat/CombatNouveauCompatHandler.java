package info.partonetrain.hold_your_enemies_closer.compat;

import info.partonetrain.hold_your_enemies_closer.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CombatNouveauCompatHandler {
    public static boolean attackingStopsSprinting = true;

    public static void checkConfig(){
        Constants.LOG.info("CombatNouveau detected, checking config");
        final String configFileLoc = System.getProperty("user.dir") + "\\config\\combatnouveau-server.toml";
        Path configFilePath = Paths.get(configFileLoc); //converts to correct path regardless of platform
        try {
            String allLines = Files.readAllLines(configFilePath).toString();
            if (allLines.contains("critical_hits_while_sprinting = true")) {
                attackingStopsSprinting = false;
            }
        } catch (IOException e) {
            Constants.LOG.error("CombatNouveau config error:" + e);
            Constants.LOG.info("Don't fret! Above error is most likely one-time occurrence from CombatNouveau config file not existing yet");
        }
    }
}