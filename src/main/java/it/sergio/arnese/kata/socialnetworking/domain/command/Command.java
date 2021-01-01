package it.sergio.arnese.kata.socialnetworking.domain.command;

import it.sergio.arnese.kata.socialnetworking.domain.SocialNetwork;

public interface Command {
    String apply(SocialNetwork socialNetwork);
}
