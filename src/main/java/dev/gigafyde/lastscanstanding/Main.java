package dev.gigafyde.lastscanstanding;

import dev.gigafyde.lastscanstanding.listeners.ReactionRoleListener;
import dev.gigafyde.lastscanstanding.listeners.RoleListener;
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
            jda.addEventListener(new ReactionRoleListener(jda));
            jda.addEventListener(new RoleListener(jda));
            jda.addEventListener(new ServerLogListener());
            ServerLogListener.serverlog = jda.getTextChannelById(803196535335813180L);
            ReactionRoleListener.allseries = jda.getRoleById(794678279579500574L);
            ReactionRoleListener.bladeofevolution = jda.getRoleById(795635019280023603L);
            ReactionRoleListener.greatimmortal = jda.getRoleById(795634948835901480L);
            ReactionRoleListener.gatewayofrevolution = jda.getRoleById(795635152750903296L);
            ReactionRoleListener.immortalemperor = jda.getRoleById(799193875251855370L);
            ReactionRoleListener.waremperor = jda.getRoleById(835169614421557266L);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
