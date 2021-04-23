package dev.gigafyde.lastscanstanding.listeners;

import java.util.Objects;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleListener extends ListenerAdapter {
    public static Logger logger = LoggerFactory.getLogger("Patreon Role Listener");
    JDA jda;
    public RoleListener(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onGuildMemberRoleAdd(@NotNull GuildMemberRoleAddEvent event) {
        if (event.getRoles().contains(jda.getRoleById("806161918028677130"))) {
            try {
                Objects.requireNonNull(jda.getTextChannelById("806163761097474118")).sendMessage("**Thank you so much for supporting us!** " + event.getMember().getAsMention()).queue();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
