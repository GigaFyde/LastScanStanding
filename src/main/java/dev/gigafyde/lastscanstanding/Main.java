package dev.gigafyde.lastscanstanding;

import dev.gigafyde.lastscanstanding.listeners.ServerLogListener;
import io.sentry.Sentry;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    final static Logger logger = LoggerFactory.getLogger("Main");

    public static void main(String[] args) {
        Sentry.init(options -> options.setDsn(System.getenv("SENTRY_DSN")));
        JDA jda;
        try {
            jda = JDABuilder.createDefault(System.getenv("TOKEN"))
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .setMemberCachePolicy(MemberCachePolicy.ALL)
                    .build();
            jda.awaitReady();
            jda.addEventListener(new ServerLogListener());
            ServerLogListener.serverlog = jda.getTextChannelById(860587554586886154L);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
