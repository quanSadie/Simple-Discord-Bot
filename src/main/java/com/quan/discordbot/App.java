package com.quan.discordbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommandInteraction;

public class App {
	public static void main(String[] args) {
		String token = "token goes here";
		DiscordApi api = new DiscordApiBuilder().setToken(token).addIntents(Intent.MESSAGE_CONTENT).login().join();

		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("!invite")) {
				event.getChannel().sendMessage("Invite me via: " + api.createBotInvite());
			}

			if (event.getMessageContent().equalsIgnoreCase("!mm09")) {
				event.getChannel().sendMessage("mm09 pro");
			}
		});

		api.addSlashCommandCreateListener(event -> {
			SlashCommandInteraction slashCommandInteraction = event.getSlashCommandInteraction();
			if (slashCommandInteraction.getCommandName().equals("help")) {
				slashCommandInteraction.createImmediateResponder().setContent("This bot is still under development")
						.append("For now it only has '!mm09' and '!invite' command").setFlags(MessageFlag.CROSSPOSTED)
						.respond();
			}
		});

	}
}
