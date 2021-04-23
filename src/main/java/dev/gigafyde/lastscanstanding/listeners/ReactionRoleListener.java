package dev.gigafyde.lastscanstanding.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReactionRoleListener extends ListenerAdapter {
    public static Logger logger = LoggerFactory.getLogger("ReactionRoleListener");
    public static Role allseries;
    public static Role greatimmortal;
    public static Role bladeofevolution;
    public static Role gatewayofrevolution;
    public static Role immortalemperor;
    public static Role waremperor;
    JDA jda;
    Guild guild;
    MessageChannel reactionChannel;


    public ReactionRoleListener(JDA jda) {
        this.jda = jda;
        this.guild = jda.getGuildById(794659389264101416L);
        this.reactionChannel = jda.getTextChannelById(795472007797669898L);

    }

    private void addRoleToMember(Member member, Role role) {
        try {
            guild.addRoleToMember(member, role).reason("Reaction Role").queue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void removeRoleFromMember(Member member, Role role) {
        try {
            guild.removeRoleFromMember(member, role).reason("Reaction Role").queue();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getChannel() != reactionChannel) return;  // Checks for reaction role channel
        try {
            switch (event.getReactionEmote().getName()) {
                case "\u0030\ufe0f\u20e3": // equals number 0 in discord reactions
                    addRoleToMember(event.getMember(), allseries);
                    break;
                case "\u0031\ufe0f\u20e3": // equals number 1 in discord reactions
                    addRoleToMember(event.getMember(), greatimmortal);
                    break;
                case "\u0032\ufe0f\u20e3": // equals number 2 in discord reactions
                    addRoleToMember(event.getMember(), bladeofevolution);
                    break;
                case "\u0033\ufe0f\u20e3": // equals number 3 in discord reactions
                    addRoleToMember(event.getMember(), waremperor);
                    break;
                case "\u0034\ufe0f\u20e3": // equals number 4 in discord reactions
                    addRoleToMember(event.getMember(), immortalemperor);
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        if (event.getMember() == null) return;
        try {
            switch (event.getReactionEmote().getName()) {
                case "\u0030\ufe0f\u20e3": // equals number 0 in discord reactions
                    removeRoleFromMember(event.getMember(), allseries);
                    break;
                case "\u0031\ufe0f\u20e3": // equals number 1 in discord reactions
                    removeRoleFromMember(event.getMember(), greatimmortal);
                    break;
                case "\u0032\ufe0f\u20e3": // equals number 2 in discord reactions
                    removeRoleFromMember(event.getMember(), bladeofevolution);
                    break;
                case "\u0033\ufe0f\u20e3": // equals number 3 in discord reactions
                    removeRoleFromMember(event.getMember(), waremperor);
                    break;
                case "\u0034\ufe0f\u20e3": // equals number 4 in discord reactions
                    removeRoleFromMember(event.getMember(), immortalemperor);
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
