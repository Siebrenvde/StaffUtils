package dev.siebrenvde.staffchat.paper;

public class PaperCompat {

    public static boolean hasBrigadier() {
        return hasClass("io.papermc.paper.command.brigadier.Commands");
    }

    public static boolean hasAsyncChatEvent() {
        return hasClass("io.papermc.paper.event.player.AsyncChatEvent");
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

}
